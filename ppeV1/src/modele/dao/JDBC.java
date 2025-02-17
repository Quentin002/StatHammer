package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * connexion avec JDBC pour utilisation par la classe fille: SQLexecutor,
 * les paramètres de connexion et les setters sont statiques
 *  
 */
abstract public class JDBC
{
	private static String dbms="mysql";
	private static String strClassName = "com.mysql.cj.jdbc.Driver";
	private static String port = "3306";
	private static String dbname;
	private static String login;
	private static String password;
	private static String host= "localhost";
	private static String strUrl;
	
	/**
	 * retourne une instance de Connection avec JDBC
	 * @return instance de Connection
	 * @throws SQLException en cas d'erreur de connexion
	 * @throws ClassNotFoundException pour Class.forName()
	 */
	protected Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName(strClassName);
		return DriverManager.getConnection(strUrl, login, password);
	}
	
	// setters
	/**
	 * génère l'url utilisée par le driver,
	 * à n'exécuter que lorsque tous les paramètres ont été renseignés (en particulier dbname, login et password),
	 * certains: host, dbms, strClassName et port ont des valeurs par défaut pour un serveur mysql sur localhost
	 */
	public static void setStrUrl() {
		if(dbname != null && login != null && password != null) {
			JDBC.strUrl = "jdbc:" + dbms + "://" + host + ":" + port + "/" +  dbname;
		}
		else System.out.print("il manque des paramètres nécéssaires à la connexion au driver");
	}
	
	public static void setHost(String host) {
		JDBC.host = host;
	}
	public static void setDBMS(String dbms) {
		JDBC.dbms = dbms;
		if(dbms == "mysql")
		{
			JDBC.strClassName = "com.mysql.cj.jdbc.Driver";
			JDBC.port = "3306";
		}
		else if(dbms == "mariadb")
		{
			JDBC.strClassName = "org.mariadb.jdbc.Driver";
			JDBC.port = "3306";
		}
	}
	public static void setDbName(String dbName) {
		JDBC.dbname = dbName;
	}
	public static void setLogin(String login) {
		JDBC.login = login;
	}
	public static void setPassword(String motdepasse) {
		JDBC.password = motdepasse;
	}
	
	/**
	 * donner tous les paramètres de connexion en une fois
	 * il est également possible de les donner un par un avec les setters en finissant pas JDBC.setStrUrl()
	 * @param host adresse du serveur
	 * @param dbms obtenir le nom du driver et le numéro du port à partir du nom du SGBD
	 * @param dbname de la base de données
	 * @param login nom d'utilisateur
	 * @param password mot de passe
	 */
	public static void setInfos(String host, String dbms, String dbname, String login, String password)
	{
		JDBC.dbname = dbname;
		JDBC.login = login;
		JDBC.password = password;
		JDBC.host = host;
		setDBMS(dbms);
		JDBC.setStrUrl();
	}
}
