(function(root, factory) {

	"use strict";

	// CommonJS module is defined
	if(typeof module !== 'undefined' && module.exports) {
		module.exports = factory(require('jquery'), require('bootstrap'));
	}
	// AMD module is defined
	else if(typeof define === "function" && define.amd) {
		define("BootstrapDatetimepicker", ["jquery", "bootstrap", "bootstrap-datetimepicker"], function($) {
			return factory($);
		});
	} else {
		// planted over the root!
		root.BootstrapDatetimepicker = factory(root.jQuery);
	}

}(this, function($) {

	"use strict";
	var BootstrapDatetimepicker = function() {}

	BootstrapDatetimepicker.createYear = function(prev, after) {
		var prevOptions = {
			format: 'yyyy'
		};
		var afterOptions = {
			format: 'yyyy'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'decade',
			minView: 'decade',
			maxView: 'decade',
			forceParse: 1
		}

		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", "yyyy");
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeYear', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", "yyyy");
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeYear', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}

	BootstrapDatetimepicker.createMonth = function(prev, after) {
		var prevOptions = {
			format: 'yyyy-mm'
		};
		var afterOptions = {
			format: 'yyyy-mm'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'year',
			minView: 'year',
			maxView: 'decade',
			forceParse: 1
		}

		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeMonth', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeMonth', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}

	BootstrapDatetimepicker.createDay = function(prev, after) {
		var prevOptions = {
			format: 'yyyy-mm-dd'
		};
		var afterOptions = {
			format: 'yyyy-mm-dd'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'month',
			minView: 'month',
			maxView: 'decade',
			forceParse: 1
		}

		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}
	BootstrapDatetimepicker.createHour = function(prev, after) {
		var prevOptions = {
			format: 'yyyy-mm-dd hh'
		};
		var afterOptions = {
			format: 'yyyy-mm-dd hh'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'month',
			minView: 'day',
			maxView: 'decade',
			forceParse: 1
		}

		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions);
		$("#" + prevOptions.id).on('changeDate', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}
	BootstrapDatetimepicker.createMinute = function(prev, after) {
		var prevOptions = {
			format: 'yyyy-mm-dd hh:ii'
		};
		var afterOptions = {
			format: 'yyyy-mm-dd hh:ii'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'month',
			minView: 'hour',
			maxView: 'decade',
			forceParse: 1,
			minuteStep: 1
		}
		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}

	BootstrapDatetimepicker.createSecond = function(prev, after) {
		var prevOptions = {
			format: 'yyyy-mm-dd hh:ii:00'
		};
		var afterOptions = {
			format: 'yyyy-mm-dd hh:ii:00'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 'month',
			minView: 'hour',
			maxView: 'decade',
			forceParse: 1,
			minuteStep: 1
		}
		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}

	BootstrapDatetimepicker.createTime = function(prev, after) {
		var prevOptions = {
			format: 'hh:ii:00'
		};
		var afterOptions = {
			format: 'hh:ii:00'
		};
		var defaultOptions = {
			language: 'zh-CN',
			weekStart: 0,
			autoclose: 1,
			startView: 'hour',
			minView: 'hour',
			maxView: 'hour',
			forceParse: 1,
			minuteStep: 1
		}
		if(typeof prev === 'object' && prev.constructor === {}.constructor) {
			prevOptions = $.extend(true, prevOptions, defaultOptions, prev);
		} else {
			prevOptions = $.extend(true, prevOptions, defaultOptions, {
				id: prev
			});
		}
		if(after && typeof after === 'object' && after.constructor === {}.constructor) {
			afterOptions = $.extend(true, afterOptions, defaultOptions, after);
		} else {
			afterOptions = $.extend(true, afterOptions, defaultOptions, {
				id: after
			});
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				$("#" + afterOptions.id).datetimepicker("setStartDate", ev.date);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				$("#" + prevOptions.id).datetimepicker("setEndDate", ev.date);
			});
		}
	}

	return BootstrapDatetimepicker;
}));