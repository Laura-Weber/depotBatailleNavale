package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Model;

public class Fenetre extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private JPanel menu;
	private JPanel partie;
	
	public Fenetre(Model m) throws InterruptedException, IOException{
		this.model = m;
		this.menu = new MenuPrincipal(this.model);
		this.partie = new Partie(this.model);

		this.add(menu, BorderLayout.CENTER); 
		
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		this.pack();
		
		this.model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Model){	
			if(this.model.getIsMenu()){
				this.remove(partie);
				this.add(menu, BorderLayout.CENTER);
				this.repaint();
				this.revalidate();
			}else{
				this.remove(menu);
				this.add(partie, BorderLayout.CENTER);
				this.repaint();
				this.revalidate();
			}
		}
	}
	
}
