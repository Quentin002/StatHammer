package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.User;
import javafx.scene.control.TextField;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import controlleur.ControllerAdmin;

public class AfficheAdmin {
    public static void affiche(Stage primaryStage, User session) {
    	
    	afficheTopMenu menu = new afficheTopMenu(primaryStage,session);
    	Text titre = new Text("Mes évènements");
    	titre.setFont(Font.font("Arial", 30));
        titre.setFill(Color.web("#2C3E50")); 
        titre.setStroke(Color.web("#34495E"));
        titre.setStrokeWidth(1);
        
    	VBox vTitre = new VBox(titre);
        Button parcourir = new Button("Parcourir");
        Button valider = new Button("Valider");
        TextField nom = new TextField("");
        nom.setPromptText("Nom de l'évènement");
        TextField descEvent = new TextField();
        descEvent.setPromptText("Quelques mots pour votre évènement ?");
        descEvent.setPrefWidth(250);
        
        // Création d'un ImageView pour afficher l'image
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(80);

        // Action du bouton
        parcourir.setOnAction(e -> {
        	ControllerAdmin.parcourir(primaryStage, imageView);
        });
        
        
        // VBox principal
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(nom, imageView, descEvent, parcourir, valider);
        hbox.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.getChildren().addAll(menu,vTitre,hbox);
        
        // Ajout du VBox à la scène
        Scene scene = new Scene(root, 800, 600);

        // Ajout d'un titre à la fenêtre
        primaryStage.setTitle("StatHammer : Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}