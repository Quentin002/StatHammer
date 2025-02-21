package modele;

import java.util.ArrayList;

public class Unit {
	
	private ArrayList<Figurine> figurines;
	private String nom;
	private int points;
	private Armee armee;
	
	public Unit(ArrayList<Figurine> figurines, String nom, int points, Armee armee) {
		this.figurines = figurines;
		this.nom = nom;
		this.points = points;
		this.armee = armee;
	}
	public Armee getArmee() {
		return armee;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void killFigurine(Figurine mort) {
		figurines.remove(mort);
	}
	@Override
	public String toString() {
		return "" + nom + "";
	}
	
	
	
	

}
