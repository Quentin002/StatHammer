function selectList(nb){
	const units_list = document.getElementById("units_list" + nb);
	units_list.innerHTML = '';
	let select_name;
	if(nb == 1){
		select_name = "left_choice_box";
	}
	else if(nb == 2){
		select_name = "right_choice_box";
	}
	else{
		return;
	}
	Battle.setListName(nb,document.getElementById(select_name).value);
	

	// copie de units_box.jsp pour test en attendant d'utiliser le serveur
	let data;
	if(nb == 1){
		data = JSON.stringify({ html: `<div class="one_unit_zone" id="col1_unit1">
	<button class="unit_button" onclick="unfoldUnits(1, 'col1_unit1');">Unité 1</button>
	<div class="unit_box hidden">
		<div class="unit_group" id="unit1_group1">
			<p class="number" onclick="openWeaponsAptitudesZone();">1</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
		</div>
		<div class="unit_group" id="unit1_group1">
			<p class="number" onclick="openWeaponsAptitudesZone();">2</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
		</div>
	</div>
</div>
<div class="one_unit_zone" id="col1_unit2">
	<button class="unit_button" onclick="unfoldUnits(1, 'col1_unit2');">Unité 2</button>
	<div class="unit_box hidden">
		<div class="unit_group" id="unit1_group1">
			<p class="number" onclick="openWeaponsAptitudesZone();">1</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
		</div>
		<div class="unit_group" id="unit1_group1">
			<p class="number" onclick="openWeaponsAptitudesZone();">2</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
		</div>
	</div>
</div>`, army_file: 'android-fill.png' });
	}
	else if(nb == 2){
		data = JSON.stringify({ html: `<div class="one_unit_zone" id="col2_unit1">
	<button class="unit_button" onclick="unfoldUnits(1, 'col2_unit1');">Unité 1</button>
	<div class="unit_box hidden">
		<div class="unit_group" id="unit1_group1">
			<p class="number" onclick="openWeaponsAptitudesZone('unit1_group1');">1</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
		</div>
		<div class="unit_group" id="unit1_group2">
			<p class="number" onclick="openWeaponsAptitudesZone('unit1_group2');">2</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
		</div>
	</div>
</div>
<div class="one_unit_zone" id="col2_unit2">
	<button class="unit_button" onclick="unfoldUnits(1, 'col2_unit2');">Unité 2</button>
	<div class="unit_box hidden">
		<div class="unit_group" id="unit2_group1">
			<p class="number" onclick="openWeaponsAptitudesZone('unit2_group1');">1</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>5PV </span></p>
		</div>
		<div class="unit_group" id="unit2_group2">
			<p class="number" onclick="openWeaponsAptitudesZone('unit2_group2');">2</p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
			<p><img src="assets/android-fill.png" alt="logo figurine"><span>2PV </span></p>
		</div>
	</div>
</div>`, army_file: 'android-fill.png' });
	}

	units_list.innerHTML = '';
	// sauvegarder le html généré (= data) dans Battle
	units_list.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
	const logo_box = document.getElementById("logo_list" + nb);
	logo_box.querySelector("img").src = "armees/" + JSON.parse(data).army_file;
	logo_box.classList.remove('hidden');
	return;

	if(Battle.getListName(col) == null){
		fetch("http://webhammer/ControllerSimu", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ action: "make_units_box", col: nb, list: Battle.getListName(nb) })
		})
			.then(response => {
				if (!response.ok) {
					throw new Error('erreur réponse requête AJAX ' + response.statusText);
				}
				return response.json();
			})
			// le serveur renvoie une vue et le nom d'une armée
			.then(data => {
				units_list.innerHTML = '';

				// sauvegarder le html généré (= data) dans Battle

				// l'insérer dans la page
				units_list.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
				
				// logo
				const logo_box = document.getElementById("logo_list" + nb);
				logo_box.querySelector("img").src = "armees/" + JSON.parse(data).army_file;
				logo_box.classList.remove('hidden');
			})
			.catch(error => {
				console.error('erreur capturée requête AJAX', error);
			});
	}
	else{
		// afficher sauvegarde du HTML
	}
}

function unfoldUnits(col, unit_section_id){
	/* déroulement des unités et cadre jaune du bouton, même algo que dans le client lourd */
	const old_unit_box = document.getElementById(Battle.getUnitId(col)); // peut être null
	const new_unit_box = document.getElementById(unit_section_id); // peut être le même élément

	// il y a déjà une unité sélectionnée
    if(Battle.getUnitId(col) != null)
    {
    	old_unit_box.querySelector(".unit_button").classList.remove("unit_selected"); // retirer bordure unité sélectionnée
		document.getElementById("weapons_aptitudes_box").classList.add("hidden"); // cacher fenêtre combat si existe
    	
    	// bouton même unité => ferme
    	if(Battle.getUnitId(col) == unit_section_id)
        {
    		new_unit_box.querySelector(".unit_box").classList.add("hidden");
    		Battle.setUnitId(col, null);
        }
    	// bouton autre unité
    	else
    	{
        	if(old_unit_box != null) {
				old_unit_box.querySelector(".unit_box").classList.add("hidden"); // supprimer ancienne zone de figurines
			}
        	Battle.setUnitId(col, unit_section_id);
        	new_unit_box.querySelector(".unit_button").classList.add("unit_selected"); // bordure unité sélectionnée
        	new_unit_box.querySelector(".unit_box").classList.remove("hidden"); // on ouvre
    	}
    }
    // pas d'unité sélectionnée => on ouvre
    else
    {
    	Battle.setUnitId(col, unit_section_id);
    	new_unit_box.querySelector(".unit_button").classList.add("unit_selected"); // bordure unité sélectionnée
    	new_unit_box.querySelector(".unit_box").classList.remove("hidden");
    }
}

function openWeaponsAptitudesZone(group_id){
	const weapons_aptitudes_box = document.getElementById("weapons_aptitudes_box");

	// code de test
	const data = JSON.stringify({ html: `<div id="weapons_box_unit_name">
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
</div>` });
	// html généré
	weapons_aptitudes_box.classList.remove("hidden");
	weapons_aptitudes_box.innerHTML = '';
	weapons_aptitudes_box.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
	return;

	fetch("http://webhammer/ControllerSimu", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ action: "make_weapons_aptitudes_zone", list: Battle.getListName(nb) })
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('erreur réponse requête AJAX ' + response.statusText);
			}
			return response.json();
		})
		// le serveur renvoie une vue et le nom d'une armée
		.then(data => {
			// html généré
			weapons_aptitudes_box.classList.remove("hidden");
			weapons_aptitudes_box.innerHTML = '';
			weapons_aptitudes_box.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});
}


/* zone armes et aptitudes */
function selectWeapon(){
	// faire que la première arme soit sélectionnée par défaut
	// avant même que cette fonction soit appelée

	const selected_weapon = document.getElementById("weapon_select").value;
	
	const table = document.getElementById("weapon_stats");
	table.classList.remove('hidden');
	
	// balises td
	const td_markups = table.querySelectorAll('td');

	// données, attention à la portée en pouces avec double quote
	const data = {A: "2", F: "4", PA: "0", D: "1", portée: "24\""};
	const weapon_stats = new Map(Object.entries(data));
	
	if(td_markups.length == weapon_stats.size)
	{
		// affichage des clés et valeurs
		const interator = weapon_stats.keys();
		for(let i = 0; i < td_markups.length; i++) {
			const key = interator.next().value;
			td_markups[i].innerHTML = key + ": " + weapon_stats.get(key);
		}
	}
	else{
		console.log("erreur avec les statistiques de l'arme");
	}
}

function selectWeaponNumber(){
	const nb_weapon = document.getElementById("weapon_number_range").value;
	console.log(nb_weapon);
}

function checkAptitude(aptitude_id){
	const box = document.getElementById(aptitude_id);
	console.log(box.name); // normalement name="" = innerHTML
	console.log(box.checked);
}


/* boutons centraux */
function calculate(){
	// contrôle: une unité doit être sélectionnée de chaque côté
	if(Battle.getListName(1) == null || Battle.getListName(2) == null
		|| Battle.getUnitId(1) == null || Battle.getUnitId(2) == null)
	{
		toastNotify("conditions non remplies pour lancer une simulation");
		return;
	}

	const histogramme = document.getElementById("histogramme");

	// en javascript, static ne fonctionne que dans une classe
	if(typeof calculate.compteur === 'undefined') {
        calculate.compteur = 0;
    }
    // remplacer illustration par l'histogramme
	if(calculate.compteur == 0){
		document.getElementById("warhammer_picture").classList.add('hidden');
		histogramme.classList.remove('hidden');
		calculate.compteur++;
		console.log(calculate.compteur);
	}

	// insérer l'histogramme en supprimant l'ancien si il existe
}

function reverseArmies(){
	// contrôle: une liste doit être sélectionnée de chaque côté
	if(Battle.getListName(1) == null || Battle.getListName(2) == null)
	{
		toastNotify("sélectionner 2 listes pour pouvoir les inverser");
		return;
	}

	// dire au serveur d'inverser les deux joueurs
	//fetch...

	// inverser l'affichage
	selectList(1);
	selectList(2);

	// inutile
	/*const units_list1 = document.getElementById("units_list1");
	const units_list2 = document.getElementById("units_list2");
	let units_list1_child = units_list1.querySelector(".one_unit_zone");
	let units_list2_child = units_list2.querySelector(".one_unit_zone");
	units_list1.innerHTML = '';
	units_list2.innerHTML = '';
	units_list1.appendChild(units_list2_child);
	units_list2.appendChild(units_list1_child);

	let tmp = units_list1_child;
	units_list1_child = units_list2_child;
	units_list2_child = tmp;*/
}