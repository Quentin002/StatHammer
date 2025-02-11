package controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Arme;

public class BDD {




	private Connection connec;
	private Statement stat;
	private String base;
	
	public BDD(String login,String pwd,String base) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base,login,pwd);
			this.stat = connec.createStatement();
			this.base = base;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public BDD(String login,String pwd,String base,Bool verif) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base,login,pwd);
			this.stat = connec.createStatement();
			this.base = base;
			verif.setBin(true);
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
	public void lister() {
		try {
			ResultSet rs = stat.executeQuery("SELECT * FROM "+base);
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
				System.out.println(res);
				res="";
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
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
	



