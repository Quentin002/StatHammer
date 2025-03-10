package controlleur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Evenement;
import vue.AfficheAdmin;

public class EvenementController {
	private static ArrayList<String> nom_event;
	private static ArrayList<String> nom_image;
	private static ArrayList<String> desc_event;
	private static ArrayList<String> date_event;
	
    public static void getEvenementsFromDB() {
    	nom_event.clear();
    	nom_image.clear();
    	desc_event.clear();
    	date_event.clear();
        // Créer une instance de BDD
        BDD bdd = new BDD();

        try {
            // Requête SQL
            String sql = "SELECT * FROM evenement";  // Requête
            PreparedStatement ps = bdd.getPreparedStatement(sql);

            // Exécution de la requête
            ResultSet rs = ps.executeQuery();

            // Traiter les résultats
            while (rs.next()) {
            	
            	String nom_evt = rs.getString("nom_evenement");
                String nom_img = rs.getString("nom_image");
                String desc_evt = rs.getString("description_evenement");
                String date_evt = rs.getString("date_evenement");
                
                nom_event.add(nom_evt);
            	nom_image.add(nom_img);
            	desc_event.add(desc_evt);
            	date_event.add(date_evt);

                // Afficher les résultats ou les ajouter à une liste/structure de données
                System.out.println("Image : " + nom_img);
                System.out.println("Événement : " + nom_evt);
                System.out.println("Description : " + desc_evt);
                System.out.println("Date : " + date_evt);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        } finally {
            // Être sûr de fermer la connexion à la base de données
            bdd.close();
        }
    }
    
    public static void insererEvenement(Evenement evt) {
        // Créer une instance de BDD
        BDD bdd = new BDD();
        try {
            // Requête SQL
            String sql = "INSERT INTO evenement (nom_evenement, nom_image, description_evenement, date_evenement, id_utilisateur) VALUES (?, ?, ?, ?, 3)";  // Requête
            PreparedStatement ps = bdd.getPreparedStatement(sql);
            
	        // Associer les valeurs aux paramètres
	        ps.setString(1, evt.getNom_evenement());
	        ps.setString(2, evt.getNom_image());
	        ps.setString(3, evt.getDescritption_evenement());
	        ps.setString(4, evt.getData_evenement());
	        
	        // Exécution de la requête
	        ps.executeUpdate();
	        System.out.println("Insertion réussiedans la base données");

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        } finally {
            // Être sûr de fermer la connexion à la base de données
            bdd.close();
        }
    }
    
    public static VBox EvenementVBox() {
        // Créer une instance de BDD
        BDD bdd = new BDD();
        VBox event = new VBox();
        event.setPrefHeight(50);
        event.setAlignment(Pos.CENTER);

        try {
            // Requête SQL
            String sql = "SELECT * FROM evenement";  // Requête
            PreparedStatement ps = bdd.getPreparedStatement(sql);

            // Exécution de la requête
            ResultSet rs = ps.executeQuery();
            
            Integer i = 0;
            // Traiter les résultats
            while (rs.next()) {
            	
            	HBox hevent = new HBox();
            	hevent.setAlignment(Pos.CENTER);
            	hevent.setPrefHeight(50);
            	i++;
            	String num_evt = new String(i+". ");
            	Label numLabel = new Label(num_evt);
            	numLabel.setPrefWidth(25);
            	hevent.getChildren().add(numLabel);
            	
            	
            	String nom_evt = rs.getString("nom_evenement");
            	Label nomLabel = new Label(nom_evt);
            	nomLabel.setPrefWidth(150);
            	hevent.getChildren().add(nomLabel);
            	
            	String nom_img = rs.getString("nom_image");
            	Label imgLabel = new Label(nom_img);
            	imgLabel.setPrefWidth(150);
            	hevent.getChildren().add(imgLabel);
            	
                String desc_evt = rs.getString("description_evenement");
                Label descLabel = new Label(desc_evt);
                descLabel.setPrefWidth(150);
            	hevent.getChildren().add(descLabel);
            	
                String date_evt = rs.getString("date_evenement");
                Label dateLabel = new Label(date_evt);
                dateLabel.setPrefWidth(100);
            	hevent.getChildren().add(dateLabel);
            	
            	Button bouton = new Button("X");
    		    hevent.getChildren().add(bouton);
                
    		    Evenement evt = new Evenement(nom_evt,nom_img,desc_evt,date_evt);
    		    
    		    bouton.setOnAction(e -> {
    		        EvenementController.supprimerEvenement(evt);
    		        
    		    });
    		    
    		    event.getChildren().add(hevent);

                // Afficher les résultats ou les ajouter à une liste/structure de données
                System.out.println("Image : " + nom_img);
                System.out.println("Événement : " + nom_evt);
                System.out.println("Description : " + desc_evt);
                System.out.println("Date : " + date_evt); 
                
            }
            
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        } finally {
            // Être sûr de fermer la connexion à la base de données
            bdd.close();
            
        }
        return event;
    }
    
	// Méthode "supprimer" modifiée pour accepter un argument
	public static void supprimerEvenement(Evenement evt) {
		// Créer une instance de BDD
		BDD bdd = new BDD();
		try {
		    // Requête SQL
		    String sql = "DELETE FROM evenement WHERE nom_evenement = ?";  // Requête DELETE
		    PreparedStatement ps = bdd.getPreparedStatement(sql);
		    
		    // Associer l'ID de l'événement à supprimer
		    ps.setString(1, evt.getNom_evenement());
		    
		    // Exécution de la requête
		    int rowsAffected = ps.executeUpdate();
		    if (rowsAffected > 0) {
		    	EvenementController.effacerImage(evt.getNom_image());
		    	AfficheAdmin.setEvents(EvenementController.EvenementVBox());
		    	AfficheAdmin.affiche(AfficheAdmin.getStage(), AfficheAdmin.getSess());
		        System.out.println("Suppression réussie dans la base de données");
		    } else {
		        System.out.println("Aucun événement trouvé avec cet ID.");
		    }
		    
		    

		} catch (SQLException e) {
		    System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
		} finally {
		    // Être sûr de fermer la connexion à la base de données
		    bdd.close();
		}
		
	}
	
	public static void effacerImage(String nom_img) {
	    String file_name = ControllerAdmin.getDestinationDir()+"/"+nom_img;
	    
	    Path path = Paths.get(file_name);
	    try {
	      boolean result = Files.deleteIfExists(path);
	      if (result) {
	        System.out.println("File is deleted!");
	      } else {
	        System.out.println("Sorry, could not delete the file.");
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
}
