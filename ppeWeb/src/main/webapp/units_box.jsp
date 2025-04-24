<% for(Unit one_unit : units_list) { %>
<div class="one_unit_zone" id="col1_unit1">
	<button class="unit_button" onclick="unfoldUnits(1, 'col1_unit1');"><%= on_unit.getName() %></button>
	<div class="unit_box hidden">
<% for(ArrayList<Figurine> fig_group : one_unit.getChildren()) { %>
		<div class="unit_group" id="unit1_group1">
<% if(col == 1) { // attaquants %>
			<p class="number" onclick="openWeaponsAptitudesZone('unit1_group1');">1</p>
<% for(Figurine one_fig : fig_group) {
String fig_icon = one_fig.getHp() > 0 ? "android-fill.png" : "android-line.png"; %>
			<p><img src="assets/<%= fig_icon %>" alt="logo figurine"><span><%= one_fig.getHpMax() %>PV </span></p>
<% } // fin foreach sur figurines attaquantes
} else if(col = 2) { // défenseurs %>
			<form oninput="nb_alive_figs_out.value = nb_alive_figs_in.value">
				<label><%= one_fig.getName() %></label>
				<div>
					<input class="alive_figs_range" type="range" name="nb_alive_figs_in" min="0" max="<%= fig_group.length %>" value="<%= fig_group.length %>" onchange="selectAliveFigsNumber();">
					<output name="nb_alive_figs_out"><%= fig_group.length %></output>
				</div>
			</form>
			<div class="fig_group">
<% for(Figurine one_fig : fig_group) {
String fig_icon = one_fig.getHp() > 0 ? "android-fill.png" : "android-line.png"; %>
				<div>
					<label for="nb_alive_figs"><img src="assets/<%= fig_icon %>" alt="logo figurine">PV: </label>
					<input type="number" id="nb_alive_figs_unit1_group1" name="nb_alive_figs" min="0" max="<%= one_fig.getHpMax() %>" value="<%= one_fig.getHp() %>" onchange="setFigurineHP();">
				</div>
<% } // fin foreach sur figurines défenseuses  %>
			</div>
<% } // fin else if %>
		</div>
<% } // fin foreach sur groupes de figurines %>
	</div>
</div>
<% } // fin foreach sur liste d'unités %>