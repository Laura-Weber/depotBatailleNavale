package model;

import model.player.*;

public class Model {

	Player human, computer;
	Epoque epoque;
	
	public Model(){
		human = new Human();
		computer = new Computer();
	}
	
}
