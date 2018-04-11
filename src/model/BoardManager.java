package model;

import java.util.ArrayList;
import java.util.Observable;

public class BoardManager extends Observable{	
	
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
			ArrayList<Position> alp = new ArrayList<Position>(modele.play(pos,id));
			if (alp.isEmpty())
				board.setCell(pos, board.FAIL);
			else
				for (Position p : alp) {
					board.setCell(p, board.HIT);
				}
			res = true;
		}
		return res;
	}
	
	/**
	 * 
	 * @return true si un des deux joueurs à gagné, false sinon.
	 */
	public boolean isFinish() {
		boolean res = false;
		if (playerBoard.finished() || computerBoard.finished() ) {
			res = true;					
		}
		return res;
	}
	
	/**
	 * 
	 * @param id = 1 sur c'est le joueur, 0 si c'est le computer
	 * @return le score correspondant à l'id
	 */
	public int getScore(int id) {
		assert (id == 0 || id == 1) : "l'id renseigné est incorrecte BoardManager.getScore";
		int res = playerBoard.getScore();	// On donne le score du joueur par defaut
		if (id == 0)	// On demande le score du computer
			res = computerBoard.getScore();
		return res;
	}
	
	public int getCellPlayer(Position pos) {
		return this.playerBoard.getCell(pos);
	}
	
	public int getCellComputer(Position pos) {
		return this.computerBoard.getCell(pos);
	}
	
	public void setCellPlayer(Position pos, int i) {
		this.playerBoard.setCell(pos, i);
		setChanged();
		notifyObservers();
	}
	
	public void setCellComputer(Position pos, int i) {
		this.computerBoard.setCell(pos, i);
		setChanged();
		notifyObservers();
	}
	
	public boolean newGame() { 
		playerBoard = new Board();
		computerBoard=new Board();
		return true;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("plateau ordi:\n");
		StringBuilder sb2 = new StringBuilder();
		sb.append("plateau joueur:\n");

		for(int i=0;i<10;i++){
			for(int j=0; j<10;j++){
				sb.append(Integer.toString(this.getCellComputer(new Position(i,j))));
				sb.append(" | ");
				sb2.append(Integer.toString(this.getCellPlayer(new Position(i,j))));
				sb2.append(" | ");
			}
			sb.append("\n");
			sb2.append("\n");
		}
		
		return sb.toString() + sb2.toString();
	}
}

