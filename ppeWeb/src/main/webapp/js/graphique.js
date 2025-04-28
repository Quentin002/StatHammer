
 //var nb = Number( document.getElementById('m'+0).innerHTML);
function execHisto(){
var tabMort =[];
for(var i = 0 ; i<50 ; i++){
	const markup = document.getElementById('m'+i);
	var nb = Number(markup.innerHTML)/10000;
	//document.getElementById("chartContainer").innerHTML+="nb"; 
	tabMort[i] = nb;
}

var tabDegat =[];
for(var i = 0 ; i<50 ; i++){
	var nb = Number( document.getElementById('d'+i).innerHTML)/10000;
	//document.getElementById("chartContainer").innerHTML+="nb"; 
	tabDegat[i] = nb;
}
var degM = Number(document.getElementById('degat_moy').innerHTML);
var mortM = Number(document.getElementById('mort_moy').innerHTML);

var dps = [];   //dataPoints.
var dps2 = [];

var chart = new CanvasJS.Chart("chartContainer1", {
	
	animationEnabled: true,
	theme: "dark1", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Graphe nombre de figurines mortes"
	},
	axisY: {
		title: "Pourcentages"
	},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "none",
		legendText: "Nbr de mort moyen : "+mortM,
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
		title: "Pourcentages"
	},
	data: [{        
		type: "column",  
		showInLegend: true, 
		legendMarkerColor: "none",
		legendText: "Nbr de degats moyen : "+degM,
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
     };

   		

//}

/**
 */