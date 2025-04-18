package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/AccueilView")
public class AccueilView extends HttpServlet {
 public static String barDeNav;
 private static final long serialVersionUID = 1L;

 public AccueilView() {
     super();
 }

 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session = request.getSession(false);
     if (session == null || session.getAttribute("nom") == null) {
         response.sendRedirect("ConnexionView");
         return;
     }

     response.setContentType("text/html; charset=UTF-8");




     String titre = "StatHammer : Accueil";
     String boutonAdmin = "";


     if ("Admin".equals(session.getAttribute("role"))) {
         boutonAdmin = "    <li><a href='BarDeNavController?action=admin'>Événements</a></li>\r\n";
     }
		
		barDeNav =
			    "<div class='barDeNav'>\r\n"
			  + "  <ul>\r\n"
			  + "    <li><a href='BarDeNavController?action=accueil'>Accueil</a></li>\r\n"
			  + boutonAdmin
			  +"<li><a class='active' href='BarDeNavController?action=Compte'>Mon compte</a></li>\r\n"
			  +"<li><a href='BarDeNavController?action=GererListe'>Gérer listes</a></li>\r\n"
			  +"<li><a href='BarDeNavController?action=creaListe'>Création liste</a></li>\r\n"
			  +"<li><a href='BarDeNavController?action=simu'>Simulation</a></li>\r\n"
			  + "    <li><a href='BarDeNavController?action=logout'>Déconnexion</a></li>\r\n"
			  + "  </ul>\r\n"
			  + "</div>\r\n";
		
     @SuppressWarnings("unchecked")
     ArrayList<String[]> evenements = (ArrayList<String[]>) session.getAttribute("evenements");

     String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;


     
     //StringBuilder permet de travailler avec la méthode ".append" pour assembler chaine de caracatère par chaine de caractère 
     StringBuilder body = new StringBuilder();
     body.append("<div class='event-grid'>\n");

     if (evenements != null && !evenements.isEmpty()) {
         for (String[] evt : evenements) {
             String nomEvt = evt[0];
             String nomImg = evt[1];
             String descEvt = evt[2];
             String dateEvt = evt[3];

             body.append("<div class='evenement-container'>\n")
                 .append("    <div class=\"evenement-content\">\n")
                 .append("        <img class=\"accueil-image\" src=\"img/").append(nomImg).append("\" alt=\"Image de l'événement\">\n")
                 .append("        <div class=\"evenement-info\">\n")
                 .append("            <h3>").append(nomEvt).append("</h3>\n")
                 .append("            <p>").append(descEvt).append("</p>\n")
                 .append("            <p class=\"date\">").append(dateEvt).append("</p>\n")
                 .append("        </div>\n")
                 .append("    </div>\n")
                 .append("</div>\n");
         }
     } else {
         body.append("<p class=\"quote\">Aucun événement disponible pour le moment.</p>\n");
     }

     body.append("</div>\n")
     
     .append("<div class=\"popup-overlay\" id=\"popup\">\n")
     .append("    <span class=\"popup-close\" id=\"popupClose\">&times;</span>\n")
     .append("    <img src=\"\" alt=\"Image en taille réelle\" class=\"popup-image\" id=\"popupImg\">\n")
     .append("</div>\n");
              
     body.append("<script src=\"js/popup.js\"></script>\n");

     String html = header + barDeNav + body + ConnexionView.footer;
     PrintWriter out = response.getWriter();
     out.println(html);

 }

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
 }
}
