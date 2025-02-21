package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Figurine {
	ArrayList<Arme> armes;
	ArrayList<Aptitude> aptidudes;
	String nom;
	String logoChemin;
	String M;
	int E;
	int SV;
	int HP;
	int CD;
	int CO;
	public Figurine(ArrayList<Arme> armes, ArrayList<Aptitude> aptidudes, String nom, String logoChemin, String m, int e, int sV, int hP, int cD, int cO) {
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
		ArrayList<Integer> stats = new ArrayList<Integer>();
		
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> F = new ArrayList<Integer>();
		ArrayList<Integer> PA = new ArrayList<Integer>();
		ArrayList<Integer> D = new ArrayList<Integer>();
		
		for (Arme arme : armes) {
			stats = arme.statsAtk();
			A.add(stats.get(0));
			F.add(stats.get(1));
			PA.add(stats.get(2));
			D.add(stats.get(3));
		}
		liste.put("A", A);
		liste.put("F", F);
		liste.put("PA", PA);
		liste.put("D", D);
		
		return liste;
	}
	public void updateHP(int dommage) {
		this.HP +=dommage;
	}
	public HashMap<String,Integer> defense(){
		HashMap<String,Integer> liste = new HashMap<String,Integer>();
		liste.put("E", E);
		liste.put("SV", SV);
		return liste;
	}
	
}

