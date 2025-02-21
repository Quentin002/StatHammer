package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class AfficheGestionCompte {

public static void affiche(Stage primaryStage) {

	
	Label etiquetteM = new Label("Gestion du compte utilisateur");
	etiquetteM.setFont(new Font("Arial", 24));
	Button bouton = new Button("Envoyer");
	Label etiquette = new Label("Modifier le pseudonyme : ");
	TextArea texte =  new TextArea("");
	Label etiquette2 = new Label("Modifier le mot de passe : ");
	TextArea texte2 =  new TextArea("");
	Label etiquette3 = new Label("Modifier  l'adresse email : ");
	TextArea texte3 =  new TextArea("");
	VBox vbox = new VBox();
	VBox vbox2 = new VBox();
	VBox vbox3 = new VBox();
	Button btn_deco = new Button("Déconnexion");
	vbox3.getChildren().add(btn_deco);
	vbox.getChildren().add(vbox3);
	vbox.getChildren().add(etiquetteM);
	vbox.getChildren().add(etiquette3);
	vbox.getChildren().add(texte3);
	vbox.getChildren().add(etiquette);
	vbox.getChildren().add(texte);
	vbox.getChildren().add(etiquette2);
	vbox.getChildren().add(texte2);
	vbox.getChildren().add(vbox2);
	vbox2.getChildren().add(bouton);
	
	Button btn_back = new Button("Retour");
   vbox.getChildren().add(btn_back);
 
    
	
	vbox.setSpacing(10);// Ajoute un espace de 10 pixels entre les composants
	vbox.setPadding(new Insets(20, 20, 20, 20)); // Ajoute des marges autour du conteneur
	vbox2.setAlignment(Pos.CENTER);
	vbox3.setAlignment(Pos.TOP_RIGHT);
	etiquette.setMaxWidth(Double.MAX_VALUE);
	texte.setMaxWidth(Double.MAX_VALUE);
	texte.setMaxHeight(0);
	texte2.setMaxHeight(0);
	texte3.setMaxHeight(0);
	VBox.setVgrow(etiquette, Priority.ALWAYS);
	VBox.setVgrow(bouton, Priority.ALWAYS);
	
	bouton.setOnAction(e -> {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Le bouton a été cliqué !");
		alert.showAndWait();
		});
	
    btn_back.setOnAction(e -> {
		primaryStage.close();
		AfficheAccueil.affiche(primaryStage);
	});
    btn_deco.setOnAction(e -> {
		primaryStage.close();
		AfficheConnexion.affiche(primaryStage);
	});
	
	Scene scene = new Scene(vbox, 800, 600);
	primaryStage.setScene(scene);
	primaryStage.show();
	}


}

