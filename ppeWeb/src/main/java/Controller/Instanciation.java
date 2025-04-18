package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Model.Aptitude;
import Model.AptitudeArme;
import Model.Arme;
import Model.ArmeDist;
import Model.ArmeMelee;
import Model.Armee;
import Model.ArmeeListe;
import Model.Faction;
import Model.Figurine;
import Model.Unit;
import Model.User;

public class Instanciation {
	
	//classe qui fait des appel à la BDD pour instancier les classes modele
	
	private static BDD conec;
	private static Battle battle_data = new Battle();
	
	public static Battle getBattleData() {
		return battle_data;
	}
	public static void resetBattle() {
		battle_data = null;
		battle_data = new Battle();
	}
	
	//recupere la liste des faction de la BDD
	public static ArrayList<Faction> getFaction() {
		
		ArrayList<Faction> rendu = new ArrayList<Faction>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			conec = new BDD();
			nom = conec.select("SELECT nom_faction FROM faction;" );
			
			for (String string : nom) {
				rendu.add(new Faction(string));
			}
			
		}catch(SQLException e) {
			
		}
		conec.close();
		return rendu;
	}
	
	//recupere la liste des armée d'une Faction précise
	public static ArrayList<Armee> getArmee(Faction faction){
		ArrayList<Armee> rendu = new ArrayList<Armee>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			conec = new BDD();
			
			nom = conec.select("SELECT a.nom_armee,a.logo_armee FROM armee a JOIN faction f USING (id_faction) WHERE f.nom_faction = ?;",faction.getNom() );
			
			
			for (int i = 0;i<nom.size();i = i+2) {
				
				rendu.add(new Armee(nom.get(i),nom.get(i+1),faction));
			}
			
		}catch(SQLException e) {
			
		}
		conec.close();
		return rendu;
	}
	
	//Récupere la liste des unités d'une armée précise
	public static ArrayList<Unit> getUniteOfArmy(Armee armee){
		ArrayList<Unit> rendu = new ArrayList<Unit>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			conec = new BDD();
			nom = conec.select("SELECT u.id_unite, u.nom_unite,  u.points_unite,u.logo_unite FROM unite u JOIN armee a USING (id_armee) WHERE a.nom_armee = ? LIMIT 20;",armee.getName() );
			
			
			for (int i = 0;i<nom.size();i = i+4) {
				rendu.add(new Unit(Instanciation.getFigurine(nom.get(i+1)),Integer.parseInt(nom.get(i)),nom.get(i+1), Integer.parseInt(nom.get(i+2)),nom.get(i+3),armee));
			}
			
			
			
			
		}catch(SQLException e) {
			
		}
		conec.close();
		return rendu;
	}
	public static void getArmyLists(User session)
	{
		conec = new BDD();
		String sql = "SELECT nom_liste, description_liste, data_liste FROM liste WHERE id_utilisateur = ?;";
		try {
			PreparedStatement stat = conec.getPreparedStatement(sql, session.getId());
			ResultSet rs = stat.executeQuery();
			session.getListes().clear();
			while(rs.next()){
				ArmeeListe one_list = new ArmeeListe(rs.getString("nom_liste"), rs.getString("description_liste"), rs.getString("data_liste"));
				session.addArmee(one_list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conec.close();
	}
	
	public static void getUnitsOfAList(ArmeeListe list) {
		conec = new BDD();
		String sql = "SELECT nom_unite, points_unite, logo_unite, nom_armee, logo_armee"
			+ " FROM unite JOIN contenir USING (id_unite) JOIN liste USING (id_liste) JOIN armee USING (id_armee)"
			+ " WHERE liste.nom_liste = ? AND unite.id_unite = contenir.id_unite AND contenir.id_liste = liste.id_liste;";
		
		try {
			PreparedStatement stat = conec.getPreparedStatement(sql, list.getName());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				list.addUnit(new Unit(Instanciation.getFigurine(rs.getString(1)),
						rs.getString(1), rs.getInt(2), rs.getString(3),
						new Armee(rs.getString(4), rs.getString(5))));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conec.close();
	}
	
	//Récupere les Figurine présente dans une unité précise
	public static ArrayList<Figurine> getFigurine(String unitName){
		ArrayList<Figurine> rendu = new ArrayList<Figurine>();
		ArrayList<String> temp = new ArrayList<String>();
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
	
	//Récupère les armes d'une figurine donnée
	public static ArrayList<Arme> getArme(String figNom){
		ArrayList<Arme> rendu = new ArrayList<Arme>();
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
	
	//Récupère les Aptitude d'une figurine donnée
	public static ArrayList<Aptitude> getAptitude(String figNom){
		ArrayList<Aptitude> rendu = new ArrayList<Aptitude>();
		ArrayList<String> nom = new ArrayList<String>();
		try {
			
			nom = conec.select("SELECT a.nom_aptitude FROM aptitude a JOIN permettre USING (id_aptitude) JOIN figurine f USING(id_figurine) WHERE f.nom_figurine = ?;",figNom );
			
			
			for (int i = 0;i<nom.size(); i++) {
				rendu.add(new Aptitude(nom.get(i)));
			}
			
			
			
			
			
			
		}catch(SQLException e) {
			
		}
		
		return rendu;
	}
	
	//Récupère les Aptitude d'une arme donnée
	public static ArrayList<AptitudeArme> getAptitudeArme(String armeNom){
		ArrayList<AptitudeArme> rendu = new ArrayList<AptitudeArme>();
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
	
	//Insert une Armée crée dans l'application dans la base de donée en la liant à un utilisateur
	public static void insertListe(ArmeeListe armee,User session) {
		conec = new BDD();
		conec.insertListe(armee, session);
		conec.close();
	}
	
}
