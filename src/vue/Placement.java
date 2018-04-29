package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;

public class Placement extends JPanel{

	/**
	 * JPanel du placement, visible que quand isPlacement de Model = true, s'affiche dans la barre de menu
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private JLabel name; //nom du JPanel
	private JButton orientation;//horizontale, verticale, celui qui est affiche est celui pris en compte
	private int orient; // 0 : horizontale, 1 : verticale
	private JComboBox<Object> bateaux;//liste deroulante
	private String[] values = {"2 cases", "3 cases 1", "3 cases 2", "4 cases", "5 cases"};//valeur de la liste deroulante
	private JButton valider;//Quand tout est ok, envoie le placement au model
	
	public Placement(Model m){
		this.model = m;
		
		this.name = new JLabel("Placement : ");
		this.add(this.name);
		this.orientation = new JButton("Horizontale");
		this.orientation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Placement.this.orient == 0){
					Placement.this.orientation.setText("Vertical");
					Placement.this.orient = 1;
				}else{
					Placement.this.orientation.setText("Horizontal");
					Placement.this.orient = 0;
				}
			}
		});
		this.add(this.orientation);
		this.orient = 0;
        this.bateaux = new JComboBox<Object>(this.values);
        this.add(this.bateaux);
		this.valider = new JButton("Valider");
		this.valider.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Placement.this.bateaux.getItemCount() > 0){
					boolean res = false;
					switch((String)Placement.this.bateaux.getSelectedItem()){
					case "2 cases" :
						res = Placement.this.model.placementHuman(2, orient);
						break;
					case "3 cases 1" : 
						res = Placement.this.model.placementHuman(3, orient);
						break;
					case "3 cases 2" :
						res = Placement.this.model.placementHuman(3, orient);
						break;
					case "4 cases" :
						res = Placement.this.model.placementHuman(4, orient);
						break;
					case "5 cases" :
						res = Placement.this.model.placementHuman(5, orient);
						break;
					}
					if(res == true){
						Placement.this.bateaux.removeItemAt(Placement.this.bateaux.getSelectedIndex());
					}
					if(Placement.this.bateaux.getItemCount() == 0){
						Placement.this.model.setIsPlacement(false);
					}
				}
			}
		});
		this.add(this.valider);
	}

}
