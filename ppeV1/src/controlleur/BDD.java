package controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Arme;

public class BDD {

	private Connection connec;
	private PreparedStatement stat;

	public BDD() {

	}

	public BDD(String login, String pwd, String base) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connec = DriverManager.getConnection("jdbc:mysql://mysql-stathammer.alwaysdata.net:3306/" + base,
					login, pwd);
			this.stat = null;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception

			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
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
			stat.setString(2, mdp);

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
