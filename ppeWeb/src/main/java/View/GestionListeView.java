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

import Controller.ConnexionController;
import Model.ArmeeListe;

/**
 * Servlet implementation class GestionListeView
 */
@WebServlet("/gerer-liste")
public class GestionListeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionListeView() {
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
            response.sendRedirect("connexion");
        } 
        else {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// récupère la liste dans la session
		ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>) session.getAttribute("listes");
		
	             
		String titre = "StatHammer : Gestion liste";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
	    
	    
	    
	    StringBuilder listage=  new StringBuilder();
	    // parcourt les listes si elles sont présentent
	    if (listes != null && !listes.isEmpty()) {
	    	int i = 0;
	        for (ArmeeListe liste : listes) {
	            int idliste = liste.getId();
	            String nomliste = liste.getNomListe();
	            String descrliste = liste.getDescriptionListe();
	            ArrayList<String> nomUniteliste = liste.getUniteListe();
	            
	            //listage.append("<div class='liste id='").append(idliste).append("'>\n")
	            listage.append("<div class='gestion_quote' style='width:100%' id='").append(idliste).append("'>\n")
	                   .append("<h2 class='titreListe'>").append(nomliste).append("</h2>\n")
	                   .append("<p>").append(descrliste).append("</p>\n");

	            if (nomUniteliste != null && !nomUniteliste.isEmpty()) {
	                listage.append("<ul>\n");
	                
	                for (String nomUnite : nomUniteliste) {
	                    listage.append("<li>").append(nomUnite);
	
	                    listage.append("</li>\n");
	                }
	                listage.append("</ul>\n");
	            } else {
	                listage.append("<p><em>Aucune unité dans cette liste.</em></p>\n");
	            }	
	            listage.append("<div class='GestionListe'>\n")
	            		//.append("<button type='button' class='GestionListe_bouton'>Exporter\n")
	            		//.append("</button>\n")
	            		.append("<form class='gestion_formInitial' action='ImportExportController' method='get'><input type=hidden name=export value=").append(i).append("><input type=submit value='Exporter'></form>")
	            		.append("<form action='ChargerModifListe' method='post' class='GestionListe_form'>\n")
	            		.append("<input type='hidden' name='idListe' value='").append(idliste).append("' />\n")
	            		.append("<button type='submit' class='GestionListe_bouton'>Paramétrer</button>\n")
	            		.append("</form>\n")
	            		.append("<button type='button' class='GestionListe_bouton modal_ouverture' id='")
	            		.append(idliste) 
	            		.append("'>Supprimer</button>\n")
	            		.append("</div>\n")
	            		.append("</div>\n");
	           i++; 
	           
	            
	        }
	    }
	     	
	    String body= 
	    		//"<h1>Interface de gestion des listes</h1>\n"
	    		"<section class='GestionListe_structure'>\r\n"
	    		+"<div class='bouton_placement' style='padding-top:36px'>"
	    		//+"<a href='accueil' class='bouton_gestionListe'>Retour</a>"
	    		+"<table style='width: -moz-available'>"
	    		//+"<tr><td>"
	    		//+"<h2 class='titreListe'>Importer une liste :</h2>"
	    		//+"</td></tr>"
	    		+"<form  class='gestion_formInitial' action='ImportExportController' method='post' enctype=\"multipart/form-data\">"	    		
	    		+"<tr><td>"
	    		+"<input type='file' id='listUpload' name='liste' accept='.txt' required>"
	    		+"</td></tr>"
	    		+"<tr><td>"
	    		+"<input type=submit value='Importer liste'  style='width: -moz-available'>"
	    		+"</td></tr>"
	    		+"</form>"
	    		+"</table>"
	    		//+"<button type='button' class='bouton_gestionListe'>Importer une liste</button>"
	    		+"</div>"
	    		//+"<div class='GestionListe_import'>"
	    		+"<div class='event-grid' style='width:100%'>"
	    		+ listage
	    		+"</div>"
	    		+"</section>"
	    		+"<div class='modal_overlay modal_hidden'>"
	    		+   "<div class='modal'>"
	    		+     "<p id='modal_message'>Voulez-vous vraiment supprimer cette armée ?</p>"
	    		+     "<div class='modal_actions'>"
	    		+       "<form method='POST' action='SupprimerListe'>"
	    		+         "<button type='submit' name='idliste'  class='modal_btn modal_supprButton'>Supprimer</button>"
	    		+         "<button type='button' class='modal_btn modal_retourButton' >Annuler</button>"
	    		+       "</form>"
	    		+     "</div>"
	    		+   "</div>"
	    		+ "</div>";
	     
	    String scriptJs = "<script src=\"js/modalGestionListe.js\"></script>";
	   
	    String html = header + AccueilView.barDeNav + body + scriptJs + ConnexionView.footer;
		out.println(html);
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