package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import Model.User;

/**
 * Servlet implementation class ConnexionController
 */
@WebServlet("/ConnexionController")
public class ConnexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1. Récupération des paramètres
	    String login = request.getParameter("login");
	    String mdp = request.getParameter("mdp");

	    if (login != null && mdp != null) {
	        login = login.trim();
	        mdp = Integer.toString(mdp.hashCode()); // même hash que dans l’appli JavaFX

	        BDD.setInfos("400129", "stathammer_greta_admin", "stathammer_v1");
	        
	        // 2. Connexion à la BDD
	        BDD conec = new BDD();

	        ArrayList<Object> rendu = conec.selectUtilisateur(login, mdp);
	        int id = conec.UtilisateurID(login, mdp);
	        String role = conec.UtilisateurRole(login, mdp);
	        conec.close();

	        try {
	            // 3. Vérification
	            if (!rendu.isEmpty() && login.equals(rendu.get(0))) {
	                // 4. Création d’un objet session (HTTP)
	                HttpSession session = request.getSession();

	                session.setAttribute("nom", login);
	                session.setAttribute("mdp", mdp);
	                session.setAttribute("id", id);
	                session.setAttribute("role", role);

	                // 5. Redirection vers AccueilView
	                response.sendRedirect("AccueilView");
	            } else {
	                // Mauvaise connexion → redirection vers erreur
	                response.sendRedirect("connexionFailed.jsp");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("connexionFailed.jsp");
	        }
	    } else {
	        response.sendRedirect("connexionFailed.jsp");
	    }
	}


}
