class Battle{
	static #selected_list_name = [null, null];
	static #id_selected_unit = [null, null];

	static getListName(nb){
		if(nb == 1 || nb == 2){
			return this.#selected_list_name[nb - 1];
		}
		return "";
	}
	static setListName(nb, unit_name){
		if(nb == 1 || nb == 2){
			this.#selected_list_name[nb - 1] = unit_name;
		}
	}
	static getUnitId(nb){
		if(nb == 1 || nb == 2){
			return this.#id_selected_unit[nb - 1];
		}
		return "";
	}
	static setUnitId(nb, unit_id){
		if(nb == 1 || nb == 2){
			this.#id_selected_unit[nb - 1] = unit_id;
		}
	}
	static unsetUnitId(nb){
		if(nb == 1 || nb == 2){
			this.#id_selected_unit[nb - 1] = null;
		}
	}
}

// même chose compatible ES5
/*const Battle = (function () {
	const selected_list_name = [null, null];
	const id_selected_unit = [null, null];

	return {
		getListName(nb) {
			if(nb == 1 || nb == 2){
				return selected_list_name[nb - 1];
			}
			return "";
		},
		setListName(nb, unit_name) {
			if(nb == 1 || nb == 2){
				selected_list_name[nb - 1] = unit_name;
			}
		},
		getUnitId(nb) {
			if(nb == 1 || nb == 2){
				return id_selected_unit[nb - 1];
			}
			return "";
		},
		setUnitId(nb, unit_id) {
			if(nb == 1 || nb == 2){
				id_selected_unit[nb - 1] = unit_id;
			}
		}
		unsetUnitId(nb){
			if(nb == 1 || nb == 2){
				id_selected_unit[nb - 1] = null;
			}
		}
	};
})();*/