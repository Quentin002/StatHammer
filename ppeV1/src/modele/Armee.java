package modele;

public class Armee {

	private String nom;
	private String logo;
	private Faction faction;
	
	public Armee(String nom, String logo) {
		this.nom = nom;
		this.logo = logo;
	}

	public Armee(String nom, String logo, Faction fact) {
		this.nom = nom;
		this.logo = logo;
		this.faction = fact;
	}

	public String getName() {
		return nom;
	}

	@Override
	public String toString() {
		return "" + nom + "";
	}
	
	public String getLogo() {
		return logo;
	}
	
	public Faction getFaction() {
		return faction;
	}
}
