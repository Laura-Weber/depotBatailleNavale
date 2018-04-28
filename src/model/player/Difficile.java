package model.player;

import model.Board;
import model.BoardManager;
import model.Position;

public class Difficile extends Difficulte{

	private static BoardManager bm;
	private Position lastPlay;
	private Position currentPlay;
	private int timeout;
	private static int EXPLORATION = 0;
	private static int DEGOMMAGE = 1;
	private int state ;


	public Difficile(BoardManager bom) {
		state = EXPLORATION;
		bm = bom;

	}

	@Override
	public Position play() {
		//partie exploration
		if(state == EXPLORATION) return exploration();
		else return coule();

	}
	
	public Position exploration(){
		lastPlay = new Position(0 + (int)(Math.random() * ((Board.SIZE - 1) + 1)),0 + (int)(Math.random() * ((Board.SIZE - 1) + 1)));
		if(bm.getCellHuman(lastPlay) == Board.SHIP){
			System.out.println("passage en mode degommage");
			state = DEGOMMAGE;
			 timeout=0;
		}
		return lastPlay;
	}

	
	public Position coule(){
		System.out.println("aaa");

		switch(timeout){
		case 0 :
			currentPlay = new Position(lastPlay.getX() + 1, lastPlay.getY());
			break;
		case 1 : 
			currentPlay = new Position(lastPlay.getX() - 1, lastPlay.getY());
			break;
		case 2 :
			currentPlay = new Position(lastPlay.getX(), lastPlay.getY() + 1);
			break;
		case 3 :
			currentPlay = new Position(lastPlay.getX(), lastPlay.getY() - 1);
			state = EXPLORATION;

			break;
		}
		if(bm.getCellHuman(currentPlay) == Board.SHIP){
			lastPlay = currentPlay;
			state = DEGOMMAGE;
			timeout=0;
		}
		System.out.println("timeout : " + timeout + " x " + currentPlay.getX() + " y " + currentPlay.getY());
		timeout++;

		return currentPlay;
	}

}