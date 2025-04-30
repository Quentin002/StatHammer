// note: blocage possible par le mode "https only" de firefox
const url = "ControllerSimu";

function selectList(nb){
	const units_list = document.getElementById("units_list" + nb);
	units_list.innerHTML = ''; // nettoyage!
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
	Battle.setListId(nb, document.getElementById(select_name).value);

	if(Battle.getListId(nb) != null){
		// logo de l'armée
		var xhr2 = new XMLHttpRequest();
		xhr2.open('POST', url, true);
		xhr2.onreadystatechange = function() {
		  if (xhr2.readyState === 4 && xhr2.status === 200) {
			const logo_box = document.getElementById("logo_list" + nb);
			logo_box.querySelector("img").src = "armees/" + xhr2.responseText;
			logo_box.classList.remove('hidden');
		  }
		};
		
				
		// vue
		var xhr1 = new XMLHttpRequest();
		xhr1.open('POST', url, true);
		xhr1.onreadystatechange = function() {
		  if (xhr1.readyState === 4 && xhr1.status === 200) {
			units_list.innerHTML = '';
			units_list.insertAdjacentHTML('afterbegin', xhr1.responseText);
			
			// envoi lorsque la première est terminée
			xhr2.send(JSON.stringify({ action: "get_army_file", col: nb }));
		  }
		};
		xhr1.send(JSON.stringify({ action: "make_units_box", col: nb, list: Battle.getListId(nb)}));
		
		
	}
	else{
		// afficher sauvegarde du HTML
	}
}

function unfoldUnits(col, unit_section_id){
	/* déroulage des unités et cadre jaune du bouton, même algo que dans le client lourd */
	const old_unit_box = document.getElementById(Battle.getUnitId(col)); // peut être null
	const new_unit_box = document.getElementById(unit_section_id); // peut être le même élément
	const tmp = unit_section_id.split("_"); // sépare colX_unitY
	const unit_number = tmp[1].substring(4, tmp[1].length);
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			if(xhr.responseText.trim() == "success"){
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
			else{
				console.log("erreur MAJ unité sélectionnée sur le serveur");
			}
		}
	};
	
	// envoi avec les même conditions
	if(Battle.getUnitId(col) != null && Battle.getUnitId(col) == unit_section_id){
		xhr.send(JSON.stringify({ action: "set_selected_unit", col: col, fig: -1 }));
	}
	else{
		xhr.send(JSON.stringify({ action: "set_selected_unit", col: col, fig: unit_number }));
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
	<input id="weapon_number_range" type="range" name="nb_weapons_in" min="0" max="3" value="3" onchange="selectNumberOfWeapons('unit1_group1');">
	<output name="nb_weapons_out">3</output>
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

	fetch(url, {
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

			// première arme sélectionnée par défaut
			selectWeapon();
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});
}


/* commande colonne de droite */
function selectAliveFigsNumber(group_id){
	const input = document.getElementById(group_id + "_input");
	//const group_id_array = group_id.split("_"); // ["unitX", "nomfigurine"]
	//const fig_name = group_id_array[1];
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			const hp_inputs = document.getElementById(group_id + "_figs").querySelectorAll(".hp_selector");
			const fig_icons = document.getElementById(group_id + "_figs").querySelectorAll(".fig_icon");
			for(let i = 0; i < input.value; i++)
			{
				hp_inputs[i].value = hp_inputs[i].max;;
				fig_icons[i].src = 'assets/android-fill.png';
			}
			for(let i = input.value; i < hp_inputs.length; i++)
			{
				hp_inputs[i].value = 0; // remplacer 2 par la bonne valeur (figurine.getHpMax() en java)
				fig_icons[i].src = 'assets/android-line.png';
			}
		}
	};
	xhr.send(JSON.stringify({ action: "set_figs_group_hp", col: 2, group_id: group_id, hp: input.value}));
}

function setFigurineHP(fig_id){
	const fig_div = document.getElementById(fig_id);
	
	var xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4 && xhr.status === 200) {
				// icône
				if(fig_div.querySelector("input").value > 0){
					fig_div.querySelector("img").src = 'assets/android-fill.png';
				}
				else{
					fig_div.querySelector("img").src = 'assets/android-line.png';
				}
				// MAJ input range
				const group_id = fig_id.split('_').slice(0, 2).join('_'); // retirer _figX
				const group_div = document.getElementById(group_id + "_figs");
				const hp_inputs = group_div.querySelectorAll(".hp_selector");
				let alive_figs_number = 0;
				for(let i = 0; i < hp_inputs.length; i++){
					if(hp_inputs[i].value > 0){
						alive_figs_number++;
					}
				}
			    document.getElementById(group_id + "_input").value = alive_figs_number;
			    group_div.querySelector("output").innerHTML = alive_figs_number;
			}
		};
		xhr.send(JSON.stringify({ action: "set_one_fig_hp", fig: fig_id, hp: fig_div.querySelector("input").value }));
}


/* zone armes et aptitudes */
 /*function selectWeapon(){
	const selected_weapon = document.getElementById("weapon_select").value;

	// code de test
	const table = document.getElementById("weapon_stats");
	const td_markups = table.querySelectorAll('td');
	table.classList.remove('hidden');
	const stats = {A: "2", F: "4", PA: "0", D: "1", portée: "24\""}; // attention portée en pouces
	const weapon_stats = new Map(Object.entries(stats));
	
	if(td_markups.length == weapon_stats.size) // = 5
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
	return;
	
	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ action: "set_weapon", weapon: selected_weapon }) // idem selectAliveFigsNumber, nb d'armes = nb figurines vivantes
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('erreur réponse requête AJAX ' + response.statusText);
			}
			return response.json();
		})
		// le serveur renvoie une vue et le nom d'une armée
		.then(data => {
			const table = document.getElementById("weapon_stats");
			const td_markups = table.querySelectorAll('td');
			table.classList.remove('hidden');
			const stats = {A: data.a, F: data.f, PA: data.pa, D: data.d, portée: data.portee + "\""}; // attention portée en pouces
			const weapon_stats = new Map(Object.entries(stats));
			
			if(td_markups.length == weapon_stats.size) // = 5
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
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});
} */

function selectWeapon() {
    const selected_weapon = document.getElementById("weapon_select").value;

    // URL de l'API (à définir selon votre configuration)
    const url = 'https://votre-api.com/endpoint';

    // Fonction pour mettre à jour les statistiques de l'arme dans le tableau
    function updateWeaponStats(stats) {
        const table = document.getElementById("weapon_stats");
        const td_markups = table.querySelectorAll('td');
        table.classList.remove('hidden');

        const weapon_stats = new Map(Object.entries(stats));

        if (td_markups.length === weapon_stats.size) {
            const iterator = weapon_stats.keys();
            for (let i = 0; i < td_markups.length; i++) {
                const key = iterator.next().value;
                td_markups[i].innerHTML = `${key}: ${weapon_stats.get(key)}`;
            }
        } else {
            console.log("Erreur avec les statistiques de l'arme");
        }
    }

    // Code de test avec des statistiques fictives 
    const testStats = { A: "2", F: "4", PA: "0", D: "1", portée: "24\"" }; // attention portée en pouces
    updateWeaponStats(testStats);

    // Requête XHR pour obtenir les statistiques réelles de l'arme
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) { // Requête terminée
            if (xhr.status >= 200 && xhr.status < 300) { // Succès
                try {
                    const data = JSON.parse(xhr.responseText);
                    // Mise à jour des statistiques avec les données réelles
                    const realStats = { A: data.a, F: data.f, PA: data.pa, D: data.d, portée: data.portee + "\"" };
                    updateWeaponStats(realStats);
                } catch (error) {
                    console.error('Erreur lors du parsing de la réponse JSON', error);
                }
            } else {
                console.error('Erreur réponse requête XHR', xhr.statusText);
            }
        }
    };

    xhr.onerror = function () {
        console.error('Erreur réseau requête XHR');
    };

    // Envoi de la requête avec les données
    xhr.send(JSON.stringify({ action: "set_weapon", weapon: selected_weapon }));
}


function selectNumberOfWeapons(group_id){
	const weapons_nb = document.getElementById("weapon_number_range").value;
	
	// code de test
	const fig_group_img = document.getElementById(group_id).querySelectorAll("img");
	const fig_group_span = document.getElementById(group_id).querySelectorAll("span");
	
	for(let i = 0; i < weapons_nb; i++){
		fig_group_img[i].src = 'assets/android-fill.png';
		//fig_group_span[i].innerHTML = "1PV"; // garder les PV dans Battle?
	}
	for(let i = weapons_nb; i < fig_group_img.length; i++){
		fig_group_img[i].src = 'assets/android-line.png';
		//fig_group_span[i].innerHTML = "0PV";
	}
	return;

	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ action: "set_figs_group_hp", col: 1, unit_group: group_id, hp: weapons_nb.value }) // idem selectAliveFigsNumber, nb d'armes = nb figurines vivantes
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('erreur réponse requête AJAX ' + response.statusText);
			}
			return response.json();
		})
		// le serveur renvoie une vue et le nom d'une armée
		.then(data => {
			const fig_group_img = document.getElementById(group_id).querySelectorAll("img");
			const fig_group_span = document.getElementById(group_id).querySelectorAll("span");
			
			for(let i = 0; i < weapons_nb; i++){
				fig_group_img[i].src = 'assets/android-fill.png';
				//fig_group_span[i].innerHTML = "1PV"; // garder les PV dans Battle?
			}
			for(let i = weapons_nb; i < fig_group_img.length; i++){
				fig_group_img[i].src = 'assets/android-line.png';
				//fig_group_span[i].innerHTML = "0PV";
			}
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});
}

function checkAptitude(aptitude_id){
	const box = document.getElementById(aptitude_id);
	console.log(box.name); // normalement name="" = innerHTML
	console.log(box.checked);
}


/* boutons centraux */
function calculate(){
	// contrôle: une unité doit être sélectionnée de chaque côté
	if(Battle.getListId(1) == null || Battle.getListId(2) == null
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
	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ action: "reverse_armies" })
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('erreur réponse requête AJAX ' + response.statusText);
			}
			return response.json();
		})
		.then(data => {
			// obtenir les deux nouvelles vues
			selectList(1);
			selectList(2);
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});

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

function toastNotify(message) {
    var toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = 'toast show';
    setTimeout(function(){ toast.className = toast.className.replace('show', ''); }, 3000);
}