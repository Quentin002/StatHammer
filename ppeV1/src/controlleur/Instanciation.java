package controlleur;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Aptitude;
import modele.Arme;
import modele.Armee;
import modele.Faction;
import modele.Figurine;
import modele.Unit;

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
	
	public static ArrayList<Unit> getUnite(Armee armee){
		ArrayList<Unit> rendu = new ArrayList<>();
		
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			ResultSet tab = conec.selectRS("SELECT u.nom_unite, u.logo_unite, u.points_unite FROM unite u JOIN armee a USING (id_armee) WHERE a.nom_armee = ?;",armee.getNom() );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(new Unit(Instanciation.getFigurine(tab.getString("nom_unit")),tab.getString("nom_unit"),tab.getInt("points_unit"),armee));
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	public static ArrayList<Figurine> getFigurine(String unitName){
		ArrayList<Figurine> rendu = new ArrayList<>();
		
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			ResultSet tab = conec.selectRS("SELECT a.nom_armee,a.logo_armee FROM armee a JOIN faction f USING (id_faction) WHERE f.nom_faction = ?;",unitName );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(null);
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Arme> getArme(String figNom){
		ArrayList<Arme> rendu = new ArrayList<>();
		
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			ResultSet tab = conec.selectRS("SELECT a.nom_armee,a.logo_armee FROM armee a JOIN faction f USING (id_faction) WHERE f.nom_faction = ?;",figNom );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(null);
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Aptitude> getAptitude(String figNom){
		ArrayList<Aptitude> rendu = new ArrayList<>();
		
		try {
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			ResultSet tab = conec.selectRS("SELECT a.nom_armee,a.logo_armee FROM armee a JOIN faction f USING (id_faction) WHERE f.nom_faction = ?;",figNom );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(null);
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
}
