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

        ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");
        ArrayList<ArmeeListe> modifListes = (ArrayList<ArmeeListe>) session.getAttribute("Modiflistes");

        Map<String, ArrayList<Figurine>> figurineMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("Figurinelistes");
        Map<String, ArrayList<Figurine>> modifFigurineMap = (Map<String, ArrayList<Figurine>>) session.getAttribute("ModifFigurinelistes");

        if ("ajouter".equals(action)) {
            // retirer l’unité des modifListes
            for (ArmeeListe modif : modifListes) {
                if (modif.getUniteListe().remove(uniteName)) break;
            }

            // ajouter l’unité à la liste sélectionnée
            for (ArmeeListe liste : listes) {
                if (liste.getId() == idListe && !liste.getUniteListe().contains(uniteName)) {
                    liste.getUniteListe().add(uniteName);
                    break;
                }
            }

            // déplacer les figurines de ModifFigurinelistes vers Figurinelistes
            if (modifFigurineMap != null && figurineMap != null) {
                ArrayList<Figurine> figurines = modifFigurineMap.remove(uniteName);
                if (figurines == null) {
                    figurines = ConnexionController.chargerFigurineListes(uniteName, idListe);
                }
                figurineMap.put(uniteName, figurines);
            }

        } else if ("supprimer".equals(action)) {
            // Pour retirer l’unité de la liste sélectionnée
            for (ArmeeListe liste : listes) {
                if (liste.getId() == idListe) {
                    if (liste.getUniteListe().remove(uniteName)) break;
                }
            }

            // Pour ajouter à modifListes
            for (ArmeeListe modif : modifListes) {
                if (!modif.getUniteListe().contains(uniteName)) {
                    modif.getUniteListe().add(uniteName);
                    break;
                }
            }

            // déplace les figurines vers ModifFigurinelistes
            if (figurineMap != null && modifFigurineMap != null) {
                ArrayList<Figurine> figurines = figurineMap.remove(uniteName);
                if (figurines == null) {
                    figurines = ConnexionController.chargerFigurineModifListes(uniteName, idListe);
                }
                modifFigurineMap.put(uniteName, figurines);
            }
        }

        // réenregistrer les listes et les maps
        session.setAttribute("listes", listes);
        session.setAttribute("Modiflistes", modifListes);
        session.setAttribute("Figurinelistes", figurineMap);
        session.setAttribute("ModifFigurinelistes", modifFigurineMap);

        response.sendRedirect("ModificationListeView");
    }
}
