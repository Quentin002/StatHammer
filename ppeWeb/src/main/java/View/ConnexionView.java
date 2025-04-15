package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Controller.BDD;

/**
 * Servlet implementation class ConnexionView
 */
@WebServlet("/ConnexionView")
public class ConnexionView extends HttpServlet {
	public static String css;
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
		BDD.setInfos("400129","stathammer_greta_admin","stathammer_v1");
		String titre = "StatHammer : Connexion";
		String action = "ConnexionController";
		css =
				"@import url('https://fonts.googleapis.com/css2?family=UnifrakturCook:wght@700&display=swap');\r\n"
				+ "body {\r\n"
				+ "  margin: 0;\r\n"
				+ "  min-height: 100vh;\r\n"
				+ "  background: linear-gradient(135deg, #0d0d1a 0%, #1b1b2f 100%);\r\n"
				+ "  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n"
				+ "  color: #f0f0f0;\r\n"
				+ "  padding: 40px;\r\n"
				+ "}\r\n"

				+ "h1 {\r\n"
				+ "  font-family: 'UnifrakturCook', serif;\r\n"
				+ "  font-size: 32px;\r\n"
				+ "  text-align: center;\r\n"
				+ "  color: #42a5f5;\r\n"
				+ "  text-shadow: 0 0 5px #2196f3;\r\n"
				+ "  margin-bottom: 30px;\r\n"
				+ "}\r\n"

				+ "form {\r\n"
				+ "  background-color: #1a1a2e;\r\n"
				+ "  border: 1px solid #3c3c5c;\r\n"
				+ "  box-shadow: 0 0 20px rgba(0, 123, 255, 0.3);\r\n"
				+ "  padding: 30px;\r\n"
				+ "  border-radius: 10px;\r\n"
				+ "  max-width: 800px;\r\n"
				+ "  margin: 0 auto;\r\n"
				+ "  display: flex;\r\n"
				+ "  flex-direction: column;\r\n"
				+ "  gap: 15px;\r\n"
				+ "}\r\n"

				+ "label {\r\n"
				+ "  font-weight: bold;\r\n"
				+ "  color: #ddd;\r\n"
				+ "  margin-top: 10px;\r\n"
				+ "}\r\n"

				+ "input[type=text], input[type=password], input[type=date], textarea, input[type=file] {\r\n"
				+ "  padding: 10px;\r\n"
				+ "  border: 1px solid #555;\r\n"
				+ "  border-radius: 4px;\r\n"
				+ "  background-color: #0f0f1a;\r\n"
				+ "  color: #f0f0f0;\r\n"
				+ "  width: 100%;\r\n"
				+ "  box-sizing: border-box;\r\n"
				+ "}\r\n"

				+ "textarea {\r\n"
				+ "  resize: vertical;\r\n"
				+ "  min-height: 80px;\r\n"
				+ "}\r\n"

				+ "input[type=submit] {\r\n"
				+ "  background-color: #007BFF;\r\n"
				+ "  color: #fff;\r\n"
				+ "  padding: 12px;\r\n"
				+ "  border: none;\r\n"
				+ "  border-radius: 4px;\r\n"
				+ "  font-weight: bold;\r\n"
				+ "  cursor: pointer;\r\n"
				+ "  transition: background-color 0.3s ease, box-shadow 0.3s ease;\r\n"
				+ "  box-shadow: 0 0 5px rgba(0,123,255,0.4);\r\n"
				+ "}\r\n"

				+ "input[type=submit]:hover {\r\n"
				+ "  background-color: #3399FF;\r\n"
				+ "  box-shadow: 0 0 10px rgba(51,153,255,0.6);\r\n"
				+ "}\r\n"

				+ ".error {\r\n"
				+ "  color: #ff5555;\r\n"
				+ "  font-weight: bold;\r\n"
				+ "  text-align: center;\r\n"
				+ "  margin-bottom: 20px;\r\n"
				+ "}\r\n"
				+ ".container {\r\n"
				+ "  display: flex;\r\n"
				+ "  flex-direction: row;\r\n"
				+ "  gap: 40px;\r\n"
				+ "  justify-content: space-between;\r\n"
				+ "  padding: 40px;\r\n"
				+ "  max-width: 1200px;\r\n"
				+ "  margin: auto;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".left-panel {\r\n"
				+ "  flex: 1;\r\n"
				+ "  max-width: 400px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".right-panel {\r\n"
				+ "  flex: 2;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".event-grid {\r\n"
				+ "  display: flex;\r\n"
				+ "  flex-wrap: wrap;\r\n"
				+ "  gap: 20px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".event-grid > div {\r\n"
				+ "  flex: 1 1 250px;\r\n"
				+ "  background-color: #1a1a2e;\r\n"
				+ "  border: 1px solid #444;\r\n"
				+ "  padding: 15px;\r\n"
				+ "  border-radius: 8px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".event-grid h2 {\r\n"
				+ "  color: #42a5f5;\r\n"
				+ "  margin-bottom: 10px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".event-grid img {\r\n"
				+ "  max-width: 100%;\r\n"
				+ "  border-radius: 6px;\r\n"
				+ "  margin-top: 10px;\r\n"
				+ "}\r\n"
				+ ".barDeNav {\r\n"
				+ "  background: linear-gradient(145deg, #2f2f3f, #1a1a2e);\r\n"
				+ "  box-shadow: 0 2px 10px rgba(0,0,0,0.7);\r\n"
				+ "  padding: 0;\r\n"
				+ "  margin: 0;\r\n"
				+ "  border-bottom: 1px solid #444;\r\n"
				+ "  position: sticky;\r\n"
				+ "  top: 0;\r\n"
				+ "  z-index: 1000;\r\n"
				+ "}\r\n"
				+ ".barDeNav ul {\r\n"
				+ "  list-style-type: none;\r\n"
				+ "  margin: 0;\r\n"
				+ "  padding: 0;\r\n"
				+ "  display: flex;\r\n"
				+ "  justify-content: center;\r\n"
				+ "  background-color: transparent;\r\n"
				+ "}\r\n"
				+ ".barDeNav ul li {\r\n"
				+ "  margin: 0;\r\n"
				+ "  padding: 0;\r\n"
				+ "}\r\n"
				+ ".barDeNav ul li a {\r\n"
				+ "  display: block;\r\n"
				+ "  color: #e0e0e0;\r\n"
				+ "  padding: 16px 24px;\r\n"
				+ "  text-decoration: none;\r\n"
				+ "  font-weight: bold;\r\n"
				+ "  letter-spacing: 1px;\r\n"
				+ "  transition: all 0.3s ease;\r\n"
				+ "  border-bottom: 2px solid transparent;\r\n"
				+ "}\r\n"
				+ ".barDeNav ul li a:hover {\r\n"
				+ "  background-color: rgba(255, 255, 255, 0.05);\r\n"
				+ "  color: #42a5f5;\r\n"
				+ "  border-bottom: 2px solid #42a5f5;\r\n"
				+ "}\r\n";

	
		
		String header =
				"<!DOCTYPE HTML>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset =\"UTF-8\"/>\r\n"
				+ "		<title>" + titre + "\r\n"
				+ "		</title>\r\n"
				+ "   <style>" + css + "</style>\r\n"
				+ "	</head>\r\n"
				+ "	<body>";
		
		String form = 
				  "		<form enctype=\"application/x-www-form-urlencoded\"action=\"" + action + "\" method=POST>"
				+ "		 	<table>"
				+ "		 		<tr>"
			    + "					<td>Login </td>"
			    + "					<td><input type=text size=20 name=login></td>"
				+ "		 		</tr>"
				+ "		 		<tr>"
			    + "					<td>Mot de passe </td>"
			    + "					<td><input type=password size=20 name=mdp></td>"
				+ "		 		</tr>"
				+ "		 		<tr>"
			    + "					<td colspan=\"2\"><center><input type=submit value=\"Connexion\"></center></td>"
				+ "		 		</tr>"
				+ "		 	</table>"
			    + "		</form>";

		
		String footer =
				"	</body>\r\n"
				+ "</html>";
		
		
		PrintWriter out = response.getWriter();
		out.println(header + form + footer);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
