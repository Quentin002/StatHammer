package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    HttpSession session=request.getSession(false);

	    String actionStr = request.getParameter("action");
	    
	    //Convertir en integer
	    int action = Integer.parseInt(actionStr);
		
		@SuppressWarnings("unchecked")
	     ArrayList<String[]> evenements = (ArrayList<String[]>) session.getAttribute("evenements");
	     
	     String[] evt = evenements.get(action);
         String nomEvt = evt[0];
         String nomImg = evt[1];
         String descEvt = evt[2];
         String dateEvt = evt[3];
         
         System.out.println(nomEvt);
         System.out.println(nomImg);
         System.out.println(descEvt);
         System.out.println(dateEvt);
         
         
         
         response.sendRedirect("AdminView");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/*
	public static void supprimerEvenement(String nomEvt, String nomImg) {
		// Créer une instance de BDD
		BDD bdd = new BDD();
		try {
		    // Requête SQL
		    String sql = "DELETE FROM evenement WHERE nom_evenement = ?";  // Requête DELETE
		    PreparedStatement ps = bdd.getPreparedStatement(sql);
		    
		    // Associer l'ID de l'événement à supprimer
		    ps.setString(1, nomEvt);
		    
		    // Exécution de la requête
		    int rowsAffected = ps.executeUpdate();
		    if (rowsAffected > 0) {
		    	AdminController.effacerImage(nomImg);
		    	
		        Connexion.eventC.getNom_event().remove(evt.getNom_evenement());
		        Connexion.eventC.getNom_image().remove(evt.getNom_image());
		        Connexion.eventC.getDesc_event().remove(evt.getDescritption_evenement());
		        Connexion.eventC.getDate_event().remove(evt.getData_evenement());

		    	
		    	
		    	AfficheAdmin.setEvents(EvenementController.EvenementVBox());
	            Connexion.event = new Evenement(Connexion.eventC.getNom_event().get(0),
	            		Connexion.eventC.getNom_image().get(0),
	            		Connexion.eventC.getDesc_event().get(0),
	            		Connexion.eventC.getDate_event().get(0));
	    		System.out.println(Connexion.eventC.getNom_image().get(0));
		    	AfficheAdmin.affiche(AfficheAdmin.getStage(), AfficheAdmin.getSess());
		    	

		        System.out.println("Suppression réussie dans la base de données");
		        
		        
		    } else {
		        System.out.println("Aucun événement trouvé avec cet ID.");
		    }
		    
		    

		} catch (SQLException e) {
		    System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
		} finally {
		    // Être sûr de fermer la connexion à la base de données
		    bdd.close();
		}
		
	}
	
		public static void effacerImage(String nomImg) {
	    String file_name = "img/"+nomImg;
	    
	    Path path = Paths.get(file_name);
	    try {
	      boolean result = Files.deleteIfExists(path);
	      if (result) {
	        System.out.println("Image effacée !");
	      } else {
	        System.out.println("Oups, impossible d'effacer.");
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
*/
}
