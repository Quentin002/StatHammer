package vue.simulation;

import vue.AfficheAccueil;
import vue.AfficheConnexion;
import vue.afficheTopMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficheSimulation
//public class Main extends Application
{
//	public static void main(String[] args) {
//		launch(args);
//	}
	
	//@Override
	//public void start(Stage primaryStage)
	public static void affiche(Stage primaryStage)
	{
		// listes d'armées
		String[] attacker_lists = {"Liste 1", "Liste 2", "Liste 3"};
		String[] defenser_lists = {"Liste 1", "Liste 2", "Liste 3"};
		
		
		/* -- menu du haut de l'écran -- */
		afficheTopMenu menu = new afficheTopMenu(primaryStage);

        // partie principale de la fenêtre
		HBox main = new HBox();
		
		
        /* -- colonne de gauche -- */
        VBox column1 = new VBox();
        column1.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        column1.setStyle("-fx-background-color: lightgreen;");
        
        // menu choix armée
        ChoiceBox<String> list1_drop_down = new ChoiceBox<>();
        list1_drop_down.prefWidthProperty().bind(column1.widthProperty());
        for(int i = 0; i < attacker_lists.length; i++)
        {
        	list1_drop_down.getItems().add(attacker_lists[i]);
        }
        list1_drop_down.setValue(attacker_lists[0]);
        
        column1.getChildren().add(list1_drop_down);
        
        
        
        /* -- colonne centrale -- */
        VBox column2 = new VBox();
        column2.prefWidthProperty().bind(main.widthProperty().multiply(0.4));
        //column2.setMaxHeight(500);//.bind(main.heightProperty().multiply(1));
        
        	// grande image
        Image image_col2 = new Image("/images/wip.jpg");
		ImageView image_box = new ImageView();
		image_box.setPreserveRatio(true);
		image_box.fitWidthProperty().bind(column2.widthProperty());
		image_box.setImage(image_col2);
		
			// contrairement aux VBox, ce conteneur ne s'adapte pas à la taille des ses enfants, c'est l'image qui s'adapte
		Pane big_image_pane = new Pane();
		big_image_pane.getChildren().add(image_box);
		
			// bouton inverser armées
		Image icons_inversion = new Image("/images/inversion_icons.png");
		ImageView icons_inversion_box = new ImageView(icons_inversion);
		Button inverser_armees = new Button();
		inverser_armees.setGraphic(icons_inversion_box);
			
			// empilement des images
		StackPane column2_top = new StackPane();
		StackPane.setAlignment(inverser_armees, Pos.BOTTOM_CENTER);
		column2_top.getChildren().addAll(big_image_pane, inverser_armees);
		
		
			// -- boites dessous la grande image -- */
		HBox column2_bottom = new HBox();
		
			// sélection arme et aptitudes d'arme
		VBox weapon_menu = new VBox();
		
		// obtenir weapon_menu en appelant une autre classe et avec les données souhaitées
		
//		weapon_menu.setStyle("-fx-background-color: lightblue;");
//		weapon_menu.prefWidthProperty().bind(column2_bottom.widthProperty().multiply(0.5));
//		
//		String unit_name = "nom de l'unité";
//		Label unit_name_label = new Label(unit_name);
//		String[] weapons = {"arme1", "arme2", "arme3"};
//		ChoiceBox<String> weapon_drop_down = new ChoiceBox<>();
//		weapon_drop_down.prefWidthProperty().bind(weapon_menu.widthProperty());
//
//        for(int i = 0; i < weapons.length; i++)
//        {
//        	weapon_drop_down.getItems().add(weapons[i]);
//        }
//        weapon_drop_down.setValue(weapons[0]);        
//        weapon_menu.getChildren().addAll(unit_name_label, weapon_drop_down);
		
		
			// figurines ciblées, zone du milieu en bas à droite
		VBox targetedFigurine = new VBox();
		targetedFigurine.setStyle("-fx-background-color: cyan;");
		targetedFigurine.prefWidthProperty().bind(column2_bottom.widthProperty().multiply(0.5));
		
		
		
			// assemblage
		column2_bottom.getChildren().addAll(weapon_menu, targetedFigurine);
		column2.getChildren().addAll(column2_top, column2_bottom);
		
		
		
        /* -- colonne de droite --*/
        VBox column3 = new VBox();
        column3.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        column3.setStyle("-fx-background-color: lightcoral;");
        
        ChoiceBox<String> list2_drop_down = new ChoiceBox<>();
        list2_drop_down.prefWidthProperty().bind(column3.widthProperty());
        for(int i = 0; i < defenser_lists.length; i++)
        {
        	list2_drop_down.getItems().add(defenser_lists[i]);
        }
        list2_drop_down.setValue(defenser_lists[0]);
        
        column3.getChildren().add(list2_drop_down);
        
        
        
        /* -- assemblage des colonnes -- */
		main.getChildren().addAll(column1, column2, column3);
		
		VBox root_box = new VBox();
		root_box.getChildren().addAll(menu, main);
		
		Scene scene = new Scene(root_box,800,600);
		primaryStage.setTitle("Simulation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/* méthode pour gérer le choix d'une liste d'armées */
	// => obtenir des unités dans la BD
	// => obtenir les VBox des deux colonnes avec une classe SimulationUnits
	
	/* méthode pour gérer le choix d'une unité et en obtenir les armes */
	// => obtenir des armes, aptitudes et aptitudes d'armes
	// => obtenir les noeuds de ce menu
	
	/* méthode pour sélectionner arme, nombre d'attaquants, aptitudes */
	
	/* méthode pour afficher les cibles en sélectionnant une unité défenseuse */
	
	/* méthode pour cibler les figurines qui font prendre dans la gueule */
	
	

}
