package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AfficheAdmin {
    public static void affiche(Stage primaryStage) {

        // Création du bouton "parcourir"
        Button parcourir = new Button("Parcourir");

        // Création d'un ImageView pour afficher l'image
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true); // Maintenir le ratio de l'image
        imageView.setFitWidth(400); // Largeur maximale de l'image dans la vue

        // Action du bouton
        parcourir.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
            
            // Ouvrir le file chooser et obtenir le fichier sélectionné
            File file = fileChooser.showOpenDialog(primaryStage);
            
            if (file != null) {
                System.out.println("Fichier sélectionné : " + file.getAbsolutePath());
                
                // Créer une nouvelle image à partir du chemin absolu du fichier
                Image image = new Image("file:" + file.getAbsolutePath());
                
                // Définir l'image dans l'ImageView
                imageView.setImage(image);

                // Chemin de destination où vous voulez sauvegarder l'image
                String destinationDir = "img\\evnmt"; // Remplacez ceci par le chemin réel de destination
                File destinationFolder = new File(destinationDir);
                
                // Créez le répertoire s'il n'existe pas
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs();
                }

                // Créez le fichier de destination avec le même nom
                File destinationFile = new File(destinationFolder, file.getName());

                try {
                    // Copier le fichier dans le dossier de destination
                    Files.copy(file.toPath(), destinationFile.toPath());
                    System.out.println("Fichier enregistré à : " + destinationFile.getAbsolutePath());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // VBox principal
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(parcourir, imageView);
        vbox.setAlignment(Pos.CENTER);

        // Ajout du VBox à la scène
        Scene scene = new Scene(vbox, 800, 600);

        // Ajout d'un titre à la fenêtre
        primaryStage.setTitle("StatHammer : Admin");
        // Association de la scène à la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
