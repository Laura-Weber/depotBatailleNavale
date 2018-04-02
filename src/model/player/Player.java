package model.player;

public abstract class Player {

	 protected int tirFail;
	 protected int tirwin;
	 protected int nbProjectiles;
	 protected String name;
	 
	 public Player(String n){
		 this.tirFail = 0;
		 this.tirwin = 0;
		 this.nbProjectiles = 35;
		 this.name = n;
	 }
	
	
}
