package controlleur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import modele.Arme;
import modele.ArmeeListe;
import modele.Evenement;
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
			String host = "mysql-stathammer.alwaysdata.net";
			//String host = "ordipolo.fr";
			this.connec = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/"+ dbname, user, password);
			this.rs = connec.prepareStatement("SELECT * FROM faction;").executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception

			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void setInfos(String login, String pwd, String base)
	{
		user = login;
		password = pwd;
		dbname = base;
	}
	
	public Statement getStatement() throws SQLException
	{
		return connec.createStatement();
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
	
	public ArrayList<Object> selectUtilisateur(String nom, String mdp) {
		ArrayList<Object> rendu = new ArrayList<Object>();
		try {

			String requete = "SELECT id_utilisateur, nom_utilisateur, role_utilisateur FROM utilisateur WHERE nom_utilisateur=? AND mdp_utilisateur = ?;";
			stat = connec.prepareStatement(requete);
			stat.setString(1, nom);
			stat.setString(2,String.valueOf(mdp));
			//stat.setString(2,String.valueOf(mdp.hashCode()));

			ResultSet rs = stat.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			ArrayList<String> column = new ArrayList<String>();

			for (int i = 1; i <= md.getColumnCount(); i++) {
				column.add(md.getColumnName(i));
			}
			while (rs.next()) {
				rendu.add(rs.getString("nom_utilisateur"));
				rendu.add(rs.getInt("id_utilisateur"));
				rendu.add(rs.getString("role_utilisateur"));
			}
			return rendu;

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());

		}
		return rendu;
	}

	public ArrayList<String> creerUtilisateur(String adresse, String nom, String mdp) {
		ArrayList<String> crea = new ArrayList<String>();

		String creationRequete = "INSERT INTO utilisateur" + " (nom_utilisateur, email_utilisateur, mdp_utilisateur)"
				+ "VALUES (?, ?, ?);";
		try {
			if (connec == null || connec.isClosed()) {
				System.err.println("La connexion à la bdd n'est pas possible.");
				return crea;
			}
			PreparedStatement stat = connec.prepareStatement(creationRequete);
			stat.setString(1, nom);
			stat.setString(2, adresse);
			stat.setString(3, mdp);

			int rowsInserted = stat.executeUpdate();

			if (rowsInserted > 0) {
				crea.add(adresse);
			}
			stat.close();
		} catch (SQLException e) {
			System.err.println("Erreur SQL : " + e.getMessage());
		}
		return crea;
	}

	public Connection getConnection() {
		return this.connec;
	}

	public void close() {
		try {
			connec.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> select(String requete,String... param ) throws SQLException{
		ArrayList<String> rendu = new ArrayList<String>();
		try {
			PreparedStatement pstat = null;
			
			pstat = connec.prepareStatement(requete);
			if(param.length>0) {
				for(int i = 1;i<=param.length;i++) {
					pstat.setString(i, param[i-1]);
				}
			}
			
			rs.close();
			rs = pstat.executeQuery();
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
	public void insertListe(ArmeeListe armee,User session) {
		try {
			int id;
			ArrayList<String> rendu = new ArrayList<String>();
			String requete = "INSERT INTO liste VALUES(DEFAULT,?,?,?,?);";
			stat = this.getPreparedStatement(requete);
			stat.setString(1, armee.getName());
			stat.setString(2, armee.getDescription());
			stat.setString(3, armee.getData());
			stat.setInt(4, session.getId());
			stat.executeUpdate();
			
			requete = "SELECT id_liste FROM liste WHERE nom_liste = ?;";
			
			rendu = this.select(requete,armee.getName());
			
			id = Integer.parseInt(rendu.getFirst());
			requete = "INSERT INTO contenir VALUES(?,?);";
			
			for(Unit unit : armee.getUnits()) {
				
				
				stat = this.getPreparedStatement(requete);
				stat.setInt(1, unit.getId());
				stat.setInt(2, id);
				stat.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	
	public void updateMp(String mdp,int id) {
		try {
			//stat = this.getPreparedStatement("UPDATE `utilisateur` SET mdp_utilisateur=? WHERE id_utilisateur=?;",mdp.hashCode(),id);
			stat = this.getPreparedStatement("UPDATE `utilisateur` SET mdp_utilisateur=? WHERE id_utilisateur=?;",mdp.hashCode(),id);
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
			
			return 0;
		}
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
