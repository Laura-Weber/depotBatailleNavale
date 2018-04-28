package vue;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;

public class Bateaux extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private String player;
	private ArrayList<String> infos;
	private JLabel name;
	private JLabel b2;
	private JLabel b31;
	private JLabel b32;
	private JLabel b4;
	private JLabel b5;
	
	public Bateaux(Model m, String s){
		this.model = m;
		this.model.getEpoqueManager().getActualEpoque().addObserver(this);
		for(int i = 0; i < 5; i++){
			this.model.getEpoqueManager().getActualEpoque().getBateauJoueur(i).addObserver(this);
			this.model.getEpoqueManager().getActualEpoque().getBateauOrdi(i).addObserver(this);
		}
		this.player = s;
		this.infos = this.model.getInfoActualEpoque();
		this.infos.remove(0);
		Collections.reverse(this.infos);
		this.setOpaque(false);
		this.setLayout(new GridLayout(6,1));
		
		this.name = new JLabel("Resistance : ");
		this.add(name);
		if(this.player.equals("Computer")){
			this.b2 = new JLabel(this.infos.get(0) + " : " + this.model.getResistanceOrdi(0));
			this.b31 = new JLabel(this.infos.get(1) + " : " + this.model.getResistanceOrdi(1));
			this.b32 = new JLabel(this.infos.get(2) + " : " + this.model.getResistanceOrdi(2));
			this.b4 = new JLabel(this.infos.get(3) + " : " + this.model.getResistanceOrdi(3));
			this.b5 = new JLabel(this.infos.get(4) + " : " + this.model.getResistanceOrdi(4));
		}else{
			this.b2 = new JLabel(this.infos.get(0) + " : " + this.model.getResistanceJoueur(0));
			this.b31 = new JLabel(this.infos.get(1) + " : " + this.model.getResistanceJoueur(1));
			this.b32 = new JLabel(this.infos.get(2) + " : " + this.model.getResistanceJoueur(2));
			this.b4 = new JLabel(this.infos.get(3) + " : " + this.model.getResistanceJoueur(3));
			this.b5 = new JLabel(this.infos.get(4) + " : " + this.model.getResistanceJoueur(4));
		}	
		this.add(this.b2);
		this.add(this.b31);
		this.add(this.b32);
		this.add(this.b4);
		this.add(this.b5);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.infos = this.model.getInfoActualEpoque();
		this.infos.remove(0);
		Collections.reverse(this.infos);
		if(this.player.equals("Computer")){
			this.b2.setText(this.infos.get(0) + " : " + this.model.getResistanceOrdi(0));
			this.b31.setText(this.infos.get(1) + " : " + this.model.getResistanceOrdi(1));
			this.b32.setText(this.infos.get(2) + " : " + this.model.getResistanceOrdi(2));
			this.b4.setText(this.infos.get(3) + " : " + this.model.getResistanceOrdi(3));
			this.b5.setText(this.infos.get(4) + " : " + this.model.getResistanceOrdi(4));
		}else{
			this.b2.setText(this.infos.get(0) + " : " + this.model.getResistanceJoueur(0));
			this.b31.setText(this.infos.get(1) + " : " + this.model.getResistanceJoueur(1));
			this.b32.setText(this.infos.get(2) + " : " + this.model.getResistanceJoueur(2));
			this.b4.setText(this.infos.get(3) + " : " + this.model.getResistanceJoueur(3));
			this.b5.setText(this.infos.get(4) + " : " + this.model.getResistanceJoueur(4));
		}			
	}
	
	
}
