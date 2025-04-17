package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class GestionCompteView
 */
@WebServlet("/GestionCompteView")
public class GestionCompteView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCompteView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("ConnexionView");
        } 
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String titre = "StatHammer : Gestion compte";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
		
		// FORMULAIRE
		String body =
				"<h1>Gestion du compte utilisateur</h1>"
			  +"<div class='container'>\r\n"
			+"<form enctype='application/x-www-form-urlencoded\' action='ControllerGestionCompte' method=POST>"
			+"<table class='tCompte'>"
			
			+"<tr><td width='230'>Modifier le pseudonyme :</td>"
			+"<td><input type=text size='100' name=pseudo></td></tr>"
			//+"</table>"
			+"<tr><td colspan='2' height='100'><input type=submit class='buttonGcompte' value='Envoyer'></td></tr>"
			+"</form>"
			
			+"<br><br>"
			+"<form enctype='application/x-www-form-urlencoded\' action='ControllerGestionCompte' method=POST>"
			//+"<table>"
			+"<tr><td width=\"230\">Modifier le mot de passe :</td>"
			+"<td><input type=password size='100'  name=mdp></td></tr>"
			+"<tr><td>Confirmer avec mot de passe actuel :</td>"
			+"<td><input type=password size='100' name=confirmMdp></td>"
			+"<tr><td colspan='2' height='100'><input type=submit class='buttonGcompte' value='Envoyer'></td></tr>"
				+"</table>"
		  	+ "   </form>\r\n"
		  	+ "</div>\r\n";
		
		String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
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
