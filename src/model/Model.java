package model;

import java.util.ArrayList;
import java.util.Observable;

import model.player.*;

public class Model extends Observable{

	Player human, computer;
	EpoqueManager epoquemanager;
	Boolean isMenu;
	
	public Model(){
		this.human = new Human(this);
		this.computer = new Computer(this);
		this.isMenu = true;
	}
	
	
	/**
	 * va chercher le résultat dans EpoqueManager
	 * @param pos
	 * @param id
	 * @return
	 */
	public ArrayList play(Position pos, int id) {
		return epoquemanager.play(pos,id);
	}
	
	public void setIsMenu(boolean b){
		this.isMenu = b;
		setChanged();
		notifyObservers();
	}
	
	public boolean getIsMenu(){
		return this.isMenu;
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
