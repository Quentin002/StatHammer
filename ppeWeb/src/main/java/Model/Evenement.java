package Model;

public class Evenement{
	
	private String nom_evenement;
	private String nom_image;
	private String descritption_evenement;
	private String date_evenement;

	public Evenement(String nom_evt, String nom_img,String desc, String date) {
		this.nom_evenement = nom_evt;
		this.nom_image = nom_img;
		this.descritption_evenement = desc;
		this.date_evenement = date;
	}

	public String getNom_evenement() {
		return nom_evenement;
	}

	public String getNom_image() {
		return nom_image;
	}

	public String getData_evenement() {
		return date_evenement;
	}

	public void setNom_evenement(String nom_evenement) {
		this.nom_evenement = nom_evenement;
	}

	public void setNom_image(String nom_image) {
		this.nom_image = nom_image;
	}

	public void setDescritption_evenement(String descritption_evenement) {
		this.descritption_evenement = descritption_evenement;
	}

	public void setDate_evenement(String date_evenement) {
		this.date_evenement = date_evenement;
	}

	public String getDescritption_evenement() {
		return descritption_evenement;
	}
}