package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Board {

	private int[][] board;
	public static int SIZE = 10;
	public static int WATER = 0;
	public static int FAIL = 1;
	public static int HIT = 2;
	public static int SHIP = 3;
	public static Image iconHit;
	public static Image iconFail;
	public static Image iconWater;
	public static Image iconShip;
	
	/**
	 * Créationd d'un Board qui est représenté par un tableau de 10x10 cases
	 */
	public Board(){
		board = new int[SIZE][SIZE];
		iconHit = new ImageIcon("./src/model/hit.png").getImage();
		iconFail = new ImageIcon("./src/model/fail.png").getImage();
		iconWater = new ImageIcon("./src/model/water.png").getImage();
		iconShip = new ImageIcon("./src/model/ship.png").getImage();
		for (int i = 0;i<SIZE;i++) 
			for (int j = 0;j<SIZE;j++) 
				board[i][j] = WATER;
	}
	
	/**
	 * Test si il reste des bateaux non touch�s 
	 * @return
	 */
	public boolean finished() {
		boolean res = true;
		for(int i = 0; i < Board.SIZE; i++){
			for(int j = 0; j < Board.SIZE; j++){
				if(this.board[i][j] == Board.SHIP){
					res = false;
				}
			}
		}
		return res; 
	}
	
	public boolean reset() { 
		boolean res = false;
		if (board!=null) {
			for (int i = 0;i<SIZE;i++)
				for (int j = 0;j<SIZE;j++)
					board[i][j] = WATER;
			res = true;
		}
		return res;
	}
	
	/*-------------SETTEUR--------------*/

	/**
	 * 
	 * @param x Ligne ciblée sur la grille
	 * @param y Colonne ciblée sur la grille
	 * @param val 0 pour WATER, 1 pour FAIL, 2 pour HIT
	 * @return un booléen qui indique true si les coordonnées sont correctes, la valeur val est assignée à la cellule. false si les coordonnées sont incorrectes.
	 */
	public boolean setCell(Position pos, int val) {
		int x = pos.getX();
		int y = pos.getY();
		assert (x>=0 & x<SIZE & y>=0 & y<SIZE) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.setCell";
		boolean res = false;
		if (x>=0 & x<SIZE & y>=0 & y<SIZE) {
			res = true;
			this.board[x][y] = val;
		}
		return res;
	}
	
	/*-------------GETTEUR--------------*/
	
	/**
	 * 
	 * @param x Ligne ciblée sur la grille
	 * @param y Colonne ciblée sur la grille
	 * @return retourne un entier qui correspond à WATER, FAIL ou HIT suivant l'était de la case. Si la case n'existe pas, retourne -1.
	 */
	public int getCell(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		assert (x>=0 & x<SIZE & y>=0 & y<SIZE) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.getCell";
		int res = -1;
		if (x>=0 & x<SIZE & y>=0 & y<SIZE)
			res = board[x][y];
		return res;
	}
	
	
	public Image getImage(Position p){
		Image res = null;
		switch(this.getCell(p)){
		case 0 : //water
			res = Board.iconWater;
			break;
		case 1 : //fail
			res = Board.iconFail;
			break;
		case 2 : //hit
			res = Board.iconHit;
			break;
		case 3 : //ship
			res = Board.iconShip;
			break;
		default : 
			break;
		}
		return res;
	}

}
