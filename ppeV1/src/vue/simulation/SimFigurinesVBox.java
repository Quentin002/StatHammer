package vue.simulation;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class SimFigurinesVBox extends VBox
{
	private int column;
	private ArrayList<SimAptAndWeaponsVBox> weapons_aptitudes_menu_view = new ArrayList<SimAptAndWeaponsVBox>();
	private String[][] figs;
	//private ArrayList<Button> group_buttons = new ArrayList<Button>();
	private boolean open = false; // les figurines sont cachées par défaut
	
	public SimFigurinesVBox(int col, String[][] figs_names){
		column = col;
		figs = figs_names;
		this.setStyle("-fx-padding: 2px;");
	}
	
	public boolean isOpen(){
		return open;
	}
	public void changeState(){
		open ^= true; // inversion de bouléen avec un masque 00000001
	}
	
	public String[][] getFigurines(){
		return figs;
	}
//	public ArrayList<Button> getFigGroupButtons(){
//		return group_buttons;
//	}
	
	public void setFigurines()
	{
		// parcours des groupes de figurines = 1ère dimension du String[][]
		for(int i = 0; i < figs.length; i++)
		{
			FlowPane fig_group = new FlowPane();
			
			if(column == 1)
			{
				// boutons numérotés des groupes de figurines
				Button number = new Button(Integer.toString(i + 1));
				number.setAlignment(Pos.CENTER);
				number.setMinHeight(25);
				number.setMinWidth(25);
				number.setStyle("-fx-text-fill: white; -fx-background-color: black;");
				fig_group.getChildren().add(number);
				
				// zone des armes et aptitudes
				SimAptAndWeaponsVBox weapons_aptitudes_menu = new SimAptAndWeaponsVBox();
				weapons_aptitudes_menu.setFigGroup(i + 1, "nom de la figurine", figs[i].length);
				String[][] weapon_list = {{"arme 1", "arme 2", "arme 3", "arme 4"},
					{"arme 5", "arme 6"},
					{"arme 7", "arme 8", "arme 9"}};
				String[][] aptitude_list = {{"aptitude 1", "aptitude 2"},
					{"aptitude 3", "aptitude 4", "aptitude 5"},
					{"aptitude 6", "aptitude 7", "aptitude 8", "aptitude 9"}};
				weapons_aptitudes_menu.setArmes(weapon_list);
				weapons_aptitudes_menu.setAptitudes(aptitude_list);
				weapons_aptitudes_menu_view.add(weapons_aptitudes_menu);

				number.setOnAction(e ->
				{
					weapons_aptitudes_menu.set_apt_and_weapons();
					//weapons_aptitudes_menu.prefWidthProperty().bind(this.widthProperty().multiply(0.2));
					AfficheSimulation.setColumn2Bottom(weapons_aptitudes_menu);
				});
			}
 			
			
			// boutons des figurines
			for(int j = 0; j < figs[i].length; j++)
			{
				HBox one_fig_box = new HBox();
				
				Image one_image = new Image("/images/android-fill.png");
				ImageView one_image_box = new ImageView();
				
				// dimensions
				one_image_box.setPreserveRatio(true);
				//one_image_box.setFitHeight(24); // appeler une des deux méthodes de dimensionnement
				one_image_box.setFitWidth(24);
				one_image_box.setImage(one_image);
				
				if(column == 1)
				{
					one_fig_box.getChildren().add(one_image_box);
				}
				else
				{
					CheckBox checkbox = new CheckBox();
					checkbox.setGraphic(one_image_box);
					fig_group.getChildren().add(checkbox);
				}
				Label hp = new Label("PV: ");
				Spinner<Integer> spinner = new Spinner<>(0, 5, 5);
				spinner.setMaxWidth(45);
				one_fig_box.getChildren().addAll(hp, spinner);
				SimFigurinesVBox.setMargin(one_fig_box, new Insets(5));
				fig_group.getChildren().add(one_fig_box);
				
			}
			SimFigurinesVBox.setMargin(fig_group, new Insets(3));
			
			this.getChildren().add(fig_group);
			
			// actions des boutons
		}
		

//        ArrayList<Button> buttons_units_list1 = units_list1.getButtons();
//        ArrayList<SimFigurinesHBox> unit_box_list1 = units_list1.getUnitBox();
// 		for(int i = 0; i < unit_box.size(); i++)
// 		{
// 			final int j = i; // merci chatgpt pour le trick!
// 			// impossible d'écrire unit_box.get(i), i ne peut entrer dans la fonction lambda parce qu'il est susceptible de changer à l'extérieur de celle-ci
// 			
// 			buttons_units_list1.get(i).setOnAction(e ->
// 			{
// 				unit_box_list1.get(j).changeState();
// 				if(unit_box_list1.get(j).isOpen())
// 				{
// 					// afficher figurines
// 					unit_box_list1.get(j).setFigurines();
// 					if(selected_unit != j + 1)
// 					{
// 						selected_unit = j + 1;
// 						apt_and_weapons_menu.getChildren().clear();
// 						apt_and_weapons_menu.set_apt_and_weapons(selected_unit);
// 					}
// 					
// 					// apt_and_weapons_menu
// 				}
// 				else
// 				{
// 					// cacher figurine
// 					unit_box_list1.get(j).getChildren().clear(); // index est constant dans sa portée, alors que i change
// 					apt_and_weapons_menu.getChildren().clear();
// 					selected_unit = 0; // valeur "impossible"
// 				}
// 			});
// 		}
	}
}
