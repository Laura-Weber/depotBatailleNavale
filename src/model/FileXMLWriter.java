package model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.sun.xml.internal.stream.writers.XMLWriter;

public class FileXMLWriter{
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	final String configFile = "config.xml";

	public FileXMLWriter(){}
	
	/**
	 * Fonction permettant d'ecrire une nouvelle époque dans le fichier XML
	 * @param name nom de l'epoque
	 * @param im apparence de l'epoque
	 * @param nom5 nom du bateau de taille 5
	 * @param nom4 nom du bateau de taille 4
	 * @param nom3 nom du bateau de taille 3
	 * @param nom2 nom du bateau de taille 3
	 * @param res5 resistance du bateau de taille 5
	 * @param res4 resistance du bateau de taille 4
	 * @param res3 resistance du bateau de taille 3
	 * @param res2 resistance du bateau de taille 2
	 */
	public void addEpoque(String name, String im, String nom5, String nom4, String nom3, String nom2, String res5, String res4, String res3, String res2){
		try {
			/*
			 * Etape 2 : création d'un parseur
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			/*
			 * Etape 3 : création d'un Document
			 */
			File file = new File(this.configFile);
			final Document document= builder.parse(file);
			
			Element epoqueTmp = document.createElement("epoque");
			Element nom = document.createElement("nom"); nom.setTextContent(name);
			Element image = document.createElement("image"); image.setTextContent(im);
			Element bateaux = document.createElement("bateaux");
			
			Element five = document.createElement("five");
			Element nomFive = document.createElement("nom");nomFive.setTextContent(nom5);
			Element resistanceFive = document.createElement("resistance");resistanceFive.setTextContent(res5);
			
			Element four = document.createElement("four");
			Element nomFour = document.createElement("nom");nomFour.setTextContent(nom4);
			Element resistanceFour = document.createElement("resistance");resistanceFour.setTextContent(res4);
			
			Element three = document.createElement("three");
			Element nomThree = document.createElement("nom");nomThree.setTextContent(nom3);
			Element resistanceThree = document.createElement("resistance");resistanceThree.setTextContent(res3);
			
			Element threebis = document.createElement("threebis");
			Element nomThreebis = document.createElement("nom");nomThreebis.setTextContent(nom3);
			Element resistanceThreebis = document.createElement("resistance");resistanceThreebis.setTextContent(res3);
			
			Element two = document.createElement("two");
			Element nomTwo = document.createElement("nom");nomTwo.setTextContent(nom2);
			Element resistanceTwo = document.createElement("resistance");resistanceTwo.setTextContent(res2);
			
			five.appendChild(nomFive);five.appendChild(resistanceFive);
			four.appendChild(nomFour);four.appendChild(resistanceFour);
			three.appendChild(nomThree);three.appendChild(resistanceThree);
			threebis.appendChild(nomThreebis);threebis.appendChild(resistanceThreebis);
			two.appendChild(nomTwo);two.appendChild(resistanceTwo);
			bateaux.appendChild(five);
			bateaux.appendChild(four);
			bateaux.appendChild(three);
			bateaux.appendChild(threebis);
			bateaux.appendChild(two);
			
			epoqueTmp.appendChild(nom);
			epoqueTmp.appendChild(image);
			epoqueTmp.appendChild(bateaux);			
			/*
			 * Etape 4 : récupération de l'Element racine
			 */
			final Element racine = document.getDocumentElement();

			/*
			 * Etape 5 : récupération des différents parametres
			 */
			final NodeList racineNoeuds = racine.getChildNodes();

			final Node noeudEpoques = racineNoeuds.item(1);//noeud Epoques
			//final NodeList epoques = noeudEpoques.getChildNodes();
			noeudEpoques.appendChild(epoqueTmp);
			// write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult resultat = new StreamResult(file);
	        transformer.transform(source, resultat);
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Fonction permettant la sauvegarde de la partie en cours
	 * @param ep nom de l'epoque utilisé lors de la partie
	 * @param boardPlayer board du joueur 
	 * @param boardComputer board de l'ordinateur
	 * @param isComputerTurn boolean qui dit si c'est le tour de l'ordi
	 * @param winPlayer nombre de touché pour le joueur
	 * @param failPlayer nombre de non-touché pour le joueur
	 * @param winComputer nombre de touché pour l'ordi
	 * @param failComputer nombre de non-touché pour l'ordi
	 * @param diff diffculté lors de la partie (0,1,2)
	 */
	public void Save(Epoque ep, int[][] boardPlayer, int[][] boardComputer, boolean isComputerTurn, int winPlayer ,int failPlayer, int winComputer, int failComputer, int diff){
		try {
			/*
			 * Etape 2 : création d'un parseur
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			/*
			 * Etape 3 : création d'un Document
			 */
			File file = new File(this.configFile);
			final Document document= builder.parse(file);
			
			/*
			 * Etape 4 : récupération de l'Element racine
			 */
			final Element racine = document.getDocumentElement();

			/*
			 * Etape 5 : récupération des différents parametres
			 */
			final NodeList racineNoeuds 	= racine.getChildNodes();
			final NodeList sauvegarde 		= racineNoeuds.item(3).getChildNodes();
			
			final Node computerTurn 		= sauvegarde.item(1);
			final Node nomepoque 			= sauvegarde.item(3);
			final Node difficulte 			= sauvegarde.item(5);
			final NodeList player 			= sauvegarde.item(7).getChildNodes();
			final NodeList computer 		= sauvegarde.item(9).getChildNodes();
			final NodeList scorePlayer 		= player.item(1).getChildNodes();
			final NodeList scoreComputer	= computer.item(1).getChildNodes();
			final Node reussiPlayer 		= scorePlayer.item(1);
			final Node ratePlayer 			= scorePlayer.item(3);
			final Node reussiComputer 		= scoreComputer.item(1);
			final Node rateComputer 		= scoreComputer.item(3);
			final NodeList bateauxPlayer 	= player.item(3).getChildNodes();
			final NodeList bateauxComputer 	= computer.item(3).getChildNodes();
			final NodeList plateauPlayer 	= player.item(5).getChildNodes();
			final NodeList plateauComputer 	= computer.item(5).getChildNodes();
			
			/*---------------------Ecriture computerTurn / nomepoque / difficulte ------------*/
			if(isComputerTurn)computerTurn.setTextContent("1");
			else computerTurn.setTextContent("0");
			nomepoque.setTextContent(ep.getName());
			difficulte.setTextContent(Integer.toString(diff));			
			/*-----------fin-----------------*/
			
			/*---------------ecriture scores-----------------*/
			reussiPlayer.setTextContent(Integer.toString(winPlayer));
			reussiComputer.setTextContent(Integer.toString(winComputer));
			ratePlayer.setTextContent(Integer.toString(failPlayer));
			rateComputer.setTextContent(Integer.toString(failComputer));
			/*--------------fin---------------*/
			
			
			/*------ Partie écriture balise resistances et positions -----------------*/
			NodeList bateauPlayer, bateauComputer, position;
			Node resistance,x,y;
			int k=0;
			int l=0;
			for(int i=1; i<bateauxPlayer.getLength();i=i+2){
				bateauPlayer = bateauxPlayer.item(i).getChildNodes();
				resistance = bateauPlayer.item(1);
				resistance.setTextContent(Integer.toString(ep.getBateauJoueur(4-k).getResistance()));
				l=0;
				for(int j=3;j<bateauPlayer.getLength();j=j+2){
					position = bateauPlayer.item(j).getChildNodes();
					x = position.item(0);
					y = position.item(1);
					x.setTextContent(Integer.toString(ep.getBateauJoueur(k).getPosition(l).getX()));
					y.setTextContent(Integer.toString(ep.getBateauJoueur(k).getPosition(l).getY()));
					l++;
				}
				
				bateauComputer = bateauxComputer.item(i).getChildNodes();
				resistance = bateauComputer.item(1);
				resistance.setTextContent(Integer.toString(ep.getBateauOrdi(4-k).getResistance()));
				l=0;
				for(int j=3;j<bateauComputer.getLength();j=j+2){
					position = bateauComputer.item(j).getChildNodes();
					x = position.item(0);
					y = position.item(1);
					x.setTextContent(Integer.toString(ep.getBateauOrdi(k).getPosition(l).getX()));
					y.setTextContent(Integer.toString(ep.getBateauOrdi(k).getPosition(l).getY()));
					l++;
				}
				k++;
			}
			/*--------------fin ecriture position et resistance ----------------*/
			
			/* --------------------- Ecriture des boards -----------------------------*/
			l=0;
			Node casePlayer, caseComputer;
			
			for(int i=0; i<10; i++){
				for(int j=0; j<10;j++){
					casePlayer = plateauPlayer.item(l);
					caseComputer = plateauComputer.item(l);
					casePlayer.setTextContent(Integer.toString(boardPlayer[j][i]));
					caseComputer.setTextContent(Integer.toString(boardComputer[j][i]));
					l++;
				}
			}
			/* -----------------------fin----------------------- */			
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult resultat = new StreamResult(file);
	        transformer.transform(source, resultat);
			
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
	}

}