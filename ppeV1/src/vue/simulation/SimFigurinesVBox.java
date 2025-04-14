package vue.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import controlleur.ControlleurSimu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
	
	public void setFigurines()
	{
		/* -- boucle sur les groupes de figuriones -- */
		int i = 0; // valeurs des boutons numérotés
		for(HashMap.Entry<String, ArrayList<Figurine>> entry : unit.getIdenticalFigsGroups().entrySet())
		{
			FlowPane fig_group = new FlowPane();
			//fig_group.setStyle("-fx-background-color: rgb(220, 220, 220);");
			String fig_name = entry.getKey();
			ArrayList<Figurine> fig_list = entry.getValue();
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
				weapons_aptitudes_menu.setFigGroup(i, fig_name, fig_list.size());
				weapons_aptitudes_menu_view.add(weapons_aptitudes_menu);

				number.setOnAction(e ->
				{
					ControlleurSimu.makeWeaponsAndAptitudeZone(fig_list, weapons_aptitudes_menu);
				});
			}
			else {
				Label fig_name_label = new Label(fig_name);
				fig_name_label.setStyle("-fx-padding: 0 5px 0 0");
				fig_group.getChildren().add(fig_name_label);
				
				Slider nb_of_defenders = new Slider(0, fig_list.size(),
					AfficheSimulation.getBattleData().getSelectedUnit(2).getAliveFigsOfAGroup(fig_name));
				nb_of_defenders.setMajorTickUnit(1);
				nb_of_defenders.setMinorTickCount(0);
				nb_of_defenders.setSnapToTicks(true);
				//nb_of_attackers.setShowTickMarks(true);
				nb_of_defenders.setShowTickLabels(true);
				//nb_of_attackers.setBlockIncrement(1);
				
				fig_group.getChildren().add(nb_of_defenders);
				
				nb_of_defenders.valueProperty().addListener((observable, oldValue, newValue) -> {
					ControlleurSimu.AliveFigsChoice(2, newValue.intValue(), fig_name);
		        });
			}
			
			
			/* -- figurines d'un groupe -- */
			for(Figurine fig : fig_list)
			{
				HBox one_fig_box = new HBox();
				Image one_image;
				if(fig.getHP() > 0) {
					one_image = new Image("/images/android-fill.png");
				}
				else {
					one_image = new Image("/images/android-line.png");
				}
				ImageView one_image_box = new ImageView();
				one_image_box.setPreserveRatio(true);
				//one_image_box.setFitHeight(24); // appeler une des deux méthodes de dimensionnement
				one_image_box.setFitWidth(24);
				one_image_box.setImage(one_image);
				one_fig_box.getChildren().add(one_image_box);
				
				if(column == 1) {
					Label hp_label = new Label(fig.getHP() + " PV ");
					one_fig_box.getChildren().add(hp_label);
				}
				else {
					Label hp_label = new Label("PV:");
					Spinner<Integer> spinner = new Spinner<>(0, fig.getHPMax(), fig.getHP());
					spinner.setMaxWidth(55);
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

