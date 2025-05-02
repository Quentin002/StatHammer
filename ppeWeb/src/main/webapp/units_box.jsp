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
<% HashMap<String, ArrayList<Figurine>> figs_group = one_unit.getIdenticalFigsGroups();
ArrayList<String> group_keys = new ArrayList<>(figs_group.keySet());
for(int j = 0; j < group_keys.size(); j++){
//for(HashMap.Entry<String, ArrayList<Figurine>> fig_group : one_unit.getIdenticalFigsGroups().entrySet()) { %>
		<div class="unit_group" id="unit<%= i %>_group<%= j %>">
<% if((int)request.getAttribute("col") == 1) { // attaquants %>
			<p class="number" onclick="openWeaponsAptitudesZone('fig_<%= j %>');"><%= j + 1 %></p>
<% for(Figurine one_fig : figs_group.get(group_keys.get(j))) {
String fig_icon = one_fig.getHP() > 0 ? "android-fill.png" : "android-line.png"; %>
			<p><img src="assets/<%= fig_icon %>" alt="logo figurine"><span><%= one_fig.getHPMax() %>PV </span></p>
<% } // fin foreach sur figurines attaquantes
} else if((int)request.getAttribute("col") == 2) { // défenseurs %>
			<form oninput="nb_alive_figs_out.value = nb_alive_figs_in.value">
				<label><%= figs_group.get(group_keys.get(j)).get(0).getNom() %></label>
				<div>
					<input id="unit<%= i %>_group<%= j %>_input" class="alive_figs_range" type="range" name="nb_alive_figs_in" min="0" max="<%= figs_group.get(group_keys.get(j)).size() %>" value="<%= figs_group.get(group_keys.get(j)).size() %>" onchange="selectAliveFigsNumber('unit<%= i %>_group<%= j %>');">
					<output name="nb_alive_figs_out"><%= figs_group.get(group_keys.get(j)).size() %></output>
				</div>
			</form>
			<div id="unit<%= i %>_group<%= j %>_figs" class="fig_group">
<% ArrayList<Figurine> one_fig_group = figs_group.get(group_keys.get(j));
for(int k = 0; k < one_fig_group.size(); k++){
String fig_icon = one_fig_group.get(k).getHP() > 0 ? "android-fill.png" : "android-line.png"; %>
				<div id="unit<%= i %>_group<%= j %>_fig<%= k %>">
					<label for="nb_alive_figs"><img class="fig_icon" src="assets/<%= fig_icon %>" alt="logo figurine">PV: </label>
					<input class="hp_selector" type="number" name="nb_alive_figs" min="0" max="<%= one_fig_group.get(k).getHPMax() %>" value="<%= one_fig_group.get(k).getHP() %>" onchange="setFigurineHP('unit<%= i %>_group<%= j %>_fig<%= k %>');">
				</div>
<% } // fin for sur figurines défenseuses  %>
			</div>
<% } // fin else if %>
		</div>
<% } // fin for sur groupes de figurines %>
	</div>
</div>
<% } // fin foreach sur liste d'unités %>