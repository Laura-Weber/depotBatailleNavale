package model.player;

import model.Board;
import model.BoardManager;
import model.Position;

public class Intermediaire extends Difficulte{

	private Position lastTouch=null;
	private Position lastPlay=null;
	private int timeout=0;
	private BoardManager bm = BoardManager.getInstance();
	private static int HIT = 2;

	public Intermediaire(BoardManager bom) {
		this.bm = bom;
	}

	@Override
	public Position play() {
		if (lastPlay != null && bm.getCellHuman(lastPlay) == HIT) lastTouch=lastPlay; //si le dernier coup a toucher un navire du joueur 
		
		int x=0,y=0;
		if(lastTouch == null){//en mode chasse aleatoire le temps de tomber sur quelque chose
			x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
			y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
		}else{
			if(timeout>3){//si on ne trouve plus rien a taper sur les cases adjaçentes
				timeout=0;
				lastTouch=null;
				x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
				y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
			}else{
				switch(timeout){
				case 0:
					if(lastTouch.getY()>0){
						y = lastTouch.getY()-1; 
						x = lastTouch.getX();
					}else{
						timeout++;
					}
				case 1:					
					if(lastTouch.getX()>0){
						y = lastTouch.getY(); 
						x = lastTouch.getX()-1;
					}else{
						timeout++;
					}
				case 2:
					if(lastTouch.getY()<9){
						y = lastTouch.getY()+1; 
						x = lastTouch.getX();
					}else{
						timeout++;
					}
				case 3:
					if(lastTouch.getX()<9){
						y = lastTouch.getY(); 
						x = lastTouch.getX()+1;
					}else{
						timeout++;
						timeout=-1;
						lastTouch=null;
						x = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
						y = 0 + (int)(Math.random() * ((Board.SIZE - 1) + 1));
					}
				}

				timeout++;
			}
		}
		lastPlay = new Position(x,y);
		if(bm.getCellHuman(lastPlay) == 0 || bm.getCellHuman(lastPlay) == 3 ) return lastPlay;
		return play();
	}



}