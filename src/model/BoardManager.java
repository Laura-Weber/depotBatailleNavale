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
	
	/**
	 * Le Board du jouer et de l'ordi et le modele
	 */
	private Board computerBoard;
	private Board playerBoard;
	private Model modele;
	
	/**
	 * L'instance qu'on peut récupérer de BoardManager
	 * @return
	 */
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
	
	/**
	 * Permet de donner le modele au BoardManager
	 * @param modele
	 * @return true si le modele donné existe, false sinon
	 */
	public boolean setModel(Model modele) {
		assert (modele != null) : "Le modele donné est incorrecte";
		boolean res = false;
		if (modele != null) {
			res = true;
			this.modele = modele;
		}
		return res;
	}
	
	/**
	 * Joue en donnant une position. Le board de l'adversaire est mis à jour. 
	 * @param pos
	 * @param id = 1 si c'est le jouer qui joue, id = 0 si c'est le computer
	 * @return true si la position donnée est correcte ,false sinon.
	 */
	public boolean Play(Position pos, int id) {
		int x = pos.getX();
		int y = pos.getY();
		Board board;
		if (id == 1)	// Si c'est le joueur qui joue ( id = 1 ), c'est le board du computer qui est modifié
			board = computerBoard;
		else 
			board = playerBoard;
		boolean res = false;
		assert (x>=0 & x<board.HAUTEUR & y>=0 & y<board.LARGEUR) : "Coordonnées pour l'accès à la cellule incorrectes dans BoardManager.PlayerPlay";
		if (x>=0 & x<board.HAUTEUR & y>=0 & y<board.LARGEUR & board.getCell(pos) == 0 ) {
			// On va regarder les bateaux et leur position du computer pour voir si la position donnée correspond à un morceau de bateau.
			for (int i = 0; i < modele.getNbPos();i++) {
				if (modele.getBoatsPositions(i).equals(pos)) {
					board.setCell(pos, board.HIT);
					res = true;
				}
			}
			if (!res)
				board.setCell(pos, board.FAIL);
		} 
		return res;
	}
	
	
	public boolean isFinish() {
		boolean bool = true;
		for (int i = 0; i < playerBoard.HAUTEUR; i++) {
			for (int j = 0; j < playerBoard.LARGEUR; j++) {
				if (playerBoard.getCell(new Position(i,j))!=0 || computerBoard.getCell(new Position(i,j))!=0 ) {
					bool = false;					
				}
			}
		}
		
		
		return bool;
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
