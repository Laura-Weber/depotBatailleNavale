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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;


public class FenetrePrincipale extends JPanel{

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

	public FenetrePrincipale(Model m) throws IOException{
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
					model.newGame();
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
					String[] values = {"facile", "normal", "difficile"};

					Object selected = JOptionPane.showInputDialog(FenetrePrincipale.this, "Choisisser le niveau de difficulté.", "Difficulté", JOptionPane.DEFAULT_OPTION, null, values, "0");	
					if(selected != null){
						switch(selected.toString()){
						case("facile") : 
							model.changeDifficulty(0);
							break;
						case("normal") :
							model.changeDifficulty(1);
							break;
						case("difficile") :
							model.changeDifficulty(2);
							break;
						}
					}
				}
			});
		this.add(this.changeDifficulties, c);
		this.newEpoque = new JButton("Creer une nouvelle epoque");
		c.gridy = 100;
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
	
	public void createNewEpoque(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(27, 1));
        
        JTextField nom = new JTextField();
        JTextField apparence = new JTextField();
        String[] items = new String[10];
        for(int i = 0; i < 10; i++){
        	items[i] = Integer.toString(i+1);
        }
        JComboBox<Object> resistanceBateau = new JComboBox<Object>(items);
		JTextField apparenceBateau2 = new JTextField(); 
		JTextField apparenceBateau3 = new JTextField(); 
		JTextField apparenceBateau3Bis = new JTextField(); 
		JTextField apparenceBateau4 = new JTextField(); 
		JTextField apparenceBateau5 = new JTextField();
		JTextField nomBateau2 = new JTextField();
		JTextField nomBateau3 = new JTextField();
		JTextField nomBateau3Bis = new JTextField();
		JTextField nomBateau4 = new JTextField();
		JTextField nomBateau5 = new JTextField();

        panel.add(new JLabel("Nom : "));
        panel.add(nom);
        panel.add(new JLabel("Chemin vers l'image de l'époque : "));
        panel.add(apparence);
        panel.add(new JLabel("Resistance : "));
        panel.add(resistanceBateau);
        panel.add(new JLabel("Chemin vers l'image du bateau a 2 cases : "));
        panel.add(apparenceBateau2);
        panel.add(new JLabel("Chemin vers l'image du premier bateau a 3 cases : "));
        panel.add(apparenceBateau3); 
        panel.add(new JLabel("Chemin vers l'image du deuxième bateau a 3 cases : "));
        panel.add(apparenceBateau3Bis);
        panel.add(new JLabel("Chemin vers l'image du bateau a 4 cases : "));
        panel.add(apparenceBateau4);
        panel.add(new JLabel("Chemin vers l'image du bateau a 5 cases : "));
        panel.add(apparenceBateau5);
        panel.add(new JLabel("Nom du bateau a 2 cases : "));
        panel.add(nomBateau2);
        panel.add(new JLabel("Nom du premier bateau a 3 cases : "));
        panel.add(nomBateau3);
        panel.add(new JLabel("Nom du deuxième bateau a 3 cases : "));
        panel.add(nomBateau3Bis);
        panel.add(new JLabel("Nom du bateau a 4 cases : "));
        panel.add(nomBateau4);
        panel.add(new JLabel("Nom du bateau a 5 cases : "));
        panel.add(nomBateau5);

        JOptionPane.showMessageDialog(this, panel);
        
        if(nom.getText().isEmpty() == false && 
			apparence.getText().isEmpty() == false &&  
			apparenceBateau2.getText().isEmpty() == false &&  
			apparenceBateau3.getText().isEmpty() == false && 
			apparenceBateau3Bis.getText().isEmpty() == false &&  
			apparenceBateau4.getText().isEmpty() == false && 
			apparenceBateau5.getText().isEmpty() == false && 
			nomBateau2.getText().isEmpty() == false && 
			nomBateau3.getText().isEmpty() == false && 
			nomBateau3Bis.getText().isEmpty() == false && 
			nomBateau4.getText().isEmpty() == false && 
			nomBateau5.getText().isEmpty() == false ){
        	this.model.createNewEpoque(nom.getText(), apparence.getText(), resistanceBateau.getSelectedIndex()+1, apparenceBateau2.getText(), apparenceBateau3.getText(), apparenceBateau3Bis.getText(), apparenceBateau4.getText(), apparenceBateau5.getText(), nomBateau2.getText(), nomBateau3.getText(), nomBateau3Bis.getText(), nomBateau4.getText(), nomBateau5.getText());
        }else{
        	JOptionPane.showMessageDialog(this,
        		    "Un ou plusieurs champs manquants.",
        		    "Champ(s) manquant(s) !!",
        		    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 800, 600, null);
	}

}
