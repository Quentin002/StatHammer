package Model;

public class Faction {

	private String nom;

	public Faction(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return "" + nom + "";
	}
}
