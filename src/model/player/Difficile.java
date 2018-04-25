package model.player;

import model.Board;
import model.BoardManager;
import model.Position;

public class Difficile extends Difficulte{

	private static BoardManager bm;
	private Position lastPlay;
	private Position debutAttaque;
	private Position currentPlay;
	private int direction = N;
	private static int X = 0;
	private static int Y = 1;
	private static int VUE = 2;
	private static int EXPLORATION = 0;
	private static int DEGOMMAGE = 1;
	private static int N = 0;
	private static int S = 1;
	private static int E = 2;
	private static int W = 3;
	private int state ;
	private int[][] board = new int[10][10];
	private int[][] exploration = {
			{2,0,0},{5,0,0},{8,0,0},
			{1,1,0},{4,1,0},{7,1,0},
			{0,2,0},{3,2,0},{6,2,0},{9,2,0},
			{2,3,0},{5,3,0},{8,3,0},
			{1,4,0},{4,4,0},{7,4,0},
			{0,5,0},{3,5,0},{6,5,0},{9,5,0},
			{2,6,0},{5,6,0},{8,6,0},
			{1,7,0},{4,7,0},{7,7,0},
			{0,8,0},{3,8,0},{6,8,0},{9,8,0},
			{2,9,0},{5,9,0},{8,9,0}
	};

	public Difficile(Computer cp, BoardManager bom) {
		super(cp);
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
		int timeout=0;
		int tmp = 0 + (int)(Math.random() * ((33 - 1) + 1));
		while (exploration[tmp][VUE]==VUE && timeout < 500){
			tmp = 0 + (int)(Math.random() * ((33 - 1) + 1));
			timeout++;
		}
		if(timeout<500){
			System.out.println("timeout : ok");
			lastPlay = new Position(exploration[tmp][X],exploration[tmp][Y]);
			exploration[tmp][VUE]=VUE;
		}else{
			System.out.println("timeout : pas ok");
			lastPlay = new Position(0 + (int)(Math.random() * ((Board.SIZE - 1) + 1)),0 + (int)(Math.random() * ((Board.SIZE - 1) + 1)));
		}
		board[lastPlay.getX()][lastPlay.getY()]=1;
		if(bm.getCellHuman(lastPlay)==3){
			System.out.println("passage en mode degommage");
			direction = 0 ;
			state = DEGOMMAGE;
			board[lastPlay.getX()][lastPlay.getY()]=2;
		}
		debutAttaque = new Position(lastPlay.getX(),lastPlay.getY());
		return lastPlay;
	}

	
	public Position coule(){
		switch(direction){
		case 0: 
			if(lastPlay.getY()>0 && board[lastPlay.getX()][lastPlay.getY()]==2){
				lastPlay = new Position(lastPlay.getX(),lastPlay.getY()-1);
				checkVue(lastPlay);
				board[lastPlay.getX()][lastPlay.getY()]=2;
				return lastPlay;
			}else{
				lastPlay = new Position(debutAttaque.getX(),debutAttaque.getY());
				direction=1;
			}
		case 1:
			if(lastPlay.getY()<9 && board[lastPlay.getX()][lastPlay.getY()]==2){
				lastPlay = new Position(lastPlay.getX(),lastPlay.getY()+1);
				checkVue(lastPlay);
				board[lastPlay.getX()][lastPlay.getY()]=2;
				return lastPlay;
			}else{
				lastPlay = new Position(debutAttaque.getX(),debutAttaque.getY());;
				direction=2;
			}
		case 2:
			if(lastPlay.getX()<9 && board[lastPlay.getX()][lastPlay.getY()]==2){
				lastPlay = new Position(lastPlay.getX()+1,lastPlay.getY());
				checkVue(lastPlay);
				board[lastPlay.getX()][lastPlay.getY()]=2;
				return lastPlay;
			}else{
				lastPlay = new Position(debutAttaque.getX(),debutAttaque.getY());
				direction=3;
			}
		case 3:
			if(lastPlay.getX()>0 && board[lastPlay.getX()][lastPlay.getY()]==2){
				lastPlay = new Position(lastPlay.getX()-1,lastPlay.getY());
				checkVue(lastPlay);
				board[lastPlay.getX()][lastPlay.getY()]=2;
				return lastPlay;
			}else{
				lastPlay = new Position(debutAttaque.getX(),debutAttaque.getY());
				direction=0;
				state = EXPLORATION;
				System.out.println("passage en mode exploration");
				return play();
			}
		}

		return lastPlay;
	}
	public void checkVue(Position p){
		for(int i=0;i<33;i++){
			if(exploration[i][X] == p.getX() && exploration[i][Y] == p.getY() ){
				exploration[i][VUE] = VUE;
				break;
			}
		}
	}



}