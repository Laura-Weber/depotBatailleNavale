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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Board;
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
	private Case casesHuman[][];
	private JPanel computer;
	private JLabel winComputer;
	private JLabel looseComputer;
	private JPanel boardComputer;
	private Case casesComputer[][];

	public Partie(Model m) throws IOException{
		this.model = m;
		this.model.addObserver(this);
		this.model.getComputer().addObserver(this);
		this.model.getHuman().addObserver(this);
		this.setSize(new Dimension(800, 600));
		this.setLayout(new GridLayout(1,2));
		this.image = ImageIO.read(new File("./src/vue/fondPartie3.jpg"));
			
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
		this.human.add(namePlayer, c);
		c.gridy = 40;
		c.insets = new Insets(0,0,0,10);
		this.winHuman = new JLabel("Tir(s) reussi(s) : " + this.model.getHuman().getWin());
		this.human.add(this.winHuman, c);
		this.looseHuman = new JLabel("Tir(s) rate(s) : " + this.model.getHuman().getFail());
		this.human.add(this.looseHuman, c);
		this.boardHuman = new JPanel();
		this.boardHuman.setBorder(BorderFactory.createLineBorder(Color.black));
		this.boardHuman.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesHuman = new Case[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesHuman[i][j] = new Case(this.model, i, j, "Human");
				this.casesHuman[i][j].addActionListener(new ActionListenerCase(this.model, i, j, "Human"));
				this.boardHuman.add(casesHuman[i][j]);
			}
		}
		c.gridy = 60;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(30,0,0,0);
		this.human.add(this.boardHuman, c);
		c = new GridBagConstraints();
		c.gridy = 120;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(10,10,10,10);
		this.human.add(new Bateaux(this.model, "Human"), c);
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
		this.computer.add(nameComputer, c);
		c.insets = new Insets(0,0,0,10);
		c.gridy = 40;
		this.winComputer = new JLabel("Tir(s) reussi(s) : " + this.model.getComputer().getWin());
		this.computer.add(this.winComputer, c);
		this.looseComputer = new JLabel("Tir(s) rate(s) : " + this.model.getComputer().getFail());
		this.computer.add(this.looseComputer, c);
		this.boardComputer = new JPanel();
		this.boardComputer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.boardComputer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesComputer = new Case[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				if(this.model.getBoardManager().getCellComputer(new Position(i, j)) == Board.FAIL || this.model.getBoardManager().getCellComputer(new Position(i, j)) == Board.HIT){
					this.casesComputer[i][j] = new Case(this.model, i, j, "Computer");
				}else{
					this.casesComputer[i][j] = new Case(this.model, i, j, "Computer");
				}
				this.casesComputer[i][j].addActionListener(new ActionListenerCase(this.model, i, j, "Computer"));
				this.boardComputer.add(casesComputer[i][j]);
			}
		}
		c.gridy = 60;
		c.gridheight = this.SIZE;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(30,0,0,0);
		this.computer.add(this.boardComputer, c);
		c = new GridBagConstraints();
		c.gridy = 120;
		c.gridwidth = this.SIZE;
		c.insets = new Insets(10,10,10,10);
		this.computer.add(new Bateaux(this.model, "Computer"), c);
		this.add(this.computer);
	}
	
	/**
	 * arg ici est une Position
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Human){
			this.winHuman.setText("Tir(s) reussi(s) : " + this.model.getHuman().getWin());
			this.looseHuman.setText("Tir(s) rate(s) : " + this.model.getHuman().getFail());
		}else if(o instanceof Computer){
			this.winComputer.setText("Tir(s) reussi(s) : " + this.model.getComputer().getWin());
			this.looseComputer.setText("Tir(s) rate(s) : " + this.model.getComputer().getFail());
		}else if(o instanceof Model && this.model.getIsFinish()){
			if(this.model.getHuman().getWin() > this.model.getComputer().getWin()){// id = 0 sur c'est le joueur, 1 si c'est le computer
				//Humain gagne
				JOptionPane.showMessageDialog(this, "Felicitations !! Vous avez gagne ! :D", "Vous avez fini !", 0);	
			}else{
				//Computer gagne
				JOptionPane.showMessageDialog(this, "Malheureusement, vous avez perdu.. Une autre fois peut etre ?", "Vous avez fini !", 0);	
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
