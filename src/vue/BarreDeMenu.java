package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Model;

public class BarreDeMenu extends JMenuBar implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private Placement placement;
	private JMenu menu;
	private JMenuItem sauvegarder;
	private JMenuItem quitter;
	
	public BarreDeMenu(Model m){
		this.model = m;
		this.model.addObserver(this);
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
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Model){
			if(this.model.getIsPlacement() == true){
				this.placement = new Placement(this.model);
				this.add(this.placement);
				this.repaint();
				this.revalidate();
			}else if(this.model.getIsPlacement() == false && this.placement != null){
				this.remove(this.placement);
			}
		}
	}
}
