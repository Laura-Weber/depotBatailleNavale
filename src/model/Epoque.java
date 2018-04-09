package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe caractéristique d'une époque
 * @author steve
 * @version 1.0
 *
 */
public class Epoque {
	
	private String nom;
	private List<Bateau> flotteJoueur;
	private List<Bateau> flotteOrdi;
	private String apparence;
	private EpoqueManager em;
	public static String ORDI = "ordi";
	public static String JOUEUR = "joueur";
	
	public Epoque(){
		this.em=EpoqueManager.getInstance();
		flotteJoueur = new ArrayList<Bateau>();
		flotteOrdi = new ArrayList<Bateau>();
		flotteJoueur.add(new TwoCases(0, nom, false, 0, nom, this));
		flotteJoueur.add(new ThreeCases(0, nom, false, 0, nom, this));
		flotteJoueur.add(new ThreeCasesBis(0, nom, false, 0, nom, this));
		flotteJoueur.add(new FourCases(0, nom, false, 0, nom, this));
		flotteJoueur.add(new FiveCases(0, nom, false, 0, nom, this));
		flotteOrdi.add(new TwoCases(0, nom, false, 0, nom, this));
		flotteOrdi.add(new ThreeCases(0, nom, false, 0, nom, this));
		flotteOrdi.add(new ThreeCasesBis(0, nom, false, 0, nom, this));
		flotteOrdi.add(new FourCases(0, nom, false, 0, nom, this));
		flotteOrdi.add(new FiveCases(0, nom, false, 0, nom, this));
		flotteOrdi.get(0).setComputer(true);
		flotteOrdi.get(1).setComputer(true);
		flotteOrdi.get(2).setComputer(true);
		flotteOrdi.get(3).setComputer(true);
		flotteOrdi.get(4).setComputer(true);
		flotteJoueur.get(0).setComputer(false);
		flotteJoueur.get(1).setComputer(false);
		flotteJoueur.get(2).setComputer(false);
		flotteJoueur.get(3).setComputer(false);
		flotteJoueur.get(4).setComputer(false);
	}
	
	/*-------------GETTEUR--------------*/
	public String getName(){return nom;}
	
	public Position getPositionJoueur(int indice){
		switch(indice){
		case 0: case 1: return flotteJoueur.get(0).getPosition(indice);
		case 2: case 3: case 4: return flotteJoueur.get(1).getPosition(indice-2);
		case 5: case 6: case 7: return flotteJoueur.get(2).getPosition(indice-5);
		case 8: case 9: case 10: case 11: return flotteJoueur.get(3).getPosition(indice-8);
		case 12: case 13: case 14: case 15: case 16: return flotteJoueur.get(4).getPosition(indice-12);
		}
		return null;
	}
	public Position getPositionOrdi(int indice){
		switch(indice){
		case 0: case 1: return flotteOrdi.get(0).getPosition(indice);
		case 2: case 3: case 4: return flotteOrdi.get(1).getPosition(indice-2);
		case 5: case 6: case 7: return flotteOrdi.get(2).getPosition(indice-5);
		case 8: case 9: case 10: case 11: return flotteOrdi.get(3).getPosition(indice-8);
		case 12: case 13: case 14: case 15: case 16: return flotteOrdi.get(4).getPosition(indice-12);
		}
		return null;
	}
	public Bateau getBateauJoueur(int i){
		assert(i>=0 & i<5):"erreur epoque getBateau";
		return flotteJoueur.get(i);
	}
	public Bateau getBateauOrdi(int i){
		assert(i>=0 & i<5):"erreur epoque getBateau";
		return flotteOrdi.get(i);
	}
	
	
	
	/*-------------SETTEUR--------------*/
	
	public boolean setName(String name){
		if(name.isEmpty())return false;
		nom = name;
		return true;
	}
	
	public boolean setApparence(String app){
		if(app.isEmpty() | app == null)return false;
		this.apparence = app;		
		return true;
	}
	
	public boolean setResistanceBateau(String personnage, int indice,int res){
		if(indice<0 | indice>4 | personnage.isEmpty() | personnage == null) return false;
		switch(personnage){
		case "joueur" : flotteJoueur.get(indice).setResistance(res); break;
		case "ordi" : flotteOrdi.get(indice).setResistance(res); break;
		default:break;
		}
		return true;
	}
	public boolean setApparenceBateau(int indice,String image){
		if(indice<0 | indice>4 | image.isEmpty() | image ==null) return false;
		flotteJoueur.get(indice).setApparence(image);
		flotteOrdi.get(indice).setApparence(image);
		return true;
	}
	public boolean setAppartenance(String personnage, int indice,boolean isComputer){
		if(indice<0 | indice>4 | personnage.isEmpty() | personnage == null) return false;
		switch(personnage){
		case "joueur" : flotteJoueur.get(indice).setComputer(isComputer); break;
		case "ordi" : flotteOrdi.get(indice).setComputer(isComputer); break;
		default:break;
		}
		return true;
	}
	
	public boolean setNomBateau(int indice, String nom){
		if(indice<0 | indice>4 | nom.isEmpty() | nom ==null ) return false;
		flotteJoueur.get(indice).setNom(nom); 
		flotteOrdi.get(indice).setNom(nom);
		return true;
		
	}

	public String toString(){
		return "nom de l'epoque : "+this.getName() + "\n info du bateau 2 du joueur:" +this.getBateauJoueur(0).toString() ; 
	}
}
