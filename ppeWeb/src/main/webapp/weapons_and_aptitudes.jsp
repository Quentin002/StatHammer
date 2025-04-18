<div id="weapons_box_unit_name">
	<p class="number">1</p><p>Initié</p>
</div>
<select id="weapon_select" name="weapon_select" onchange="selectWeapon()">
	<option value="Arme 1">Arme 1</option>
	<option value="Arme 2">Arme 2</option>
	<option value="Arme 3">Arme 3</option>
	<option value="Arme 4">Arme 4</option>
</select>
<table id="weapon_stats" class="hidden">
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
	<input id="weapon_number_range" type="range" name="nb_weapons_in" min="0" max="4" value="4" onchange="selectWeaponNumber();">
	<output name="nb_weapons_out">4</output>
</form>
<div id="aptitudes">
	<div>
		<input type="checkbox" id="apt1" name="Aptitude1" onclick="checkAptitude('apt1');">
		<label for="apt1">Aptitude1</label>
	</div>
	<div>
		<input type="checkbox" id="apt2" name="Aptitude2" onclick="checkAptitude('apt2');">
		<label for="apt2">Aptitude2</label>
	</div>
	<div>
		<input type="checkbox" id="apt3" name="Aptitude3" onclick="checkAptitude('apt3');">
		<label for="apt3">Aptitude3</label>
	</div>
	<div>
		<input type="checkbox" id="apt4" name="Aptitude4"
		 onclick="checkAptitude('apt4');">
		<label for="apt4">Aptitude4</label>
	</div>
	<div>
		<input type="checkbox" id="apt5" name="Aptitude5" onclick="checkAptitude('apt5');">
		<label for="apt5">Aptitude5</label>
	</div>
</div>