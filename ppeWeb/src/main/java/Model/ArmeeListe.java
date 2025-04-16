package Model;

import java.util.ArrayList;

public class ArmeeListe {
    private int id;
    private int idArmee;
    private String nom;
    private String description;
    private String data;
	private ArrayList<Unit> unites = new ArrayList<Unit>();
    private ArrayList<String> unit_names = new ArrayList<String>();

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
	
    public ArmeeListe(int id, int idArmee, String nom, String description, String data, int idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.data= data;
        this.idArmee = idArmee;
        this.idUtilisateur = idUtilisateur;
    }


	public int getIdListe() { return id; }
    public int getIdArmee() { return idArmee;}
    public String getNomListe() { return nom; }
    public String getDescriptionListe() { return description; }
    public String getDataListe() { return data; }
    public ArrayList<String> getUniteListe() { return unit_names; }

    public void setUniteListe(ArrayList<String> unite) {
        this.unit_names = unite;
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
	public void removeUnit(Unit unit) {
		unites.remove(unit);
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
    public String toString() {
        return "Liste ID: " + id + ", Nom: " + nom + ", Description: " + description + 
               ", Data: " + data + ", unite: " + unit_names;
    }
}