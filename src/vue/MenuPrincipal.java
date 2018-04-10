package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;


public class MenuPrincipal extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private BufferedImage image;
	private JButton newGame;
	private JButton continueGame;
	private JButton changeDifficulties;
	private JButton newEpoque;

	public MenuPrincipal(Model m) throws IOException{
		this.model = m;
    	this.setSize(new Dimension(800, 600));
		this.image = ImageIO.read(new File("./src/vue/fond1.jpg"));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		this.newGame = new JButton("Nouvelle partie");
		c.gridy = 40;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.newGame.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						model.setIsMenu(false);
					}
			});
		this.add(this.newGame, c);
		this.continueGame = new JButton("Reprendre la partie");
		c.gridy = 60;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.continueGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		this.add(this.continueGame, c);
		this.changeDifficulties = new JButton("Changer la difficult�");
		c.gridy = 80;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.changeDifficulties.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				
				}
			});
		this.add(this.changeDifficulties, c);
		this.newEpoque = new JButton("Cr�er une nouvelle �poque");
		c.gridy = 100;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.newEpoque.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
			
				}
			});
		this.add(this.newEpoque, c);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
