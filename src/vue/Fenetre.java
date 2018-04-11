package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Model;

public class Fenetre extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private BarreDeMenu menu;
	private FenetrePrincipale principale;
	private Partie partie;
	
	public Fenetre(Model m) throws InterruptedException, IOException{
		this.model = m;
		this.principale = new FenetrePrincipale(this.model);
		this.partie = new Partie(this.model);
		this.menu = new BarreDeMenu(this.model);

		this.setJMenuBar(this.menu);
		this.add(this.principale, BorderLayout.CENTER); 
		
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		
		this.model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Model){	
			if(this.model.getIsMenu()){
				this.menu.update(this.model.getIsMenu());
				this.remove(partie);
				this.add(principale, BorderLayout.CENTER);
				this.repaint();
				this.revalidate();
			}else{
				this.menu.update(this.model.getIsMenu());
				this.remove(principale);
				this.add(partie, BorderLayout.CENTER);
				this.repaint();
				this.revalidate();
			}
		}
	}
	
}
