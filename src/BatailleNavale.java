import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vue.*;

public class BatailleNavale {

    public static void main(String[] args) throws IOException, InterruptedException {
    	JFrame frame = new JFrame("Bataille navale");

    	JPanel menu = new MenuPrincipal();
    	JPanel partie = new Partie();

    	//frame.add(menu, BorderLayout.CENTER);    	    	
    	frame.add(partie, BorderLayout.CENTER);
    	
    	frame.pack();
    	frame.setSize(new Dimension(800, 600));
    	frame.setVisible(true);
    	
    }
}
