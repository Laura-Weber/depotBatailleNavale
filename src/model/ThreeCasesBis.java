package model;


public class ThreeCasesBis extends Bateau{
		
	public ThreeCasesBis(int res, String name, boolean computer, int size, String image, Epoque ep) {
		super(res, name, computer, image, ep);
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.positions.add(new Position());
		this.setTaille();
	}	
	
}