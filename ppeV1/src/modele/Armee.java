package modele;

public class Armee {
	
	private String nom;
	private String logo;
	private Faction faction;
	
	public Armee(String nom, String logo, Faction faction) {
		this.nom = nom;
		this.logo = logo;
		this.faction = faction;
	}

	public Faction getFaction() {
		return faction;
	}

	public String getNom() {
		return nom;
	}

	public String getLogo() {
		return logo;
	}
	

	
	
	
	
}
