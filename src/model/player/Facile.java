package model.player;

import model.Board;
import model.Position;

public class Facile extends Difficulte{


	@Override
	public Position play() {
		int x,y;
		x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
		y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
		return new Position(x, y);
	}
	
	
	
}