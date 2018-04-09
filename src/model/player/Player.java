package model.player;

import model.Model;

public abstract class Player {

	 protected int tirFail;
	 protected int tirwin;
	 protected int nbProjectiles;
	 protected String name;
	 protected Model model;
	 
	 public Player(String n, Model m){
		 this.model = m ;
		 this.tirFail = 0;
		 this.tirwin = 0;
		 this.nbProjectiles = 35;
		 this.name = n;
	 }
	
	
}
