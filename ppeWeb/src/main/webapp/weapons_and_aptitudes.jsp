<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList"
import="java.util.HashMap"
import="Model.ArmeeListe"
import="Model.Unit"
import="Model.Arme" 
import="Model.Figurine"
import="Model.Aptitude" %>
<% ArrayList<Figurine> fig_group = (ArrayList<Figurine>)request.getAttribute("fig_group");
int group = (int)request.getAttribute("group");
//ArrayList<Figurine> group_keys = (ArrayList<Figurine>)request.getAttribute("group_keys");
Figurine one_fig = fig_group.get(0); 
Arme selected_weapon = one_fig.getSelectedWeapon();
ArrayList<Arme> weapon_list = one_fig.getArmes();%>
<div id="weapons_box_unit_name">
	<p class="number"><%= group + 1 %></p><p><%= one_fig.getNom() %></p>
</div>
<select id="weapon_select" name="weapon_select" onchange="selectWeapon('group<%= group %>')">
<%//for(Arme one_weapon : weapon_list) {
for(int i = 0; i < weapon_list.size(); i++) { %>
	<option value="<%= i %>"><%= weapon_list.get(i).getNom() %></option>
<% } %>
</select>
<table id="weapon_stats">
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
<form oninput="nb_weapons_out.value = nb_weapons_in.value">
	<label>Nombre d'attaquants:</label>
	<input id="weapon_number_range" type="range" name="nb_weapons_in" min="0" max="<%= fig_group.size() %>" value="<%= (int)request.getAttribute("alive_figs") %>" onchange="selectNumberOfWeapons('group<%= group %>');">
	<output name="nb_weapons_out"><%= (int)request.getAttribute("alive_figs") %></output>
</form>
<div id="aptitudes">
<% for(Aptitude aptitude : one_fig.getAptitudes()) { %>
	<div>
		<input type="checkbox" id="apt1" name="<%= aptitude.getName() %>" onclick="checkAptitude('apt1');">
		<label for="apt1"><%= aptitude.getName() %></label>
	</div>
<% } %>
</div>