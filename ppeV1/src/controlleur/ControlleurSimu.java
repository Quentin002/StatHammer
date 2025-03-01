package controlleur;

import java.sql.SQLException;

import javafx.scene.layout.Pane;
import modele.Calcul;
import modele.Unit;
import vue.simulation.SimHistogramme;

public class ControlleurSimu {
	private Pane p;
	private Unit u1;
	private Unit u2;
	
	public ControlleurSimu(Pane p, Unit u1, Unit u2) {
		super();
		this.p = p;
		this.u1 = u1;
		this.u2 = u2;
	}
	
	public static void afficheSimu(Pane p,Unit u1,Unit u2) {
		p.getChildren().clear();
		Calcul c =Calcul.bataille(u1, u2);
		
		SimHistogramme.setHist(c.getTabdegat1(), c.getTabmort1(), c.getDegat_moyen1(), c.getMort_moyen1(),p);
	}


}
