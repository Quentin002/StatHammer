<%@page import="Controller.StockageCreerListe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<title>CreerListe</title>
</head>
<%@ include file="barreNav.jsp" %>
<body>
	<form action="AiguillageServlet" method="get">
		
 		<table>
            <tr>
                <td>
                    <input type="submit" name="creer" value="Créer une liste" />
                    <input type="text" name="nomListe"/>
                    
                    <%
                    for(String nom:StockageCreerListe.getNomFac()){
                    	%>
                     <input type="submit" name="faction" value="<%=nom %>"/>
                    <%
                    }
                    %>

                    
                </td>
                <td rowspan=4>
                <%
              for(String nom:StockageCreerListe.getNomUnit()){
              	%>
               <input type="submit" name="plus" value="<%=nom %>" />
               <br>
              <%
              }
              %>
                </td>
               
            </tr>
            <tr>
                <td>
                    
                        <%
                    for(String nom:StockageCreerListe.getNomArmee()){
                    	%>
                     <input type="submit" name="armee" value="<%=nom %>"/>
                    <%
                    }
                    %>
                    
                </td>
                
            </tr>
             <tr>
                <td>
					<%
					for(String name:StockageCreerListe.getArmeeListe().getUnitNames()){
						
					%>
					<input type="submit"name="moins" value="<%=name %>" />
                    <br>
					<%
					}
					%>
                </td>
              
            </tr>
		</table>

	</form>
</body>
</html>