package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Model;

public class BarreDeMenu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private JMenu menu;
	private JMenuItem sauvegarder;
	private JMenuItem retour;
	
	public BarreDeMenu(Model m){
		this.model = m;
		this.menu = new JMenu("Menu");
		
		this.sauvegarder = new JMenuItem("Sauvegarder");
		this.sauvegarder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BarreDeMenu.this.model.save();
			}
		});
		this.menu.add(this.sauvegarder);
		
		this.retour = new JMenuItem("Retour");
		this.retour.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BarreDeMenu.this.model.setIsMenu(true);
			}			
		});
		this.retour.setEnabled(false);
		this.menu.add(this.retour);
		
		this.add(this.menu);
	}
	
	public void update(boolean isMenu){
		if(isMenu == false){
			this.retour.setEnabled(true);
		}else{
			this.retour.setEnabled(false);
		}
	}

}
