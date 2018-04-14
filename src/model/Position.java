package model;

/**
 * 
 * @author steve
 * @version 1.0
 *Classe servant a créer des position. 1 position représente 1 case occupée par un bateau.
 *Un bateau doit disposer de plusieurs position car il mesure plus d'une case
 */
public class Position {
	
	private int x,y;
	
	public Position(int i, int j){
		assert(i>=0 & i<10 & j>=0 & j<10):"erreur création position , i ou j incorrect";
		x=i;
		y=j;
	}
	public Position(){
		x=0;
		y=0;
	}
	
	public void setX(int i){
		assert(i>=0 & i<10):"position incorrecte";
		x=i;
	}
	public void setY(int i){
		assert(i>=0 & i<10):"position incorrecte";
		y=i;
	}
	
	
	public int getX(){return x;}
	public int getY(){return y;}
	public String toString(){
		return "position x : "+x +"||| position y : "+y +"\n";
	}
	public boolean isEqual(Position p) {
		return this.x==p.getX() & this.y==p.getY();
	}
	
}