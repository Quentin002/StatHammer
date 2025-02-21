package controlleur;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Aptitude;
import modele.AptitudeArme;
import modele.Arme;
import modele.ArmeDist;
import modele.ArmeMelee;
import modele.Armee;
import modele.Faction;
import modele.Figurine;
import modele.Unit;

public class Instanciation {

	public static BDD conec;
	
	public static ArrayList<Faction> getFaction() {
		ArrayList<Faction> rendu = new ArrayList<Faction>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
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
			
			ResultSet tab = conec.selectRS("SELECT f.nom_figurine,f.M,f.E,f.SV,f.PV,f.CD,f.CO,r.nb_figurine FROM figurine f "
					+ "JOIN remplir r USING (id_figurine) JOIN unite u USING(id_unite) WHERE u.nom_unite = ?;",unitName );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				for(int i=0;i<tab.getInt("nb_figurine");i++) {
					rendu.add(new Figurine(Instanciation.getArme(tab.getString("nom_figurine")),Instanciation.getAptitude(tab.getString("nom_figurine")),
						tab.getString("nom_figurine"),"",tab.getString("M"),tab.getInt("E"),tab.getInt("SV"),tab.getInt("PV"),tab.getInt("CD"),tab.getInt("CO")));
				}
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Arme> getArme(String figNom){
		ArrayList<Arme> rendu = new ArrayList<>();
		
		try {
			
			ResultSet tab = conec.selectRS("SELECT a.nom_arme,a.PORTEE,a.A,a.F,a.PA,a.D,t.CT,m.CC FROM figurine f JOIN equiper e USING(id_figurine) JOIN arme a USING (id_arme) LEFT JOIN tir t USING (id_tir) LEFT JOIN melee m USING(id_melee) WHERE f.nom_figurine = ?;",figNom );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				if(tab.getString("PORTEE").equals("Mêlée")) {
					rendu.add(new ArmeMelee(tab.getString("nom_arme"),Instanciation.getAptitudeArme(tab.getString("nom_arme")),tab.getString("A"),tab.getInt("F"),tab.getInt("PA"),tab.getString("D"),tab.getInt("CC")));
				}else {
					rendu.add(new ArmeDist(tab.getString("nom_arme"),Instanciation.getAptitudeArme(tab.getString("nom_arme")),tab.getString("A"),tab.getInt("F"),tab.getInt("PA"),tab.getString("D"),tab.getInt("CT")));
				}
				
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Aptitude> getAptitude(String figNom){
		ArrayList<Aptitude> rendu = new ArrayList<>();
		
		try {
			
			ResultSet tab = conec.selectRS("SELECT a.nom_aptitude FROM aptitude a JOIN permettre USING (id_aptitude) JOIN figurine f USING(id_figurine) WHERE f.nom_figurine = ?;",figNom );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(new Aptitude(tab.getString("nom_aptitude")));
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	public static ArrayList<AptitudeArme> getAptitudeArme(String armeNom){
		ArrayList<AptitudeArme> rendu = new ArrayList<>();
		
		try {
			
			ResultSet tab = conec.selectRS("SELECT a.nom_aptitude_arme FROM aptitude_arme a JOIN posseder USING (id_aptitude_arme) JOIN arme ar USING(id_arme) WHERE ar.nom_arme = ?;",armeNom );
			ResultSetMetaData md = tab.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			
			
			
			while(tab.next()) {
				rendu.add(new AptitudeArme(tab.getString("nom_aptitude_arme")));
			}
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
}
