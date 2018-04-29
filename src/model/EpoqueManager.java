package model;

import java.util.ArrayList;
import java.util.List;

public class EpoqueManager {	

	/**
	 * Cette classe est un singleton
	 */
	private volatile static EpoqueManager unique = null;
	private FileXMLReader XMLr;
	private FileXMLWriter XMLw;
	private Model modele;
	private Epoque actualEpoque;
	private List<Epoque> epoques;
	private static int JOUEUR = 1;
	private static int ORDI = 0;

	private EpoqueManager() {
		epoques = new ArrayList<Epoque>();
		actualEpoque = new Epoque();
		XMLr = new FileXMLReader();
		XMLw = new FileXMLWriter();
		readAllEpoque();
	}

	public static EpoqueManager getInstance() { 
		if (unique==null) {
			synchronized (EpoqueManager.class) {
				if (unique==null) {
					unique = new EpoqueManager();
				}
			}
		}
		return unique;
	}
	/**
	 * fonction qui prépare l'epoque et les bateaux pour une nouvelle partie
	 */
	public void newGame(){
		for (int i=0;i<5;i++){
			actualEpoque.getBateauJoueur(i).reset();
			actualEpoque.getBateauOrdi(i).reset();
		}
	}
	
	/**
	 * fonction qui joue le coup du joueur ou de l'ordi suivant l'id. 
	 * @param p position a laquelle on souhaite frapper
	 * @param id identifiant du joueur ordi = 0 et joueur = 1
	 * @return Liste de position,  taille 0 signifie non touché, taille 1 signifie touché et non coulé, taille n signifie touché/coulé 
	 */
	public ArrayList<Position> play(Position p, int id){
		assert(p!=null & (id==ORDI | id ==JOUEUR)):"Epoque manager : erreur play() id ou pos";
		ArrayList<Position> pos = new ArrayList<Position>();
		Bateau b;
		for(int i=0;i<5;i++){
			if(id==ORDI){//si ordi tape joueur
				b = actualEpoque.getBateauJoueur(i);
			}else{
				b = actualEpoque.getBateauOrdi(i);
			}
			if(b.checkPosition(p)){
				b.hit();
				if(b.isDead()){
					for(int j=0; j<b.getTaille();j++){
						pos.add(b.getPosition(j));
					}
					b.setDead(0);
					return pos;
				}else{
					pos.add(p);
					return pos;
				}
			}	
		}

		return pos;
	}
	/**
	 * fonction qui va mettre a jour sa liste d'epoque en fonction du fichier XML de sauvegarde
	 */
	public void readAllEpoque(){
		this.epoques = new ArrayList<Epoque>();
		XMLr.readConfigFile();
		Epoque epTmp;
		for(int i=0; i<XMLr.getNBEpoque();i++){
			epTmp = new Epoque();
			epTmp.setName(XMLr.getNomEpoque(i));
			epTmp.setNomBateau(0, XMLr.getNomBateau(i,0));
			epTmp.setNomBateau(1, XMLr.getNomBateau(i,1));
			epTmp.setNomBateau(2, XMLr.getNomBateau(i,2));
			epTmp.setNomBateau(3, XMLr.getNomBateau(i,3));
			epTmp.setNomBateau(4, XMLr.getNomBateau(i,4));
			epTmp.setApparence(XMLr.getApparenceEpoque(i));
			epTmp.setResistanceInitBateau("joueur",0, XMLr.getResistanceBateau(i,0));
			epTmp.setResistanceInitBateau("joueur",1, XMLr.getResistanceBateau(i,1));
			epTmp.setResistanceInitBateau("joueur",2, XMLr.getResistanceBateau(i,2));
			epTmp.setResistanceInitBateau("joueur",3, XMLr.getResistanceBateau(i,3));
			epTmp.setResistanceInitBateau("joueur",4, XMLr.getResistanceBateau(i,4));
			epTmp.setResistanceInitBateau("ordi",0, XMLr.getResistanceBateau(i,0));
			epTmp.setResistanceInitBateau("ordi",1, XMLr.getResistanceBateau(i,1));
			epTmp.setResistanceInitBateau("ordi",2, XMLr.getResistanceBateau(i,2));
			epTmp.setResistanceInitBateau("ordi",3, XMLr.getResistanceBateau(i,3));
			epTmp.setResistanceInitBateau("ordi",4, XMLr.getResistanceBateau(i,4));
			epoques.add(epTmp);

		}
	}
	/**
	 * fonction qui va écrire une nouvelle époque dans le fichier XML
	 * @param nom nom de l'epoque
	 * @param apparence chemin vers l'image de l'epoque
	 * @param res2 resistance bateau de taille 2
	 * @param res3 resistance bateau de taille 3
	 * @param res4 resistance bateau de taille 4
	 * @param res5 resistance bateau de taille 5
	 * @param nom2 nom bateau de taille 2
	 * @param nom3 nom bateau de taille 3
	 * @param nom4 nom bateau de taille 4
	 * @param nom5 nom bateau de taille 5
	 * @return true si se passe bien 
	 */
	public boolean addEpoque(String nom, 
			String apparence, 
			String res2, 
			String res3, 
			String res4, 
			String res5,
			String nom2,
			String nom3,
			String nom4,
			String nom5){
		this.XMLw.addEpoque(nom, apparence, nom5, nom4, nom3, nom2, res5, res4, res3, res2);
		this.readAllEpoque();
		return true;
	}
	/**
	 * fonction permettant la sauvegarde de la partie en cours. uniquement si les bateaux sont tous placés.
	 * @param isComputerTurn boolean representant si c'est le tour de l'ordi lors de la sauvegarde
	 * @param diff represente la difficulté lors de la sauvegarde : 0 facile / 1 normal / 2 difficile
	 */
	public void save(boolean isComputerTurn, int diff){
		int[][] boardJoueur = new int[10][10];
		int[][] boardComputer = new int[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				boardJoueur[j][i]	= modele.getBoardManager().getCellHuman(new Position(j,i));
				boardComputer[j][i]	= modele.getBoardManager().getCellComputer(new Position(j,i));
			}
		}
		int winPlayer = modele.getHuman().getWin();
		int winComputer = modele.getComputer().getWin();
		int failPlayer = modele.getHuman().getFail();
		int failComputer = modele.getComputer().getFail();
		this.XMLw.Save(actualEpoque, boardJoueur, boardComputer, isComputerTurn, winPlayer, failPlayer, winComputer, failComputer, diff);
	}
	/**
	 * permet de charger la partie sauvegardée et d'initialiser les boards et les bateaux.
	 */
	public void loadGame(){
		XMLr.readConfigFile();
		setActualEpoque(XMLr.getNomEpoque());
		int[][] bC = XMLr.getBoardComputer();
		int[][] bP = XMLr.getBoardPlayer();
		for(int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				modele.getBoardManager().setCellComputer(new Position(i,j), bC[i][j] );
				modele.getBoardManager().setCellHuman(new Position(i,j), bP[i][j] );
			}
		}
		for(int i=0; i<5; i++){			
			Bateau bateauTmpJ = actualEpoque.getBateauJoueur(i);
			Bateau bateauTmpC = actualEpoque.getBateauOrdi(i);			
			for(int j=0; j<bateauTmpJ.getTaille();j++){
				int xP = XMLr.getPositionBateauPlayer(i).get(j).getX();
				int yP = XMLr.getPositionBateauPlayer(i).get(j).getY();
				bateauTmpJ.setPosition(j, xP, yP);
			}
			for(int j=0; j<bateauTmpC.getTaille();j++){
				int xC = XMLr.getPositionBateauComputer(i).get(j).getX();
				int yC = XMLr.getPositionBateauComputer(i).get(j).getY();
				bateauTmpC.setPosition(j, xC, yC);
			}
			this.actualEpoque.getBateauJoueur(i).setResistance(XMLr.getResistanceBateauPlayer(i));
			this.actualEpoque.getBateauOrdi(i).setResistance(XMLr.getResistanceBateauComputer(i));
		}
		modele.setScorePlayer(XMLr.getreussiPlayer(), XMLr.getratePlayer());
		modele.setScoreComputer(XMLr.getreussiComputer(), XMLr.getrateComputer());
		
	}
	
	/**
	 * vérifie si on peut poser un bateau sur la case souhaité ainsi que toute celles qu'il va occuper
	 * @param pos position de depart
	 * @param type taille du bateau
	 * @param orient orientation : horizontale = 1, verticale = 0
	 * @return true si c'est possible de le placer, sinon false
	 */
	public boolean checkPlacementPossiblePlayer(Position pos, int type, int orient){
		Position tmp = new Position(pos.getX(),pos.getY());
		int res=-1;
		for(int i=0; i<type;i++){
			if(orient==1){
				res = this.getIndiceBateauPlayer(tmp);
				if(res>=0)return false;
				tmp.setX(tmp.getX()+1);
			}else{
				res = this.getIndiceBateauPlayer(tmp);
				if(res>=0)return false;
				tmp.setY(tmp.getY()+1);				
			}
		}
		return true;
	}
	/**
	 * vérifie si on peut poser un bateau sur la case souhaité ainsi que toute celles qu'il va occuper
	 * @param pos position de depart
	 * @param type taille du bateau
	 * @param orient orientation : horizontale = 1, verticale = 0
	 * @return true si c'est possible de le placer, sinon false
	 */
	public boolean checkPlacementPossibleComputer(Position pos, int type, int orient){
		Position tmp = new Position(pos.getX(),pos.getY());
		int res=-1;
		for(int i=0; i<type;i++){
			if(orient==1){
				res = this.getIndiceBateauComputer(tmp);
				if(res>=0)return false;
				tmp.setX(tmp.getX()+1);
			}else{
				res = this.getIndiceBateauComputer(tmp);
				if(res>=0)return false;
				tmp.setY(tmp.getY()+1);				
			}
		}
		return true;
	}
	
	
	/*-------------SETTEUR--------------*/

	public boolean setActualEpoque(int i){
		if(i > epoques.size() & i<0)return false;
		actualEpoque.setName(epoques.get(i).getName());
		actualEpoque.setApparence(epoques.get(i).getApparence());
		for(int j = 0; j < 5; j++){
			actualEpoque.setNomBateau(j, epoques.get(i).getBateauJoueur(j).getNom());
			actualEpoque.setResistanceInitBateau("joueur", j, epoques.get(i).getBateauJoueur(4-j).getResistance());
			actualEpoque.setResistanceInitBateau("ordi", j, epoques.get(i).getBateauOrdi(4-j).getResistance());
		}
		return true;
	}
	public boolean setActualEpoque(String name){
		for(int i=0;i<epoques.size();i++){
			if(epoques.get(i).getName().equals(name)){
				return setActualEpoque(i);
			}
		}
		return false;
	}
	
	public boolean setModel(Model modele) {
		if (modele == null) return false;
		this.modele = modele;
		return true;
	}
	
	/*-------------GETTEUR--------------*/

	public Epoque getEpoqueSave(){
		for(int i=0;i<this.epoques.size();i++){
			if(this.XMLr.getNomEpoque().equals(epoques.get(i).getName()))return epoques.get(i);
		}
		return null;
	}

	public ArrayList<String> getAllNameOfEpoques(){
		ArrayList<String> name = new ArrayList<String>();
		for(int i=0;i<epoques.size();i++){
			name.add(epoques.get(i).getName());
		}
		return name;
	}

	public ArrayList<String> getInfoActualEpoque(){
		ArrayList<String> infos = new ArrayList<String>();
		infos.add(actualEpoque.getApparence());
		for(int i=0;i<5;i++){
			infos.add(actualEpoque.getBateauJoueur(i).getNom());
		}
		return infos;
	}

	public int getNBEpoque(){
		return epoques.size();
	}
	
	public Epoque getEpoque(int i){
		return this.epoques.get(i);
	}
	
	public Epoque getActualEpoque(){
		return this.actualEpoque;
	}

	public int getIndiceBateauPlayer(Position p ){
		for(int i=0;i<5;i++){
			if(actualEpoque.getBateauJoueur(i).checkPosition(p))return i;
		}
		return -1;
	}
	
	public int getIndiceBateauComputer(Position p ){
		for(int i=0;i<5;i++){
			if(actualEpoque.getBateauOrdi(i).checkPosition(p))return i;
		}
		return -1;
	}
	
	public int getDifficultySaved(){
		return Integer.parseInt(XMLr.getDifficulty());
	}
	
}

