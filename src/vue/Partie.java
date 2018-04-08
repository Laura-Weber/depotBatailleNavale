package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Partie extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int SIZE = 10; 
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

	public Partie(){
		this.setLayout(new GridLayout(1,2));
		
		this.player = new JPanel();
		this.player.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 20;
		JLabel namePlayer = new JLabel("Player");
		namePlayer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.player.add(namePlayer, c);
		this.winPlayer = new JLabel("Tir(s) réussi(s) : 14");
		this.winPlayer.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.player.add(this.winPlayer, c);
		this.boardPlayer = new JPanel();
		this.boardPlayer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesPlayer = new JLabel[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesPlayer[i][j] = new JLabel("");
				this.casesPlayer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesPlayer[i][j].setPreferredSize(new Dimension(30,30));
				this.boardPlayer.add(casesPlayer[i][j]);
			}
		}
		c.insets = new Insets(200,0,0,0);
		this.player.add(this.boardPlayer, c);
		this.add(this.player);
		
		this.computer = new JPanel();
		this.computer.setBorder(BorderFactory.createLineBorder(Color.black));
		this.computer.setLayout(new GridBagLayout());
		this.boardComputer = new JPanel();
		this.boardComputer.setLayout(new GridLayout(this.SIZE, this.SIZE));
		this.casesComputer = new JLabel[10][10];
		for(int i = 0; i < this.SIZE; i++){
			for(int j = 0; j < this.SIZE; j++){
				this.casesComputer[i][j] = new JLabel("");
				this.casesComputer[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.casesComputer[i][j].setPreferredSize(new Dimension(30,30));
				this.boardComputer.add(casesComputer[i][j]);
			}
		}
		c.insets = new Insets(200,0,0,0);
		this.computer.add(this.boardComputer, c);
		this.add(this.computer);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
