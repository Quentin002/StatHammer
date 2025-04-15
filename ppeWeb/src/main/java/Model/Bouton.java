package Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Bouton extends Button{

	public Bouton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bouton(String text, Node graphic) {
		super(text, graphic);
		// TODO Auto-generated constructor stub
	}

	public Bouton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	
	public Bouton setOnAction2(EventHandler<ActionEvent> value) {
		super.setOnAction(value);
		return this;
		
	}
}
