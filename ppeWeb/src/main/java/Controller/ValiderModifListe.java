package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ValiderModifListe")
public class ValiderModifListe extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValiderModifListe() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Récupération de l'id de la liste à modifier
        String idListeStr = request.getParameter("idListe");
        if (idListeStr == null || idListeStr.isEmpty()) {
            response.sendRedirect("erreur.jsp");
            return;
        }

        int idListe = Integer.parseInt(idListeStr);

        // Récupération de ajouts et suppressions depuis la session
        ArrayList<String> ajouts = (ArrayList<String>) session.getAttribute("ajouts");
        ArrayList<String> suppressions = (ArrayList<String>) session.getAttribute("suppressions");

        if (ajouts == null) ajouts = new ArrayList<>();
        if (suppressions == null) suppressions = new ArrayList<>();

        try {
            ConnexionController.modifierListe(idListe, ajouts, suppressions);

            // Nettoyage des listes en session
            session.removeAttribute("ajouts");
            session.removeAttribute("suppressions");

            // Redirection vers la page de gestion de liste
            response.sendRedirect("gerer-liste");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("accueil");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("accueil");
    }
}
