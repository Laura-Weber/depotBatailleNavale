package model;

public class Board {

	private int[][] board;
	public static int SIZE = 10;
	public static int WATER = 0;
	public static int FAIL = 1;
	public static int HIT = 2;
	public static int SHIP = 3;
	
	private int hits = 0;
	
	/**
	 * Créationd d'un Board qui est représenté par un tableau de 10x10 cases
	 */
	public Board(){
		board = new int[SIZE][SIZE];
		for (int i = 0;i<SIZE;i++) 
			for (int j = 0;j<SIZE;j++) 
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
		assert (x>=0 & x<SIZE & y>=0 & y<SIZE) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.getCell";
		int res = -1;
		if (x>=0 & x<SIZE & y>=0 & y<SIZE)
			res = board[x][y];
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
		assert (x>=0 & x<SIZE & y>=0 & y<SIZE) : "Coordonnées pour l'accès à la cellule incorrectes dans Board.setCell";
		boolean res = false;
		if (x>=0 & x<SIZE & y>=0 & y<SIZE) {
			res = true;
			this.board[x][y] = val;
			if (val == HIT) {
				this.hits ++;
				assert (hits <= 17) : "Nombre de touché supérieur au nombre max dans Board.setCell";
			}
		}
		return res;
	}
	
	public int getScore() { return hits; }
	
	public boolean finished() {
		boolean res = true;
		for(int i = 0; i < Board.SIZE; i++){
			for(int j = 0; j < Board.SIZE; j++){
				if(this.board[i][j] == 3){
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
			hits = 0;
			res = true;
		}
		return res;
	}
}
