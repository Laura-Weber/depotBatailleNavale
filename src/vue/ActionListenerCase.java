package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import model.Position;

public class ActionListenerCase implements ActionListener{

	private Model model;
	private String name;
	private int iCase;
	private int jCase;
	
	public ActionListenerCase(Model m, int i, int j, String n){
		this.model = m;
		this.name = n;
		this.iCase = i;
		this.jCase = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.name.equals("Computer")){
			this.model.getBoardManager().setCellComputer(new Position(iCase, jCase), 1);
		}else{
			this.model.getBoardManager().setCellPlayer(new Position(iCase, jCase), 1);
		}
	}

}
