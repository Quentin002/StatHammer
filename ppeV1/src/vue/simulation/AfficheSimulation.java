package vue.simulation;

import vue.afficheTopMenu;
import java.util.ArrayList;

import controlleur.ControlleurSimu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Calcul;

public class AfficheSimulation
//public class Main extends Application
{
	private static int selected_unit;
	
//	public static void main(String[] args) {
//		launch(args);	
//	}
	
//	//@Override
//	public void start(Stage primaryStage)
	public static void affiche(Stage primaryStage)
	//public static void affiche(Stage primaryStage, ArrayList<ArmeeListe> liste)
	{
		// listes d'armées
		// y mettre en réalité les noms des listes d'armée
//		for(ArmeeListe one_list : liste)
//			String[] attacker_lists =
//			String[] defenser_lists =
//		{}
		String[] attacker_lists = {"Liste 1", "Liste 2", "Liste 3"};
		String[] defender_lists = {"Liste 1", "Liste 2", "Liste 3"};
		

		VBox root_box = new VBox();
		afficheTopMenu menu = new afficheTopMenu(primaryStage); // menu du haut de l'écran
		HBox main = new HBox(); // partie principale de la fenêtre
		
		
        /* -- colonne de gauche -- */
        ScrollPane column1_box = new ScrollPane(); // conteneur avec barres de défilement
        column1_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        //column1_box.setFitToHeight(true);
        column1_box.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column1 = new VBox();
        column1.prefWidthProperty().bind(column1_box.widthProperty());
        //column1.setMaxHeight(Double.MAX_VALUE);
        //column1.setStyle("-fx-background-color: lightgreen;");
        
        //column1_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        
        
        // menu choix armée
        ChoiceBox<String> list1_drop_down = new ChoiceBox<>();
        list1_drop_down.prefWidthProperty().bind(column1_box.widthProperty());
        for(int i = 0; i < attacker_lists.length; i++)
        {
        	list1_drop_down.getItems().add(attacker_lists[i]);
        }
        list1_drop_down.setValue(attacker_lists[0]);
        
        column1.getChildren().add(list1_drop_down);
        
        // unités colonne 1
        SimUnitsVBox units_list1 = new SimUnitsVBox();
        units_list1.setList(attacker_lists[0]); // valeur par défaut
        column1.getChildren().add(units_list1);
        column1_box.setContent(column1);
        
        // changement de liste
//        list1_drop_down.setOnAction(e -> {
//        	// nettoyage
//        	units_list1.getChildren().clear();
//        	units_list1.setList(list1_drop_down.getValue());
//        	
//        	// on affiche les listes
//        	System.out.println("sélection ChoiceBox 1 : " + list1_drop_down.getValue());            
//        });
        
        
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
		
			// boite de gauche: sélection d'armes et d'aptitudes d'arme
		SimAptAndWeaponsVBox apt_and_weapons_menu = new SimAptAndWeaponsVBox();
		apt_and_weapons_menu.prefWidthProperty().bind(column2_bottom.widthProperty().multiply(0.5));
		//apt_and_weapons_menu.set_apt_and_weapons(1); // 1 par défaut
		
		// actions des boutons qui déplient les unités de la colonne 1
        // cette évènement devait être géré dans SimUnitsVBox, mais on a besoin de le faire ici
        ArrayList<Button> buttons_units_list1 = units_list1.getButtons();
        ArrayList<SimFigurinesHBox> unit_box_list1 = units_list1.getUnitBox();
 		for(int i = 0; i < buttons_units_list1.size(); i++)
 		{
 			final int j = i; // merci chatgpt pour le trick!
 			// impossible d'écrire unit_box.get(i), i ne peut entrer dans la fonction lambda parce qu'il est susceptible de changer à l'extérieur de celle-ci
 			
 			buttons_units_list1.get(i).setOnAction(e ->
 			{
 				unit_box_list1.get(j).changeState();
 				if(unit_box_list1.get(j).isOpen())
 				{
 					// afficher figurines
 					unit_box_list1.get(j).setFigurines();
 					if(selected_unit != j + 1)
 					{
 						selected_unit = j + 1;
 						apt_and_weapons_menu.getChildren().clear();
 						apt_and_weapons_menu.set_apt_and_weapons(selected_unit);
 					}
 					
 					// apt_and_weapons_menu
 				}
 				else
 				{
 					// cacher figurine
 					unit_box_list1.get(j).getChildren().clear(); // index est constant dans sa portée, alors que i change
 					apt_and_weapons_menu.getChildren().clear();
 					selected_unit = 0; // valeur "impossible"
 				}
 			});
 		}
		
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
		
		
			// boite de droite: figurines ciblées, zone du milieu en bas à droite
		VBox targetedFigurine = new VBox();
		targetedFigurine.setStyle("-fx-background-color: cyan;");
		targetedFigurine.prefWidthProperty().bind(column2_bottom.widthProperty().multiply(0.5));
		
		
		
			// assemblage
		column2_bottom.getChildren().addAll(apt_and_weapons_menu, targetedFigurine);
		column2.getChildren().addAll(column2_top, column2_bottom);
		
		
		
        /* -- colonne de droite --*/
		ScrollPane column3_box = new ScrollPane(); // conteneur avec barres de défilement
        column3_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        column3_box.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column3 = new VBox();
        column3.prefWidthProperty().bind(column3_box.widthProperty());
        
        ChoiceBox<String> list2_drop_down = new ChoiceBox<>();        
        list2_drop_down.prefWidthProperty().bind(column3_box.widthProperty());
        for(int i = 0; i < defender_lists.length; i++)
        {
        	list2_drop_down.getItems().add(defender_lists[i]);
        }
        list2_drop_down.setValue(defender_lists[0]);
        
        column3.getChildren().add(list2_drop_down);
        
        // unités colonne 3
        SimUnitsVBox units_list2 = new SimUnitsVBox();
        units_list2.setList(defender_lists[0]); // afficher la première par défaut
        column3.getChildren().add(units_list2);
        column3_box.setContent(column3);
        
        
        /* -- assemblage -- */
		main.getChildren().addAll(column1_box, column2, column3_box);
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
