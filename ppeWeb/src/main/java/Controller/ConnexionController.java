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
}
