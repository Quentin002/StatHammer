<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList"
import="java.util.HashMap"
import="Model.ArmeeListe"
import="Model.Unit"
import="Model.Figurine" %>
<% ArrayList<Unit> units_list = (ArrayList<Unit>)request.getAttribute("units_list");
Object col = request.getAttribute("col");
for(int i = 0; i < units_list.size(); i++) {
	Unit one_unit = units_list.get(i); %>
<div class="one_unit_zone" id="col<%= col %>_unit<%= i %>">
	<button class="unit_button" onclick="unfoldUnits(1, 'col<%= col %>_unit<%= i %>');"><%= one_unit.getName() %></button>
	<div class="unit_box hidden">
<% int black_number = 0;
for(HashMap.Entry<String, ArrayList<Figurine>> fig_group : one_unit.getIdenticalFigsGroups().entrySet()) {
	black_number++; %>
		<div class="unit_group" id="<%= "fig_" + fig_group.getKey() %>">
<% if((int)request.getAttribute("col") == 1) { // attaquants %>
			<p class="number" onclick="openWeaponsAptitudesZone('<%= "fig_" + fig_group.getKey() %>');"><%= black_number %></p>
<% for(Figurine one_fig : fig_group.getValue()) {
String fig_icon = one_fig.getHP() > 0 ? "android-fill.png" : "android-line.png"; %>
			<p><img src="assets/<%= fig_icon %>" alt="logo figurine"><span><%= one_fig.getHPMax() %>PV </span></p>
<% } // fin foreach sur figurines attaquantes
} else if((int)request.getAttribute("col") == 2) { // défenseurs %>
			<form oninput="nb_alive_figs_out.value = nb_alive_figs_in.value">
				<label><%= fig_group.getValue().get(0).getNom() %></label>
				<div>
					<input id="unit<%= i %>_group<%= fig_group.getKey() %>_input" class="alive_figs_range" type="range" name="nb_alive_figs_in" min="0" max="<%= fig_group.getValue().size() %>" value="<%= fig_group.getValue().size() %>" onchange="selectAliveFigsNumber('unit<%= i %>_group<%= fig_group.getKey() %>');">
					<output name="nb_alive_figs_out"><%= fig_group.getValue().size() %></output>
				</div>
			</form>
			<div id="unit<%= i %>_group<%= fig_group.getKey() %>_figs" class="fig_group">
<% ArrayList<Figurine> one_fig_group = fig_group.getValue();
for(int j = 0; j < one_fig_group.size(); j++){
String fig_icon = one_fig_group.get(j).getHP() > 0 ? "android-fill.png" : "android-line.png"; %>
				<div id="unit<%= i %>_group_<%= fig_group.getKey() %>_fig<%= j %>">
					<label for="nb_alive_figs"><img class="fig_icon" src="assets/<%= fig_icon %>" alt="logo figurine">PV: </label>
					<input class="hp_selector" type="number" name="nb_alive_figs" min="0" max="<%= one_fig_group.get(j).getHPMax() %>" value="<%= one_fig_group.get(j).getHP() %>" onchange="setFigurineHP('unit<%= i %>_group_<%= fig_group.getKey() %>_fig<%= j %>');">
				</div>
<% } // fin foreach sur figurines défenseuses  %>
			</div>
<% } // fin else if %>
		</div>
<% } // fin foreach sur groupes de figurines %>
	</div>
</div>
<% } // fin foreach sur liste d'unités %>