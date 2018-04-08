package model;

public class Board {

	private int[][] board;
	public static int HAUTEUR = 10;
	public static int LARGEUR = 10;
	public static int WATER = 0;
	public static int FAIL = 1;
	public static int HIT = 2;
	
	/**
	 * Créationd d'un Board qui est représenté par un tableau de 10x10 cases
	 */
	public Board(){
		board = new int[HAUTEUR][LARGEUR];
		for (int i = 0;i<HAUTEUR;i++) 
			for (int j = 0;j<LARGEUR;j++) 
				board[i][j] = WATER;
	}
	
	/**
	 * 
	 * @param x Ligne ciblée sur la grille
	 * @param y Colonne ciblée sur la grille
	 * @return retourne un entier qui correspond à WATER, FAIL ou HIT suivant l'était de la case. Si la case n'existe pas, retourne -1.
	 */
	public int getCell(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		assert (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.getCell";
		int res;
		if (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR)
			res = board[x][y];
		else 
			res = -1;
		return res;
	}
	
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
		assert (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.setCell";
		boolean res;
		if (x>=0 & x<HAUTEUR & y>=0 & y<LARGEUR) {
			res = true;
			this.board[x][y] = val;
		}
		else 
			res = false;
		return res;
	}
}
