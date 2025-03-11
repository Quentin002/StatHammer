package modele;

import java.util.ArrayList;

public class ArmeeListe {
    private int idListe;
    private String nomListe;
    private String descriptionListe;
    private String dataListe;
    private ArrayList<String> unite;

    public ArmeeListe(int id, String nom, String description, String data) {
        this.idListe = id;
        this.nomListe = nom;
        this.descriptionListe = description;
        this.dataListe = data;
    }

    public int getIdListe() { return idListe; }
    public String getNomListe() { return nomListe; }
    public String getDescriptionListe() { return descriptionListe; }
    public String getDataListe() { return dataListe; }
    public ArrayList<String> getUniteListe() { return unite; }

    public void setUniteListe(ArrayList<String> unite) {
        this.unite = unite;
    }
    @Override
    public String toString() {
        return "Liste ID: " + idListe + ", Nom: " + nomListe + ", Description: " + descriptionListe + 
               ", Data: " + dataListe + ", unite: " + unite;
    }
}