package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
				ArrayList<String[]> evenements = chargerEvenements(conec);
				session.setAttribute("evenements", evenements);

				// Debug : affichage console
				System.out.println("Événements stockés en session :");
				for (String[] evt : evenements) {
					System.out.println("Nom : " + evt[0]);
					System.out.println("Image : " + evt[1]);
					System.out.println("Description : " + evt[2]);
					System.out.println("Date : " + evt[3]);
					System.out.println("-------------------------");
				
				// Chargement des listes
				ArrayList<Model.ArmeeListe> listes = chargerListes(conec,session);
				session.setAttribute("listes", listes);
				}

				response.sendRedirect("AccueilView");
			} else {
				// Authentification échouée
				response.sendRedirect("connexionFailed.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("connexionFailed.jsp");
		} finally {
			conec.close();
		}
	}

	private ArrayList<String[]> chargerEvenements(BDD conec) throws SQLException {
		ArrayList<String[]> evenements = new ArrayList<>();
		String sql = "SELECT * FROM evenement";
		PreparedStatement ps = conec.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String nom_evt = rs.getString("nom_evenement");
			String nom_img = rs.getString("nom_image");
			String desc_evt = rs.getString("description_evenement");
			String date_evt = rs.getString("date_evenement");

			evenements.add(new String[]{nom_evt, nom_img, desc_evt, date_evt});
		}

		return evenements;
	}
	private ArrayList<Model.ArmeeListe> chargerListes(BDD conec, HttpSession session) throws SQLException{
		ArrayList<Model.ArmeeListe> listes = new ArrayList<>();
		String sql = "SELECT l.id_liste, l.nom_liste, l.description_liste, u.nom_unite, u.id_armee\r\n"
				+ "	        FROM liste l\r\n"
				+ "	        LEFT JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "	        LEFT JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "	        WHERE l.id_utilisateur="
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
}
