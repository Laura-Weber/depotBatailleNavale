package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EpoqueManager {	

	/**
	 * Cette classe est un singleton
	 */
	private volatile static EpoqueManager unique = null;
	private FileXMLReader XMLr;
	private FileXMLWriter XMLw;
	private Model modele;
	private Epoque actualEpoque;
	private List<Epoque> epoques;
	private static int JOUEUR = 1;
	private static int ORDI = 0;

	private EpoqueManager() {
		epoques = new ArrayList<Epoque>();
		XMLr = new FileXMLReader();
		XMLw = new FileXMLWriter();
		readAllEpoque();
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


	public void readAllEpoque(){
		this.epoques = new ArrayList<Epoque>();
		XMLr.init();
		Epoque epTmp;
		for(int i=0; i<XMLr.getNBEpoque();i++){
			epTmp = new Epoque();
			epoques.add(epTmp);
			//epTmp.set ......
		}
	}
	public boolean addEpoque(HashMap param){
		//créer l'epoque ici
		//et dit au XMLWriter de créer le fichier.
		return false;
	}
	
	public boolean removeEpoque(String name){
		//get l'id associé au nom et lance un removeEpoque via l'id
		return false;
	}
	public boolean removeEpoque(int id){
		//remove ici 
		// et remove le fichier .xml aussi
		return false;
	}
	public int getNBEpoque(){
		return epoques.size();
	}
	public Epoque getActualEpoque(){
		return this.actualEpoque;
	}
	
	
	
	public static void main(String[] args){
		
		Epoque ep1 = new Epoque(); 
		
		ep1.setName("pirate");
		ep1.setApparence("./ep1.jpg");
		
		ep1.setApparenceBateau(0, "b1.png");
		ep1.setApparenceBateau(1, "b1.png");
		ep1.setApparenceBateau(2, "b1.png");
		ep1.setApparenceBateau(3, "b1.png");
		ep1.setApparenceBateau(4, "b1.png");
		
		ep1.setNomBateau(0, "bateau0");
		ep1.setNomBateau(1, "bateau1");
		ep1.setNomBateau(2, "bateau2");
		ep1.setNomBateau(3, "bateau3");
		ep1.setNomBateau(4, "bateau4");
		
		ep1.setResistanceBateau(ep1.JOUEUR, 0, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 1, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 2, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 3, 2);
		ep1.setResistanceBateau(ep1.JOUEUR, 4, 2);
		ep1.setResistanceBateau(ep1.ORDI, 0, 2);
		ep1.setResistanceBateau(ep1.ORDI, 1, 2);
		ep1.setResistanceBateau(ep1.ORDI, 2, 2);
		ep1.setResistanceBateau(ep1.ORDI, 3, 2);
		ep1.setResistanceBateau(ep1.ORDI, 4, 2);
		System.out.println(ep1.toString());
		
	}
	
}

