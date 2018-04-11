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
	private JMenuItem quitter;
	
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
		
		this.quitter = new JMenuItem("Quitter la partie");
		this.quitter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BarreDeMenu.this.model.setIsMenu(true);
			}			
		});
		this.menu.add(this.quitter);
		
		this.menu.setEnabled(false);
		this.add(this.menu);
	}
	
	public void update(boolean isMenu){
		if(isMenu == false){
			this.menu.setEnabled(true);
		}else{
			this.menu.setEnabled(false);
		}
	}

}
