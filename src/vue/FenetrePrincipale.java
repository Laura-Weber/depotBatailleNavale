package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;


public class FenetrePrincipale extends JPanel implements Observer{

	/**
	 * Jpanel du menu de demarrage, contient les differentes options de jeu
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private BufferedImage image;//image de fond, change en fonction de l'epoque
	private String src; //path de l'image de fond, change en fonction de l'epoque
	private JButton newGame;
	private JButton continueGame;
	private JButton changeDifficulties;
	private JButton changeEpoque;
	private JButton newEpoque;

	public FenetrePrincipale(Model m){
		this.model = m;
		this.model.getEpoqueManager().getActualEpoque().addObserver(this);
    	this.setSize(new Dimension(800, 600));
    	this.src = "./src/vue/fond1.jpg";
		try {
			this.image = ImageIO.read(new File(this.src));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
						model.newGame();
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
					model.setIsMenu(false);
					model.loadGame();
				}
			});
		this.add(this.continueGame, c);
		
		this.changeDifficulties = new JButton("Changer la difficulte");
		c.gridy = 80;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.changeDifficulties.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] values = {"Facile", "Normal", "Difficile"};
					Object selected = JOptionPane.showInputDialog(FenetrePrincipale.this, "Choisissez le niveau de difficulte.", "Difficulte de l'ordinateur", JOptionPane.DEFAULT_OPTION, null, values, "0");	
					if(selected != null){
						switch(selected.toString()){
						case("Facile") : 
							model.changeDifficulty(0);
							break;
						case("Normal") :
							model.changeDifficulty(1);
							break;
						case("Difficile") :
							model.changeDifficulty(2);
							break;
						}
					}
				}
			});
		this.add(this.changeDifficulties, c);
		
		this.changeEpoque = new JButton("Changer d'epoque");
		c.gridy = 100;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.changeEpoque.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] values = new String[FenetrePrincipale.this.model.getSizeEpoque()];
					ArrayList<String> allEpoque = FenetrePrincipale.this.model.getAllNameOfEpoques();
					for(int i = 0; i < FenetrePrincipale.this.model.getSizeEpoque(); i++){
						values[i] = allEpoque.get(i);
					}
					Object selected = JOptionPane.showInputDialog(FenetrePrincipale.this, "Choisissez l'epoque.", "Changer d'epoque", JOptionPane.DEFAULT_OPTION, null, values, "0");	
					for(int i = 0; i < FenetrePrincipale.this.model.getSizeEpoque(); i++){
						if(!(selected == null) && selected.toString().equals(allEpoque.get(i))){
							FenetrePrincipale.this.model.changeEpoque(selected.toString());
						}
					}
				}
			});
		this.add(this.changeEpoque, c);
		
		this.newEpoque = new JButton("Creer une nouvelle epoque");
		c.gridy = 120;
		c.gridwidth = 4;
		c.insets = new Insets(15,0,0,0);
		this.newEpoque.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FenetrePrincipale.this.createNewEpoque();
				}
			});
		this.add(this.newEpoque, c);
	}
	
	/**
	 * Fonction de creation du JPanel de createEpoque, s'ouvre dans un JOptionPane, envoie les donnees au model si tout est rempli
	 */
	public void createNewEpoque(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(21, 1));
        
        JTextField nom = new JTextField();
        BoutonBrowse apparence = new BoutonBrowse(this);
        
		JTextField nomBateau2 = new JTextField();
		JTextField nomBateau3 = new JTextField();
		JTextField nomBateau4 = new JTextField();
		JTextField nomBateau5 = new JTextField();
		
        String[] items = new String[2];
    	items[0] = Integer.toString(1);
    	items[1] = Integer.toString(2);
		JComboBox<Object> resistanceBateau2 = new JComboBox<Object>(items);
		items = new String[3];
    	items[0] = Integer.toString(1);
    	items[1] = Integer.toString(2);
    	items[2] = Integer.toString(3);
		JComboBox<Object> resistanceBateau3 = new JComboBox<Object>(items);
		items = new String[4];
    	items[0] = Integer.toString(1);
    	items[1] = Integer.toString(2);
    	items[2] = Integer.toString(3);
    	items[3] = Integer.toString(4);
		JComboBox<Object> resistanceBateau4 = new JComboBox<Object>(items);
		items = new String[5];
    	items[0] = Integer.toString(1);
    	items[1] = Integer.toString(2);
    	items[2] = Integer.toString(3);
    	items[3] = Integer.toString(4);
    	items[4] = Integer.toString(5);
		JComboBox<Object> resistanceBateau5 = new JComboBox<Object>(items);

        panel.add(new JLabel("Nom de l'epoque : "));
        panel.add(nom);
        panel.add(new JLabel("Chemin vers l'image de l'epoque : "));
        panel.add(apparence);
        panel.add(new JLabel("Nom du bateau a 2 cases : "));
        panel.add(nomBateau2);
        panel.add(new JLabel("Nom du bateau a 3 cases : "));
        panel.add(nomBateau3);
        panel.add(new JLabel("Nom du bateau a 4 cases : "));
        panel.add(nomBateau4);
        panel.add(new JLabel("Nom du bateau a 5 cases : "));
        panel.add(nomBateau5);
        panel.add(new JLabel("Resistance du bateau a 2 cases : "));
        panel.add(resistanceBateau2);
        panel.add(new JLabel("Resistance du bateau a 3 cases : "));
        panel.add(resistanceBateau3); 
        panel.add(new JLabel("Resistance du bateau a 4 cases : "));
        panel.add(resistanceBateau4);
        panel.add(new JLabel("Resistance du bateau a 5 cases : "));
        panel.add(resistanceBateau5);

        JOptionPane.showMessageDialog(this, panel, "Creation d'une epoque.", 3);
 
        
        if(nom.getText().isEmpty() == false && 
			apparence.getString().isEmpty() == false &&  
			nomBateau2.getText().isEmpty() == false && 
			nomBateau3.getText().isEmpty() == false && 
			nomBateau4.getText().isEmpty() == false && 
			nomBateau5.getText().isEmpty() == false ){
        	this.model.createNewEpoque(nom.getText(), 
        			apparence.getString(), 
        			Integer.toString(resistanceBateau2.getSelectedIndex()+1), 
        			Integer.toString(resistanceBateau3.getSelectedIndex()+1), 
        			Integer.toString(resistanceBateau4.getSelectedIndex()+1), 
        			Integer.toString(resistanceBateau5.getSelectedIndex()+1), 
        			nomBateau2.getText(), 
        			nomBateau3.getText(), 
        			nomBateau4.getText(), 
        			nomBateau5.getText());
        }else{
        	JOptionPane.showMessageDialog(this,
        		    "Un ou plusieurs champs manquants, action annulee.",
        		    "Champ(s) manquant(s) !",
        		    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	/**
	 * Permet de dessiner le fond avec la bonne image
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(!this.src.equals(this.model.getEpoqueManager().getActualEpoque().getApparence())){
			this.src = this.model.getEpoqueManager().getActualEpoque().getApparence();
			try {
				this.image = ImageIO.read(new File(this.src));
				this.repaint();
			} catch (IOException e1) {
				//ne fait rien si il ne trouve pas l'image, reste avec le fond courant
			}	
		}	
	}

}
