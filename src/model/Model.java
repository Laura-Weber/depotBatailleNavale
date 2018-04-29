package model;

import java.util.ArrayList;
import java.util.Observable;

import model.player.*;

public class Model extends Observable{

	private Player human, computer;
	private EpoqueManager epoquemanager;
	private boolean isMenu;
	private BoardManager bm;
	private boolean computerTurn;	
	private boolean isPlacement;
	private boolean isFinish;
	private int difficulty;
	private static int DEFAULT = 0 ;
	
	private Position selectedPlacement = null;
	
	public Model(){	
		this.epoquemanager = EpoqueManager.getInstance();
		this.isMenu = true;
		this.bm = BoardManager.getInstance();	
		this.computerTurn = false;	
		this.isPlacement = false;
		this.isFinish = false;
		this.human = new Human(this);
		this.computer = new Computer(this, bm);
		this.changeDifficulty(0);
		this.epoquemanager.setModel(this);
		epoquemanager.readAllEpoque();
		epoquemanager.setActualEpoque(DEFAULT);
	}
	
	
	/**
	 * initialise tout comme il faut pour lancer une nouvelle partie 
	 */
	public void newGame(){
		this.epoquemanager.newGame();
		this.bm.newGame();
		this.human.newGame();
		this.computer.newGame();
		this.computerTurn = false;
		this.setIsPlacement(true);
	}
	/**
	 * declenche une newGame puis va lire la sauvegarde pour initialiser les anciens coup joué
	 */
	public void loadGame(){
		this.changeDifficulty(epoquemanager.getDifficultySaved());
		this.newGame();
		this.epoquemanager.newGame();
		this.setIsPlacement(false);
		epoquemanager.loadGame();
		
	}
	/**
	 * change la difficulté courante
	 * @param diff 0,1,2
	 * @return true si se passe bien 
	 */
	public boolean changeDifficulty(int diff){
		difficulty = diff;
		return computer.changeDifficulty(diff);
	}
	/**
	 * La partie est finie quand il n'y a plus de bateaux � toucher
	 * @return true si c'est fini
	 */
	public boolean isFinish() {
		if(bm.isFinish()){
			this.isFinish = true;
			setChanged();
			notifyObservers();
		}
		return bm.isFinish();
	}

	/**
	 * sauvegarde la partie courante
	 */
	public void save(){
		epoquemanager.save(computerTurn,difficulty);
	}
	/**
	 * créer une nouvelle époque
	 * @param nom nom epoque
	 * @param apparence url image de l'epoque
	 * @param res2 resistance bateau taille 2
	 * @param res3 resistance bateau taille 3
	 * @param res4 resistance bateau taille 4
	 * @param res5 resistance bateau taille 5
	 * @param nom2 nom bateau taille 2
	 * @param nom3 nom bateau taille 3
	 * @param nom4 nom bateau taille 4
	 * @param nom5 nom bateau taille 5
	 * @return
	 */
	public boolean createNewEpoque(
			String nom, 
			String apparence, 
			String res2, 
			String res3, 
			String res4, 
			String res5,
			String nom2,
			String nom3,
			String nom4,
			String nom5){
		return epoquemanager.addEpoque(nom,apparence,res2,res3,res4,res5,nom2,nom3,nom4,nom5);
	}
	
	/**
	 * change l'epoque actuel
	 * @param name nom de l'epoque choisit
	 * @return true si bien passé
	 */
	public boolean changeEpoque(String name){
		return epoquemanager.setActualEpoque(name);
	}


	/*--------------------FONCTIONS DE PLACEMENTS------------------------*/
	/**
	 * Fonction utilis�e lors du placement de l'humain
	 * @param type taille du bateau a placer
	 * @param orient horizontale ou verticale (1 ou 0)
	 * @return
	 */
	public boolean placementHuman(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null && this.checkPlacementPossiblePlayer(selectedPlacement, type, orient)){
			res = this.bm.placementHuman(type, orient, selectedPlacement);
			if(res)epoquemanager.getActualEpoque().placementHuman(type, orient, selectedPlacement);
		}
		this.selectedPlacement = null;
		return res;
	}
	
	/**
	 * Fonction utilis�e lors du placement de l'ordinateur
	 * @param type taille du bateau a placer
	 * @param orient horizontale ou verticale (1 ou 0)
	 * @return
	 */
	public boolean placementComputer(int type, int orient, Position p){
		boolean res = false;
		if(p != null && this.checkPlacementPossibleComputer(p, type, orient)){
			res = this.bm.placementComputer(type, orient, p);
			epoquemanager.getActualEpoque().placementComputer(type, orient, p);

		}
		this.selectedPlacement = null;
		return res;
	}
	/**
	 * verifie si le placement désiré est possible
	 * @param pos position de depart 
	 * @param type taille bateau
	 * @param orient horizontal ou vertical (1,0)
	 * @return true si c'est possible false sinon
	 */
	public boolean checkPlacementPossiblePlayer(Position pos, int type, int orient){
		return epoquemanager.checkPlacementPossiblePlayer(pos,type,orient);
	}
	/**
	 * verifie si le placement désiré est possible
	 * @param pos position de depart 
	 * @param type taille bateau
	 * @param orient horizontal ou vertical (1,0)
	 * @return true si c'est possible false sinon
	 */
	public boolean checkPlacementPossibleComputer(Position pos, int type, int orient){
		return epoquemanager.checkPlacementPossibleComputer(pos,type,orient);
	}
	/*----------------------FIN FONCTION PLACEMENTS-------------------*/
	
	
	/*----------------------FONCTION DE FRAPPES-----------------------*/
	/**
	 * c'est l'humain qui joue
	 * @param p position de la frappe
	 */
	public void playHuman(Position p){
		if(this.getComputerTurn() == false && !this.isFinish()){
			if(this.bm.getCellComputer(p) == Board.SHIP){				
				ArrayList<Position> res = this.play(p, 1); // 1 player, 0 computer
				for(Position pos : res){
					this.bm.setCellComputer(pos, Board.HIT);
					this.human.setWin(this.human.getWin() + 1);
				}
				this.computerTurn = true;
				((Computer) this.computer).play();
			}else if(this.bm.getCellComputer(p) == Board.WATER){
				this.human.setFail(this.human.getFail() + 1);
				this.bm.setCellComputer(p, Board.FAIL);
				this.computerTurn = true;
				((Computer) this.computer).play();
			}
		}
	}
	
	/**
	 * c'est l'ordinateur qui joue
	 * @param p position de la frappe
	 */
	public void playComputer(Position p){
		if(this.getComputerTurn() == true && !this.isFinish()){
			if(this.bm.getCellHuman(p) == Board.SHIP){
				ArrayList<Position> res = this.play(p, 0); // 1 player, 0 computer
				for(Position pos : res){
					this.bm.setCellHuman(pos, Board.HIT);
					this.computer.setWin(this.computer.getWin() + 1);
				}
				this.computerTurn = false;
			}else if(this.bm.getCellHuman(p) == Board.WATER){
				this.computer.setFail(this.computer.getFail() + 1);
				this.bm.setCellHuman(p, Board.FAIL);
				this.computerTurn = false;
			}else{
				((Computer) this.computer).play();
			}
		}	
	}
	/**
	 * va chercher le résultat dans EpoqueManager
	 * @param pos
	 * @param id
	 * @return
	 */
	public ArrayList<Position> play(Position pos, int id) {
		return epoquemanager.play(pos,id);	
	}
	/*---------------FIN DES FRAPPES--------------------*/
	
	
	/*-------------SETTEUR--------------*/
	
	public void setIsMenu(boolean b){
		this.isMenu = b;
		if(b == true){
			this.setIsPlacement(false);
		}
		setChanged();
		notifyObservers();
	}
	
	public void setComputerTurn(boolean b){
		this.computerTurn = b;
	}
	
	public void setIsPlacement(boolean b){
		this.isPlacement = b;
		setChanged();
		notifyObservers();
	}
	
	public void setSelectedPlacement(Position p){
		this.selectedPlacement = p;
	}
	
	public void setScorePlayer(String getreussiPlayer, String getratePlayer) {
		this.human.setWin(Integer.parseInt(getreussiPlayer));
		this.human.setFail(Integer.parseInt(getratePlayer));
	}
	
	public void setScoreComputer(String getreussiComputer, String getrateComputer) {
		this.computer.setWin(Integer.parseInt(getreussiComputer));
		this.computer.setFail(Integer.parseInt(getrateComputer));
	}

	
	/*-------------GETTEUR--------------*/
	public boolean getIsMenu(){return this.isMenu;}
	public boolean getComputerTurn(){return this.computerTurn;}
	public BoardManager getBoardManager(){return this.bm;}
	public EpoqueManager getEpoqueManager(){return this.epoquemanager;}
	public boolean getIsPlacement(){return this.isPlacement;}	
	public Player getHuman(){return this.human;}
	public Player getComputer(){return this.computer;}
	public ArrayList<String> getAllNameOfEpoques(){return epoquemanager.getAllNameOfEpoques();}
	public ArrayList<String> getInfoActualEpoque(){return epoquemanager.getInfoActualEpoque();} 
	public int getSizeEpoque(){return epoquemanager.getAllNameOfEpoques().size();}
	public int getResistanceOrdi(int bateau){return this.epoquemanager.getActualEpoque().getBateauOrdi(bateau).getResistance();}
	public int getResistanceJoueur(int bateau){return this.epoquemanager.getActualEpoque().getBateauJoueur(bateau).getResistance();}
	public int getIndiceBateauPlayer(Position p ){return epoquemanager.getIndiceBateauPlayer(p);}
	public int getIndiceBateauComputer(Position p ){return epoquemanager.getIndiceBateauComputer(p);}
	public boolean getIsFinish(){
		boolean res = this.isFinish;
		this.isFinish = false;
		return res;
	}
}
