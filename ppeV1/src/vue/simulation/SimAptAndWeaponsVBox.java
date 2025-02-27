package vue.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SimAptAndWeaponsVBox extends VBox
{
	private int group_number;
	private String group_name;
	int group_size;
	String[][] weapon_list;
	String[][] aptitude_list;
	
	//public SimAptAndWeaponsVBox(){}
	
	// setters
	public void setFigGroup(int fig_group_number, String fig_group_name, int fig_group_size){
		group_number = fig_group_number;
		group_name = fig_group_name;
		group_size = fig_group_size;
	}
	public void setArmes(String[][] weapons){
		weapon_list = weapons;
	}
	public void setAptitudes(String[][] aptitudes){
		aptitude_list = aptitudes;
	}
	
	public void set_apt_and_weapons()
	{
		/* -- ligne 1: numéro du group et nom figurine -- */
		HBox first_row = new HBox();
		
		// numéro du group de figurines identiques
		Label number = new Label(Integer.toString(group_number));
		number.setAlignment(Pos.CENTER);
		number.setMinHeight(25);
		number.setMinWidth(25);
		number.setStyle("-fx-text-fill: white; -fx-background-color: black;");
		HBox.setMargin(number, new Insets(0, 2, 2, 0));
				
		Label figurine = new Label(group_name);
		first_row.getChildren().addAll(number, figurine);
		
		
		/* -- ligne 2: menu déroulant choix de l'arme -- */
		ChoiceBox<String> select_weapon = new ChoiceBox<String>();
		for(int i = 0; i < weapon_list[group_number - 1].length; i++)
        {
			select_weapon.getItems().add(weapon_list[group_number - 1][i]);
        }
		select_weapon.setValue(weapon_list[group_number - 1][0]);
		
		// choix du nombre d'attaquants (= nombre d'armes)
		Slider nb_of_attackers = new Slider(1, group_size, 1);
		nb_of_attackers.setMajorTickUnit(1);
		nb_of_attackers.setMinorTickCount(0);
		nb_of_attackers.setSnapToTicks(true);
		//nb_of_attackers.setShowTickMarks(true);
		nb_of_attackers.setShowTickLabels(true);
		//nb_of_attackers.setBlockIncrement(1);
		nb_of_attackers.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Valeur: " + newValue.intValue()); // debug
        });
		
		// checkbox
		TilePane aptitudes = new TilePane();
		for(int i = 0; i < aptitude_list[group_number - 1].length; i++)
        {
			CheckBox one_aptitude = new CheckBox(aptitude_list[group_number - 1][i]);
			one_aptitude.setStyle("-fx-padding: 2px;");
			aptitudes.getChildren().add(one_aptitude);
        }
		
		//SimAptAndWeaponsVBox.setMargin(this.getChildren(), new Insets(5));
		this.getChildren().addAll(first_row, select_weapon, nb_of_attackers, aptitudes);
		//this.setAlignment(Pos.CENTER); // fonctionne pour le label
		
	}
}
