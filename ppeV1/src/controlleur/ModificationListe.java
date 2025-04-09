package controlleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modele.ArmeeListe;
import modele.Figurine;

public class ModificationListe {

    private BDD conec; // Instance de la classe BDD qui gère la connexion à la bdd

    public ModificationListe() {
        this.conec = new BDD("400129", "stathammer_greta_admin", "stathammer_v1");
    }

    // Récupère la liste des armées en fonction de l'id de la liste choisie
    public ArrayList<ArmeeListe> getArmeeListe(int idListe) {
        ArrayList<ArmeeListe> listes = new ArrayList<>();
        String requete = """
            SELECT l.id_liste, l.nom_liste, l.description_liste, l.data_liste, u.nom_unite
            FROM liste l
            LEFT JOIN contenir c ON l.id_liste = c.id_liste
            LEFT JOIN unite u ON c.id_unite = u.id_unite
            WHERE l.id_liste = ?;
        """;

        try (Connection conn = reopenConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idListe);
            ResultSet recup = stmt.executeQuery();
            HashMap<Integer, ArmeeListe> mapListes = new HashMap<>();

            while (recup.next()) {
                int id = recup.getInt("id_liste");
                String nom = recup.getString("nom_liste");
                String description = recup.getString("description_liste");
                String data = recup.getString("data_liste");
                String nomUnite = recup.getString("nom_unite");

                ArmeeListe liste = mapListes.get(id);
                if (liste == null) {
                    liste = new ArmeeListe(id, id, nom, description, data);
                    liste.setUniteListe(new ArrayList<>());
                    mapListes.put(id, liste);
                }

                if (nomUnite != null) {
                    liste.getUniteListe().add(nomUnite);
                }
            }
            listes.addAll(mapListes.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listes;
    }

    public ArrayList<Figurine> getFigurineListe(int idListe, String nomUnit) {
        ArrayList<Figurine> listesFigurine = new ArrayList<>();
        String requete = """
            SELECT f.nom_figurine, f.M, f.E, f.SV, f.PV, f.CD, f.CO
            FROM liste l
            JOIN contenir c ON l.id_liste = c.id_liste
            JOIN unite u ON c.id_unite = u.id_unite
            JOIN remplir r ON u.id_unite = r.id_unite
            JOIN figurine f ON r.id_figurine = f.id_figurine
            WHERE l.id_liste = ? AND u.nom_unite = ?;
        """;

        try (Connection conn = reopenConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idListe);
            stmt.setString(2, nomUnit);
            ResultSet recup = stmt.executeQuery();

            while (recup.next()) { // récupère toutes les info de la figurine
                String nomFigurine = recup.getString("nom_figurine");
                String M = recup.getString("M");
                int E = recup.getInt("E");
                int SV = recup.getInt("SV");
                int PV = recup.getInt("PV");
                int CD = recup.getInt("CD");
                int CO = recup.getInt("CO");

                Figurine figurine = new Figurine(new ArrayList<>(), new ArrayList<>(), nomFigurine, "", M, E, SV, PV, CD, CO);
                listesFigurine.add(figurine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listesFigurine;
    }

    public ArrayList<ArmeeListe> getAllUnites(int idArmee, int idListe) {
        ArrayList<ArmeeListe> toutesUnites = new ArrayList<>();
        String requete = "SELECT u.nom_unite\r\n"
        		+ "FROM unite u\r\n"
        		+ "WHERE u.id_armee = ?\r\n"
        		+ "AND u.nom_unite NOT IN (\r\n"
        		+ "    SELECT un.nom_unite\r\n"
        		+ "    FROM unite un\r\n"
        		+ "    JOIN contenir c ON un.id_unite = c.id_unite\r\n"
        		+ "    WHERE c.id_liste = ?\r\n"
        		+ ");";

        try (Connection conn = reopenConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idArmee);
            stmt.setInt(2, idListe);
            ResultSet recup = stmt.executeQuery();

            while (recup.next()) {
                String nomUnite = recup.getString("nom_unite");
                ArmeeListe armee = new ArmeeListe(idArmee, idArmee, nomUnite, nomUnite, nomUnite);
                armee.setUniteListe(new ArrayList<>());
                armee.getUniteListe().add(nomUnite);
                toutesUnites.add(armee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toutesUnites;
    }

    public ArrayList<Figurine> getAllFigurineListe(int idArmee, String unit) {
        ArrayList<Figurine> listesAllFigurine = new ArrayList<>();
        String requete = """
            SELECT DISTINCT f.nom_figurine, f.M, f.E, f.SV, f.PV, f.CD, f.CO
            FROM unite u
            JOIN remplir r ON u.id_unite = r.id_unite
            JOIN figurine f ON r.id_figurine = f.id_figurine
            WHERE u.id_armee = ? AND u.nom_unite = ?;
        """;

        try (Connection conn = reopenConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idArmee);
            stmt.setString(2, unit);
            ResultSet recup = stmt.executeQuery();

            while (recup.next()) { // récupère toutes les info de la figurine
                String nomFigurine = recup.getString("nom_figurine");
                String M = recup.getString("M");
                int E = recup.getInt("E");
                int SV = recup.getInt("SV");
                int PV = recup.getInt("PV");
                int CD = recup.getInt("CD");
                int CO = recup.getInt("CO");

                Figurine figurine = new Figurine(new ArrayList<>(), new ArrayList<>(), nomFigurine, "", M, E, SV, PV, CD, CO);
                listesAllFigurine.add(figurine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listesAllFigurine;
    }

    private Connection reopenConnection() throws SQLException {
        if (conec.getConnection().isClosed()) {
            conec = new BDD("400129", "stathammer_greta_admin", "stathammer_v1");
        }
        return conec.getConnection();
    }

    public void ajouterUnites(int idListe, ArrayList<String> unites) {
        String query =  """
        	    INSERT INTO contenir (id_unite, id_liste)
        	    SELECT id_unite, ? FROM unite WHERE nom_unite = ? LIMIT 1
        	""";
        try (Connection conn = reopenConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (String unite : unites) {
                pstmt.setInt(1, idListe);
                pstmt.setString(2, unite);
                System.out.println("Requête: " + pstmt);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUnites(int idListe, ArrayList<String> unites) {
        String query = "DELETE FROM contenir WHERE id_liste = ? AND id_unite = (SELECT id_unite FROM unite WHERE nom_unite = ? LIMIT 1)";
        try (Connection conn = reopenConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (String unite : unites) {
                pstmt.setInt(1, idListe);
                pstmt.setString(2, unite);
                System.out.println("Requête: " + pstmt);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
