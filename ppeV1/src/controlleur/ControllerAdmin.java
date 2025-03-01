package controlleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;
import modele.Evenement;
import modele.User;

public class ControllerAdmin {
	
	static File destinationFile ;
	static File file;
	
	public static void parcourir(Stage primaryStage, ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Images", "*.png", "*.jpg"));
        
        // Ouvrir le file chooser et obtenir le fichier sélectionné
        file = fileChooser.showOpenDialog(primaryStage);
        
        if (file != null) {
        	
        	System.out.println("Fichier sélectionné : " + file.getName());
        	System.out.println("Chemin du fichier : " + file.getAbsolutePath());
            
            // Créer une nouvelle image à partir du chemin absolu du fichier
            Image image = new Image("file:" + file.getAbsolutePath());
            
            // Définir l'image dans l'ImageView
            imageView.setImage(image);

            // Destination pour sauvegarder l'image
            String destinationDir = "src\\images"; 
            File destinationFolder = new File(destinationDir);
            
            // Créez le répertoire s'il n'existe pas
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            
            File nouveauNom = new File(file.getName());
            destinationFile = new File(destinationFolder, nouveauNom.getName());
            	
        }
	}
	
	//méthode "valider"
	public static void valider() {
		try {
            // Copier le fichier dans le dossier de destination
            Files.copy(file.toPath(), destinationFile.toPath());
            System.out.println("Fichier enregistré à : " + destinationFile.getAbsolutePath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}