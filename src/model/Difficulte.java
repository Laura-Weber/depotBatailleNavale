package model;

import model.player.Computer;

public abstract class Difficulte {
	
	private Computer computer;
	
	public Difficulte(Computer cp){
		computer = cp;
	}
	
	public abstract Position play();
	
}