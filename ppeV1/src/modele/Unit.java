package modele;

import java.util.ArrayList;

public class Unit {
	
	private ArrayList<Figurine> figurines;
	private int id;
	private String nom;
	private int points;
	private String logo;
	private Armee armee;
	
	public Unit(ArrayList<Figurine> figurines, String nom, int points, String logo, Armee armee) {
		this.figurines = figurines;
		this.nom = nom;
		this.points = points;
		this.logo = logo;
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
	public String getLogo() {
		return nom;
	}
	public Armee getArmee() {
		return armee;
	}
	
	public void killFigurine(Figurine mort) {
		figurines.remove(mort);
	}
	
	@Override
	public String toString() {
		return "" + nom + "";
	}
}

