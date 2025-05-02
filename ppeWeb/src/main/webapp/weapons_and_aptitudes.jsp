<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList"
import="java.util.HashMap"
import="Model.ArmeeListe"
import="Model.Unit"
import="Model.Arme" 
import="Model.Figurine"%>
<% ArrayList<Figurine> fig_group = (ArrayList<Figurine>)request.getAttribute("fig_group");
Figurine one_fig = fig_group.get(0); 
Arme selected_weapon = fig_group.get(0).getSelectedWeapon();
 ArrayList<Arme> weapon_list = fig_group.get(0).getArmes();%>

<div id="weapons_box_unit_name">
	<p class="number">1</p><p><%= one_fig.getNom() %></p>
</div>
<select id="weapon_select" name="weapon_select" onchange="selectWeapon()">
 <%for( Arme one_weapon :  weapon_list ) { %>
	<option value="<%= one_weapon.getNom() %>"><%= one_weapon.getNom() %></option>
<% } %>
</select>
<table id="weapon_stats" class="hidden" > 
	<tr>
		<td><%= selected_weapon.getA() %></td>
		<td><%= selected_weapon.getF() %></td>
		<td><%= selected_weapon.getPA() %></td>
		<td><%= selected_weapon.getD() %></td>
		<td><%= selected_weapon.getPortee() %></td>
	</tr>
</table>
<form oninput="nb_weapons_out.value = nb_weapons_in.value">
	<label>Nombre d'attaquants:</label>
	<input id="weapon_number_range" type="range" name="nb_weapons_in" min="0" max="<%= fig_group.size() %>" value="4" onchange="selectNumberOfWeapons('unit1_group1');">
	<output name="nb_weapons_out">4</output>
</form>
<div id="aptitudes">
<% for(Aptitude aptitude : aptitudes_list) { %>
	<div>
		<input type="checkbox" id="apt1" name="<%= aptitude.getName() %>" onclick="checkAptitude('apt1');">
		<label for="apt1"><%= aptitude.getName() %></label>
	</div>
<% } %>
</div>