package vue;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPrincipal extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private JButton newGame;
	private JButton continueGame;
	private JButton changeDifficulties;
	private JButton newEpoque;

	public MenuPrincipal() throws IOException{
		this.image = ImageIO.read(new File("./src/vue/fond1.jpg"));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		this.newGame = new JButton("Nouvelle partie");
		c.gridy = 40;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.add(this.newGame, c);
		this.continueGame = new JButton("Reprendre la partie");
		c.gridy = 60;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.add(this.continueGame, c);
		this.changeDifficulties = new JButton("Changer la difficulté");
		c.gridy = 80;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.add(this.changeDifficulties, c);
		this.newEpoque = new JButton("Créer une nouvelle époque");
		c.gridy = 100;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.add(this.newEpoque, c);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
