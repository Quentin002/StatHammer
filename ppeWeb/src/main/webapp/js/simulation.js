// note: blocage possible par le mode "https only" de firefox
const url = "ControllerSimu";

function selectList(nb){
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
	
	const units_list = document.getElementById("units_list" + nb);
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
		console.log("erreur, pas de liste sélectionnée");
	}
}

function unfoldUnits(col, unit){
	/* déroulage des unités et cadre jaune du bouton, même algo que dans le client lourd */
	const old_unit_box = document.getElementById("col" + col + "_unit" + Battle.getUnitId(col)); // peut être null
	const new_unit_box = document.getElementById("col" + col + "_unit" + unit); // peut être le même élément
	
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
			    	if(Battle.getUnitId(col) == unit)
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
			        	Battle.setUnitId(col, unit);
			        	new_unit_box.querySelector(".unit_button").classList.add("unit_selected"); // bordure unité sélectionnée
			        	new_unit_box.querySelector(".unit_box").classList.remove("hidden"); // on ouvre
			    	}
			    }
			    // pas d'unité sélectionnée => on ouvre
			    else
			    {
			    	Battle.setUnitId(col, unit);
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
	if(Battle.getUnitId(col) != null && Battle.getUnitId(col) == unit){
		xhr.send(JSON.stringify({ action: "set_selected_unit", col: col, unit: -1 }));
	}
	else{
		xhr.send(JSON.stringify({ action: "set_selected_unit", col: col, unit: unit }));
	}
}

function openWeaponsAptitudesZone(group_id){
	const weapons_aptitudes_box = document.getElementById("weapons_aptitudes_box");
	
	var xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4 && xhr.status === 200) {
				// html généré
				weapons_aptitudes_box.classList.remove("hidden");
				weapons_aptitudes_box.innerHTML = '';
				weapons_aptitudes_box.insertAdjacentHTML('afterbegin', xhr.responseText);
				
				// envoi d'une deuxième requête
				selectWeapon(group_id);
			}
		};
		xhr.send(JSON.stringify({ action: "make_weapons_aptitudes_zone", col: 1, group: group_id}));
}


/* commande colonne de droite */
function selectAliveFigsNumber(group_id){
	const parent_div_id = "unit" + Battle.getUnitId(2) + "_" + group_id;
	const input = document.getElementById(parent_div_id + "_input");
	//const group_id_array = group_id.split("_"); // ["unitX", "groupY"]
	//const fig_name = group_id_array[1];
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			console.log(xhr.responseText);
			if(xhr.responseText != null){
				const hp_inputs = document.getElementById(parent_div_id + "_figs").querySelectorAll(".hp_selector");
				const fig_icons = document.getElementById(parent_div_id + "_figs").querySelectorAll(".fig_icon");
				for(let i = 0; i < input.value; i++)
				{
					hp_inputs[i].value = hp_inputs[i].max;
					fig_icons[i].src = 'assets/android-fill.png';
				}
				for(let i = input.value; i < hp_inputs.length; i++)
				{
					hp_inputs[i].value = 0; // remplacer 2 par la bonne valeur (figurine.getHpMax() en java)
					fig_icons[i].src = 'assets/android-line.png';
				}
			}
			else{
				console.log("erreur MAJ points de vie du groupe sur le serveur");
			}
		}
	};
	xhr.send(JSON.stringify({ action: "set_figs_group_hp", col: 2, group: group_id, alive_figs: input.value}));
}

function setFigurineHP(group_id, fig_id){
	const fig_div = document.getElementById("unit" + Battle.getUnitId(2) + "_" + group_id + "_" +  fig_id);
	
	var xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4 && xhr.status === 200) {
				if(xhr.responseText.trim() == "success"){
					// icône
					if(fig_div.querySelector("input").value > 0){
						fig_div.querySelector("img").src = 'assets/android-fill.png';
					}
					else{
						fig_div.querySelector("img").src = 'assets/android-line.png';
					}
					// MAJ input range
					//const group_id = fig_id.split('_')[0]; // garder groupY et retirer _figZ
					const group_div = document.getElementById("col2_unit" + Battle.getUnitId(2) + "_" + group_id); // parent du groupe d'unités
					const hp_inputs = group_div.querySelector(".fig_group")
						.querySelectorAll(".hp_selector");
					let alive_figs_number = 0;
					for(let i = 0; i < hp_inputs.length; i++){
						if(hp_inputs[i].value > 0){
							alive_figs_number++;
						}
					}
				    //document.getElementById(group_id + "_input").value = alive_figs_number;
					group_div.querySelector("input").value = alive_figs_number;
				    group_div.querySelector("output").innerHTML = alive_figs_number;
				}
				else{
					console.log("erreur MAJ points de vie de la figurine sur le serveur ");
				}
			}
		};
		xhr.send(JSON.stringify({ action: "set_one_fig_hp", col: 2, fig_div_id: group_id + "_" + fig_id, hp: fig_div.querySelector("input").value }));
}


/* zone armes et aptitudes */
function selectWeapon(group_id){
	const selected_weapon = document.getElementById("weapon_select").value;
	const table = document.getElementById("weapon_stats");
	const td_markups = table.querySelectorAll('td');
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			const stats = JSON.parse(xhr.responseText);
			//stats["portée"] = stats["portée"] + "\""; // portée en pouces
			
			if(td_markups.length === Object.keys(stats).length) // = 5
			{
				// affichage des clés et valeurs
				const keys = Object.keys(stats);
				for(let i = 0; i < td_markups.length; i++) {
					td_markups[i].innerHTML = keys[i] + ": " + stats[keys[i]];
				}
			}
			else{
				console.log("erreur avec les statistiques de l'arme");
			}
		}
	};
	xhr.send(JSON.stringify({ action: "set_weapon", col: 1, group: group_id, weapon: selected_weapon }));
}

function selectNumberOfWeapons(group_id){
	const weapons_nb = document.getElementById("weapon_number_range").value;
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			const fig_group_img = document.getElementById("col1_unit" + Battle.getUnitId(1) + "_" + group_id).querySelectorAll("img");
			const fig_group_span = document.getElementById("col1_unit" + Battle.getUnitId(1) + "_" + group_id).querySelectorAll("span");
			
			for(let i = 0; i < weapons_nb; i++){
				fig_group_img[i].src = 'assets/android-fill.png';
				fig_group_span[i].innerHTML = xhr.responseText + "PV";
			}
			for(let i = weapons_nb; i < fig_group_img.length; i++){
				fig_group_img[i].src = 'assets/android-line.png';
				fig_group_span[i].innerHTML = "0PV";
			}
		}
	};
	// on envoie "set_figs_group_hp" comme dans selectAliveFigsNumber()
	xhr.send(JSON.stringify({ action: "set_figs_group_hp", col: 1, group: group_id, alive_figs: weapons_nb }));
}

function checkAptitude(aptitude_id){
	const box = document.getElementById(aptitude_id);
	console.log(box.name); // normalement name="" = innerHTML
	console.log(box.checked);
}


/* boutons centraux */
// => graphiqueSimu est dans simu.js

function reverseArmies(){
	// contrôle: une liste doit être sélectionnée de chaque côté
	if(Battle.getListId(1) == null || Battle.getListId(2) == null)
	{
		toastNotify("sélectionner 2 listes pour pouvoir les inverser");
		return;
	}

	// dire au serveur d'inverser les deux joueurs
	var xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4 && xhr.status === 200) {
				if(xhr.responseText.trim() == "success"){
					// nettoyage
					Battle.setUnitId(1, null);
					Battle.setUnitId(2, null);
					const weapons_aptitudes_box = document.getElementById("weapons_aptitudes_box");
					weapons_aptitudes_box.classList.add("hidden");
					weapons_aptitudes_box.innerHTML = '';
					
					// modifier le choix du select
					const select1 = document.getElementById("left_choice_box");
					const select2 = document.getElementById("right_choice_box");
					select1.value = Battle.getListId(2);
					select2.value = Battle.getListId(1);
					
					// obtenir les deux nouvelles vues avec deux nouvelles requêtes AJAX
					selectList(1);
					selectList(2);
				}
				else{
					console.log("erreur à l'inversion attaquant/défenseur sur le serveur");
				}
			}
		};
		xhr.send(JSON.stringify({ action: "reverse_armies" }));
}

function toastNotify(message) {
    var toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = 'toast show';
    setTimeout(function(){ toast.className = toast.className.replace('show', ''); }, 3000);
}