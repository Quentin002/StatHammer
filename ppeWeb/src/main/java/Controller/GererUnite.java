package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Model.ArmeeListe;
import Model.Figurine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/GererUnite")
public class GererUnite extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("ConnexionView");
            return;
        }

        String action = request.getParameter("action"); // "ajouter" ou "supprimer"
        String uniteName = request.getParameter("uniteName");
        int idListe = Integer.parseInt(request.getParameter("idListe"));

        // Chargement des listes existantes
        ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");
        ArrayList<ArmeeListe> modifListes = (ArrayList<ArmeeListe>) session.getAttribute("Modiflistes");
        Map<String, ArrayList<Figurine>> figurineMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("Figurinelistes");
        Map<String, ArrayList<Figurine>> modifFigurineMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("ModifFigurinelistes");

        // Nouvelles listes de suivi des changements
        ArrayList<String> ajouts = (ArrayList<String>) session.getAttribute("ajouts");
        ArrayList<String> suppressions = (ArrayList<String>) session.getAttribute("suppressions");
        if (ajouts == null) ajouts = new ArrayList<>();
        if (suppressions == null) suppressions = new ArrayList<>();

        if ("ajouter".equals(action)) {
            // Si l’unité était précédemment marquée pour suppression, on l'annule
            suppressions.remove(uniteName);

            // Si ce n'était pas déjà un ajout, on l'ajoute à la liste d’ajouts
            if (!ajouts.contains(uniteName)) {
                ajouts.add(uniteName);
            }

            // Logique UI
            for (ArmeeListe modif : modifListes) {
                modif.getUniteListe().remove(uniteName);
            }

            for (ArmeeListe liste : listes) {
                if (liste.getId() == idListe && !liste.getUniteListe().contains(uniteName)) {
                    liste.getUniteListe().add(uniteName);
                    break;
                }
            }

            if (modifFigurineMap != null && figurineMap != null) {
                ArrayList<Figurine> figurines = modifFigurineMap.remove(uniteName);
                if (figurines == null) {
                    figurines = ConnexionController.chargerFigurineListes(uniteName, idListe);
                }
                figurineMap.put(uniteName, figurines);
            }

        } else if ("supprimer".equals(action)) {
            ajouts.remove(uniteName); // Si ajout déjà prévu, on l'annule
            if (!suppressions.contains(uniteName)) {
                suppressions.add(uniteName);
            }

            for (ArmeeListe liste : listes) {
                if (liste.getId() == idListe) {
                    liste.getUniteListe().remove(uniteName);
                }
            }

            for (ArmeeListe modif : modifListes) {
                if (!modif.getUniteListe().contains(uniteName)) {
                    modif.getUniteListe().add(uniteName);
                    break;
                }
            }

            if (figurineMap != null && modifFigurineMap != null) {
                ArrayList<Figurine> figurines = figurineMap.remove(uniteName);
                if (figurines == null) {
                    figurines = ConnexionController.chargerFigurineModifListes(uniteName, idListe);
                }
                modifFigurineMap.put(uniteName, figurines);
            }
        }

        // Mise à jour des attributs de session
        session.setAttribute("ajouts", ajouts);
        session.setAttribute("suppressions", suppressions);
        session.setAttribute("listes", listes);
        session.setAttribute("Modiflistes", modifListes);
        session.setAttribute("Figurinelistes", figurineMap);
        session.setAttribute("ModifFigurinelistes", modifFigurineMap);
        response.sendRedirect("ModificationListeView");
    }
}
