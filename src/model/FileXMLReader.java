package model;

import java.util.ArrayList;

public class FileXMLReader{
	public ArrayList<ArrayList<String>> epoques;
	
	
	public FileXMLReader(){
		
	}
	
	public void init(){// va récuperer le fichier XML dans le dossier et le traiter ...
		epoques = new ArrayList<ArrayList<String>>();
	}

	public int getNBEpoque() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<String> getEpoque(int i){
		assert(i<epoques.size()):"erreur accés epoques XMLREADER";
		return epoques.get(i);
	}
	
}