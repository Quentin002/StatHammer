/*class BattleData{
	static #selected_list = [null, null];
	static #selected_unit = [null, null];

	static getList(nb){
		if(nb == 1 || nb == 2){
			return this.#selected_list[nb - 1];
		}
		return "";
	}
	static setList(nb, unit_name){
		if(nb == 1 || nb == 2){
			this.#selected_list[nb - 1] = unit_name;
		}
	}
	static getUnit(nb){
		if(nb == 1 || nb == 2){
			return this.#selected_unit[nb - 1];
		}
		return "";
	}
	static setUnit(nb, unit_name){
		if(nb == 1 || nb == 2){
			this.#selected_unit[nb - 1] = unit_name;
		}
	}
}*/

// même chose compatible ES5
var BattleData = (function () {
	const selectedList = [null, null];
	const selectedUnit = [null, null];

	return {
		getList(nb) {
			if(nb == 1 || nb == 2){
				return selectedList[nb - 1];
			}
			return "";
		},
		setList(nb, unitName) {
			if(nb == 1 || nb == 2){
				selectedList[nb - 1] = unitName;
			}
		},
		getUnit(nb) {
			if(nb == 1 || nb == 2){
				return selectedUnit[nb - 1];
			}
			return "";
		},
		setUnit(nb, unitName) {
			if(nb == 1 || nb == 2){
				selectedUnit[nb - 1] = unitName;
			}
		}
	};
})();