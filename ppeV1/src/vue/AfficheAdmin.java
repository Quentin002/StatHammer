package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Evenement;
import modele.User;
import javafx.scene.control.TextField;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import controlleur.BDD;
import controlleur.ControllerAdmin;
import controlleur.EvenementController;

public class AfficheAdmin {
	private static VBox events;
	private static Stage stage;
	private static User sess;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		AfficheAdmin.stage = stage;
	}

	public static User getSess() {
		return sess;
	}

	public static void setSess(User sess) {
		AfficheAdmin.sess = sess;
	}

	public static VBox getEvents() {
		return events;
	}
	
	public static void affiche(Stage primaryStage, User session) {
		stage = primaryStage;
		sess = session;
		events = EvenementController.EvenementVBox();
		AfficheTopMenu menu = new AfficheTopMenu(stage,sess);
    	Text titre = new Text("Mes évènements");
    	titre.setFont(Font.font("Arial", 30));
        titre.setFill(Color.web("#2C3E50")); 
        titre.setStroke(Color.web("#34495E"));
        titre.setStrokeWidth(1);
        
        
        // Création du DatePicker
        DatePicker date = new DatePicker();
        date.setMaxWidth(60);
        
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
        
        // Action des boutons
        parcourir.setOnAction(e -> {
        	ControllerAdmin.parcourir(stage, imageView);
        });
        
        valider.setOnAction(e -> {
        	if (nom.getText().isEmpty() || descEvent.getText().isEmpty()||ControllerAdmin.getFile() == null){
        		
        		// Affichage du message d'erreur si n champ est vide
                showErrorMessage("Tous les champs doivent être remplis.");
        	}
        	else {
	        	// Récupération de la date sélectionnée
	        	LocalDate localDate = date.getValue();
	        	
	        	if (localDate != null) {
	                // Formater la date en chaîne de caractères au format "yyyy-MM-dd"
	                String dateString = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
	                System.out.println("Date sélectionnée : " + dateString);
	                System.out.println(dateString);
	                System.out.println("Date sélectionnée (java.util.Date) : " + dateString);
	                events = ControllerAdmin.valider(nom.getText(),ControllerAdmin.getFile().getName(),descEvent.getText(), dateString);
	                AfficheAdmin.affiche(stage, sess);
	            }
	        	else {
	        		
	        		// Affichage du message d'erreur si n champ est vide
	                showErrorMessage("Tous les champs doivent être remplis.");
	        	}
        	}
        });
        
        // VBox principal
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(nom, imageView, descEvent, date, parcourir, valider);
        hbox.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.getChildren().addAll(menu,vTitre,hbox,events);
        
        // Ajout du VBox à la scène
        Scene scene = new Scene(root, 800, 600);

        // Ajout d'un titre à la fenêtre
        primaryStage.setTitle("StatHammer : Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public static void setEvents(VBox events) {
		AfficheAdmin.events = events;
	}
	
    // Méthode pour afficher une erreur
    private static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("AbiStock : Erreur de connexion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
