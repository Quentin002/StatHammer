package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Figurine {
	ArrayList<Arme> armes;
	ArrayList<Aptitude> aptidudes;
	String nom;
	String logoChemin;
	int M;
	int E;
	int SV;
	int HP;
	int CD;
	int CO;
	public Figurine(ArrayList<Arme> armes, ArrayList<Aptitude> aptidudes, String nom, String logoChemin, int m, int e, int sV, int hP, int cD, int cO) {
		super();
		this.armes = armes;
		this.aptidudes = aptidudes;
		this.nom = nom;
		this.logoChemin = logoChemin;
		M = m;
		E = e;
		SV = sV;
		HP = hP;
		CD = cD;
		CO = cO;
	}
	
	//renvoi un dico avec en clé les noms des stats des armes et en valeurs une liste de valeur de toute les armes 
	public HashMap<String,ArrayList<Integer>> statAtk(){
		HashMap<String,ArrayList<Integer>> liste = new HashMap<String, ArrayList<Integer>>();
		//Ajouter du code a prendre depuis arme
		return liste;
	}
	public void updateHP(int dommage) {
		this.HP +=dommage;
	}
	public ArrayList<Arme> defense(){
		ArrayList<Arme> liste = new ArrayList<Arme>();
		return liste;
	}
	
}

