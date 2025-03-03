package controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Arme;
import modele.ArmeeListe;
import modele.Armee;
import modele.Unit;
import modele.User;

public class BDD {

	private Connection connec;
	private PreparedStatement stat = null;
	private ResultSet rs ;
	
	private static String user;
	private static String password;
	private static String dbname;
		
	public BDD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connec = DriverManager.getConnection("jdbc:mysql://mysql-stathammer.alwaysdata.net:3306/"+ dbname, user, password);
			this.rs = connec.prepareStatement("SELECT * FROM faction;").executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void setInfos(String login, String pwd, String base)
	{
		user = login;
		password = pwd;
		dbname = base;
	}
	
	public PreparedStatement getPreparedStatement(String sql, Object... params) throws SQLException
	{
		stat = connec.prepareStatement(sql);
		if(params.length > 0)
		{
			for (int i = 0; i < params.length; i++) {
				stat.setObject(i + 1, params[i]);
			}
		}
		return stat;
	}
	
	
	public void close() {
		try {
			connec.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> selectUtilisateur(String nom, String mdp) {
		ArrayList<String> rendu = new ArrayList<String>();
		try {
			stat = this.getPreparedStatement("SELECT nom_utilisateur FROM utilisateur WHERE nom_utilisateur=? AND mdp_utilisateur = ?;",
				nom, mdp);
			
			ResultSet rs = stat.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			String res ="";
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			while(rs.next()) {
				for (String col : column) {
					res += rs.getString(col)+" ";
				}
				
				rendu.add(res);
				res="";
				
			}
			return rendu;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
			
		}
		return rendu;
	}
	public ArrayList<String> select(String requete,String... param ) throws SQLException{
		ArrayList<String> rendu = new ArrayList<String>();
		try {
			
			
			stat = connec.prepareStatement(requete);
			if(param.length>0) {
				for(int i = 1;i<=param.length;i++) {
					stat.setString(i, param[i-1]);
				}
			}
			
			rs.close();
			rs = stat.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			ArrayList<String> column = new ArrayList<String>();
			
			
			for(int i =1;i<=md.getColumnCount();i++) {
				column.add(md.getColumnName(i));
			}
			while(rs.next()) {
				for (String col : column) {
					rendu.add(rs.getString(col));
				}
				
				
				
			}
			return rendu;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
			
		}
		return rendu;
	}
	
	public void updateUtilisateur(String pseudo,int id) {
		try {
			stat = this.getPreparedStatement("UPDATE `utilisateur` SET nom_utilisateur=? WHERE id_utilisateur=?;",pseudo,id);
			stat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	public void updateMp(String mdp,int id) {
		try {
			stat = this.getPreparedStatement("UPDATE `utilisateur` SET mdp_utilisateur=? WHERE id_utilisateur=?;",mdp,id);
			stat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	public int UtilisateurID(String nom, String mdp) {
		try {
			stat = this.getPreparedStatement("SELECT id_utilisateur FROM utilisateur WHERE nom_utilisateur=? AND mdp_utilisateur = ?;",
				nom, mdp);
			
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("id_utilisateur");

			return id;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
			
		}
		return 0;
	}
	public String UtilisateurRole(String nom, String mdp) {
		String role = "null";
		try {
			stat = this.getPreparedStatement("SELECT role_utilisateur FROM utilisateur WHERE nom_utilisateur=? AND mdp_utilisateur = ?;",
				nom, mdp);
			
			ResultSet rs = stat.executeQuery();
			rs.next();
			role = rs.getString("role_utilisateur");

			return role;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
			
		}
		return role;
	}
	
	public String UtilisateurMdp(int id) {
		String mdp = "test null";
		try {
			stat = this.getPreparedStatement("SELECT mdp_utilisateur FROM utilisateur WHERE id_utilisateur=?;",
				id);
			
			ResultSet rs = stat.executeQuery();
			rs.next();
			mdp = rs.getString("mdp_utilisateur");

			return mdp;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
			
		}
		return mdp;
	}
	
	public void getArmyLists(User session)
	{
		String sql = "SELECT nom_liste, description_liste, data_liste FROM liste WHERE id_utilisateur = ?;";
		try {
			stat = this.getPreparedStatement(sql, session.getId());
			ResultSet rs = stat.executeQuery();
			session.getListes().clear();
			while(rs.next()){
				ArmeeListe one_list = new ArmeeListe(rs.getString("nom_liste"), rs.getString("description_liste"), rs.getString("data_liste"));
				session.addArmee(one_list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getUnits(ArmeeListe list) {
		String sql = "";
		//list.addUnit(null);
	}
	
	public Armee getArmy(Unit unit) {
		String sql = "SELECT nom_armee, logo_armee JOIN unite USING (id_armee) WHERE id_unite = ?;";
		Armee army = null;
		try {
			stat = this.getPreparedStatement(sql, unit.getId());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()){
				army = new Armee(rs.getString("nom_armee"), rs.getString("logo_armee"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return army;
	}
	
	public void newEvent(String nom_evt, String nom_img,String desc, String date) {
		try {
	        
			// Utilisation de requête préparée avec des paramètres
	        String requete = "INSERT INTO evenement (nom_evenement, nom_image, description_evenement, date_evenement, id_utilisateur) VALUES (?, ?, ?, ?, 3)";
	        stat = this.getPreparedStatement(requete);
	        
	        // Associer les valeurs aux paramètres
	        stat.setString(1, nom_evt);
	        stat.setString(2, nom_img);
	        stat.setString(3, desc);
	        stat.setString(4, date);
	        
	        // Exécution de la requête
	        stat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("probleme sur newEvent");
			e.printStackTrace();
		}
	}		
	
	public void ajouter(Arme a) {
		try {
			stat.executeUpdate("INSERT INTO arme (id,prenom,login,password,statut,age) VALUES (NULL,");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprimer(Arme a) {
		try {
			stat.executeUpdate("DELETE FROM acces WHERE login =''");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
	



