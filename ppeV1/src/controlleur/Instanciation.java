package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.Aptitude;
import modele.AptitudeArme;
import modele.Arme;
import modele.ArmeDist;
import modele.ArmeMelee;
import modele.Armee;
import modele.ArmeeListe;
import modele.Faction;
import modele.Figurine;
import modele.Unit;
import modele.User;

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
	
	public static ArrayList<Unit> getUniteOfArmy(Armee armee){
		ArrayList<Unit> rendu = new ArrayList<>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
			nom = conec.select("SELECT u.id_unite, u.nom_unite,  u.points_unite,u.logo_unite FROM unite u JOIN armee a USING (id_armee) WHERE a.nom_armee = ? LIMIT 20;",armee.getName() );
			
			
			for (int i = 0;i<nom.size();i = i+4) {
				rendu.add(new Unit(Instanciation.getFigurine(nom.get(i+1)),Integer.parseInt(nom.get(i)),nom.get(i+1), Integer.parseInt(nom.get(i+2)),nom.get(i+3),armee));
			}
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	public static ArrayList<Figurine> getFigurine(String unitName){
		ArrayList<Figurine> rendu = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		try {
			
			temp = conec.select("SELECT f.nom_figurine,f.M,f.E,f.SV,f.PV,f.CD,f.CO,r.nb_figurine FROM figurine f "
					+ "JOIN remplir r USING (id_figurine) JOIN unite u USING(id_unite) WHERE u.nom_unite = ?;",unitName );
			
			
			for (int i = 0;i<temp.size();i = i+8) {
				for (int j =0;j< Integer.parseInt(temp.get(7));j++) {
					rendu.add(new Figurine(Instanciation.getArme(temp.get(i)),Instanciation.getAptitude(temp.get(i)),temp.get(i),"",temp.get(i+1),Integer.parseInt(temp.get(i+2)),Integer.parseInt(temp.get(i+3)),Integer.parseInt(temp.get(i+4)),Integer.parseInt(temp.get(i+5)),Integer.parseInt(temp.get(i+6))));
			
				}
			}
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Arme> getArme(String figNom){
		ArrayList<Arme> rendu = new ArrayList<>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
			nom = conec.select("SELECT a.nom_arme,a.PORTEE,a.A,a.F,a.PA,a.D,t.CT,m.CC FROM figurine f JOIN equiper e USING(id_figurine) JOIN arme a USING (id_arme) LEFT JOIN tir t USING (id_tir) LEFT JOIN melee m USING(id_melee) WHERE f.nom_figurine = ?;",figNom );
			
			
			for (int i = 0;i<nom.size();i = i+8) {
				if (nom.get(i+7) == null) {
					rendu.add(new ArmeDist(nom.get(i),Instanciation.getAptitudeArme(nom.get(i)),nom.get(i+1),nom.get(i+2),Integer.parseInt(nom.get(i+3)),Integer.parseInt(nom.get(i+4)),nom.get(i+5),Integer.parseInt(nom.get(i+6))));
				}else {
					rendu.add(new ArmeMelee(nom.get(i),Instanciation.getAptitudeArme(nom.get(i)),nom.get(i+1),nom.get(i+2),Integer.parseInt(nom.get(i+3)),Integer.parseInt(nom.get(i+4)),nom.get(i+5),Integer.parseInt(nom.get(i+7))));
				}
			}
			
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	public static ArrayList<Aptitude> getAptitude(String figNom){
		ArrayList<Aptitude> rendu = new ArrayList<>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
			nom = conec.select("SELECT a.nom_aptitude FROM aptitude a JOIN permettre USING (id_aptitude) JOIN figurine f USING(id_figurine) WHERE f.nom_figurine = ?;",figNom );
			
			for (int i = 0;i<nom.size();i = i++) {
				rendu.add(new Aptitude(nom.get(i)));
			}
			
			
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	public static ArrayList<AptitudeArme> getAptitudeArme(String armeNom){
		ArrayList<AptitudeArme> rendu = new ArrayList<>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
			nom = conec.select("SELECT a.nom_aptitude_arme FROM aptitude_arme a JOIN posseder USING (id_aptitude_arme) JOIN arme ar USING(id_arme) WHERE ar.nom_arme = ?;",armeNom );
			
			
			for (int i = 0;i<nom.size();i = i++) {
				rendu.add(new AptitudeArme(nom.get(i)));
			}
			
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	public static void insertListe(ArmeeListe armee,User session) {
		conec.insertListe(armee, session);
	}
	
}
