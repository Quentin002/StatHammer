package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

import Model.ArmeeListe;
import Model.Figurine;

@WebServlet("/ChargerModifListe")
public class ChargerModifListe extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChargerModifListe() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("ConnexionView");
            return;
        }

        int idListeParam = Integer.parseInt(request.getParameter("idArmee"));
        session.setAttribute("idListeParam", idListeParam);
        ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");

        if (listes != null && !listes.isEmpty()) {
            // Préparer les figurines par unité
            Map<String, ArrayList<Figurine>> figurineParUnite = new HashMap<>();

            for (ArmeeListe liste : listes) {
                if (liste.getId() == idListeParam) {
                    for (String nomUnite : liste.getUniteListe()) {
                        ArrayList<Figurine> figs = ConnexionController.chargerFigurineListes(nomUnite, liste.getId());
                        figurineParUnite.put(nomUnite, figs);
                    }
                }
            }
            session.setAttribute("Figurinelistes", figurineParUnite);
            System.out.println(figurineParUnite);
            int idArmee = listes.get(0).getIdArmee();
            ArrayList<ArmeeListe> unitModif = ConnexionController.chargerUnitModifListes(idListeParam, idArmee);
            session.setAttribute("Modiflistes", unitModif);

            Map<String, ArrayList<Figurine>> modifFigurineParUnite = new HashMap<>();
            for (ArmeeListe unit : unitModif) {
                for (String uniteName : unit.getUniteListe()) {
                    ArrayList<Figurine> figs = ConnexionController.chargerFigurineModifListes(uniteName, idArmee);
                    modifFigurineParUnite.put(uniteName, figs);
                }
            }
            session.setAttribute("ModifFigurinelistes", modifFigurineParUnite);

            response.sendRedirect("ModificationListeView");
        } else {
            response.sendRedirect("GestionListeView");
        }
    }
}
