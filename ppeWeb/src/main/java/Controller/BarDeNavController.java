package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class BarDeNavController
 */
@WebServlet("/BarDeNavController")
public class BarDeNavController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarDeNavController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    HttpSession session=request.getSession(false);

		String action = request.getParameter("action");
		if (action == null) {
			action = "logout"; // valeur par défaut
		}

		if (session != null) {
			
			switch (action) {
				case "accueil":
					response.sendRedirect("AccueilView");
					break;
				case "admin":
					response.sendRedirect("AdminView");
					break;
				case "Compte":
					response.sendRedirect("GestionCompteView");
					break;
				case "GererListe":
					response.sendRedirect("GestionListeView");
					break;
				case "logout":
					session.invalidate();
					session = null;
					response.sendRedirect("ConnexionView");
					break;
				default:
					response.sendRedirect("AccueilView");
					break;
			}
		} else {
			// Rediriger vers une page de connexion ou erreur
			response.sendRedirect("ConnexionView");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
