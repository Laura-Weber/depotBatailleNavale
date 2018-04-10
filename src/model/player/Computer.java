package model.player;

import model.*;

public class Computer extends Player{
	private Difficulte algo;
	private static int FACILE = 0;
	private static int INTERMEDIAIRE=1;
	private static int DIFFICILE=2;

	public Computer(Model m){
		super("Computer",m);
		this.changeDifficulty(FACILE);
	}
	
	public boolean changeDifficulty(int diff){
		if(diff==FACILE){
			algo = new Facile(this);
			return true;
		}
		if(diff==INTERMEDIAIRE){
			algo = new Intermediaire(this);
			return true;
		}
		if(diff==DIFFICILE){
			algo = new Difficile(this);
			return true;
		}
		return false;
	}
	
	public Position play(){
		return algo.play();
	}
	
}
