package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Controller.BDD;
import Model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ControllerCreationCompte
 */
@WebServlet("/ControllerCreationCompte")
public class ControllerCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerCreationCompte() {
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
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String email = request.getParameter("email");
		
		if(pseudo != null && pseudo.isEmpty()==false && pseudo.contains(" ")==false &&
		mdp != null && mdp.isEmpty()==false && mdp.contains(" ")==false	&&
		email != null && email.isEmpty()==false && email.contains(" ")==false) {
			
			BDD.setInfos("400129","stathammer_greta_admin","stathammer_v1");		
			BDD conec = new BDD();

			mdp = Integer.toString(mdp.hashCode());
			conec.creerUtilisateur(email, pseudo, mdp);
			conec.close();
			response.sendRedirect("connexion");

		}
		else {
			response.sendRedirect("creer-compte");
		}		
	}
}
