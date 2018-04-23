package model;

import java.util.ArrayList;
import java.util.Observable;

import model.player.*;

public class Model extends Observable{

	private Player human, computer;
	private EpoqueManager epoquemanager;
	private Boolean isMenu;
	private BoardManager bm;
	private boolean computerTurn=true;	
	private boolean isPlacement;
	private Position selectedPlacement = null;
	
	public Model(){
		this.human = new Human(this);
		this.computer = new Computer(this);		
		epoquemanager = EpoqueManager.getInstance();
		this.isMenu = true;
		bm = BoardManager.getInstance();	
		this.isPlacement = false;
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
		this.setIsPlacement(true);
		return bm.newGame();
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


	public boolean playerTurn() {
		return this.computerTurn;
	}
	
	public boolean placementHuman(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null){
			this.bm.placementHuman(type, orient, selectedPlacement);
			res = true;
		}
		this.selectedPlacement = null;
		return res;
	}
	
	public boolean placementComputer(int type, int orient){
		boolean res = false;
		if(this.selectedPlacement != null){
			this.bm.placementComputer(type, orient, selectedPlacement);
			res = true;
		}
		this.selectedPlacement = null;
		return res;
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

/*public boolean placerBateau(String orientation, int id, Position pos) {
boolean res;
int x = pos.getX();
int y = pos.getY();
assert (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.placerBateau";
if (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR) {
	// Le bateau est placé verticalement
	if (orientation.equals("v")) {
		for (int i = x;i<x+id;i++) {
			this.board[i][y] = B;
		}
	}
	res = true;
}
else
	res = false;
return res;
}*/
