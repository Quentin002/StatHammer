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
	//v2
	public ArrayList<Figurine> getList_unit() {
		return figurines;
	}
	public Armee getArmee() {
		return armee;
	}
	
	public void killFigurine(Figurine mort) {
		figurines.remove(mort);
	}
	
	
	
	

}
