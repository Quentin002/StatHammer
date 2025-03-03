package modele;

public class Armee {
	
	private String nom;
	private String logo;
	
	public Armee(String nom, String logo) {
		this.nom = nom;
		this.logo = logo;
	}

	public String getName() {
		return nom;
	}
	
	public String getLogo() {
		return logo;
	}
	
}
