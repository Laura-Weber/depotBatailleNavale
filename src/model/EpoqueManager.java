package model;

import java.util.ArrayList;
import java.util.List;

public class EpoqueManager {	

	/**
	 * Cette classe est un singleton
	 */
	private volatile static EpoqueManager unique = null;
	private Model modele;
	private Epoque actualEpoque;
	private List<Epoque> epoques;
	private static int JOUEUR = 1;
	private static int ORDI = 0;

	private EpoqueManager() {
		epoques = new ArrayList<Epoque>();
	}

	public static EpoqueManager getInstance() { 
		if (unique==null) {
			synchronized (EpoqueManager.class) {
				if (unique==null) {
					unique = new EpoqueManager();
				}
			}
		}
		return unique;
	}
	public boolean setActualEpoque(int i){
		if(i > epoques.size() & i<0)return false;
		actualEpoque = epoques.get(i);
		return true;
	}
	public boolean setModel(Model modele) {
		if (modele == null) return false;
		this.modele = modele;
		return true;
	}

	public ArrayList<Position> play(Position p, int id){
		assert(p!=null & (id==ORDI | id ==JOUEUR)):"Epoque manager : erreur play() id ou pos";
		ArrayList<Position> pos = new ArrayList<Position>();
		Bateau b;
		for(int i=0;i<5;i++){
			if(id==ORDI){//si ordi tape joueur
				b = actualEpoque.getBateauJoueur(i);
			}else{
				b = actualEpoque.getBateauOrdi(i);
			}
			if(b.checkPosition(p)){
				if(b.getResistance()<2){
					for(int j=0; j<b.getTaille();j++){
						pos.add(b.getPosition(j));
					}
					b.setDead(0);
					return pos;
				}else{
					b.setResistance(b.getResistance()-1);
					pos.add(p);
					return pos;
				}
			}	
		}

		return pos;
	}


}
