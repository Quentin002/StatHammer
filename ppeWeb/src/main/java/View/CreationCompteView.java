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
		String css = "<link rel=\"stylesheet\" href=\"css/main.css\">";
		String titre = "<title>Création compte</title>";
		String headerTop = 
				  "<!DOCTYPE HTML>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset =\"UTF-8\"/>\r\n";
			
		String headerBottom =
				  "\r\n"
				+ "		<link rel=\"stylesheet\" href=\"css/main.css\">\r\n"
				+ "	</head>\r\n"
				+ "	<body>";
			
		String header = headerTop + titre + css +headerBottom;
	    
		String footer =
				  "	</body>\r\n"
				+ "</html>";
		
	    String body = 
	    	      " <form class=\"login-form\" enctype=\"application/x-www-form-urlencoded\" action=\"ControllerCreationCompte\" method=POST>"
	    	    + "  <table style='table-layout: fixed;'>"
	    	    + "    <tr><td>Email</td><td><input type=text size=20 name=email></td></tr>"
	    	    + "    <tr><td>Pseudonyme</td><td><input type=text size=20 name=pseudo></td></tr>"
	    	    + "    <tr style='height:1em'><td></td><td></td></tr>"
	    	    + "    <tr>"
	    	    + "      <td>Mot de passe</td>"
	    	    + "      <td><div class=\"password-wrapper\">"
	    	    + "        <input type=\"password\" size=\"20\" name=\"mdp\" id=\"pswCreaCom\" required>"
	    	    + "        <i class=\"fa-solid fa-eye\" id=\"togglePswCreaCom\"></i>"
	    	    + "      </div></td>"
	    	    + "    </tr>"
	    	    + "    <tr>"
	    	    + "      <td>Confirmer</td>"
	    	    + "      <td><div class=\"password-wrapper\">"
	    	    + "        <input type=\"password\" size=\"20\" name=\"mdpConf\" id=\"pswConfirm\" required>"
	    	    + "      </div></td>"
	    	    + "    </tr>"
	    	    + "    <tr id='btnCreerCompte'>"
	    	    + "      <td colspan='2' height='100'>"
	    	    + "        <center><input type='submit' id='btnDisabled' value='Créer un compte' disabled></center>"
	    	    + "      </td>"
	    	    + "    </tr>"
	    	    + "    <tr><td colspan='2' height='10'><center><a class='lien' href='connexion'>Retour</a></center></td></tr>"
	    	    + "  </table>"
	    	    + "</form>"
	    	    + "<div class='quote' id='message'>"
	    	    + "  <table style='width:100%;padding-left:2em'>"
	    	    + "    <tr>"
	    	    + "      <td id='letter' class='invalid'>Un caractère en <b>minuscule</b></td>"
	    	    + "      <td id='capital' class='invalid'>Un caractère en <b>majuscule</b></td>"
	    	    + "    </tr>"
	    	    + "    <tr>"
	    	    + "      <td id='number' class='invalid'>Un <b>chiffre</b></td>"
	    	    + "      <td id='length' class='invalid'><b>8 caractères</b> au minimum</td>"
	    	    + "    </tr>"
	    	    + "  </table>"
	    	    + "</div>"
	    	    
	    	    //Javascript
	    	    + "<script src='js/togglePasswordCreaCom.js'></script>\n"
	    	    + "<script src='js/validatePassword.js'></script>\n"
	    	    + "<script src='js/mdpConf.js'></script>\n";

		
					String html = header + body + footer;
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
