package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import View.ConnexionView;

/**
 * Servlet implementation class ControlleurGestionCompte
 */
@WebServlet("/ControllerGestionCompte")
public class ControllerGestionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerGestionCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session=request.getSession(false);
		String pseudo = request.getParameter("pseudo");
		String newMdp = request.getParameter("mdp");
		String confirmMdp = request.getParameter("confirmMdp");
		int id = (int)session.getAttribute("id") ;
		
		if(pseudo != null && pseudo.isEmpty()==false && pseudo.trim().contains(" ")==false) {
			
			try {
				ConnexionController.updatePseudo(pseudo.trim(),id); // bdd
				response.sendRedirect("http://localhost:8080/ppeWeb/AccueilView");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			response.getWriter().append(pseudo);
		}
		else if(newMdp != null && newMdp.isEmpty() ==false && newMdp.contains(" ")==false) {
			//response.sendRedirect("http://localhost:8080/ppe/Accueil");
			try {
				String mdp =ConnexionController.selectMdp(id);
				if(Integer.toString(confirmMdp.hashCode()).equals(mdp)) {
						ConnexionController.updateMdp(newMdp,id);
						response.sendRedirect("http://localhost:8080/ppeWeb/AccueilView");
				}
				else {

					response.sendRedirect("http://localhost:8080/ppeWeb/GestionCompteView");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		else {
			//response.getWriter().append("erreur");
			response.sendRedirect("http://localhost:8080/ppeWeb/GestionCompteView");
		}
		
	}

}
