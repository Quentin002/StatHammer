package vue.simulation;

import java.util.ArrayList;

import controlleur.ControlleurSimu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import modele.Aptitude;
import modele.Arme;
import modele.Figurine;

public class SimAptAndWeaponsVBox extends VBox
{
	private int group_number;
	private String group_name;
	int group_size;
	String[] weapon_names;
	String[] aptitude_names;
	
	//public SimAptAndWeaponsVBox(){}
	
	// setters
	public void setFigGroup(int fig_group_number, String fig_group_name, int fig_group_size){
		group_number = fig_group_number;
		group_name = fig_group_name;
		group_size = fig_group_size;
	}
	public void setArmes(ArrayList<Arme> weapon_list){
		weapon_names = new String[weapon_list.size()];
		for(int i = 0; i < weapon_list.size(); i++) {
			weapon_names[i] = weapon_list.get(i).getNom();
		}
	}
	public void setAptitudes(ArrayList<Aptitude> aptitude_list){
		aptitude_names = new String[aptitude_list.size()];
		for(int i = 0; i < aptitude_list.size(); i++) {
			aptitude_names[i] = aptitude_list.get(i).getName();
		}
	}
	
	public void setAptAndWeapons(ArrayList<Figurine> fig_group)
	{
		/* -- ligne 1: numéro du group et nom figurine -- */
		HBox first_row = new HBox();
		
		// numéro du group de figurines identiques
		Label number = new Label(Integer.toString(group_number));
		number.setAlignment(Pos.CENTER);
		number.setMinHeight(25);
		number.setMinWidth(25);
		number.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-background-radius: 4;");
		HBox.setMargin(number, new Insets(0, 2, 2, 0));
				
		Label figurine_name = new Label(group_name);
		first_row.getChildren().addAll(number, figurine_name);
		
		
		/* -- ligne 2: menu déroulant choix de l'arme -- */
		ChoiceBox<String> select_weapon = new ChoiceBox<String>();
		
		// sécurité
		for(int i = 0; i < weapon_names.length; i++)
        {
			select_weapon.getItems().add(weapon_names[i]);
        }
		
		// valeur initiale menu déroulante
		Arme weapon = AfficheSimulation.getBattleData().getSeletedWeapon(1, group_name);
		if(weapon != null) {
			select_weapon.setValue(weapon.getNom());
		}
		else {
			select_weapon.setValue(weapon_names[0]);
		}
		// caractéristiques
		FlowPane weapon_stats = new FlowPane();
		setWeaponStats(weapon, weapon_stats);
		
		select_weapon.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ControlleurSimu.selectAWeapon(this, newValue, fig_group, weapon_stats);
        });
		
		// choix du nombre d'attaquants (= nombre d'armes)
		Slider nb_of_attackers = new Slider(0, group_size,
			AfficheSimulation.getBattleData().getSelectedUnit(1).getAliveFigsOfAGroup(group_name));
		nb_of_attackers.setMajorTickUnit(1);
		nb_of_attackers.setMinorTickCount(0);
		nb_of_attackers.setSnapToTicks(true);
		//nb_of_attackers.setShowTickMarks(true);
		nb_of_attackers.setShowTickLabels(true);
		//nb_of_attackers.setBlockIncrement(1);
		
		nb_of_attackers.valueProperty().addListener((observable, oldValue, newValue) -> {
			ControlleurSimu.AliveFigsChoice(1, newValue.intValue(), group_name);
        });
		
		// checkbox
		FlowPane aptitudes = new FlowPane();
		for(int i = 0; i < aptitude_names.length; i++)
        {
			CheckBox one_aptitude = new CheckBox(aptitude_names[i]);
			one_aptitude.setStyle("-fx-padding: 2px;");
			aptitudes.getChildren().add(one_aptitude);
			
			one_aptitude.setOnAction(e -> {
				ControlleurSimu.checkOrUncheckAnAptitude(one_aptitude.isSelected(), one_aptitude.getText(), fig_group);
			});
        }
		
		this.getChildren().addAll(first_row, select_weapon, weapon_stats, nb_of_attackers, aptitudes);
	}
	
	public void setWeaponStats(Arme weapon, FlowPane weapon_stats) {
		weapon_stats.setStyle("-fx-padding: 3px;");
		Label A = new Label("A " + weapon.getA() + " | ");
		Label F = new Label("F " + weapon.getF() + " | ");
		Label PA = new Label("PA " + weapon.getPA() + " | ");
		Label D = new Label("D " + weapon.getD() + " | ");
		Label portee = new Label("portée " + weapon.getPortee());
		weapon_stats.getChildren().addAll(A, F, PA, D, portee);
	}
}

