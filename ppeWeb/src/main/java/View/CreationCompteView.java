package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CreationCompteView
 */
@WebServlet("/creer-compte")
public class CreationCompteView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCompteView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String titre = "Création compte";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
	    
		String body = "<h1>Création de compte utilisateur</h1>"
				+"<div class='container''>"
				
					+"<form enctype='application/x-www-form-urlencoded\' action='ControllerCreationCompte' method=POST>"
					+"<table class=tCompte>"
					+"<tr><td width='230'>Email :</td>"
					+"<td><input type=text size='100' name=email></td></tr>"

					
					+"<tr><td width=\"230\">Pseudonyme :</td>"
					+"<td><input type=text size='100'  name=pseudo></td></tr>"
					+"<tr><td>Mot de passe :</td>"
					+"<td><input type=password size='100' name=mdp></td>"
					 +"</form>"
					 +"<tr><td colspan='2' height='100'><input type=submit class='buttonGcompte' value='Envoyer'></td></tr>"
					 +"<form enctype='application/x-www-form-urlencoded\' action='connexion' method=POST>"
					 +"<tr><td colspan='2'><input type=submit class='buttonGcompte' value='Retour'></td></tr>"
					 +"</form>"
					 +"</table>"
					+"</div>";
		
					String html = header + body + ConnexionView.footer;
					out.println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
