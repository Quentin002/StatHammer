package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Unit {
	
	private ArrayList<Figurine> figurines;
	HashMap<String, ArrayList<Figurine>> identical_figs = new HashMap<String, ArrayList<Figurine>>();
	HashMap<String, Integer> numbers_of_weapons = new HashMap<String, Integer>();
	private int id;
	private String nom;
	private int points;
	private String logo;
	private Armee armee;
	
	public Unit(ArrayList<Figurine> figurines, String nom, int points, String logo, Armee armee) {
		this.figurines = figurines;
		this.makeIdenticalFigsGroups();
		this.nom = nom;
		this.points = points;
		this.logo = logo;
		this.armee = armee;
	}
	
	// bientôt inutile
	public Unit(String nom_unit,ArrayList<Figurine> list_unit) {
		this.nom = nom_unit;
		this.figurines = list_unit;
	}
	
	// getters
	public ArrayList<Figurine> getFigurines() {
		return figurines;
	}
	public HashMap<String, ArrayList<Figurine>> getIdenticalFigsGroups(){
		return identical_figs;
	}
	public HashMap<String, Integer> getNumbersOfWeapons() {
		return numbers_of_weapons;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return nom;
	}
	public int getPoints() {
		return points;
	}
	public String getLogo() {
		return nom;
	}
	public Armee getArmee() {
		return armee;
	}
	
	// inutile?
//	public void killFigurine(Figurine mort) {
//		figurines.remove(mort);
//	}
	
	private void makeIdenticalFigsGroups() {
		for(Figurine fig : figurines)
		{
			if(!identical_figs.containsKey(fig.getNom())) // si nouveau type de figurines
			{
				identical_figs.put(fig.getNom(), new ArrayList<Figurine>()); // création d'uné paire clé/liste dans la hashmap
				numbers_of_weapons.put(fig.getNom(), 1);
			}
			else {
				numbers_of_weapons.replace(fig.getNom(), numbers_of_weapons.get(fig.getNom()) + 1);
			}
			identical_figs.get(fig.getNom()).add(fig); // récupérer la liste correspondante à la clé et ajouter la figurine
		}
	}
	
	@Override
	public String toString() {
		return "" + nom + "";
	}
}

