<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<header>
<%! String boutonAdmin =""; %>
<% if ("Admin".equals(session.getAttribute("role"))) {%>
	<% boutonAdmin = "<li><a href='BarDeNavController?action=admin'>Événements</a></li>\r\n"; %>
<%} %>


<div class='barDeNav'>
			    <ul>
			      <li><a href='BarDeNavController?action=accueil'><i class="fas fa-house"></i></a></li>
			  <%= boutonAdmin %>
			  <li><a class='active' href='BarDeNavController?action=Compte'>Mon compte</a></li>
			  <li><a href='BarDeNavController?action=GererListe'>Gérer listes</a></li>
			 <li><a href='BarDeNavController?action=creaListe'>Création liste</a></li>
			  <li><a href='BarDeNavController?action=simu'>Simulation</a></li>
			     <li style='margin-left: auto;'><a href='BarDeNavController?action=logout' class='logout-btn'><i class='fas fa-sign-out-alt'></i></a></li>
			   </ul>
			</div>


</header>