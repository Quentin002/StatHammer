package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * exécuter des requêtes SQL:
 * connexion avec JDBC => préparation de la requête => exécution
 * => copie du ResultSet dans une ArrayList d'HashMap => fermeture de la connexion 
 */
public class SQLexecutor extends JDBC
{
	Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
	private boolean copy_mode = false;
	ArrayList<HashMap<String, Object>> result_copy = null;
	
	// utilise le contructeur par défaut de java
	
	/**
	 * retourne un ResultSet
	 * @return ResultSet
	 */
	public ResultSet getResultSet()
	{
		return resultSet;
	}
	/**
	 * retourne une ArrayList d'HashMap
	 * @return ArrayList d'HashMap
	 */
	public ArrayList<HashMap<String, Object>> getResultCopy()
	{
		return result_copy;
	}
	
	/**
	 * choisir si dans le cas d'un SELECT executeQuery doit retourner une copie du ResultSet
	 * @param rc copy_mode
	 */
	public void setCopyMode(boolean rc)
	{
		copy_mode = rc;
	}
	
	/**
	 * Exécute une requête SQL et retourne le résultat si applicable.
	 * @param sql requête avec des ?
	 * @param params nombre libre de paramètres, "Object..." permet de créer un tableau à partir des paramètres
	 * @throws SQLException en cas d'erreur SQL
	 * @throws ClassNotFoundException pour DriverManager.getConnection
	 */
	public void executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException
	{
		boolean isSelect = sql.trim().toLowerCase().startsWith("select");
	    try {
	    	if(connection == null)
	    	{
	    		connection = getConnection();
	    	}
	    	
	    	statement = connection.prepareStatement(sql);
			if(params.length > 0)
			{
				this.setParameters(statement, params);
			}
			
			if(isSelect) {
				resultSet = statement.executeQuery();
				if(copy_mode)
				{
					result_copy = null;
					result_copy = copyInArrayListHashMap(resultSet);
					resultSet.close();
				}
		    }
			else {
				statement.executeUpdate();
		    }
		}
	    finally {
	    	if(copy_mode)
		    {
		    	statement.close();
	            connection.close();
	            connection = null;
		    }
	    }
	}
	
	/**
	 * Affecte les paramètres aux requêtes préparées.
	 * @param statement
	 * @param params Object... permet d'ajouter autant de paramètres qu'on veut et ça fait un tableau
	 * @throws SQLException en cas d'erreur SQL
	 */
	private void setParameters(PreparedStatement statement, Object... params) throws SQLException
	{
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i + 1, params[i]);
		}
	}
	
	/**
	 * copie du ResultSet dans une ArrayList de HashMap
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<HashMap<String, Object>> copyInArrayListHashMap(ResultSet rs) throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while(rs.next())
        {
            HashMap<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++)
            {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            result.add(row);
        }
        return result;
    }
	
	/**
	 * fermeture de la connexion et effacement de l'objet pour réutilisation
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		resultSet.close();
		statement.close();
		connection.close();
		connection = null;
	}
}
