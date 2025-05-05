class Battle{
	static #selected_list_id = [null, null];
	static #selected_unit_id = [null, null];

	static getListId(nb){
		if(nb == 1 || nb == 2){
			return this.#selected_list_id[nb - 1];
		}
		return "";
	}
	static setListId(nb, list_id){
		if(nb == 1 || nb == 2){
			this.#selected_list_id[nb - 1] = list_id;
		}
	}
	static getUnitId(nb){
		if(nb == 1 || nb == 2){
			return this.#selected_unit_id[nb - 1];
		}
		return "";
	}
	static setUnitId(nb, unit_id){
		if(nb == 1 || nb == 2){
			this.#selected_unit_id[nb - 1] = unit_id;
		}
	}
	static unsetUnitId(nb){
		if(nb == 1 || nb == 2){
			this.#selected_unit_id[nb - 1] = null;
		}
	}
}

// même chose compatible ES5
/*const Battle = (function () {
	const selected_list_id = [null, null];
	const selected_unit_id = [null, null];

	return {
		getListId(nb) {
			if(nb == 1 || nb == 2){
				return selected_list_id[nb - 1];
			}
			return "";
		},
		setListId(nb, list_id) {
			if(nb == 1 || nb == 2){
				selected_list_id[nb - 1] = list_id;
			}
		},
		getUnitId(nb) {
			if(nb == 1 || nb == 2){
				return selected_unit_id[nb - 1];
			}
			return "";
		},
		setUnitId(nb, unit_id) {
			if(nb == 1 || nb == 2){
				selected_unit_id[nb - 1] = unit_id;
			}
		}
		unsetUnitId(nb){
			if(nb == 1 || nb == 2){
				selected_unit_id[nb - 1] = null;
			}
		}
	};
})();*/