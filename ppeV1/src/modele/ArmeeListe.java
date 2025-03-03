package modele;

import java.util.ArrayList;

public class ArmeeListe {
	
	private ArrayList<Unit> unites = new ArrayList<Unit>();
	private String nom;
	private String description;
	private String data;
	
	public ArmeeListe(String nom, String description, String data) {
		this.nom = nom;
		this.description = description;
		this.data = data;
	}
	
	public ArmeeListe(ArrayList<Unit> unit_list, String nom, String description, String data) {
		this.unites = unit_list;
		this.nom = nom;
		this.description = description;
		this.data = data;
	}

	// getters
	public String getName() {
		return nom;
	}
	public String getDescription() {
		return description;
	}
	public String getData() {
		return data;
	}
	public ArrayList<Unit> getUnits() {
		return unites;
	}
	
	public void addUnit(Unit unit) {
		unites.add(unit);
	}
	
	public ArrayList<Unit> getUnites() {
		return unites;
	}
}
