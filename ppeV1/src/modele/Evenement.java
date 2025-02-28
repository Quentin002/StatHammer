package modele;

public class Evenement{
	
	private String nom_evenement;
	private String URL_evenement;
	private String date_evenement;
	private String descritption_evenement;

	public Evenement(String nom,String URL,String date,String decription ) {
		this.nom_evenement = nom;
		this.URL_evenement = nom;
		this.date_evenement = nom;
		this.descritption_evenement = nom;
	}

	public String getNom_evenement() {
		return nom_evenement;
	}

	public String getURL_evenement() {
		return URL_evenement;
	}

	public String getData_evenement() {
		return date_evenement;
	}

	public String getDescritption_evenement() {
		return descritption_evenement;
	}
}