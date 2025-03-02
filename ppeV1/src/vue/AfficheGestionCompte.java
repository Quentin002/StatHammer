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
import modele.User;
public class AfficheGestionCompte {

public static void affiche(Stage primaryStage,User session) {


	
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
	VBox vboxL = new VBox();

	afficheTopMenu menu = new afficheTopMenu(primaryStage,session);
	
	
	

	
	Scene scene = new Scene(vbox, 800, 600);
	vbox.getChildren().add(menu);
	vbox.getChildren().add(vboxL);
	vboxL.getChildren().add(etiquetteM);
	vboxL.getChildren().add(etiquette3);
	vboxL.getChildren().add(texte3);
	vboxL.getChildren().add(etiquette);
	vboxL.getChildren().add(texte);
	vboxL.getChildren().add(etiquette2);
	vboxL.getChildren().add(texte2);
	vboxL.getChildren().add(vbox2);
	vbox2.getChildren().add(bouton);
	vboxL.setSpacing(10);// Ajoute un espace de 10 pixels entre les composants
	vboxL.setPadding(new Insets(20, 20, 20, 20)); // Ajoute des marges autour du conteneur
	vbox2.setAlignment(Pos.CENTER);
	etiquette.setMaxWidth(Double.MAX_VALUE);
	texte.setMaxWidth(Double.MAX_VALUE);
	texte.setMaxHeight(0);
	texte2.setMaxHeight(0);
	texte3.setMaxHeight(0);
	VBox.setVgrow(etiquette, Priority.ALWAYS);
	VBox.setVgrow(bouton, Priority.ALWAYS);
	primaryStage.setScene(scene);
	primaryStage.show();
	}


}

