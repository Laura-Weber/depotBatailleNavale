package model;

import java.util.ArrayList;
import java.util.HashMap;
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

	public boolean setActualEpoque(int i){
		if(i > epoques.size() & i<0)return false;
		actualEpoque = epoques.get(i);
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
			b.setTaille();
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

	public Epoque getEpoqueSave(){
		for(int i=0;i<this.epoques.size();i++){
			if(this.XMLr.getNomEpoque().equals(epoques.get(i).getName()))return epoques.get(i);
		}
		return null;
	}

	public void readAllEpoque(){
		this.epoques = new ArrayList<Epoque>();
		XMLr.readConfigFile();
		Epoque epTmp;
		for(int i=0; i<XMLr.getNBEpoque();i++){
			epTmp = new Epoque(this);
			epoques.add(epTmp);
			epTmp.setName(XMLr.getNomEpoque(i));
			epTmp.setNomBateau(4, XMLr.getNomBateau(i,0));
			epTmp.setNomBateau(3, XMLr.getNomBateau(i,1));
			epTmp.setNomBateau(2, XMLr.getNomBateau(i,2));
			epTmp.setNomBateau(1, XMLr.getNomBateau(i,3));
			epTmp.setNomBateau(0, XMLr.getNomBateau(i,4));
			epTmp.setApparence(XMLr.getApparenceEpoque(i));
			epTmp.setResistanceBateau("joueur",4, XMLr.getResistanceBateau(i,0));
			epTmp.setResistanceBateau("joueur",3, XMLr.getResistanceBateau(i,1));
			epTmp.setResistanceBateau("joueur",2, XMLr.getResistanceBateau(i,2));
			epTmp.setResistanceBateau("joueur",1, XMLr.getResistanceBateau(i,3));
			epTmp.setResistanceBateau("joueur",0, XMLr.getResistanceBateau(i,4));
			epTmp.setResistanceBateau("ordi",4, XMLr.getResistanceBateau(i,0));
			epTmp.setResistanceBateau("ordi",3, XMLr.getResistanceBateau(i,1));
			epTmp.setResistanceBateau("ordi",2, XMLr.getResistanceBateau(i,2));
			epTmp.setResistanceBateau("ordi",1, XMLr.getResistanceBateau(i,3));
			epTmp.setResistanceBateau("ordi",0, XMLr.getResistanceBateau(i,4));
		}
	}
	public boolean addEpoque(String nom, 
			String apparence, 
			int resistanceBateau, 
			String apparenceBateau2, 
			String apparenceBateau3, 
			String apparenceBateau3Bis, 
			String apparenceBateau4, 
			String apparenceBateau5,
			String nomBateau2,
			String nomBateau3,
			String nomBateau3Bis,
			String nomBateau4,
			String nomBateau5){
		//créer l'epoque ici
		//et dit au XMLWriter de créer le fichier.
		return false;
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
			infos.add(actualEpoque.getBateauJoueur(i).getApparence());
		}
		return infos;
	}

	public boolean removeEpoque(String name){
		//get l'id associé au nom et lance un removeEpoque via l'id
		return false;
	}
	public boolean removeEpoque(int id){
		//remove ici 
		// et remove le fichier .xml aussi
		return false;
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


	public void createDefaultEpoque(){
		Epoque ep1 = new Epoque(this); 
		epoques.add(ep1);
		ep1.setName("default");
		ep1.setApparence("./ep1.jpg");

		ep1.setApparenceBateau(0, "b1.png");
		ep1.setApparenceBateau(1, "b1.png");
		ep1.setApparenceBateau(2, "b1.png");
		ep1.setApparenceBateau(3, "b1.png");
		ep1.setApparenceBateau(4, "b1.png");

		ep1.setNomBateau(0, "bateau0");
		ep1.setNomBateau(1, "bateau1");
		ep1.setNomBateau(2, "bateau2");
		ep1.setNomBateau(3, "bateau3");
		ep1.setNomBateau(4, "bateau4");

		ep1.setResistanceBateau(ep1.JOUEUR, 0, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 1, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 2, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 3, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 4, 2);
		ep1.setResistanceBateau(ep1.ORDI, 0, 2);
		ep1.setResistanceBateau(ep1.ORDI, 1, 2);
		ep1.setResistanceBateau(ep1.ORDI, 2, 2);
		ep1.setResistanceBateau(ep1.ORDI, 3, 2);
		ep1.setResistanceBateau(ep1.ORDI, 4, 2);
		actualEpoque=ep1;
	}
	
	public void initActualEpoque(){

	}
	public void save(boolean isComputerTurn, int diff){
		int[][] boardJoueur = new int[10][10];
		int[][] boardComputer = new int[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				boardJoueur[i][j]	= modele.getBoardManager().getCellHuman(new Position(i,j));
				boardComputer[i][j]	= modele.getBoardManager().getCellComputer(new Position(i,j));
			}
		}
		
		int winPlayer = modele.getHuman().getWin();
		int winComputer = modele.getComputer().getWin();
		int failPlayer = modele.getHuman().getFail();
		int failComputer = modele.getComputer().getFail();
		this.XMLw.Save(actualEpoque, boardJoueur, boardComputer, isComputerTurn, winPlayer, failPlayer, winComputer, failComputer, diff);
	}
	public boolean init() {//initialise l'epoque actuelle
		if(actualEpoque==null){
			this.createDefaultEpoque();
			return true;
		}else{
			this.initActualEpoque();
			return true;
		}
	}
	/* Main de test de l'ecriture et lecture dans le fichier xml .
	public static void main(String[] args){
		int[][] boardPlayer = new int[10][10];
		int[][] boardComputer = new int[10][10];
		EpoqueManager em = EpoqueManager.getInstance();
		em.XMLw.Save(em.epoques.get(0), boardPlayer, boardComputer, false, 12, 3, 7, 1, 0);
	}
	*/
}

