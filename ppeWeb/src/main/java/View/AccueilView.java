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

import Model.Evenement;


@WebServlet("/accueil")
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
         response.sendRedirect("connexion");
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
			  + "    <li><a href='BarDeNavController?action=accueil'><i class=\"fas fa-house\"></i></a></li>\r\n"
			  + boutonAdmin
			  + "    <li><a class='active' href='BarDeNavController?action=Compte'>Mon compte</a></li>\r\n"
			  + "    <li><a href='BarDeNavController?action=GererListe'>Gérer listes</a></li>\r\n"
			  + "    <li><a href='BarDeNavController?action=creaListe'>Création liste</a></li>\r\n"
			  + "    <li><a href='BarDeNavController?action=simu'>Simulation</a></li>\r\n"
			  + "<li style='margin-left: auto;'><a href='BarDeNavController?action=logout' class='logout-btn'><i class='fas fa-sign-out-alt'></i></a></li>\r\n"
			  + "  </ul>\r\n"
			  + "</div>\r\n";
		
	 @SuppressWarnings("unchecked")
	 ArrayList<Evenement> evenements = (ArrayList<Evenement>) session.getAttribute("events");
	 
     String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;


     
     //StringBuilder permet de travailler avec la méthode ".append" pour assembler chaine de caracatère par chaine de caractère 
     StringBuilder body = new StringBuilder();
     
     //la balise <section> est pour le bloc global des événements
     body.append("<section class='event-grid' aria-label='Événements disponibles'>\n");

     if (evenements != null && !evenements.isEmpty()) {
         for (Evenement evt : evenements) {
             String nomEvt = evt.getNom_evenement();
             String nomImg = evt.getNom_image();
             String descEvt = evt.getDescritption_evenement();
             String dateEvt = evt.getData_evenement();
             	
             //la balise <article> est pour chaque événement
             body.append("  <article class='evenement-container'>\n")
                 .append("    <div class='evenement-content'>\n")
                 .append("      <img class='accueil-image' src='img/").append(nomImg).append("' alt='Image de l'événement ").append(nomEvt).append("'>\n")
                 .append("      <div class='evenement-info'>\n")
                 .append("        <h3>").append(nomEvt).append("</h3>\n")
                 .append("        <p>").append(descEvt).append("</p>\n")
                 .append("        <p class='date'>").append(dateEvt).append("</p>\n")
                 .append("      </div>\n")
                 .append("    </div>\n")
                 .append("  </article>\n");
         }
     } else {
    	 //message s'il n'y a aucun événement
         body.append("  <p class='quote'>Aucun événement disponible pour le moment.</p>\n");
     }

     body.append("</section>\n")

     // Popup et JS
         .append("<div class='popup-overlay' id='popup'>\n")
         .append("  <span class='popup-close' id='popupClose'>&times;</span>\n")
         .append("  <img src='' alt='Image en taille réelle' class='popup-image' id='popupImg'>\n")
         .append("</div>\n")
         .append("<script src='js/popup.js'></script>\n");


     String html = header + barDeNav + body + ConnexionView.footer;
     PrintWriter out = response.getWriter();
     out.println(html);

 }

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
 }
}
