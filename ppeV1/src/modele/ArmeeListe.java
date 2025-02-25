package modele;

public class ArmeeListe {
    private int id;
    private String nom;
    private String description;
    private String data;
    private int idUtilisateur;

    public ArmeeListe(int id, String nom, String description, String data, int idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.data = data;
        this.idUtilisateur = idUtilisateur;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getData() { return data; }
    public int getIdUtilisateur() { return idUtilisateur; }

    @Override
    public String toString() {
        return "Liste ID: " + id + ", Nom: " + nom + ", Description: " + description + 
               ", Data: " + data + ", ID Utilisateur: " + idUtilisateur;
    }
}
