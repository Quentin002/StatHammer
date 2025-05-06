function graphiqueSimu(){
	// contrôle: une unité doit être sélectionnée de chaque côté
	if(Battle.getListId(1) == null || Battle.getListId(2) == null
		|| Battle.getUnitId(1) == null || Battle.getUnitId(2) == null)
	{
		toastNotify("conditions non remplies pour lancer une simulation");
		return;
	}

	const histogramme = document.getElementById("histogramme");
	
	// remplacer l'illustration par l'histogramme la première fois
	// en javascript, static ne fonctionne que dans une classe
	if(typeof graphiqueSimu.compteur === 'undefined') {
        graphiqueSimu.compteur = 0;
    }
	if(graphiqueSimu.compteur == 0){
		document.getElementById("warhammer_picture").classList.add('hidden');
		histogramme.classList.remove('hidden');
		graphiqueSimu.compteur++;
	}
		let data;
		var xhr2 = new XMLHttpRequest();
				xhr2.open("POST", "ControllerSimu", true);
				xhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				xhr2.onreadystatechange = function () {
				    if (xhr2.readyState === 4 && xhr2.status === 200) {
				        //console.log(xhr.responseText);
						var response = xhr2.responseText;
						//console.log(response);

						 data = JSON.parse(xhr2.responseText);
						console.log(data.degat_moyen);
						console.log(data.table_degat[0]);
						//console.log(data.table_degat[0]);
						//let a = data.degat_moyen;
						
						var xhr = new XMLHttpRequest();
						xhr.open("POST", "ControllerGraphique", true);
						xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						xhr.onreadystatechange = function () {
						    if (xhr.readyState === 4 && xhr.status === 200) {
						        //console.log(xhr.responseText);
								var response = xhr.responseText;
								//console.log(response);
								//document.getElementById("warhammer_picture").innerHTML="";
								document.getElementById("graphique").innerHTML=response;
								//document.getElementById("graphique").insertAdjacentHTML('afterbegin',response);
								//execHisto(data.degat_moyen,data.mort_moyen);
								execHisto(data.degat_moyen,data.mort_moyen,data.table_degat,data.table_mort);
								
						    }
						};
						// Envoyer la requête avec les paramètres
							
							xhr.send();
				    }
				};
		xhr2.send(JSON.stringify({ action:"bataille"}));
		
	
	
}

 //var nb = Number( document.getElementById('m'+0).innerHTML);
function execHisto(deg_moy,mort_moy,tabDegat,tabMort){
//var tabMort =[];
for(var i = 0 ; i<tabMort.length ; i++){
	//const markup = document.getElementById('m'+i);
	//var nb = Number(markup.innerHTML)/10000;
	//document.getElementById("chartContainer").innerHTML+="nb"; 
	tabMort[i] =  tabMort[i]/10000 ;
}

//var tabDegat =[];
for(var i = 0 ; i<tabDegat.length ; i++){
	//var nb = Number( document.getElementById('d'+i).innerHTML)/10000;
	//document.getElementById("chartContainer").innerHTML+="nb"; 
	//tabDegat[i] = nb;
	tabDegat[i] = tabDegat[i]/10000;
}
//var degM = Number(document.getElementById('degat_moy').innerHTML);
//var mortM = Number(document.getElementById('mort_moy').innerHTML);

var dps = [];   //dataPoints.
var dps2 = [];

var chart = new CanvasJS.Chart("chartContainer1", {
	
	animationEnabled: true,
	theme: "dark1", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Graphe nombre de figurines mortes"
	},
	axisY: {
		title: "Pourcentages",
		minimum : 0
	},
	axisX: {
		interval : 1
		},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "none",
		legendText: "Nbr de mort moyen : "+mort_moy,
		dataPoints : dps
	}]
});


var chart2 = new CanvasJS.Chart("chartContainer2", {
	
	animationEnabled: true,
	theme: "dark1", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Graphe nombre de dégats"
	},
	axisY: {
		title: "Pourcentages",
		minimum : 0
	},
	axisX: {
		interval : 1
		},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "none",
		legendText: "Nbr de degats moyen : "+deg_moy,
		dataPoints : dps2
	}]
});

 parseDataPoints(tabMort,dps);
 parseDataPoints(tabDegat,dps2);

chart.render();
chart2.render();
}

function parseDataPoints (tab,dps) {
    for (var i = 0; i < 50; i++){
		if(tab[i] != 0){
			dps.push({y: tab[i], label :i});
		}	
	}    
}






