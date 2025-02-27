package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import controlleur.AdminController;

public class AfficheAdmin {
    public static void affiche(Stage primaryStage) {

        // Création du bouton "parcourir"
        Button parcourir = new Button("Parcourir");
        Button valider = new Button("Valider");
        Text img1 = new Text("votre_event.png");
        TextField descEvent = new TextField("Description");

        // Création d'un ImageView pour afficher l'image
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true); // Maintenir le ratio de l'image
        imageView.setFitWidth(80); // Largeur maximale de l'image dans la vue

        // Action du bouton
        parcourir.setOnAction(e -> {
        	AdminController.verif(primaryStage, imageView);
        });

        // VBox principal
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(img1, imageView, descEvent, parcourir, valider);
        hbox.setAlignment(Pos.CENTER);

        // Ajout du VBox à la scène
        Scene scene = new Scene(hbox, 800, 600);

        // Ajout d'un titre à la fenêtre
        primaryStage.setTitle("StatHammer : Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
