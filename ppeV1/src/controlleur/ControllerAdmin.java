package controlleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheAdmin;
import vue.AfficheConnexionFailed;
import modele.Evenement;
import modele.User;

public class ControllerAdmin {
	private static String destinationDir;
	private static File destinationFile ;
	private static File file;
	
	public static void parcourir(Stage primaryStage, ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Images", "*.png", "*.jpg"));
        
        // Ouvrir le file chooser et obtenir le fichier sélectionné
        file = fileChooser.showOpenDialog(primaryStage);
        
       boolean nomFich = true;
        
        if (file != null) {

        	for (int i = 0 ; i < Connexion.eventC.getNbrEvt();i++) {
        		
        		if (file.getName().equals(Connexion.eventC.getNom_image().get(i))) {
        			
        	        Alert alert = new Alert(Alert.AlertType.ERROR);
        	        alert.setTitle("AbiStock : Erreur de connexion");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Le nom de l'image est déjà prise.");
        	        alert.showAndWait();
        	        nomFich = false;
        	        
        		}
        	}
        	if (nomFich) {
            	System.out.println("Fichier sélectionné : " + file.getName());
            	System.out.println("Chemin du fichier : " + file.getAbsolutePath());
                
                // Créer une nouvelle image à partir du chemin absolu du fichier
                Image image = new Image("file:" + file.getAbsolutePath());
                
                // Définir l'image dans l'ImageView
                imageView.setImage(image);

                // Destination pour sauvegarder l'image
                destinationDir = "src\\images"; 
                File destinationFolder = new File(destinationDir);
                
                // Créez le répertoire s'il n'existe pas
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs();
                }
                
                File nouveauNom = new File(file.getName());
                destinationFile = new File(destinationFolder, nouveauNom.getName());
                		
        	}

        }
	}
	
	
	//méthode "valider"
	public static VBox valider(String nom_evt, String nom_img, String desc, String date) {
		Evenement evt = new Evenement(nom_evt, nom_img, desc, date);
		VBox event = new VBox();
		event.setPrefHeight(50);
        event.setAlignment(Pos.CENTER);
		try {
            // Copier le fichier dans le dossier de destination
            Files.copy(file.toPath(), destinationFile.toPath());
            System.out.println("Fichier enregistré à : " + destinationFile.getAbsolutePath());
            EvenementController.insererEvenement(evt);
			event = EvenementController.EvenementVBox();
			
        } catch (IOException ioException) {
        	System.out.println("Probleme sur Valider");
            ioException.printStackTrace();
        }
		return event;
    }
	
	public static File getFile() {
		return file;
	}


	public static String getDestinationDir() {
		return destinationDir;
	}
	

	
}