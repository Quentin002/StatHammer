package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.Armee;
import modele.Faction;

public class Instanciation {

	
	public static ArrayList<Faction> getFaction() {
		ArrayList<Faction> rendu = new ArrayList<Faction>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			nom = conec.select("SELECT nom_faction FROM faction;" );
			for (String string : nom) {
				rendu.add(new Faction(string));
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Armee> getArmee(Faction faction){
		ArrayList<Armee> rendu = new ArrayList<Armee>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			nom = conec.select("SELECT a.nom_armee,a.logo_armee FROM armee a JOIN faction f USING (id_faction) WHERE f.nom_faction = ?;",faction.getNom() );
			
			for (int i = 0;i<nom.size();i = i+2) {
				rendu.add(new Armee(nom.get(i),nom.get(i+1),faction));
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
}
