package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.ArmeeListe;
import Model.Evenement;
import Model.Unit;


@WebServlet("/ConnexionController")
public class ConnexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConnexionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirection vers page de connexion ou erreur
		response.sendRedirect("connexionFailed.jsp");
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");

		if (login == null || mdp == null) {
			response.sendRedirect("connexionFailed.jsp");
			return;
		}

		login = login.trim();
		mdp = Integer.toString(mdp.hashCode()); // Hash comme dans l'appli JavaFX

		BDD.setInfos("400129", "stathammer_greta_admin", "stathammer_v1");
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");

		try {
			// Authentification
			ArrayList<Object> rendu = conec.selectUtilisateur(login, mdp);
			int id = conec.UtilisateurID(login, mdp);
			String role = conec.UtilisateurRole(login, mdp);
			String email = conec.UtilisateurEmail(login, mdp);

			if (!rendu.isEmpty() && login.equals(rendu.get(0))) {
				
				// Authentifié : création de session
				HttpSession session = request.getSession();
				
				session.setAttribute("nom", login);
				session.setAttribute("mdp", mdp);
				session.setAttribute("id", id);
				session.setAttribute("role", role);
				session.setAttribute("email", email);

				// Chargement des événements
				chargerEvenements(conec,session);
				ArrayList<Evenement> evenements = (ArrayList<Evenement>) session.getAttribute("events");
				
				// Debug : affichage console				
				System.out.println("Événements stockés en session :");
				for (int i = 0; i < evenements.size(); i++) {
					Evenement evt = evenements.get(i);
					System.out.println("Nom : " + evt.getNom_evenement());
					System.out.println("Image : " + evt.getNom_image());
					System.out.println("Description : " + evt.getDescritption_evenement());
					System.out.println("Date : " + evt.getData_evenement());
					System.out.println("-------------------------");
				
				// Chargement des listes
				ArrayList<Model.ArmeeListe> listes = chargerListes(conec,session);
				for(ArmeeListe armeeListes : listes) {
					for(Unit unit : armeeListes.getUnits()) {
						unit.setFigurine(Instanciation.getFigurine2(unit.getName()));
					}
				}
				session.setAttribute("listes", listes);
				}
				conec.close();
				System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
				response.sendRedirect("accueil");
			} else {
				// Authentification échouée
				conec.close();
				System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
				response.sendRedirect("connexionFailed.jsp");
			}
		} catch (Exception e) {
			conec.close();
			System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
			e.printStackTrace();
			response.sendRedirect("connexionFailed.jsp");
		}
	}

	public static void chargerEvenements(BDD conec, HttpSession session) throws SQLException {
		//ArrayList<String[]> evenements = new ArrayList<>();
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		String sql = "SELECT * FROM evenement";
		PreparedStatement ps = conec.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
		    
			String nom_evt = rs.getString("nom_evenement");
			String nom_img = rs.getString("nom_image");
			String desc_evt = rs.getString("description_evenement");
			String date_evt = rs.getString("date_evenement");

			evenements.add(new Evenement(nom_evt, nom_img, desc_evt, date_evt));	
		}
		
		session.setAttribute("events", evenements);
	}
	
	public static void updatePseudo(String pseudo,int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		conec.updateUtilisateur(pseudo,id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
	}
	public static void updateMdp(String mdp,int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		conec.updateMp(mdp,id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
	}
	public static String selectMdp(int id) throws SQLException {
		BDD conec = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
		String mdp =conec.UtilisateurMdp(id);
		conec.close();
		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
		return mdp;
	}
	public static void suppressionListe(String idListe) throws SQLException{
		BDD conec = new BDD();
		conec.SuppressionListe(idListe);
		conec.close();
	}
	private ArrayList<Model.ArmeeListe> chargerListes(BDD conec, HttpSession session) throws SQLException{
		ArrayList<Model.ArmeeListe> listes = new ArrayList<>();
		String sql = "SELECT l.id_liste, l.nom_liste, l.description_liste, u.nom_unite, u.id_armee\r\n"
				+ "	        FROM liste l\r\n"
				+ "	        LEFT JOIN contenir c ON l.id_liste = c.id_liste\r\n"
				+ "	        LEFT JOIN unite u ON c.id_unite = u.id_unite\r\n"
				+ "	        WHERE l.id_utilisateur="
				+ session.getAttribute("id") ; 
		HashMap<Integer, Model.ArmeeListe> mapListes = new HashMap<>();
		
		PreparedStatement ps = conec.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id_liste = rs.getInt("id_liste");
			String nom_liste = rs.getString("nom_liste");
			String descr_liste = rs.getString("description_liste");
			String nomUnite_liste = rs.getString("nom_unite");
			int idArmee_liste = rs.getInt("id_armee");
			
			// Vérifier si la liste existe déjà dans la map
            Model.ArmeeListe liste = mapListes.get(id_liste);
            if (liste == null) {
                liste = new Model.ArmeeListe(id_liste,  nom_liste, descr_liste,"",(int)session.getAttribute("id"));
                liste.setUniteListe(new ArrayList<>()); // Initialiser la liste des unités
                mapListes.put(id_liste, liste);
            }
            if (nomUnite_liste != null) {
                liste.getUniteListe().add(nomUnite_liste);
            }
		}
		listes.addAll(mapListes.values());
		
		return listes;
	}
}
