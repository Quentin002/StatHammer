<div id="weapons_box_unit_name">
	<p class="number">1</p><p><%= one_fig.getName() %></p>
</div>
<select id="weapon_select" name="weapon_select" onchange="selectWeapon()">
<% for(Weapon one_weapon : weapons) { %>
	<option value="<%= one_weapon.getName() %>"><%= one_weapon.getName() %></option>
<% } %>
</select>
<table id="weapon_stats" class="hidden">
	<tr>
		<td><%= stats.get(0) %></td>
		<td><%= stats.get(1) %></td>
		<td><%= stats.get(2) %></td>
		<td><%= stats.get(3) %></td>
		<td><%= stats.get(4) %></td>
	</tr>
</table>
<form oninput="nb_weapons_out.value = nb_weapons_in.value">
	<label>Nombre d'attaquants:</label>
	<input id="weapon_number_range" type="range" name="nb_weapons_in" min="0" max="<%= fig_group.length %>" value="4" onchange="selectWeaponNumber();">
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