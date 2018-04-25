package model.player;

import model.*;

public class Computer extends Player{
	private Difficulte algo;
	private static int FACILE = 0;
	private static int INTERMEDIAIRE=1;
	private static int DIFFICILE=2;
	private static BoardManager bm;
	
	public Computer(Model m, BoardManager bom){
		super("Computer",m);
		this.changeDifficulty(FACILE);
		bm = bom;
	}
		
	public void placement(){
		int[] values = {2,3,3,4,5};
		int orient, x, y; // 0 : horizontale, 1 : verticale
		for(int i = 0; i < 5; i++){
			orient = 0 + (int)(Math.random() * ((1 - 0) + 1));
			x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
			y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
			while(this.model.getBoardManager().placementComputer(values[i], orient, new Position(x, y)) == false){
				orient = 0 + (int)(Math.random() * ((1 - 0) + 1));
				x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
				y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
			}
		}
	}
	
	public Position play(){
		this.model.playComputer(algo.play());
		return algo.play();
	}
	
	@Override
	public boolean changeDifficulty(int diff){
		if(diff==FACILE){
			algo = new Facile(this);
			return true;
		}
		if(diff==INTERMEDIAIRE){
			algo = new Intermediaire(this, bm);
			return true;
		}
		if(diff==DIFFICILE){
			algo = new Difficile(this, bm);
			return true;
		}
		return false;
	}

	@Override
	public void newGame() {
		this.setWin(0);
		this.setFail(0);
		this.placement();
	}
	
}
