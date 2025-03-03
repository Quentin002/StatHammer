package modele;

import java.util.ArrayList;

public class ArmeeListe {
	
	private ArrayList<Unit> unites;
	private String nom;
	private String description;
	private String data;
	
	
	public ArmeeListe(ArrayList<Unit> unites, String nom, String description, String data) {
		this.unites = unites;
		this.nom = nom;
		this.description = description;
		this.data = data;
	}
	
	public void addUnit(Unit unit) {
		unites.add(unit);
	}
	public ArrayList<Unit> getUnites() {
		return unites;
	}
	

}
