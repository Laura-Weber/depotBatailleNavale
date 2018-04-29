package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Model;

public class Fenetre extends JFrame implements Observer{

	/**
	 * Classe de la JFrame, switch entre 2 JPanels (Partie, FenetrePrincipale) en fonction de isMenu du Model
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private BarreDeMenu menu;
	private FenetrePrincipale principale;
	private Partie partie;
	
	public Fenetre(Model m){
		super("Bataille navale");
		this.model = m;
		this.model.addObserver(this);
		this.principale = new FenetrePrincipale(this.model);
		this.partie = new Partie(this.model);
		this.menu = new BarreDeMenu(this.model);
		this.addWindowListener(new WindowListener(){
				@Override
				public void windowActivated(WindowEvent arg0) {}
				@Override
				public void windowClosed(WindowEvent arg0) {}
				@Override
				
				public void windowClosing(WindowEvent arg0) {
					Fenetre.this.dispose();
				}

				@Override
				public void windowDeactivated(WindowEvent arg0) {}
				@Override
				public void windowDeiconified(WindowEvent arg0) {}
				@Override
				public void windowIconified(WindowEvent arg0) {}
				@Override
				public void windowOpened(WindowEvent arg0) {}
		});

		this.setJMenuBar(this.menu);
		this.add(this.principale, BorderLayout.CENTER); 
		
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
	}

	//C'est ici que s'effectue le switch entre les JPanels
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Model){	
			if(this.model.getIsMenu()){
				this.menu.update(this.model.getIsMenu());
				this.remove(partie);
				this.add(principale, BorderLayout.CENTER);
				this.repaint();
			}else{
				this.menu.update(this.model.getIsMenu());
				this.remove(principale);
				this.add(partie, BorderLayout.CENTER);
				this.repaint();
			}
		}
	}
	
}
