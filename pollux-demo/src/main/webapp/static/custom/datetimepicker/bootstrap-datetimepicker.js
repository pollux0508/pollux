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
	var other = $('<input class="form-control" type="text"><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>');

	BootstrapDatetimepicker.init =function(id){
		var tmp=other.clone();
		$('#'+id).append(tmp);
	}
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", "yyyy");
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeYear', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setFullYear(startDate.getFullYear()+1);
				startDate.setMonth(0);
				startDate.setDate(1);
				startDate.setHours(0);
				startDate.setMinutes(0);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", "yyyy");
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeYear', function(ev) {
				var endDate = ev.date;
				endDate.setFullYear(endDate.getFullYear()-1);
				endDate.setMonth(0);
				endDate.setDate(1);
				endDate.setHours(0);
				endDate.setMinutes(0);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeMonth', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setMonth(startDate.getMonth()+1);
				startDate.setDate(1);
				startDate.setHours(0);
				startDate.setMinutes(0);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeMonth', function(ev) {
				var endDate = ev.date;
				endDate.setMonth(endDate.getMonth()-1);
				endDate.setDate(1);
				endDate.setHours(0);
				endDate.setMinutes(0);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setDate(startDate.getDate()+1);
				startDate.setHours(0);
				startDate.setMinutes(0);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				var endDate = ev.date;
				endDate.setDate(endDate.getDate()-1);
				endDate.setHours(0);
				endDate.setMinutes(0);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions);
		$("#" + prevOptions.id).on('changeDate', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setHours(startDate.getHours()+1);
				startDate.setMinutes(0);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				var endDate = ev.date;
				endDate.setHours(endDate.getHours()-1);
				endDate.setMinutes(0);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setMinutes(startDate.getMinutes()+1);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				var endDate = ev.date;
				endDate.setMinutes(endDate.getMinutes()-1);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
			todayBtn: 0,
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
		this.init(prevOptions.id);
		if(after){
			this.init(afterOptions.id);
		}
		$("#" + prevOptions.id).attr("data-date-format", prevOptions.format);
		$("#" + prevOptions.id).datetimepicker(prevOptions).on('changeDate', function(ev) {
			if(after) {
				var startDate = ev.date;
				startDate.setMinutes(startDate.getMinutes()+1);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				var endDate = ev.date;
				endDate.setMinutes(endDate.getMinutes()-1);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
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
				var startDate = ev.date;
				startDate.setMinutes(startDate.getMinutes()+1);
				startDate.setSeconds(0);
				startDate.setMilliseconds(0);
				$("#" + afterOptions.id).datetimepicker("setStartDate", startDate);
			}
		});

		if(after) {
			$("#" + afterOptions.id).attr("data-date-format", afterOptions.format);
			$("#" + afterOptions.id).datetimepicker(afterOptions).on('changeDate', function(ev) {
				var endDate = ev.date;
				endDate.setMinutes(endDate.getMinutes()-1);
				endDate.setSeconds(0);
				endDate.setMilliseconds(0);
				$("#" + prevOptions.id).datetimepicker("setEndDate", endDate);
			});
		}
	}

	return BootstrapDatetimepicker;
}));