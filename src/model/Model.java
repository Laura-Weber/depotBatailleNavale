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
	 * va chercher le résultat dans EpoqueManager
	 * @param pos
	 * @param id
	 * @return
	 */
	public ArrayList<Position> play(Position pos, int id) {
		if(id==0){
			this.computerTurn=false;
		}else{
			this.computerTurn=true;
		}
		return epoquemanager.play(pos,id);	
	}
	
	public boolean changeDifficulty(int diff){
		difficulty = diff;
		return computer.changeDifficulty(diff);
	}
	
	
	public void newGame(){
		this.bm.newGame();
		this.human.newGame();
		this.computer.newGame();
		this.computerTurn = false;	
		this.setIsPlacement(true);
	}
	
	public void save(){
		epoquemanager.save(computerTurn,difficulty);
	}
	
	public boolean createNewEpoque(
			String nom, 
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
		return epoquemanager.addEpoque(nom,apparence,resistanceBateau,apparenceBateau2, apparenceBateau3, apparenceBateau3Bis,apparenceBateau4, apparenceBateau5,nomBateau2,nomBateau3,nomBateau3Bis,nomBateau4,nomBateau5);
	}
	
	
	public boolean changeEpoque(String name){
		return epoquemanager.setActualEpoque(name);
	}

	/**
	 * La partie est finie quand il n'y a plus de bateaux � toucher
	 * @return
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
	 * Fonction utilis�e lors du placement de l'humain
	 * @param type
	 * @param orient
	 * @return
	 */
	public boolean placementHuman(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null){
			res = this.bm.placementHuman(type, orient, selectedPlacement);
			epoquemanager.getActualEpoque().placementHuman(type, orient, selectedPlacement);
		}
		this.selectedPlacement = null;
		return res;
	}
	
	/**
	 * Fonction utilis�e lors du placement de l'ordinateur
	 * @param type
	 * @param orient
	 * @return
	 */
	public boolean placementComputer(int type, int orient, Position p){
		boolean res = false;
		if(p != null){
			res = this.bm.placementComputer(type, orient, p);
			epoquemanager.getActualEpoque().placementComputer(type, orient, p);

		}
		this.selectedPlacement = null;
		return res;
	}
	
	/**
	 * c'est l'humain qui joue
	 * @param p
	 */
	public void playHuman(Position p){
		if(this.getComputerTurn() == false && !this.isFinish()){
			if(this.bm.getCellComputer(p) == Board.SHIP){
				epoquemanager.getActualEpoque().hitBoatComputer(p);
				this.human.setWin(this.human.getWin() + 1);
				this.bm.setCellComputer(p, Board.HIT);
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
	 * @param p
	 */
	public void playComputer(Position p){
		if(this.getComputerTurn() == true && !this.isFinish()){
			if(this.bm.getCellHuman(p) == Board.SHIP){
				epoquemanager.getActualEpoque().hitBoatPlayer(p);
				this.computer.setWin(this.computer.getWin() + 1);
				this.bm.setCellHuman(p, Board.HIT);
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
	
	/*-------------GETTEUR--------------*/
	
	public boolean getIsMenu(){
		return this.isMenu;
	}
	
	public boolean getComputerTurn(){
		return this.computerTurn;
	}
	
	public BoardManager getBoardManager(){
		return this.bm;
	}
	
	public boolean getIsPlacement(){
		return this.isPlacement;
	}
	
	public boolean getIsFinish(){
		boolean res = this.isFinish;
		this.isFinish = false;
		return res;
	}
	
	public Player getHuman(){
		return this.human;
	}
	
	public Player getComputer(){
		return this.computer;
	}
	
	public ArrayList<String> getAllNameOfEpoques(){
		return epoquemanager.getAllNameOfEpoques();
	}
	
	public ArrayList<String> getInfoActualEpoque(){
		return epoquemanager.getInfoActualEpoque();
	} 
	
	public int getSizeEpoque(){
		return epoquemanager.getAllNameOfEpoques().size();
	}
	public int getResistanceOrdi(int bateau){
		return this.epoquemanager.getActualEpoque().getBateauOrdi(bateau).getResistance();
	}
	public int getResistanceJoueur(int bateau){
		return this.epoquemanager.getActualEpoque().getBateauJoueur(bateau).getResistance();
	}
	
}
