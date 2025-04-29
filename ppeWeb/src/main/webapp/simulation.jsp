<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    	<div class="sim_grid">
    		<div id="left_col">
    			<!-- choicebox liste d'armée -->
    			<div class="select_box">
	    			<select id="left_choice_box" name="left_choice_box" onchange="selectList(1);">
	    				<option value="" disabled selected>liste attaquante</option>

	    				<!-- options générées par le serveur à l'ouverture de la page -->
						<option value="Liste1">Liste1</option>
						<option value="Liste2">Liste2</option>
						<option value="Liste3">Liste3</option>
						<option value="Liste4">Liste4</option>
					</select>
					<div id="logo_list1" class="logo_armee_div hidden">
						<img class="logo_armee" src="" alt="logo_armee">
					</div>
				</div>

				<!-- zone des unités -->
				<section class="units_list" id="units_list1">
					<!-- boite à unités récupérée par selectList -->
				</section>
				
				<!-- zone des armes et aptitudes-->
				<aside id="weapons_aptitudes_box" class="hidden">
					<!-- boite récupérée par fetch -->
				</aside>
			</div>

    		<div id="central_col">
    			<button id="action_button" onclick="calculate();">Action !!</button>
    			<div id="warhammer_picture"><img src="assets/black_templar.webp" alt="illustration"></div>
    			<section id="histogramme" class="hidden">
    				Affichage de l'histogramme
    			</section>
    			<button id="reverse_armies" onclick="reverseArmies();"><img src="assets/inversion_icons.png" alt="inversion"></button>
    		</div>

    		<div id="right_col">
    			<div class="select_box">
	    			<select id="right_choice_box" name="right_choice_box" onchange="selectList(2);">
	    				<option value="" disabled selected>liste défenseuse</option>

	    				<!-- options générées par le serveur à l'ouverture de la page -->
						<option value="Liste1">Liste1</option>
						<option value="Liste2">Liste2</option>
						<option value="Liste3">Liste3</option>
					</select>
					<div id="logo_list2" class="logo_armee_div hidden">
						<img class="logo_armee" src="armees/android-fill.png" alt="logo_armee">
					</div>
				</div>
				
				<!-- unités de la liste -->
				<section class="units_list" id="units_list2">
					<!-- boite à unités récupérée par selectList -->
				</section>
    		</div>
    	</div>