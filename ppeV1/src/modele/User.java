package modele;

import java.util.ArrayList;

public class User {
	
	private ArrayList<ArmeeListe> listes;
	private String nom;
	private String mdp;
	private String email;
	private int id;
	private String role;
	
	
	
	public User(ArrayList<ArmeeListe> listes, String nom, String mdp, String email, int id,String role) {
		this.listes = listes;
		this.nom = nom;
		this.mdp = mdp;
		this.email = email;
		this.id = id;
		this.role=role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User( String nom, int id2, String role2) {
		this.nom = nom;
		this.id=id2;
		this.role=role2;
	}
	
	public ArrayList<ArmeeListe> getListes() {
		return listes;
	}
	public void setListes(ArrayList<ArmeeListe> listes) {
		this.listes = listes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void addArmee(ArmeeListe armee) {
		listes.add(armee);
	}
	
	public void removeArmee(ArmeeListe armee) {
		listes.remove(armee);
	}
	
	

}