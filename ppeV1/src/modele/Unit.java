package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Unit {
	
	private ArrayList<Figurine> figurines;
	private HashMap<String, ArrayList<Figurine>> identical_figs = new HashMap<String, ArrayList<Figurine>>();
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
	public Unit(ArrayList<Figurine> figurines,int id, String nom, int points,String logo, Armee armee) {
		this.id = id;
		this.figurines = figurines;
		this.nom = nom;
		this.logo = logo;
		this.points = points;
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
	
	// est exécuté dès qu'on touche au slider
	public int getAliveFigsOfAGroup(String group_name) {
		int nb = 0;
		System.out.println("hashmap groupe de figs: " + identical_figs);
		for(Figurine fig : identical_figs.get(group_name))
		{
			if(fig.getHP() > 0) {
				nb++;
			}
		}
		return nb;
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
	
	private void makeIdenticalFigsGroups() {
		for(Figurine fig : figurines)
		{
			if(!identical_figs.containsKey(fig.getNom())) // si nouveau type de figurines
			{
				identical_figs.put(fig.getNom(), new ArrayList<Figurine>()); // création d'uné paire clé/liste dans la hashmap
			}
			identical_figs.get(fig.getNom()).add(fig); // récupérer la liste correspondante à la clé et ajouter la figurine
		}
	}
	
	@Override
	public String toString() {
		return "" + nom + "";
	}
}
