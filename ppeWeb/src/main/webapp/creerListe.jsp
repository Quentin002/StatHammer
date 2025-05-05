<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="Controller.StockageCreerListe" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/creer_liste.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<title>CreerListe</title>
</head>
<body>
<%@ include file="barreNav.jsp" %>
	<form class="create_list_form" action="AiguillageServlet" method="get">
		<div class="left_column">
			<div>
				<input type="submit" name="creer" value="Créer une liste" />
				<div class="list_name">
					<label for="nomListe">Nom: </label>
	                <input type="text" id="nomListe" name="nomListe"/>
                </div>
            </div>
            <div class="factions_armies">
	            <div class="factions">
	            	<h2>factions</h2>
	                <% for(String nom : StockageCreerListe.getNomFac()){ %>
	                <input type="submit" name="faction" value="<%= nom %>"/>
	                <% } %>
				</div>
				<div class="armies">
					<h2>armées</h2>
	                <% for(String nom : StockageCreerListe.getNomArmee()){ %>
					<input type="submit" name="armee" value="<%= nom %>"/>
					<% } %>
				</div>
			</div>
			<div class="destination_zone">
				<h2>sélection</h2>
				<div>
					<% for(String name : StockageCreerListe.getArmeeListe().getUnitNames()){ %>
					<input type="submit"name="moins" value="<%= name %>" /><br>
					<% } %>
				</div>
			</div>
		</div>
		<div class="right_column">
			<h2>unités</h2>
			<% for(String nom : StockageCreerListe.getNomUnit()){ %>
			<input type="submit" name="plus" value="<%= nom %>" /><br>
			<% } %>
		</div>
	</form>
</body>
</html>