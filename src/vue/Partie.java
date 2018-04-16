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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BoardManager;
import model.Model;
import model.Position;
import model.player.Player;
import model.player.Computer;

public class Partie extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private int SIZE = 10; 
	private BufferedImage image;
	private JPanel player;
	private JLabel winPlayer;
	private JLabel loosePlayer;
	private JPanel boardPlayer;
	private JButton casesPlayer[][];
	private JPanel computer;
	private JLabel winComputer;
	private JLabel looseComputer;
	private JPanel boardComputer;
	private JButton casesComputer[][];

	public Partie(Model m) throws IOException{
		this.model = m;
		this.model.getBoardManager().addObserver(this);
    	this.setSize(new Dimension(800, 600));
		this.setLayout(new GridLayout(1,2));
		this.image = ImageIO.read(new File("./src/vue/fondPartie.jpg"));
			
		//***** JPanel de Player *****/
		this.player = new JPanel();
		this.player.setOpaque(false);
		this.player.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridy = 20;
		c.insets = new Insets(0,0,10,0);
		JLabel namePlayer = new JLabel(this.model.getHuman().getName());
		namePlayer.setOpaque(true);
		this.player.add(namePlayer, c);
		c.gridy = 40;
		c.insets = new Insets(0,0,0,10);
		this.winPlayer = new JLabel("Tir(s) réussi(s) : " + this.model.getHuman().getWin());
		this.winPlayer.setOpaque(true);
		this.player.add(this.winPlayer, c);
		this.loosePlayer = new JLabel("Tir(s) raté(s) : " + this.model.getHuman().getFail());
		this.loosePlayer.setOpaque(true);
		this.player.add(this.loosePlayer, c);
		this.boardPlayer = new JPanel();
		this.boardPlayer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesPlayer = new JButton[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesPlayer[i][j] = new JButton(Integer.toString(this.model.getBoardManager().getCellPlayer(new Position(i, j))));
				this.casesPlayer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesPlayer[i][j].setPreferredSize(new Dimension(30,30));
				this.casesPlayer[i][j].setBackground(Color.white);
				this.casesPlayer[i][j].setOpaque(true);
				this.casesPlayer[i][j].addActionListener(new ActionListenerCase(this.model, i, j, "Player"));
				this.boardPlayer.add(casesPlayer[i][j]);
			}
		}
		c.gridy = 60;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(50,0,0,0);
		this.player.add(this.boardPlayer, c);
		this.add(this.player);
		
		//***** JPanel de Computer *****/
		this.computer = new JPanel();
		this.computer.setOpaque(false);
		this.computer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computer.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridy = 20;
		c.insets = new Insets(0,0,10,0);
		JLabel nameComputer = new JLabel(this.model.getComputer().getName());
		nameComputer.setOpaque(true);
		this.computer.add(nameComputer, c);
		c.insets = new Insets(0,0,0,10);
		c.gridy = 40;
		this.winComputer = new JLabel("Tir(s) réussi(s) : " + this.model.getComputer().getWin());
		this.winComputer.setOpaque(true);
		this.computer.add(this.winComputer, c);
		this.looseComputer = new JLabel("Tir(s) raté(s) : " + this.model.getComputer().getFail());
		this.looseComputer.setOpaque(true);
		this.computer.add(this.looseComputer, c);
		this.boardComputer = new JPanel();
		this.boardComputer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesComputer = new JButton[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesComputer[i][j] = new JButton(Integer.toString(this.model.getBoardManager().getCellComputer(new Position(i, j))));
				this.casesComputer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesComputer[i][j].setPreferredSize(new Dimension(30,30));
				this.casesComputer[i][j].setBackground(Color.white);
				this.casesComputer[i][j].setOpaque(true);
				this.casesComputer[i][j].addActionListener(new ActionListenerCase(this.model, i, j, "Computer"));
				this.boardComputer.add(casesComputer[i][j]);
			}
		}
		c.gridy = 60;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(50,0,0,0);
		this.computer.add(this.boardComputer, c);
		this.add(this.computer);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Player){
			this.winPlayer.setText("Tir(s) réussi(s) : " + this.model.getComputer().getWin());
			this.loosePlayer.setText("Tir(s) raté(s) : " + this.model.getComputer().getFail());
		}else if(o instanceof Computer){
			this.winComputer.setText("Tir(s) réussi(s) : " + this.model.getComputer().getWin());
			this.looseComputer.setText("Tir(s) raté(s) : " + this.model.getComputer().getFail());
		}else if(o instanceof BoardManager){
			this.casesComputer[((Position) arg).getX()][((Position)arg).getY()].setText(Integer.toString(this.model.getBoardManager().getCellComputer((Position)arg)));
			this.casesPlayer[((Position) arg).getX()][((Position)arg).getY()].setText(Integer.toString(this.model.getBoardManager().getCellPlayer((Position)arg)));
		}

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
