package vue.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import controlleur.ControlleurSimu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import modele.Figurine;
import modele.Unit;

public class SimFigurinesVBox extends VBox
{
	private Unit unit;
	private int column;
	private ArrayList<SimAptAndWeaponsVBox> weapons_aptitudes_menu_view = new ArrayList<SimAptAndWeaponsVBox>();
	private boolean open = false; // les figurines sont cachées par défaut
	
	public SimFigurinesVBox(int col, Unit unit){
		column = col;
		this.unit = unit;
		this.setStyle("-fx-padding: 2px;");
	}
	
	public boolean isOpen(){
		return open;
	}
	public void changeState(){
		open ^= true; // inversion de bouléen avec un masque 00000001
	}
	
//	public String[][] getFigurines() {}
	
	public void setFigurines()
	{
		HashMap<String, ArrayList<Figurine>> identical_figs = new HashMap<String, ArrayList<Figurine>>();
		
		for(Figurine fig : unit.getFigurines())
		{
			if(!identical_figs.containsKey(fig.getNom())) // si nouveau type de figurines
			{
				identical_figs.put(fig.getNom(), new ArrayList<Figurine>()); // création d'uné paire clé/liste dans la hashmap
			}
			identical_figs.get(fig.getNom()).add(fig); // récupérer la liste correspondante à la clé et ajouter la figurine
		}
		
		// parcours des groupes de figurines = 1ère dimension du String[][]
		//for(int i = 0; i < identical_figs.size(); i++)
		int i = 0; // pour les boutons numérotés
		for(HashMap.Entry<String, ArrayList<Figurine>> entry : identical_figs.entrySet())
		{
			FlowPane fig_group = new FlowPane();
			String key = entry.getKey();
			ArrayList<Figurine> valueFigList = entry.getValue();
			i++;
			if(column == 1)
			{
				// boutons numérotés des groupes de figurines
				Button number = new Button(Integer.toString(i));
				number.setAlignment(Pos.CENTER);
				number.setMinHeight(25);
				number.setMinWidth(25);
				number.setStyle("-fx-text-fill: white; -fx-background-color: black;");
				fig_group.getChildren().add(number);
				
				// zone des armes et aptitudes
				SimAptAndWeaponsVBox weapons_aptitudes_menu = new SimAptAndWeaponsVBox();
				weapons_aptitudes_menu.setFigGroup(i, key, valueFigList.size());
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
					AfficheSimulation.setColumn1Bottom(weapons_aptitudes_menu);
				});
			}
			
			// figurines: checkbox, image, PV et spinner
			for(Figurine fig : valueFigList)
			{
				HBox one_fig_box = new HBox();
				Image one_image;
				if(fig.getHP() > 0) {
					one_image = new Image("/images/android-fill.png");
				}
				else { // utile?
					one_image = new Image("/images/android-line.png");
				}
				ImageView one_image_box = new ImageView();
				//one_fig_box.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 2; -fx-padding: 0 3px 0 0;");
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
					one_fig_box.getChildren().add(checkbox);
				}
				if(column == 1) {
					Label hp_label = new Label(fig.getHP() + " PV ");
					one_fig_box.getChildren().add(hp_label);
				}
				else {
					Label hp_label = new Label("PV:");
					Spinner<Integer> spinner = new Spinner<>(0, fig.getHP(), fig.getHP());
					spinner.setMaxWidth(45);
					one_fig_box.getChildren().add(hp_label);
					one_fig_box.getChildren().add(spinner);
					one_fig_box.setStyle("-fx-padding: 0 3px 0 0;");
					
					spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
						ControlleurSimu.hpUpdate(newValue, fig, one_image_box);
			        });
				}
				
				SimFigurinesVBox.setMargin(one_fig_box, new Insets(5));
				fig_group.getChildren().add(one_fig_box);
			}
			SimFigurinesVBox.setMargin(fig_group, new Insets(3));
			
			this.getChildren().add(fig_group);
		}
	}
}

