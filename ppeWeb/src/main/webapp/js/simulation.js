var selected_list1;
var selected_list2;
var selected_unit1;
var selected_unit2;

function selectList(nb){
	var units_box = document.getElementById("units_list" + nb);
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
	var selected_list = document.getElementById(select_name).value;


	// code de test à supprimer quand il y aura un serveur en face
	data = JSON.stringify({ html: `<div class="units_box" id="unit_box1">
	<button class="unit_button" onclick="unfoldUnits(1, 'col1_unit1');">
		Unité 1</button>
	<div id="unit_group1">groupe d'unités 1</div>
	<div id="unit_group1">groupe d'unités 2</div>
</div>`, army_file: 'android-fill.png' });
	units_box.innerHTML = '';
	units_box.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
	const logo_box = document.getElementById("logo_list" + nb);
	logo_box.querySelector("img").src = "armees/" + JSON.parse(data).army_file;
	logo_box.classList.remove('hidden');
	return;

	
	fetch("http://webhammer/ControllerSimu", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ list: selected_list })
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
			units_box.innerHTML = '';
			units_box.insertAdjacentHTML('afterbegin', JSON.parse(data).html);
			
			// logo
			const logo_box = document.getElementById("logo_list" + nb);
			logo_box.querySelector("img").src = "armees/" + JSON.parse(data).army_file;
			logo_box.classList.remove('hidden');
		})
		.catch(error => {
			console.error('erreur capturée requête AJAX', error);
		});
}

function unfoldUnits(col, unit_box_id) {
	// body...
}

function calculate(){
	// contrôle: une unité doit être sélectionnée de chaque côté
	if(selected_list1 == null || selected_list2 == null
		|| selected_unit1 == null || selected_unit2 == null)
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