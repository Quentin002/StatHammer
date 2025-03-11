package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Figurine {
	ArrayList<Arme> armes;
	Arme selected_weapon;
	
	ArrayList<Aptitude> aptitudes;
	HashMap<String, Aptitude> selectedAptitudes = new HashMap<String, Aptitude>();
	
	String nom;
	String logoChemin;
	String M;
	int E;
	int SV;
	int HP;
	int HP_max;
	int CD;
	int CO;
	
	public Figurine(ArrayList<Arme> armes, ArrayList<Aptitude> aptitudes, String nom, String logoChemin, String m, int e, int sV, int hP, int cD, int cO) {
		//super(); // inutile, Figurine n'a pas de parent
		
		this.armes = armes;
		if(armes.size() > 0) {
			selected_weapon = armes.get(0); // arme par défaut
		}
		this.aptitudes = aptitudes;
		
		this.nom = nom;
		this.logoChemin = logoChemin;
		M = m;
		E = e;
		SV = sV;
		HP = hP;
		HP_max = hP;
		CD = cD;
		CO = cO;
	}
	
	public Figurine(String nom_figurine, int E, int SV, int HP,ArrayList<Arme> l1) {
		this.nom = nom_figurine;
		this.E = E;
		this.SV = SV;
		this.HP = HP;
		this.armes = l1;
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
//	public void updateHP(int dommage) {
//		this.HP +=dommage;
//	}
	public HashMap<String,Integer> defense(){
		HashMap<String,Integer> liste = new HashMap<String,Integer>();
		liste.put("E", E);
		liste.put("SV", SV);
		return liste;
	}
	

	public ArrayList<Arme> getArmes() {
		return armes;
	}
	public Arme getWeaponByName(String name) {
		for(Arme weapon : armes) {
			if(weapon.getNom() == name) {
				return weapon;
			}
		}
		return armes.get(0); // arme par défaut si aucune ne correspond
	}
	public Arme getSelectedWeapon() {
		return selected_weapon;
	}
	public void setWeapon(Arme weapon) {
		selected_weapon = weapon;
	}
	
	public ArrayList<Aptitude> getAptitudes() {
		return aptitudes;
	}
	public HashMap<String, Aptitude> getSelectedAptitudes() {
		return selectedAptitudes;
	}
//	public void setSelectedAptitudes(HashMap<String, Aptitude> aptitudes) {
//		selectedAptitudes = aptitudes;
//	}

	public String getNom() {
		return nom;
	}

	public String getLogoChemin() {
		return logoChemin;
	}

	public String getM() {
		return M;
	}

	public int getE() {
		return E;
	}

	public int getSV() {
		return SV;
	}

	public int getHP() {
		return HP;
	}
	public int getHPMax() {
		return HP_max;
	}

	public int getCD() {
		return CD;
	}

	public int getCO() {
		return CO;
	}
	

	public void setHP(int hP) {
		HP = hP;
	}

	@Override
	public String toString() {
		return "" + nom + "";
	}
	
	
}