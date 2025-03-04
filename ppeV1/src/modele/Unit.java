package modele;

import java.util.ArrayList;

public class Unit {
	
	private ArrayList<Figurine> figurines;
	private int id;
	private String logo;
	private String nom;
	private int points;
	private Armee armee;
	
	public Unit(ArrayList<Figurine> figurines,int id, String nom, int points,String logo, Armee armee) {
		this.id = id;
		this.figurines = figurines;
		this.nom = nom;
		this.logo = logo;
		this.points = points;
		this.armee = armee;
	}
	public Unit(String nom_unit,ArrayList<Figurine> list_unit) {
		this.nom = nom_unit;
		this.figurines = list_unit;
	}
	
	// getters
	public ArrayList<Figurine> getFigurines() {
		return figurines;
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
	public Armee getArmee() {
		return armee;
	}
	public String getLogo() {
		return logo;
	}
	
	public void killFigurine(Figurine mort) {
		figurines.remove(mort);
	}
	
	@Override
	public String toString() {
		return "" + nom + "";
	}
}

