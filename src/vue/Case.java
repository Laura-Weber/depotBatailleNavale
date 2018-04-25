package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.Board;
import model.Model;
import model.Position;

public class Case extends JButton implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int SIZE = 30;
	private Model model;
	private Image image;
	private Position position;
	private String name;

	public Case(Model m, int x, int y, String s){
		this.model = m;
		this.model.getBoardManager().addObserver(this);
		this.position = new Position(x, y);
		this.name = s;
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(Case.SIZE,Case.SIZE));
		this.setOpaque(true);
	}
	
	public void setImage(Image s){
		if(s != null){
			this.image = s;
			this.paintComponent(this.getGraphics());
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.image == null) return;
		g.drawImage(this.image, 0, 0, Case.SIZE - 2, Case.SIZE - 2, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		Position tmp = new Position(((Position) arg).getX(), ((Position)arg).getY());
		if(tmp.getX() == this.position.getX() && tmp.getY() == this.position.getY()){
			if(this.name.equals("Computer")){
				if(this.model.getBoardManager().getCellComputer(tmp) == Board.FAIL || this.model.getBoardManager().getCellComputer(tmp) == Board.HIT){
					this.setImage(this.model.getBoardManager().getComputerImage(tmp));
				}else{
					this.setImage(Board.iconWater);
				}
			}else{
				this.setImage(this.model.getBoardManager().getHumanImage(tmp));
			}
		}		
	}
	
}
