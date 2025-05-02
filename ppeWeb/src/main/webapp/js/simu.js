
function GraphiqueSimu (){
	var xhr = new XMLHttpRequest();
	
	// Configurer la requête
	xhr.open("POST", "ControllerGraphique", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	// Définir la fonction de callback pour traiter la réponse
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        // Traiter la réponse JSON
	        //console.log(xhr.responseText);
			var response = xhr.responseText;
			//console.log(response);
			document.getElementById("warhammer_picture").innerHTML="";
			document.getElementById("graphique").innerHTML=response;
			//document.getElementById("graphique").insertAdjacentHTML('afterbegin',response);
	  
					execHisto();     
	    }
	};
	
	// Envoyer la requête avec les paramètres
	var params = 'param1=val&param2=value2';
	xhr.send(params);
	}

/**
 * 
 */