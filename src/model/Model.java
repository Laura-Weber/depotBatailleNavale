package model;

import java.util.ArrayList;
import java.util.Observable;

import model.player.*;

public class Model extends Observable{

	private Player human, computer;
	private EpoqueManager epoquemanager;
	private Boolean isMenu;
	private BoardManager bm;
	private boolean computerTurn;	
	private boolean isPlacement;
	private Position selectedPlacement = null;
	
	public Model(){	
		this.epoquemanager = EpoqueManager.getInstance();
		this.isMenu = true;
		this.bm = BoardManager.getInstance();	
		this.computerTurn = false;	
		this.isPlacement = false;
		this.human = new Human(this);
		this.computer = new Computer(this);
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
		return computer.changeDifficulty(diff);
	}
	
	public boolean newGame(){
		boolean res = this.bm.newGame();
		this.human = new Human(this);
		this.computer = new Computer(this);
		this.computerTurn = false;	
		this.setIsPlacement(true);
		return res;
	}
	
	public void save(){
		
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

	public boolean isFinish() {
		return bm.isFinish();
	}
	
	public boolean placementHuman(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null){
			res = this.bm.placementHuman(type, orient, selectedPlacement);
		}
		this.selectedPlacement = null;
		return res;
	}
	
	public boolean placementComputer(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null){
			res = this.bm.placementComputer(type, orient, selectedPlacement);
		}
		this.selectedPlacement = null;
		return res;
	}
	
	/**
	 * click sur le plateau de Human
	 * @param p
	 */
	public void clickHuman(Position p){
		if(this.getComputerTurn() == true){
			if(this.bm.getCellHuman(p) == Board.SHIP){
				this.bm.setCellHuman(p, Board.HIT);
			}else if(this.bm.getCellHuman(p) == Board.WATER){
				this.bm.setCellHuman(p, Board.FAIL);
			}
		}
	}
	
	/**
	 * click sur le plateau de Computer
	 * @param p
	 */
	public void clickComputer(Position p){
		if(this.getComputerTurn() == false){
			if(this.bm.getCellComputer(p) == Board.SHIP){
				this.bm.setCellComputer(p, Board.HIT);
			}else if(this.bm.getCellComputer(p) == Board.WATER){
				this.bm.setCellComputer(p, Board.FAIL);
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
}
