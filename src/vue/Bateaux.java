package vue;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;

public class Bateaux extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private String player;
	private JLabel name;
	private JLabel b2;
	private JLabel b31;
	private JLabel b32;
	private JLabel b4;
	private JLabel b5;
	
	public Bateaux(Model m, String s){
		this.model = m;
		this.player = s;
		this.setOpaque(false);
		this.setLayout(new GridLayout(6,1));
		
		this.name = new JLabel("Resistance : ");
		this.add(name);
		if(this.player.equals("Computer")){
			this.b2 = new JLabel("2 cases : " + this.model.getResistanceOrdi(0));
			this.b31 = new JLabel("3 cases 1 : " + this.model.getResistanceOrdi(1));
			this.b32 = new JLabel("3 cases 2 : " + this.model.getResistanceOrdi(2));
			this.b4 = new JLabel("4 cases : " + this.model.getResistanceOrdi(3));
			this.b5 = new JLabel("5 cases : " + this.model.getResistanceOrdi(4));
		}else{
			this.b2 = new JLabel("2 cases : " + this.model.getResistanceJoueur(0));
			this.b31 = new JLabel("3 cases 1 : " + this.model.getResistanceJoueur(1));
			this.b32 = new JLabel("3 cases 2 : " + this.model.getResistanceJoueur(2));
			this.b4 = new JLabel("4 cases : " + this.model.getResistanceJoueur(3));
			this.b5 = new JLabel("5 cases : " + this.model.getResistanceJoueur(4));
		}	
		this.add(this.b2);
		this.add(this.b31);
		this.add(this.b32);
		this.add(this.b4);
		this.add(this.b5);
	}
	
	
}
