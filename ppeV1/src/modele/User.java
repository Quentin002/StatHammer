package modele;

import java.util.ArrayList;

public class User {
	
	private ArrayList<ArmeeListe> listes;
	private String nom;
	private String mdp;
	private String email;
	
	
	public User(ArrayList<ArmeeListe> listes, String nom, String mdp, String email) {
		this.listes = listes;
		this.nom = nom;
		this.mdp = mdp;
		this.email = email;
	}
	public User( String nom) {
		this.nom = nom;
	}
	
	public void addArmee(ArmeeListe armee) {
		listes.add(armee);
	}
	
	public void removeArmee(ArmeeListe armee) {
		listes.remove(armee);
	}
	
	

}
