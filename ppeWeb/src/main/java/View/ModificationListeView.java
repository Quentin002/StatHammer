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
import java.util.Map;
import java.util.HashMap;

import Controller.ConnexionController;
import Model.ArmeeListe;
import Model.Figurine;

@WebServlet("/ModificationListeView")
public class ModificationListeView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("ConnexionView");
            return;
        }

        String titre = "StatHammer : Gestion liste";
        String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
        
        // récupération de l'id de la liste en session
        int idListeParam = (Integer) session.getAttribute("idListeParam");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // récupération de la liste et des figurines en session
        ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");
        Map<String, ArrayList<Figurine>> figMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("Figurinelistes");
        if (figMap == null) figMap = new HashMap<>();

        StringBuilder importListe = new StringBuilder();

        if (listes != null && !listes.isEmpty()) {
            for (ArmeeListe liste : listes) {
                int idliste = liste.getId();
                String nomliste = liste.getNomListe();
                String descrliste = liste.getDescriptionListe();
                ArrayList<String> nomUniteliste = liste.getUniteListe();
                int idArmeeliste = liste.getIdArmee();

                if (idliste == idListeParam) {
                    importListe.append("<div><h2>").append(nomliste).append("</h2>")
                        .append("<p>").append(descrliste).append("</p><ul>");

                    for (String nomUnite : nomUniteliste) {
                        importListe.append("<li>").append(nomUnite);
                        ArrayList<Figurine> figurines = figMap.get(nomUnite);
                        if (figurines != null) {
                            for (Figurine fig : figurines) {
                                importListe.append("<ul>").append(fig.getNom()).append("</ul>");
                            }
                        }
                        importListe.append("<form method='post' action='GererUnite' class='ModificationListe_form'>")
                            .append("<input type='hidden' name='action' value='supprimer'>")
                            .append("<input type='hidden' name='uniteName' value=\"").append(nomUnite).append("\">")
                            .append("<input type='hidden' name='idListe' value='").append(idliste).append("'>")
                            .append("<button type='submit' class='ModificationListe_bouton'>Supprimer</button>")
                            .append("</form></li>");
                    }
                    importListe.append("</ul></div>");
                }
            }
        }

        Map<String, ArrayList<Figurine>> modifFigMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("ModifFigurinelistes");
        if (modifFigMap == null) modifFigMap = new HashMap<>();

        StringBuilder importModif = new StringBuilder();
        ArrayList<ArmeeListe> unitModif = (ArrayList<ArmeeListe>) session.getAttribute("Modiflistes");
        if (unitModif != null) {
            importModif.append("<div><h3>Unités disponibles</h3><ul>");
            for (ArmeeListe unit : unitModif) {
                for (String uniteName : unit.getUniteListe()) {
                    importModif.append("<li>").append(uniteName);

                    ArrayList<Figurine> figs = modifFigMap.get(uniteName);
                    if (figs != null) {
                        for (Figurine fig : figs) {
                            importModif.append("<ul>").append(fig.getNom()).append("</ul>");
                        }
                    }

                    importModif.append("<form method='post' action='GererUnite' class='ModificationListe_form'>")
                        .append("<input type='hidden' name='action' value='ajouter'>")
                        .append("<input type='hidden' name='uniteName' value=\"").append(uniteName).append("\">")
                        .append("<input type='hidden' name='idListe' value='").append(idListeParam).append("'>")
                        .append("<button type='submit' class='ModificationListe_bouton'>Ajouter</button>")
                        .append("</form></li>");
                }
            }
            importModif.append("</ul></div>");
        }

        String body = "<h1>Modificateur de liste</h1><section class='ModificationListe_structure'>"
            +importModif 
            +importListe
            +"<form method='post' action='ValiderModifListe' class='Validationliste_form'>\r\n"
            + "    <input type=\"hidden\" name=\"idListe\" value='"
            +idListeParam 
            +"'>\r\n"
            + "    <button type='submit' class='Validationliste_bouton'>Valider les modifications</button>\r\n"
            + "</form>"
            +"</section>";

        String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
        out.println(html);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
