package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * classe de création des bateaux et de leurs caractéristiques.
 * @author steve
 * @version 1.0
 *
 */
public abstract class Bateau extends Observable{
	
	private int resistance;
	private String nom;
	private boolean isComputer;
	private int taille;
	private String apparence;
	private Epoque epoque;
	protected List<Position> positions;
	private boolean isPlaced = false;
	private int resInit;
	private boolean isDead = false;
	
	public Bateau(int res, String name, boolean computer, String image, Epoque ep){
		resistance = res;
		resInit = res;
		nom = name;
		isComputer = computer;
		taille = 0;
		apparence = image;
		epoque = ep;
		positions = new ArrayList<Position>();
	}
	public Bateau(){}
	/*--------------------GETTEUR---------------------*/
	public int getResistance() {return resistance;}
	public String getNom() {return nom;}
	public boolean isComputer() {return isComputer;}
	public int getTaille() {return taille;}
	public String getApparence() {return apparence;}
	public Epoque getEpoque() {return epoque;}
	public Position getPosition(int i){return positions.get(i);}
	
	/*--------------------SETTEUR---------------------*/
	public boolean setResistance(int resistance) {
		if(resistance < 1 & resistance > getTaille())return false;
		this.resistance = resistance;
		setChanged();
		notifyObservers();
		return true;
	}
	public void setResInit(int res){
		this.resInit = res;
		this.resistance = res;
		setChanged();
		notifyObservers();
	}
	public boolean setNom(String nom) {
		this.nom = nom;
		return nom.isEmpty();
	}
	public boolean setComputer(boolean isComputer) {
		this.isComputer = isComputer;
		return true;
	}

	public void setTaille(int t){
		this.taille = t;
	}
	public boolean setApparence(String apparence) {
		if(apparence.isEmpty())return false;
		this.apparence = apparence;
		return true;
	}
	public boolean setEpoque(Epoque epoque) {
		if(epoque == null)return false;
		this.epoque = epoque;
		return true;
	}
	
	public void setPosition(int indice, int x, int y){
		assert(indice < positions.size() & indice >= 0):"Cases , erreur d'assignation de position indice trop grand ou trop petit";
		positions.add(new Position(x,y));
		isPlaced=true;
	}
	public void setDead(int i) {
		this.setResistance(0);
	}
	/**
	 * vérifie si le bateau est sur la position p
	 * @param p position a trouver 
	 * @return true si le bateau a une de ses position egales a p , false sinon
	 */
	public boolean checkPosition(Position p) {
		if(isPlaced()){
			for(int i=0;i<taille;i++){
				if(this.getPosition(i).isEqual(p))return true; 
			}
		}
		return false;
	}
	/**
	 * fonction de vérification , vérifie si  le bateau a été placé
	 * @return true s'il a été placé, false sinon
	 */
	public boolean isPlaced(){
		return isPlaced;
	}
	/**
	 * remet a son état initial un bateau
	 */
	public void reset(){
		isPlaced=false;
		this.setResistance(resInit);
		positions = new ArrayList<Position>();
		this.isDead = false;
	}
	/**
	 * frappe le bateau, réduit de 1 sa resistance
	 */
	public void hit(){
		this.setResistance(this.resistance-1);
		if(resistance==0)this.isDead = true;
	}
	/**
	 * verifie que le bateau est mort
	 * @return true si le bateau est mort (resistance =0) , false sinon
	 */
	public boolean isDead(){
		return isDead;
	}
	
	public String toString(){
		return "nom bateau : "+this.nom+"|| apparence : "+ this.apparence+"|| resistance : "+this.resistance+"|| taille : "+this.taille;
	}
}