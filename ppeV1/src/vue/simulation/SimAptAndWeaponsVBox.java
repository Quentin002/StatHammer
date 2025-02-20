package vue.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SimAptAndWeaponsVBox extends VBox
{
	public SimAptAndWeaponsVBox(){
		this.setStyle("-fx-background-color: yellow;");
	}
	
	public void set_apt_and_weapons(int unit_number)
	{
		String fig_name = "Figurine 1";
		int max_weapons = 10;
		String[] weapon_list = {"arme 1", "arme 2","arme 3", "arme 4"};
		
		Label figurine = new Label(fig_name);
		HBox second_row = new HBox();
		
		// numéro index de l'unité et choix de l'arme
		Label number = new Label(Integer.toString(unit_number));
		//unit_number.setStyle("-fx-background-color: orange;");
		number.setAlignment(Pos.CENTER);
		number.setMinHeight(25);
		number.setMinWidth(25);
		number.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");
		ChoiceBox<String> select_weapon = new ChoiceBox<String>();
		for(int i = 0; i < weapon_list.length; i++)
        {
			select_weapon.getItems().add(weapon_list[i]);
        }
		select_weapon.setValue(weapon_list[0]);
		
		// choix du nombre d'attaquants (= nombre d'armes)
		Slider nb_of_attackers = new Slider(1, max_weapons, 1);
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
		
		
		HBox.setMargin(number, new Insets(0, 5, 2, 5));
		HBox.setMargin(select_weapon, new Insets(0, 5, 2, 0));
		second_row.getChildren().addAll(number, select_weapon);
		//second_row.setAlignment(javafx.geometry.Pos.BASELINE_CENTER);
		
		this.getChildren().addAll(figurine, second_row, nb_of_attackers, aptitudes);
		//this.setAlignment(Pos.CENTER); // fonctionne pour le label
	}
}
