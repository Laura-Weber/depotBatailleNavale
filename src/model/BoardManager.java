package model;

public class BoardManager {	
	
	/**
	 * Cette classe est un singleton
	 */
	private volatile static BoardManager unique = null;
		
	private BoardManager() {
		this.computerBoard = new Board();
		this.playerBoard = new Board();
	}
	
	private Board computerBoard;
	private Board playerBoard;
	
	public static BoardManager getInstance() { 
		if (unique==null) {
			synchronized (BoardManager.class) {
				if (unique==null) {
					unique = new BoardManager();
				}
			}
		}
		return unique;
	}
	private Model modele;
	
	public boolean setModel(Model modele) {
		assert (modele != null) : "Le modele donné est incorrecte";
		boolean res;
		if (modele != null) {
			res = true;
			this.modele = modele;
		}
		else res = false;
		return res;
	}
	
	public boolean PlayerPlay(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		boolean res;
		assert (x>=0 & x<computerBoard.HAUTEUR & y>=0 & y<computerBoard.LARGEUR) : "Coordonnées pour l'accès à la cellule incorrectes dans BoardManager.PlayerPlay";
		if (x>=0 & x<computerBoard.HAUTEUR & y>=0 & y<computerBoard.LARGEUR) {
			res = true;
		} 
		else res = false;
		return res;
		
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
