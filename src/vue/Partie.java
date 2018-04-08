package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Partie extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int SIZE = 10; 
	private BufferedImage image;
	private JPanel player;
	private JLabel winPlayer;
	private JLabel loosePlayer;
	private JLabel stillPlayer;
	private JPanel boardPlayer;
	private JLabel casesPlayer[][];
	private JPanel computer;
	private JLabel winComputer;
	private JLabel looseComputer;
	private JLabel stillComputer;
	private JPanel boardComputer;
	private JLabel casesComputer[][];

	public Partie() throws IOException{
		this.setLayout(new GridLayout(1,2));
		this.image = ImageIO.read(new File("./src/vue/fondPartie2.jpg"));
				
		this.player = new JPanel();
		this.player.setOpaque(false);
		this.player.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 20;
		JLabel namePlayer = new JLabel("Player");
		namePlayer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.add(namePlayer, c);
		this.winPlayer = new JLabel("Tir(s) r�ussi(s) : 14");
		this.winPlayer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.player.add(this.winPlayer, c);
		this.loosePlayer = new JLabel("Tir(s) rat�(s) : 5");
		this.loosePlayer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.player.add(this.loosePlayer, c);
		this.stillPlayer = new JLabel("Tir(s) restant(s) : 3");
		this.stillPlayer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.player.add(this.stillPlayer, c);
		this.boardPlayer = new JPanel();
		this.boardPlayer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesPlayer = new JLabel[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesPlayer[i][j] = new JLabel("");
				this.casesPlayer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesPlayer[i][j].setPreferredSize(new Dimension(30,30));
				this.casesPlayer[i][j].setBackground(Color.cyan);
				this.casesPlayer[i][j].setOpaque(true);
				this.boardPlayer.add(casesPlayer[i][j]);
			}
		}
		c.gridy = 40;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(50,0,0,0);
		this.player.add(this.boardPlayer, c);
		this.add(this.player);
		
		this.computer = new JPanel();
		this.computer.setOpaque(false);
		this.computer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computer.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 20;
		JLabel nameComputer = new JLabel("Computer");
		nameComputer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computer.add(nameComputer, c);
		this.winComputer = new JLabel("Tir(s) r�ussi(s) : 14");
		this.winComputer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.computer.add(this.winComputer, c);
		this.looseComputer = new JLabel("Tir(s) rat�(s) : 5");
		this.looseComputer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.computer.add(this.looseComputer, c);
		this.stillComputer = new JLabel("Tir(s) restant(s) : 3");
		this.stillComputer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.computer.add(this.stillComputer, c);
		this.boardComputer = new JPanel();
		this.boardComputer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesComputer = new JLabel[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesComputer[i][j] = new JLabel("");
				this.casesComputer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesComputer[i][j].setPreferredSize(new Dimension(30,30));
				this.casesComputer[i][j].setBackground(Color.CYAN);
				this.casesComputer[i][j].setOpaque(true);
				this.boardComputer.add(casesComputer[i][j]);
			}
		}
		c.gridy = 40;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(50,0,0,0);
		this.computer.add(this.boardComputer, c);
		this.add(this.computer);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
