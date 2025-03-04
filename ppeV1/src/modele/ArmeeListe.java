package modele;

import java.util.ArrayList;

public class ArmeeListe
{
	private int id;
	private ArrayList<Unit> unites = new ArrayList<Unit>();
	private String nom;
	private String description;
	private String data;
	private int idUtilisateur;
	
	public ArmeeListe(String nom, String description, String data) {
		this.nom = nom;
		this.description = description;
		this.data = data;
	}
	
	public ArmeeListe(ArrayList<Unit> unit_list, String nom, String description, String data) {
		this.unites = unit_list;
		this.nom = nom;
		this.description = description;
		this.data = data;
	}
	
	public ArmeeListe(int id, String nom, String description, String data, int idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.data = data;
        this.idUtilisateur = idUtilisateur;
    }

	// getters
	public int getId() {
    	return id;
    }
	public String getName() {
		return nom;
	}
	public void setName(String name) {
		nom = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		description = desc;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
    public int getIdUtilisateur() {
    	return idUtilisateur;
    }
    
	public ArrayList<Unit> getUnits() {
		return unites;
	}
	public void setUnits(ArrayList<Unit> units) {
		unites = units;
	}
	public String[] getUnitNames() {
		String[] unit_names = new String[unites.size()];
		for(int i = 0; i < unites.size(); i++) {
			unit_names[i] = unites.get(i).getName();
		}
		return unit_names;
	}
	public void addUnit(Unit unit) {
		unites.add(unit);
	}
	
	@Override
    public String toString() {
        return "Liste ID: " + id + ", Nom: " + nom + ", Description: " + description + 
               ", Data: " + data + ", ID Utilisateur: " + idUtilisateur;
    }
}
    