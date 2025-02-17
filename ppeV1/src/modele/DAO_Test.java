/* pour tester les classes */

package modele;

import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import modele.dao.JDBC;
import modele.dao.SQLexecutor;

/*
 * fonction main pour tester des commandes
 */
public class DAO_Test
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		/* paramètres de la BDD à envoyer une fois au début*/
		
		// version envoi tout en même temps
		JDBC.setInfos("localhost", "mysql", "tp", "root", "");
		
		// version envoie un par un, finir par un JDBC.setStrUlr(); 
		JDBC.setHost("localhost");
		JDBC.setDBMS("mysql");
		JDBC.setDbName("tp");
		JDBC.setLogin("root");
		JDBC.setPassword("");
		JDBC.setStrUrl();
		
		
		/* début du contrôleur */
		SQLexecutor executor = new SQLexecutor();
		
		
		executor.setCopyMode(false); // mode copie désactivé (= mode normal)
		// un SELECT produit un ResultSet
		// ne pas oublier de fermer la connexion à la fin du contrôleur
		executor.executeQuery("SELECT * FROM acces WHERE id = ?", 1);
		ResultSet result1 = executor.getResultSet();
		while(result1.next())
		{
			System.out.println("prénom: " + result1.getString("prenom"));
		}
		
		// les requêtes INSERT, UPDATE et DELETE utilise la connexion ouverte et la laissent ouverte
		executor.executeQuery("INSERT INTO acces (prenom, login, password, statut, age) VALUES (?, ?, ?, ?, ?)", "Dylan", "Toto", "Titi", "Etudiant", 22);
		executor.executeQuery("UPDATE acces SET statut = ? WHERE prenom = ?", "joue à fortnite", "Dylan");
		executor.executeQuery("DELETE FROM acces WHERE prenom = ?", "Dylan");

		executor.close(); // fermeture !!
		
		
		
		executor.setCopyMode(true); // mode copie activé
		// un SELECT produit une copie dans une array list d'hashmap
		// la connexion est fermée automatiqument après chaque requête
		executor.executeQuery("SELECT * FROM acces WHERE id = ?", 2);
		ArrayList<HashMap<String, Object>> result2 = executor.getResultCopy();
		
		for(HashMap<String, Object> row : result2) // foreach sur les entrées
		{
			// obtenir un champ d'une entrée
		    System.out.println("prénom: " + row.get("prenom"));
		    
		    // tout afficher avec "foreach"
		    for(HashMap.Entry<String, Object> one_field : row.entrySet())
			{
				System.out.print(one_field.getKey() + ": " + one_field.getValue() + "\n");
			}
		}

		executor.executeQuery("INSERT INTO acces (prenom, login, password, statut, age) VALUES (?, ?, ?, ?, ?)", "Dylan", "Toto", "Titi", "Etudiant", 22);
		executor.executeQuery("UPDATE acces SET statut = ? WHERE prenom = ?", "joue à fortnite", "Dylan");
		executor.executeQuery("DELETE FROM acces WHERE prenom = ?", "Dylan");
	}
}
