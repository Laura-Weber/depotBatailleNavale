package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Classe caractéristique d'une époque
 * @author steve
 * @version 1.0
 *
 */
public class Epoque extends Observable{
	
	private String nom;
	private List<Bateau> flotteJoueur;
	private List<Bateau> flotteOrdi;
	private String apparence;
	public static String ORDI = "ordi";
	public static String JOUEUR = "joueur";
	
	public Epoque(){
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
	
	public String getApparence(){
		return this.apparence;
	}
	
	/*-------------SETTEUR--------------*/
	
	public boolean setName(String name){
		if(name == null)return false;
		nom = name;
		return true;
	}
	
	public boolean setApparence(String app){
		if(app == null)return false;
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
		setChanged();
		notifyObservers();
		return true;
	}
	
	public boolean setResistanceInitBateau(String personnage, int indice,int res){
		if(indice<0 | indice>4 | personnage.isEmpty() | personnage == null) return false;
		switch(personnage){
		case "joueur" : flotteJoueur.get(indice).setResInit(res); break;
		case "ordi" : flotteOrdi.get(indice).setResInit(res); break;
		default:break;
		}
		setChanged();
		notifyObservers();
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
		if( nom ==null && indice<0 && indice>4) return false;
		flotteJoueur.get(indice).setNom(nom); 
		flotteOrdi.get(indice).setNom(nom);
		setChanged();
		notifyObservers();
		return true;
		
	}
	
	/*fin setter*/
	
	/*----placements des bateaux -----*/
	/**
	 * 
	 * @param type
	 * @param orient  0 : horizontale, 1 : verticale
	 * @param pos
	 */
	public boolean placementHuman(int type, int orient, Position pos){
		int bateau = 0;
		switch (type){
			case 2: bateau = 0;break;
			case 3: if(!this.flotteJoueur.get(1).isPlaced())bateau=1;else bateau=2; break;
			case 4: bateau = 3; break;
			case 5: bateau = 4; break;
		}
		boolean res = false;
		if(orient == 0){
			if(pos.getY() + type < Board.SIZE + 1){
				res = true;
				for(int i = 0; i < type; i++){
					this.flotteJoueur.get(bateau).setPosition(i, pos.getX(), pos.getY()+i);
				}
			}
		}else{
			if(pos.getX() + type < Board.SIZE + 1){
				res = true;
				for(int i = 0; i < type; i++){
					this.flotteJoueur.get(bateau).setPosition(i, pos.getX()+i, pos.getY());
				}
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param type
	 * @param orient 0 : horizontale, 1 : verticale
	 * @param pos position de la premiere case
	 */
	
	public boolean placementComputer(int type, int orient, Position pos){
		int bateau = 0;
		switch (type){
			case 2: bateau = 0;break;
			case 3: if(!this.flotteOrdi.get(1).isPlaced())bateau=1;else bateau=2; break;
			case 4: bateau = 3; break;
			case 5: bateau = 4; break;
		}
		boolean res = false;
		if(orient == 0){
			if(pos.getY() + type < Board.SIZE + 1){
				res = true;
				for(int i = 0; i < type; i++){
					this.flotteOrdi.get(bateau).setPosition(i, pos.getX(), pos.getY()+i);
				}
			}
		}else{
			if(pos.getX() + type < Board.SIZE + 1){
				res = true;
				for(int i = 0; i < type; i++){
					this.flotteOrdi.get(bateau).setPosition(i, pos.getX()+i, pos.getY());
				}
			}
		}
		return res;
	}
	
	
	public void resetPartie(){
		for(int i=0;i<5;i++){
			this.flotteJoueur.get(i).reset();
			this.flotteOrdi.get(i).reset();
		}
	}
	
	public void hitBoatPlayer(Position p){
		for( int i=0; i<this.flotteJoueur.size();i++){
			if(this.flotteJoueur.get(i).checkPosition(p)) this.flotteJoueur.get(i).hit();
		}
	}
	public void hitBoatComputer(Position p){
		for( int i=0; i<this.flotteOrdi.size();i++){
			if(this.flotteOrdi.get(i).checkPosition(p)) this.flotteOrdi.get(i).hit();
		}
	}
	
	
	
	
	

	public String toString(){
		return "nom de l'epoque : "+this.getName() + "\n info du bateau 2 du joueur:" +this.getBateauJoueur(0).toString() ; 
	}
}
