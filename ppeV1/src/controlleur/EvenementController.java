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
	private static int nbrEvt;
	
	public void setNbrEvt(int nbrEvt) {
		EvenementController.nbrEvt = nbrEvt;
	}

	private static ArrayList<String> nom_event;
	private static ArrayList<String> nom_image;
	private static ArrayList<String> desc_event;
	private static ArrayList<String> date_event;
	
	
    public ArrayList<String> getNom_event() {
		return nom_event;
	}

	public ArrayList<String> getNom_image() {
		return nom_image;
	}

	public ArrayList<String> getDesc_event() {
		return desc_event;
	}

	public ArrayList<String> getDate_event() {
		return date_event;
	}

	//EvenementController permet de recenser tous les évènements sous forme d'ArrayList<String> 
	//Le rôle de ce constructeur a changé pas mal de fois, d'où un nom peu cohérent...
	public EvenementController() {
		
		nbrEvt = 0;
		
		nom_event= new ArrayList<String>();
		nom_image= new ArrayList<String>();
		desc_event= new ArrayList<String>();
		date_event= new ArrayList<String>();
		
		
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
            	int i=0;
            	nbrEvt = getNbrEvt() + 1;
            	
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
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        } finally {
            // Être sûr de fermer la connexion à la base de données
            bdd.close();
        }
    }
    
	//Méthode pour insérer un évènement dans la BDD ainsi que d'ajouter cet évènement dans EvenementController
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
	        
	        Connexion.eventC.getNom_event().add(evt.getNom_evenement());
	        Connexion.eventC.getNom_image().add(evt.getNom_image());
	        Connexion.eventC.getDesc_event().add(evt.getDescritption_evenement());
	        Connexion.eventC.getDate_event().add(evt.getData_evenement());
	        
	        Connexion.eventC.setNbrEvt(Connexion.eventC.getNbrEvt()+1);
	        
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
        VBox event = new VBox();
        event.setPrefHeight(50);
        event.setAlignment(Pos.CENTER);
            
        for (int i = 0; i < Connexion.eventC.getNbrEvt() ; i++) {
        	            	
            	HBox hevent = new HBox();
            	hevent.setAlignment(Pos.CENTER);
            	hevent.setPrefHeight(50);

            	String num_evt = new String(i+1+". ");
            	Label numLabel = new Label(num_evt);
            	numLabel.setPrefWidth(25);
            	hevent.getChildren().add(numLabel);
            	
            	
            	String nom_evt = Connexion.eventC.getNom_event().get(i);
            	Label nomLabel = new Label(nom_evt);
            	nomLabel.setPrefWidth(150);
            	hevent.getChildren().add(nomLabel);
            	
            	String nom_img = Connexion.eventC.getNom_image().get(i);
            	Label imgLabel = new Label(nom_img);
            	imgLabel.setPrefWidth(150);
            	hevent.getChildren().add(imgLabel);
            	
                String desc_evt = Connexion.eventC.getDesc_event().get(i);
                Label descLabel = new Label(desc_evt);
                descLabel.setPrefWidth(150);
            	hevent.getChildren().add(descLabel);
            	
                String date_evt = Connexion.eventC.getDate_event().get(i);
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
		    	
		        Connexion.eventC.getNom_event().remove(evt.getNom_evenement());
		        Connexion.eventC.getNom_image().remove(evt.getNom_image());
		        Connexion.eventC.getDesc_event().remove(evt.getDescritption_evenement());
		        Connexion.eventC.getDate_event().remove(evt.getData_evenement());
		        
		        Connexion.eventC.setNbrEvt(Connexion.eventC.getNbrEvt()-1);
		    	
		    	
		    	AfficheAdmin.setEvents(EvenementController.EvenementVBox());
	            Connexion.event = new Evenement(Connexion.eventC.getNom_event().get(0),
	            		Connexion.eventC.getNom_image().get(0),
	            		Connexion.eventC.getDesc_event().get(0),
	            		Connexion.eventC.getDate_event().get(0));
	    		System.out.println(Connexion.eventC.getNom_image().get(0));
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
	        System.out.println("Image effacée !");
	      } else {
	        System.out.println("Oups, impossible d'effacer.");
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

	public int getNbrEvt() {
		return nbrEvt;
	}
}
