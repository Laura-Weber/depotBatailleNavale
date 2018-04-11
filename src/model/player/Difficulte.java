package model.player;

import model.Position;

public abstract class Difficulte {
	
	private Computer computer;
	
	public Difficulte(Computer cp){
		computer = cp;
	}
	
	public abstract Position play();
	
}