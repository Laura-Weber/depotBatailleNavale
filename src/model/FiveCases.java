package model;


public class FiveCases extends Bateau{
		
	public FiveCases(int res, String name, boolean computer, int size, String image, Epoque ep) {
		super(res, name, computer, image, ep);
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.setTaille();
	}	
	
}