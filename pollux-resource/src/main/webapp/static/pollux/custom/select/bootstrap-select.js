(function(root, factory) {

	"use strict";

	// CommonJS module is defined
	if(typeof module !== 'undefined' && module.exports) {
		module.exports = factory(require('jquery'), require('bootstrap'));
	}
	// AMD module is defined
	else if(typeof define === "function" && define.amd) {
		define("BootstrapSelect", ["jquery", "bootstrap", "bootstrap-select"], function($) {
			return factory($);
		});
	} else {
		// planted over the root!
		root.BootstrapSelect = factory(root.jQuery);
	}

}(this, function($) {

	"use strict";
	var BootstrapSelect = function() {}
	/**
	 * function: clear
	 *
	 * @param {type} selectId
	 */
	BootstrapSelect.clear = function(selectId) {
		var selectObj = document.getElementById("" + selectId);
		selectObj.options.length = 0;
		$("#" + selectId).selectpicker('refresh');
	};

	/**
	 * Warning window
	 * function: create
	 * @param {type} configure,choices
	 */
	BootstrapSelect.create = function(configure, choices) {
		var selectOptions = {};
		var defaultOptions = {
			selectId: null,
			fieldId: "id",
			fieldValue: "name",
			selectedIndex: null
		};
		if(typeof configure === 'object' && configure.constructor === {}.constructor) {
			selectOptions = $.extend(true, defaultOptions, configure);
		} else {
			selectOptions = $.extend(true, defaultOptions, {
				selectId: configure
			});
		}

		var selectObj = document.getElementById("" + selectOptions.selectId);
		selectObj.options.length = 0;
		var size = choices.length;
		if(selectOptions.selectedIndex && selectOptions.selectedIndex >= size) {
			selectOptions.selectedIndex = size - 1;
		}
		if(choices instanceof Array) {
			for(var i = 0; i < size; i++) {
				var choice = choices[i];
				if(!(choice instanceof Array)) {
					selectObj.options.add(new Option(choice["" + selectOptions.fieldValue], choice["" + selectOptions.fieldId]));
				}
			}
		} else {
			selectObj.options.add(new Option(choices["" + selectOptions.fieldValue], choices["" + selectOptions.fieldId]));
		}
		if(selectOptions.selectedIndex) {
			selectObj.options[selectOptions.selectedIndex].selected = true;
		}
		$("#" + selectOptions.selectId).selectpicker('refresh');
	};

	BootstrapSelect.createHasDefault = function(configure, choices) {
		var selectOptions = {};
		var defaultOptions = {
			selectId: null,
			fieldId: "id",
			fieldValue: "name",
			selectedIndex: null
		};
		if(typeof configure === 'object' && configure.constructor === {}.constructor) {
			selectOptions = $.extend(true, defaultOptions, configure);
		} else {
			selectOptions = $.extend(true, defaultOptions, {
				selectId: configure
			});
		}

		var selectObj = document.getElementById("" + selectOptions.selectId);
		selectObj.options.length = 0;

		var size = choices.length;
		if(selectOptions.selectedIndex && selectOptions.selectedIndex >= size) {
			selectOptions.selectedIndex = size - 1;
		}
		selectObj.options.add(new Option("请选择","-1"));
		if(choices instanceof Array) {
			for(var i = 0; i < size; i++) {
				var choice = choices[i];
				if(!(choice instanceof Array)) {
					selectObj.options.add(new Option(choice["" + selectOptions.fieldValue], choice["" + selectOptions.fieldId]));
				}
			}
		} else {
			selectObj.options.add(new Option(choices["" + selectOptions.fieldValue], choices["" + selectOptions.fieldId]));
		}
		if(selectOptions.selectedIndex) {
			selectObj.options[selectOptions.selectedIndex].selected = true;
		}
		$("#" + selectOptions.selectId).selectpicker('refresh');
	};

	return BootstrapSelect;

}));