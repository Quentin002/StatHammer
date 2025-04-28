package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Evenement;


/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
@MultipartConfig
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

	    @SuppressWarnings("unchecked")
	    ArrayList<Evenement> evenements = (ArrayList<Evenement>) session.getAttribute("events");
		
		
	    if (request.getParameter("supprEvt") != null) {

		    String supprEvtStr = request.getParameter("supprEvt");
		    
		    //Convertir en integer
		    int supprEvt = Integer.parseInt(supprEvtStr);
			    
		     Evenement evt = evenements.get(supprEvt);
	         String nomEvt = evt.getNom_evenement();
	         String nomImg = evt.getNom_image();
	         
	         supprimerEvenement(session, nomEvt, nomImg);
	         
	    }
        
         response.sendRedirect("evenements");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 HttpSession session=request.getSession(false);

		    // Upload de l'image via Multipart
		    if (request.getContentType() != null ) {
		        String nom_evt = request.getParameter("nom");
		        String desc_evt = request.getParameter("desc");
		        String date_evt = request.getParameter("date");

		        Part imagePart = request.getPart("image");
		        String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

		        // Dossier de destination
		        String uploadDir = getServletContext().getRealPath("/img");
		        File uploads = new File(uploadDir);
		        if (!uploads.exists()) {
		            uploads.mkdirs();
		        }

		        // Emplacement final de l'image
		        File file = new File(uploads, fileName);
		        imagePart.write(file.getAbsolutePath()); // Écrit l'image sur le disque

		        Evenement newEvt = new Evenement(nom_evt, fileName, desc_evt, date_evt);
		        insererEvenement(newEvt, session);
		    }	
		    response.sendRedirect("evenements");

	}
	
	public static void supprimerEvenement(HttpSession session, String nomEvt, String nomImg) {
		// Créer une instance de BDD
		
		BDD bdd = new BDD();
		System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
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
		    	ConnexionController.chargerEvenements(bdd, session);
		    	
		        System.out.println("Suppression réussie dans la base de données");   
		        
		    } else {
		        System.out.println("Aucun événement trouvé avec cet ID.");
		    }
		    
		    

		} catch (SQLException e) {
		    System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
		} finally {
		    // Être sûr de fermer la connexion à la base de données
		    bdd.close();
			System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
		}
		
	}
	
		public static void effacerImage(String nomImg) {
	    String file_name = "img/"+ nomImg ;
	    
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
		
		
	    public static void insererEvenement(Evenement evt, HttpSession session) {
	        // Créer une instance de BDD
	        BDD bdd = new BDD();
			System.out.println(" - - - - - - - Connexion à la base de données : >-- ouverte --< ");
	        try {
	            // Requête SQL
	            String sql = "INSERT INTO evenement (nom_evenement, nom_image, description_evenement, date_evenement, id_utilisateur) VALUES (?, ?, ?, ?, 3)";  // Requête
	            PreparedStatement ps = bdd.getPreparedStatement(sql);
	            
		        // Associer les valeurs aux paramètres
		        ps.setString(1, evt.getNom_evenement());
		        ps.setString(2, evt.getNom_image());
		        ps.setString(3, evt.getDescritption_evenement());
		        ps.setString(4, evt.getData_evenement());
		       
		        // Exécution de la requête
		        ps.executeUpdate();

		        ConnexionController.chargerEvenements(bdd, session);
		        System.out.println("Insertion réussie dans la base données");

	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
	        } finally {
	            // Être sûr de fermer la connexion à la base de données
	            bdd.close();
	    		System.out.println(" - - - - - - - Connexion à la base de données : <-- fermée --> ");
	        }
	    }

}
