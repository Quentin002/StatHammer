package vue;

import java.sql.SQLException;

import controlleur.Connexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.User;

public class AfficheGestionCompte {

public static void affiche(Stage primaryStage,User session) {


	
	Label etiquetteM = new Label("Gestion du compte utilisateur");
	etiquetteM.setFont(new Font("Arial", 24));
	Button bouton1 = new Button("Envoyer");
	Button bouton2 = new Button("Envoyer");
	Label etiquette = new Label("Modifier le pseudonyme : ");
	TextField texte =  new TextField();
	Label etiquette2 = new Label("Modifier le mot de passe : ");
	PasswordField texte2 =  new PasswordField();
	Label etiquette3 = new Label("Confirmer avec mot de passe actuel : ");
	PasswordField texte3 =  new PasswordField();
	VBox vbox = new VBox();
	
	VBox vboxT = new VBox();
	vboxT.getChildren().add(etiquetteM);
	//VBox vboxL = new VBox();
	
	afficheTopMenu menu = new afficheTopMenu(primaryStage,session);
	
	VBox vbox1 = new VBox();
	vbox1.getChildren().add(etiquette);
	vbox1.getChildren().add(texte);
	vbox1.getChildren().add(bouton1);
	
	VBox vbox2 = new VBox();
	vbox2.getChildren().add(etiquette2);
	vbox2.getChildren().add(texte2);
	vbox2.getChildren().add(etiquette3);
	vbox2.getChildren().add(texte3);
	VBox vboxb = new VBox();
	vboxb.getChildren().add(bouton2);
	vbox2.getChildren().add(vboxb);
	
	
	Scene scene = new Scene(vbox, 800, 600);
	vbox.getChildren().add(menu);
	
	vbox.getChildren().add(vboxT);
	vbox.getChildren().add(vbox1);
	vbox.getChildren().add(vbox2);

	vboxT.setAlignment(Pos.CENTER);
	vboxb.setAlignment(Pos.CENTER);
	vbox.setSpacing(10);
	vbox1.setSpacing(10);// Ajoute un espace de 10 pixels entre les composants
	vbox1.setPadding(new Insets(20, 20, 20, 20)); // Ajoute des marges autour du conteneur
	
	vbox2.setSpacing(10);// Ajoute un espace de 10 pixels entre les composants
	vbox2.setPadding(new Insets(20, 20, 20, 20)); // Ajoute des marges autour du conteneur
	vbox1.setAlignment(Pos.CENTER);
	bouton2.setAlignment(Pos.CENTER);
	etiquette.setMaxWidth(Double.MAX_VALUE);
	texte.setMaxWidth(Double.MAX_VALUE);
	texte2.setMaxWidth(Double.MAX_VALUE);
	texte3.setMaxWidth(Double.MAX_VALUE);
	
	bouton1.setOnAction(e -> {
		if(texte.getText() != null && !texte.getText().trim().isEmpty() && texte.getText().trim().contains(" ")==false)
		try {
			Connexion.updatePseudo(texte.getText().trim(),session.getId()); // bdd
			session.setNom(texte.getText().trim()); // instance de l'utilisateur
			menu.setLogin(session.getNom()); // barre de menu
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	});
	
	bouton2.setOnAction(e -> {
		
		try {
			String mdp =Connexion.selectMdp(session.getId());
			if (texte3.getText().trim().equals(mdp)==true) {

				if(texte2.getText() != null && !texte2.getText().trim().isEmpty() && texte2.getText().trim().contains(" ")==false)
				try {
					Connexion.updateMdp(texte2.getText().trim(), session.getId());
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		 catch (SQLException e1) {
			e1.printStackTrace();
		}
		});
	
	primaryStage.setScene(scene);
	primaryStage.show();
	}
}

