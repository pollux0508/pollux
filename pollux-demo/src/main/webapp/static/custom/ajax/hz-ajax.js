(function(root, factory) {

	"use strict";

	// CommonJS module is defined
	if(typeof module !== 'undefined' && module.exports) {
		module.exports = factory(require('jquery'));
	}
	// AMD module is defined
	else if(typeof define === "function" && define.amd) {
		define("HZAjax", ["jquery"], function($) {
			return factory($);
		});
	} else {
		// planted over the root!
		root.HZAjax = factory(root.jQuery);
	}

}(this, function($) {
	"use strict";
	var HZAjax = function() {}
	var loading = $('<div id="wait"><div class="idiv"></div><div class="spinner"><div class="rect1"></div><div class="rect2"></div><div class="rect3"></div><div class="rect4"></div></div>');
	/**
	 * 异步简单对象 异步beforeSend不会生效
	 */
	HZAjax.createAjaxAsyn = function(url, data, successCallBack, errorCallBack, completeCallBack) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({
			type: "post",
			data: data,
			url: url,
			cache:false,
			async: false,
			dataType: "json",
			beforeSend: function() {
				$('body').append(loading);
			},
			success: function(data) {
				if(successCallBack) {
					successCallBack(data);
				}
			},
			error: function(e) {
				if(errorCallBack) {
					errorCallBack(e);
				}
			},
			complete: function(data) {
				if(completeCallBack) {
					completeCallBack(data);
				}
				loading.remove();
			}
		});
	}
	/**
	 * 同步简单对象
	 */
	HZAjax.createAjaxSync = function(url, data, successCallBack, errorCallBack,completeCallBack) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({
			type: "post",
			data: data,
			url: url,
			async: true,
			cache:false,
			dataType: "json",
			beforeSend: function() {
				$('body').append(loading);
			},
			success: function(data) {
				if(successCallBack) {
					successCallBack(data);
				}
			},
			error: function(e) {
				if(errorCallBack) {
					errorCallBack(e);
				}
			},
			complete: function(data) {
				if(completeCallBack) {
					completeCallBack(data);
				}
				loading.remove();
			}
		});
	}

	/**
	 * 同步复杂对象
	 */
	HZAjax.createAjaxSyncComplex = function(url, data, successCallBack, errorCallBack,completeCallBack) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({
			type: "post",
			data: JSON.stringify(data),
			url: url,
			async: true,
			cache:false,
			dataType: "json",
			contentType:"application/json",
			beforeSend: function() {
				$('body').append(loading);
			},
			success: function(data) {
				if(successCallBack) {
					successCallBack(data);
				}
			},
			error: function(e) {
				if(errorCallBack) {
					errorCallBack(e);
				}
			},
			complete: function(data) {
				if(completeCallBack) {
					completeCallBack(data);
				}
				loading.remove();
			}
		});
	}
	return HZAjax;
}));