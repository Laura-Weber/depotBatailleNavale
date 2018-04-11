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
	public boolean setActualEpoque(String name){
		for(int i=0;i<epoques.size();i++){
			if(epoques.get(i).getName().equals(name)){
				return setActualEpoque(i);
			}
		}
		return false;
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
	public boolean addEpoque(String nom, 
			String apparence, 
			int resistanceBateau, 
			String apparenceBateau2, 
			String apparenceBateau3, 
			String apparenceBateau3Bis, 
			String apparenceBateau4, 
			String apparenceBateau5,
			String nomBateau2,
			String nomBateau3,
			String nomBateau3Bis,
			String nomBateau4,
			String nomBateau5){
		//créer l'epoque ici
		//et dit au XMLWriter de créer le fichier.
		return false;
	}

	public ArrayList<String> getAllNameOfEpoques(){
		ArrayList<String> name = new ArrayList<String>();
		for(int i=0;i<epoques.size();i++){
			name.add(epoques.get(i).getName());
		}
		return name;
	}

	public ArrayList<String> getInfoActualEpoque(){
		ArrayList<String> infos = new ArrayList<String>();
		infos.add(actualEpoque.getApparence());
		for(int i=0;i<5;i++){
			infos.add(actualEpoque.getBateauJoueur(i).getApparence());
		}
		return infos;
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
	public Epoque getEpoque(int i){
		return this.epoques.get(i);
	}
	public Epoque getActualEpoque(){
		return this.actualEpoque;
	}


	public void createDefaultEpoque(){
		Epoque ep1 = new Epoque(); 
		epoques.add(ep1);
		ep1.setName("default");
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
		actualEpoque=ep1;
	}
	
	public void initActualEpoque(){
		
	}
	
	public boolean init() {//initialise l'epoque actuelle
		if(actualEpoque==null){
			this.createDefaultEpoque();
			return true;
		}else{
			this.initActualEpoque();
			return true;
		}
	}

}

