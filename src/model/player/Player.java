package model.player;

import java.util.Observable;

import model.Model;

public abstract class Player extends Observable{

	 protected Model model;
	 protected String name;
	 protected int tirFail;
	 protected int tirwin;
	 
	 public Player(String n, Model m){
		 this.model = m ;
		 this.name = n;
		 this.tirFail = 0;
		 this.tirwin = 0;
	 }
	 
	 public abstract void newGame();
	 public abstract boolean changeDifficulty(int diff);
	 
	/*-------------SETTEUR--------------*/

	 public void setWin(int i){
		this.tirwin = i;
		setChanged();
		notifyObservers();
	 }
	 
	 public void setFail(int i){
		this.tirFail = i;
		setChanged();
		notifyObservers();
	 }
	 
	/*-------------GETTEUR--------------*/

	 public String getName(){
		 return this.name;
	 }
	 
	 public int getFail(){
		 return this.tirFail;
	 }

	 public int getWin(){
		return this.tirwin;
	 }
	 	 
}


