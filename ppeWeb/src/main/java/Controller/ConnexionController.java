package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.ArmeeListe;
import Model.Evenement;
import Model.Figurine;


@WebServlet("/ConnexionController")
public class ConnexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConnexionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirection vers page de connexion ou erreur
		response.sendRedirect("connexionFailed.jsp");
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");

		if (login == null || mdp == null) {
			response.sendRedirect("connexionFailed.jsp");
			return;
		}

		login = login.trim();
		mdp = Integer.toString(mdp.hashCode()); // Hash comme dans l'appli JavaFX

		BDD.setInfos("400129", "stathammer_greta_admin", "stathammer_v1");
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");

		try {
			// Authentification
			ArrayList<Object> rendu = conec.selectUtilisateur(login, mdp);
			int id = conec.UtilisateurID(login, mdp);
			String role = conec.UtilisateurRole(login, mdp);

			if (!rendu.isEmpty() && login.equals(rendu.get(0))) {
				
				// Authentifié : création de session
				HttpSession session = request.getSession();
				
				session.setAttribute("nom", login);
				session.setAttribute("mdp", mdp);
				session.setAttribute("id", id);
				session.setAttribute("role", role);

				// Chargement des événements
				chargerEvenements(conec,session);
				ArrayList<Evenement> evenements = (ArrayList<Evenement>) session.getAttribute("events");
				// Debug : affichage console
				
				System.out.println("Événements stockés en session :");
				for (int i = 0; i < evenements.size(); i++) {
					Evenement evt = evenements.get(i);
					System.out.println("Nom : " + evt.getNom_evenement());
					System.out.println("Image : " + evt.getNom_image());
					System.out.println("Description : " + evt.getDescritption_evenement());
					System.out.println("Date : " + evt.getData_evenement());
					System.out.println("-------------------------");
				
				
				}
				// Chargement des listes
				ArrayList<Model.ArmeeListe> listes = chargerListes(conec,session);
				session.setAttribute("listes", listes);
				
				ArrayList<Model.ArmeeListe> liste = (ArrayList<ArmeeListe>) session.getAttribute("listes");
				//System.out.println(liste.get(0).getId());
				int idliste= liste.get(0).getIdListe();
				//ArrayList<String> nomUnite= liste.get(0).getUniteListe();
				
				if (listes != null && !listes.isEmpty()) {
		            for (ArmeeListe liste1 : listes) {
		            	int IDliste= liste1.getIdListe();
		                ArrayList<String> nomUniteliste = liste1.getUniteListe();
		                
		                
		                    if (nomUniteliste != null && !nomUniteliste.isEmpty()) {
		                        for (String nomUnite : nomUniteliste) {
		                        	ArrayList<Model.Figurine> figurines = chargerFigurineListes(conec, session, nomUnite,idliste);
		                        	session.setAttribute("figurines", figurines);
		                        }
		                    }}}
				//ArrayList<Model.Figurine> figurines = chargerFigurineListes(conec,session, nomUnite, idliste);
				
				//ArrayList<Integer> listId = null ;
				/*ArrayList <Integer >listId = new ArrayList<Integer>();
				for(ArmeeListe list : liste) {
						listId.add(list.getId());	
						System.out.print(list.getId());
				}*/
				
				ArrayList<Model.Figurine> figurine = (ArrayList<Figurine>) session.getAttribute("figurines");
				System.out.println(figurine);
				
				
				
				conec.close();
				System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
				response.sendRedirect("AccueilView");
			} else {
				// Authentification échouée
				conec.close();
				System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
				response.sendRedirect("connexionFailed.jsp");
			}
		} catch (Exception e) {
			conec.close();
			System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
			e.printStackTrace();
			response.sendRedirect("connexionFailed.jsp");
		}
	}

	public static void chargerEvenements(BDD conec, HttpSession session) throws SQLException {
		//ArrayList<String[]> evenements = new ArrayList<>();
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		String sql = "SELECT * FROM evenement";
		PreparedStatement ps = conec.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
		    
			String nom_evt = rs.getString("nom_evenement");
			String nom_img = rs.getString("nom_image");
			String desc_evt = rs.getString("description_evenement");
			String date_evt = rs.getString("date_evenement");

			evenements.add(new Evenement(nom_evt, nom_img, desc_evt, date_evt));	
		}
		
		session.setAttribute("events", evenements);
	}
	
	public static void updatePseudo(String pseudo,int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		conec.updateUtilisateur(pseudo,id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
	}
	public static void updateMdp(String mdp,int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		conec.updateMp(mdp,id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
	}
	public static String selectMdp(int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		String mdp =conec.UtilisateurMdp(id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
		return mdp;
	}
	public static void suppressionListe(String idListe) throws SQLException{
		BDD conec = new BDD();
		conec.SuppressionListe(idListe);
		conec.close();
	}
	private ArrayList<Model.ArmeeListe> chargerListes(BDD conec, HttpSession session) throws SQLException{
		ArrayList<Model.ArmeeListe> listes = new ArrayList<>();
		
		String sql = "SELECT l.id_liste, l.nom_liste, l.description_liste, u.nom_unite, u.id_armee \r\n"
				+ "FROM liste l\r\n"
				+ "LEFT JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "LEFT JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "WHERE l.id_utilisateur="
				+ session.getAttribute("id") ; 
		HashMap<Integer, Model.ArmeeListe> mapListes = new HashMap<>();
		
		PreparedStatement ps = conec.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id_liste = rs.getInt("id_liste");
			String nom_liste = rs.getString("nom_liste");
			String descr_liste = rs.getString("description_liste");
			String nomUnite_liste = rs.getString("nom_unite");
			int idArmee_liste = rs.getInt("id_armee");
			
			// Vérifier si la liste existe déjà dans la map
            Model.ArmeeListe liste = mapListes.get(id_liste);
            if (liste == null) {
                liste = new Model.ArmeeListe(id_liste, idArmee_liste, nom_liste, descr_liste);
                //ArrayList<Unit> unit_list,, String nom, String description
                liste.setUniteListe(new ArrayList<>()); // Initialiser la liste des unités
                mapListes.put(id_liste, liste);
            }
            if (nomUnite_liste != null) {
                liste.getUniteListe().add(nomUnite_liste);
            }
            
		}
		listes.addAll(mapListes.values());
		
		return listes;
	}
	
	public static ArrayList<Figurine> chargerFigurineListes(BDD conec, HttpSession session, String nomUnite, int idliste2) throws SQLException {
		ArrayList<Model.Figurine> listesFigurine = new ArrayList<>();
		String sql = "SELECT f.nom_figurine, f.M, f.E, f.SV, f.PV, f.CD, f.CO\r\n"
				+ "            FROM liste l\r\n"
				+ "            JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "            JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "            JOIN remplir r ON u.id_unite = r.id_unite\r\n"
				+ "            JOIN figurine f ON r.id_figurine = f.id_figurine\r\n"
				+ "            WHERE l.id_liste ="
				+idliste2
				+ " AND u.nom_unite =\""
				+nomUnite
				+ "\" ;" 
									; 
	        PreparedStatement ps = conec.getPreparedStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			String nom_figurine = rs.getString("nom_figurine");
			String M = rs.getString("M");
			int E = rs.getInt("E");
            int SV = rs.getInt("SV");
            int PV = rs.getInt("PV");
            int CD = rs.getInt("CD");
            int CO = rs.getInt("CO");
			
			//Model.Figurine figurines = new Model.Figurine(new ArrayList<>(), new ArrayList<>(), nom_figurine, "", M, E, SV, PV, CD, CO);
			//listesFigurine.add(figurines);
            Model.Figurine figliste = figliste.get("nom_figurine");
            if (figliste == null) {
                figliste = new Model.Figurine(new ArrayList<>(), new ArrayList<>(), nom_figurine, "", M, E, SV, PV, CD, CO);
                //ArrayList<Unit> unit_list,, String nom, String description
                figliste.getNom();
                figliste.put(nom_figurine, figliste);
            }
			
		}
		listesFigurine.addAll(figliste.values());
		
		
		return listesFigurine;
	}
	
	//requête sans la mapliste mais avec des requêtes corrects
	/*public static ArrayList<Figurine> chargerFigurineListes(BDD conec, HttpSession session, String nomUnite, int idliste2) throws SQLException {
		ArrayList<Model.Figurine> listesFigurine = new ArrayList<>();
		String sql = "SELECT f.nom_figurine, f.M, f.E, f.SV, f.PV, f.CD, f.CO\r\n"
				+ "            FROM liste l\r\n"
				+ "            JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "            JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "            JOIN remplir r ON u.id_unite = r.id_unite\r\n"
				+ "            JOIN figurine f ON r.id_figurine = f.id_figurine\r\n"
				+ "            WHERE l.id_liste ="
				+idliste2
				+ " AND u.nom_unite =\""
				+nomUnite
				+ "\" ;" 
									; 
	        PreparedStatement ps = conec.getPreparedStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			String nom_figurine = rs.getString("nom_figurine");
			String M = rs.getString("M");
			int E = rs.getInt("E");
            int SV = rs.getInt("SV");
            int PV = rs.getInt("PV");
            int CD = rs.getInt("CD");
            int CO = rs.getInt("CO");
			
			Model.Figurine figurines = new Model.Figurine(new ArrayList<>(), new ArrayList<>(), nom_figurine, "", M, E, SV, PV, CD, CO);
			listesFigurine.add(figurines);
			
		}
		
		
		
		return listesFigurine;
	}*/
	
	/*public static ArrayList<Figurine> chargerFigurineListes(BDD conec, HttpSession session, ArrayList<String> nomUnite) {
		ArrayList<Model.Figurine> listesFigurine = new ArrayList<>();
		
		String sql = "SELECT f.nom_figurine, f.M, f.E, f.SV, f.PV, f.CD, f.CO\r\n"
				+ "            FROM liste l\r\n"
				+ "            JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "            JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "            JOIN remplir r ON u.id_unite = r.id_unite\r\n"
				+ "            JOIN figurine f ON r.id_figurine = f.id_figurine\r\n"
				+ "            WHERE l.id_liste =?"
				
				+ " AND u.nom_unite =? ;"
									; 
		try (Connection conec = reopenConnection();
	             PreparedStatement ps = conec.prepareStatement(sql)) {
			ps.setInt(1, idliste);
            ps.setString(2, nomUnite);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			String nom_figurine = rs.getString("nom_figurine");
			String M = rs.getString("M");
			int E = rs.getInt("E");
            int SV = rs.getInt("SV");
            int PV = rs.getInt("PV");
            int CD = rs.getInt("CD");
            int CO = rs.getInt("CO");
			
			Model.Figurine figurines = new Model.Figurine(new ArrayList<>(), new ArrayList<>(), nom_figurine, "", M, E, SV, PV, CD, CO);
			listesFigurine.add(figurines);
		}
		
		
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return listesFigurine;
	}*/

	/*private static BDD conec; // Instance de la classe BDD qui gère la connexion à la bdd

    private static Connection reopenConnection() throws SQLException {
        
            conec = new BDD();
        
        return conec.getConnection();
    }*/
	
}
