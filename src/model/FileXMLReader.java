package model;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

public class FileXMLReader{
	public ArrayList<ArrayList<String>> epoques;
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	final String configFile = "config.xml";
	int nbEpoque = 1;
	static int X = 0;
	static int Y = 1;
	static int RES = 0; 
	static int NOM = 1;
	static int IM = 0;
	static int BATEAU = 2;
	String[][][][] epoquesTab;
	String[][][] bateauxPlayer = new String[5][6][2];
	String[][][] bateauxComputer = new String[5][6][2];
	int[][] boardP = new int[10][10];
	int[][] boardC = new int[10][10];
	String tourOrdi; 	 
	String nomEpoque; 	
	String difficulte; 	
	String reussiPlayer; 	
	String ratePlayer;	
	String reussiComputer; 	
	String rateComputer;	



	public FileXMLReader(){}
	/*getter lié aux époques*/
	public int getNBEpoque() {
		return nbEpoque;
	}
	public String getNomEpoque(int i){
		return epoquesTab[i][NOM][NOM][NOM];
	}
	public String getNomBateau(int epoque, int bateau){
		return epoquesTab[epoque][BATEAU][bateau][NOM];
	}
	public String getApparenceEpoque(int i){
		return epoquesTab[i][IM][IM][IM];
	}
	public int getResistanceBateau(int ep, int bateau){
		return Integer.parseInt(epoquesTab[ep][BATEAU][bateau][RES]);
	}
	/*fin des getters epoques*/

	/*getters liés a la sauvegarde*/

	public String getDifficulty(){return difficulte;}
	public String getreussiPlayer(){return reussiPlayer;}
	public String getratePlayer(){return ratePlayer;}
	public String getreussiComputer(){return reussiComputer;}
	public String getrateComputer(){return rateComputer;}
	public String getNomEpoque(){return nomEpoque;}
	public String getTourOrdi(){return tourOrdi;}
	public int[][] getBoardPlayer(){return boardP;}
	public int[][] getBoardComputer(){return boardC;}

	public List<Position> getPositionBateauPlayer(int i){
		List<Position> tmp = new ArrayList<Position>();
		int j=1;
		while(!bateauxPlayer[i][j][0].isEmpty() & j<6){
			tmp.add(new Position(Integer.parseInt(bateauxPlayer[i][j][0]),Integer.parseInt(bateauxPlayer[i][j][1])));
			j++;
		}
		return tmp;
	}

	public List<Position> getPositionBateauComputer(int i){
		List<Position> tmp = new ArrayList<Position>();
		int j=1;
		while(!bateauxPlayer[i][j][0].isEmpty() & j<6){
			tmp.add(new Position(Integer.parseInt(bateauxComputer[i][j][0]),Integer.parseInt(bateauxComputer[i][j][1])));
			j++;
		}
		return tmp;
	}
	public int getResistanceBateauPlayer(int i){return Integer.parseInt(bateauxPlayer[i][0][0]);}
	public int getResistanceBateauComputer(int i){return Integer.parseInt(bateauxComputer[i][0][0]);}

	/*fin getters de la sauvegarde*/


	public void readConfigFile(){
		try {
			/*
			 * Etape 2 : création d'un parseur
			 */
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
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			final Node noeudEpoques = racineNoeuds.item(1);//noeud Epoques
			final Node noeudSauvegarde = racineNoeuds.item(3);//noeud Sauvegarde
			final NodeList epoques = noeudEpoques.getChildNodes();
			final NodeList sauvegarde = noeudSauvegarde.getChildNodes();

			/*------------------PARTIE DE LECTURE DES EPOQUES--------------------*/
			NodeList epoque ;
			Node five,four,three,threebis,two;
			int tmp=0;
			for(int i=0;i<epoques.getLength();i=i+1){
				tmp=tmp+1;
			}
			epoquesTab = new String[tmp][3][5][2];
			nbEpoque = tmp;
			tmp=0;
			for (int i = 0 ; i<epoques.getLength();i=i+1){//pour chaque epoque
				epoque = epoques.item(i).getChildNodes();
				epoquesTab[tmp][NOM][NOM][NOM] = epoque.item(0).getTextContent();//nom époque
				epoquesTab[tmp][IM][IM][IM] = epoque.item(1).getTextContent(); // url image

				NodeList bateaux = epoque.item(2).getChildNodes(); // recuperation des enfants bateaux

				five = bateaux.item(0);//five
				epoquesTab[tmp][BATEAU][0][NOM] = five.getChildNodes().item(0).getTextContent();//nom bateau
				epoquesTab[tmp][BATEAU][0][RES] = five.getChildNodes().item(1).getTextContent();//resistance

				four = bateaux.item(1);//four
				epoquesTab[tmp][BATEAU][1][NOM] = four.getChildNodes().item(0).getTextContent();//nom bateau
				epoquesTab[tmp][BATEAU][1][RES] = four.getChildNodes().item(1).getTextContent();//resistance

				three = bateaux.item(2);//three
				epoquesTab[tmp][BATEAU][2][NOM] = three.getChildNodes().item(0).getTextContent();//nom bateau
				epoquesTab[tmp][BATEAU][2][RES] = three.getChildNodes().item(1).getTextContent();//resistance

				threebis = bateaux.item(3);//threebis
				epoquesTab[tmp][BATEAU][3][NOM] = threebis.getChildNodes().item(0).getTextContent();//nom bateau
				epoquesTab[tmp][BATEAU][3][RES] = threebis.getChildNodes().item(1).getTextContent();//resistance

				two = bateaux.item(4);//two
				epoquesTab[tmp][BATEAU][4][NOM] = two.getChildNodes().item(0).getTextContent();//nom bateau
				epoquesTab[tmp][BATEAU][4][RES] = two.getChildNodes().item(1).getTextContent();//resistance
				tmp=tmp+1;
				//sauvegarde des données a faire plus tard

			}

			/*------------------------FIN LECTURE EPOQUE-------------------------*/


			/*----------------------DEBUT LECTURE SAUVEGARDE JOUEUR------------------*/
			tourOrdi 	= sauvegarde.item(1).getTextContent(); 
			nomEpoque 	= sauvegarde.item(3).getTextContent();
			difficulte 	= sauvegarde.item(5).getTextContent();

			NodeList player, computer, scorePlayer, scoreComputer;
			player = sauvegarde.item(7).getChildNodes();
			computer = sauvegarde.item(9).getChildNodes();

			/*les scores*/
			scorePlayer 			= player.item(1).getChildNodes();
			scoreComputer 			= computer.item(1).getChildNodes();
			reussiPlayer 	= scorePlayer.item(1).getTextContent();
			ratePlayer 		= scorePlayer.item(3).getTextContent();
			reussiComputer 	= scoreComputer.item(1).getTextContent();
			rateComputer 	= scoreComputer.item(3).getTextContent();
			/*fin scores*/

			/*  les bateaux */
			NodeList bateauxPlayer 		= player.item(3).getChildNodes();
			NodeList bateauxComputer 	= computer.item(3).getChildNodes();
			int j=0;
			int d = 1;
			for(int i = 1 ;i<bateauxPlayer.getLength();i=i+2){
				this.bateauxPlayer[j][RES][RES] = bateauxPlayer.item(i).getChildNodes().item(1).getTextContent();
				this.bateauxComputer[j][RES][RES] = bateauxComputer.item(i).getChildNodes().item(1).getTextContent();
				d=1;
				for(int k=3;k<bateauxPlayer.item(i).getChildNodes().getLength();k=k+2){
					this.bateauxPlayer[j][d][X] = bateauxPlayer.item(i).getChildNodes().item(k).getChildNodes().item(0).getTextContent();//pos X 
					this.bateauxPlayer[j][d][Y] = bateauxPlayer.item(i).getChildNodes().item(k).getChildNodes().item(1).getTextContent();//pos Y
					this.bateauxComputer[j][d][X] = bateauxComputer.item(i).getChildNodes().item(k).getChildNodes().item(0).getTextContent();//pos X 
					this.bateauxComputer[j][d][Y] = bateauxComputer.item(i).getChildNodes().item(k).getChildNodes().item(1).getTextContent();//pos Y					
					d++;
				}				
				j++;
			}
			/* fin des bateaux */

			/* les boards */
			NodeList boardPlayer 	= player.item(5).getChildNodes();
			NodeList boardComputer	= computer.item(5).getChildNodes();
			int t = 0;
			int x = 0;
			int y = 0;
			for(int i=1; i<boardPlayer.getLength()-1;i++){
				if(boardPlayer.item(i).getTextContent().isEmpty())boardP[x][y]=0;
				else boardP[x][y] = Integer.parseInt(boardPlayer.item(i).getTextContent());
				if(boardComputer.item(i).getTextContent().isEmpty()) boardC[x][y] = 0;
				else boardC[x][y] = Integer.parseInt(boardComputer.item(i).getTextContent());
				x++;
				if(x==10){
					x=0;
					y++;
				}
			}		
			/* fin des boards*/
			/*-----------------------FIN LECTURE SAUVEGARDE JOUEUR---------------------*/
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}		

	}


}
