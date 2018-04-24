package model.player;

import model.Model;

public class Human extends Player {

	public Human(Model m){
		super("Me",m);
	}
	
	public void init(){
		
	}

	@Override
	public boolean changeDifficulty(int diff) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void newGame() {
		this.tirwin = 0;
		this.tirFail = 0;		
	}
	
}
