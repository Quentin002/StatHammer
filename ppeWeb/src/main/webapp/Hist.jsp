<!--  <%@page import="java.util.ArrayList"%> -->
<%@page import="java.util.Arrays"%>
<%@page import="Model.Calcul"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			
	<!-- table :  <%= request.getAttribute("table") %> -->
	<!-- ${table} -->
	
	<%
	Calcul list = new Calcul();
          list = (Calcul)request.getAttribute("table");
          int[] tabMort = list.getTabmort1();
          int[] tabDegat = list.getTabdegat1();
    %>
   <p id="degat_moy" hidden><%= list.getDegat_moyen1() %></p> 
   <p id ="mort_moy" hidden> <%= list.getMort_moyen1() %></p> 
   
   <% for(int i = 0; i < tabDegat.length; i+=1) { 
   String idM = "m"+i;
   String idD = "d"+i;
   %>
			<p id= <%= idM %> hidden ><%= tabMort[i] %>   </p>
			<p id= <%= idD %> hidden ><%= tabDegat[i] %>   </p>  
   <% } %>
	
	<div id="chartContainer1" style="height: 300px; width:500px"></div>
	<div id="chartContainer2" style="height: 300px; width:500px"></div>
	
		
	
	 
	
	
	
	

