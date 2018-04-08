package model;

import java.util.ArrayList;

import model.player.*;

public class Model {

	Player human, computer;
	Epoque epoque;
		
	public Model(){
		human = new Human();
		computer = new Computer();
	}
	
	/**
	 * utilisée dans BoardManager
	 * @return
	 */
	public int getNbPos() {
		return 17;
	}
	
	/**
	 * utilisée dans BoardManager
	 * Accès au ième bateau
	 * @param i
	 * @return
	 */
	public Bateau getBoatsPositions(int i) {
		return null;
	}
	
}
