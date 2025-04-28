package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ConnexionView
 */
@WebServlet("/connexion")
public class ConnexionView extends HttpServlet {
	public static String headerTop;
	public static String headerBottom;
	public static String footer;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ConnexionView() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String titre = "StatHammer : Connexion";
	
		headerTop = 
			  "<!DOCTYPE HTML>\r\n"
			+ "<html>\r\n"
			+ "	<head>\r\n"
			+ "		<meta charset =\"UTF-8\"/>\r\n"
			+ " 	<link rel=\"icon\" type=\"image/png\" href=\"assets/icone_black_templars.png\">"
			+ "		<title>";
		
		headerBottom =
			  "\r\n"
			+ "		</title>\r\n"
			+ "		<link rel=\"stylesheet\" href=\"css/main.css\">\r\n"
			+"<script src=\"js/graphique.js\"></script>\r\n"
			+ "		<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css\">\r\n"
			+ "	</head>\r\n"
			+ "	<body>";
		
		String header = headerTop + titre + headerBottom;
		
		String form = 
				  "		<form class=\"login-form\" enctype=\"application/x-www-form-urlencoded\" action=\"ConnexionController\" method=POST>"
				+ "		 	<table>"
				+ "		 		<tr>"
			    + "					<td>Login </td>"
			    + "					<td><input type=text size=20 name=login></td>"
				+ "		 		</tr>"
				+ "		 		<tr>"
				+ "					<td>Mot de passe</td>"
				+ "					<td>"
				+ "                      <div class=\"password-wrapper\">"
				+ "                          <input type=\"password\" size=\"20\" name=\"mdp\" id=\"password\">"
				+ "                          <i class=\"fa-solid fa-eye\" id=\"togglePassword\"></i>"
				+ "                      </div>"
				+ "                  </td>"
				+ "		 		</tr>"
				+ "		 		<tr>"
			    + "					<td colspan=\"2\" height='100'><center><input type=submit value=\"Connexion\"></center></td>"
				+ "		 		</tr>"
				+ "		 		<tr>"
			    + "					<td colspan=\"2\" height='10'><a class='lien' href='creer-compte'><center>Vous ne possédez pas de compte ? Créez-en un !</center></a></td>"
				+ "		 		</tr>"			    
				+ "		 	</table>"
			    + "		</form>"
				+ "     <p class=\"quote\">\"Le sot fait confiance à la chance, le sage s'en remet à StatHammer.\"</p>"
			    + "     <script src=\"js/togglePassword.js\"></script>\n";

		
		footer =
				  "	</body>\r\n"
				+ "</html>";
		
		
		String html = header + form + footer;
		
		PrintWriter out = response.getWriter();
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
