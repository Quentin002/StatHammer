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
	public BDD(String login,String pwd,String base) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connec = DriverManager.getConnection("jdbc:mysql://mysql-stathammer.alwaysdata.net:3306/"+base,login,pwd);
			this.stat = null;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
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
			
			String requete = "SELECT nom_utilisateur FROM utilisateur WHERE nom_utilisateur=? AND mdp_utilisateur = ?;";
			stat = connec.prepareStatement(requete);
			stat.setString(1, nom);
			stat.setString(2, mdp);
			
			ResultSet rs = stat.executeQuery();
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
	



