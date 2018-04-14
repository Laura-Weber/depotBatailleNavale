package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class BoutonBrowse extends JButton implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s;
	private JPanel panel;
    JFileChooser fc = new JFileChooser();
	
    public BoutonBrowse(JPanel p){
    	super("Browse");
    	this.panel = p;
    	this.addActionListener(this);
    }
    
    public String getString(){
    	if(s == null){
    		s = "";
    	}
    	return this.s;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		fc.showOpenDialog(this.panel);  
		if(fc.getSelectedFile() != null){
			s = fc.getSelectedFile().getAbsolutePath();		
		}
	}

}
