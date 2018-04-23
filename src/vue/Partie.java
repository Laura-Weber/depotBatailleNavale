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

import model.Board;
import model.BoardManager;
import model.Model;
import model.Position;
import model.player.Computer;
import model.player.Human;

public class Partie extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private int SIZE = 10; 
	private BufferedImage image;
	private JPanel human;
	private JLabel winHuman;
	private JLabel looseHuman;
	private JPanel boardHuman;
	private JButton casesHuman[][];
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
			
		//***** JPanel de Human *****/
		this.human = new JPanel();
		this.human.setOpaque(false);
		this.human.setBorder(BorderFactory.createLineBorder(Color.black));
		this.human.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridy = 20;
		c.insets = new Insets(0,0,10,0);
		JLabel namePlayer = new JLabel(this.model.getHuman().getName());
		namePlayer.setOpaque(true);
		this.human.add(namePlayer, c);
		c.gridy = 40;
		c.insets = new Insets(0,0,0,10);
		this.winHuman = new JLabel("Tir(s) réussi(s) : " + this.model.getHuman().getWin());
		this.winHuman.setOpaque(true);
		this.human.add(this.winHuman, c);
		this.looseHuman = new JLabel("Tir(s) raté(s) : " + this.model.getHuman().getFail());
		this.looseHuman.setOpaque(true);
		this.human.add(this.looseHuman, c);
		this.boardHuman = new JPanel();
		this.boardHuman.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesHuman = new JButton[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesHuman[i][j] = new JButton(Integer.toString(this.model.getBoardManager().getCellHuman(new Position(i, j))));
				this.casesHuman[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesHuman[i][j].setPreferredSize(new Dimension(30,30));
				this.casesHuman[i][j].setBackground(Color.white);
				this.casesHuman[i][j].setOpaque(true);
				this.casesHuman[i][j].addActionListener(new ActionListenerCase(this.model, i, j, "Human"));
				this.boardHuman.add(casesHuman[i][j]);
			}
		}
		c.gridy = 60;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(50,0,0,0);
		this.human.add(this.boardHuman, c);
		this.add(this.human);
		
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
				if(this.model.getBoardManager().getCellComputer(new Position(i, j)) == Board.FAIL || this.model.getBoardManager().getCellComputer(new Position(i, j)) == Board.HIT){
					this.casesComputer[i][j] = new JButton(Integer.toString(this.model.getBoardManager().getCellComputer(new Position(i, j))));
				}else{
					this.casesComputer[i][j] = new JButton();
				}
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
		if(o instanceof Human){
			this.winHuman.setText("Tir(s) réussi(s) : " + this.model.getComputer().getWin());
			this.looseHuman.setText("Tir(s) raté(s) : " + this.model.getComputer().getFail());
		}else if(o instanceof Computer){
			this.winComputer.setText("Tir(s) réussi(s) : " + this.model.getComputer().getWin());
			this.looseComputer.setText("Tir(s) raté(s) : " + this.model.getComputer().getFail());
		}else if(o instanceof BoardManager){
			Position tmp = new Position(((Position) arg).getX(), ((Position)arg).getY());
			if(this.model.getBoardManager().getCellComputer(tmp) == Board.FAIL || this.model.getBoardManager().getCellComputer(tmp) == Board.HIT){
				this.casesComputer[tmp.getX()][tmp.getY()].setText(Integer.toString(this.model.getBoardManager().getCellComputer(tmp)));
			}else{
				this.casesComputer[tmp.getX()][tmp.getY()].setText("");
			}
			this.casesHuman[tmp.getX()][tmp.getY()].setText(Integer.toString(this.model.getBoardManager().getCellHuman(tmp)));
		}

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
