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
	
}
