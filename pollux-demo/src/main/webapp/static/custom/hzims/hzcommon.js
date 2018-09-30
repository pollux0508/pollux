/*
 * 通用JS方法
 * 为便于查看，将方法的定义写在上方，实现写在下方。
 */
(function() {
	var hzcommon = hzcommon || {};
	window.hzcommon = hzcommon;
	/*	
	 * 用途：将时间戳转为时间字符串,带毫秒
	 * 说明：
	 * 返回：格式如2012-02-20 13:30:30.123
	 * 引用：
	 * 作者：hx
	*/
	hzcommon.timeStampToStringMil = function(ns){};
	/*	
	 * 用途：将时间戳转为时间字符串,
	 * 说明：
	 * 返回：格式如2012-02-20 13:30:30 
	 * 引用：
	 * 作者：hx
	*/
	hzcommon.timeStampToString = function(ns){};
	/*	
	 * 用途：显示浮动警告窗
	 * 说明：type可以为info或danger
	 * 返回： 
	 * 引用：
	 * bootstrap-notify/animate.css
	 * bootstrap-notify/bootstrap-notify.min.js
	 * 作者：hx
	*/
	hzcommon.showMsg = function(type, data){};
	/*	
	 * 用途：获取全局的key值
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hx
	*/
	hzcommon.getKey = function(key){};
	/*	
	 * 用途：设置全局的key值
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hx
	*/
	hzcommon.setKey = function(key, value) {
	};
	/*
	 * 用途：cookie的设置
	 * 说明：
	 * 返回：
	 * 引用：
	 * 作者：ypj
	 * */
	hzcommon.setCookie = function(name,value,days){};
	/*
	 * 用途：cookie的删除
	 * 说明：
	 * 返回：
	 * 引用：
	 * 作者：ypj
	 * */
	hzcommon.delCookie = function(name){};
	/*
	 * 用途：cookie的读取 
	 * 说明：
	 * 返回：
	 * 引用：
	 * 作者：ypj
	 * */
	hzcommon.getCookie = function(name){};
	/*	
	 * 用途：dataTables设置
	 * 说明：
	 * 返回： obj 指定的table对象，data指定的数据源，columns定义字段，defs自定义列
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.dataTable = function(obj,data,columns,defs){};
	/*	
	 * 用途：获取url中的参数
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.getSingleRequest=function(strName){};
	/*	
	 * 用途：获取datetimepicker
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.timeInt= function(format,start,min){};
	/*	
	 * 用途：将form表单原色的值序列化成对象
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.serializeObject= function(form){};

	//数字格式化 个位数前加0
	hzcommon.Appendzero = function(obj){};
	/*	
	 * 用途：返回上一页（返回、取消操作）
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.returnBack = function(obj){};
	/*	
	 * 用途：首页模块项
	 * 说明：title模块标题；content模块内容
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.homeBlock = function(title,content){};
	/*	
	 * 用途：实施消息提醒
	 * 说明：title消息框标题；msg 提醒内容
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.newMsg = function(title,msg){};
	/*============================实现===============================*/
	hzcommon.showMsg = function(type, data) {
		$.notify({
			// options
			icon: 'glyphicon glyphicon-info-sign',
			message: data 
		},{
			// settings
			type: type,
			placement: {
				from: "top",
				align: "center"
			},
			allow_dismiss: false,
			delay: 2000,
			timer:500,
			z_index:1050,
			animate: {
				enter: 'animated fadeInUp',
				exit: 'animated fadeOutDown'
			}
		});
	};
	function numToFullNum(num){
		var numstr = "";
		if (num < 10)
			numstr = "0" + num;
		else
			numstr = num;
		return numstr;
	}
	function numToFullNum2(num){
		var numstr = "";
		if (num < 10)
			numstr = "00" + num;
		else if (num < 100)
			numstr = "0" + num;
		else
			numstr = num;
		return numstr;
	}
	hzcommon.timeStampToStringMil = function(ns) {
		var myDate = new Date(parseInt(ns));
		myDate.getDate();       //获取当前日(1-31)
		myDate.getDay();        //获取当前星期X(0-6,0代表星期天)
		myDate.getTime();       //获取当前时间(从1970.1.1开始的毫秒数)
		myDate.getHours();      //获取当前小时数(0-23)
		myDate.getMinutes();    //获取当前分钟数(0-59)
		myDate.getSeconds();    //获取当前秒数(0-59)
		myDate.getMilliseconds();   //获取当前毫秒数(0-999)
		return myDate.getFullYear() + "-" +
			numToFullNum(myDate.getMonth() + 1) + "-" + 
			numToFullNum(myDate.getDate()) + " " + 
			numToFullNum(myDate.getHours()) + ":" + 
			numToFullNum(myDate.getMinutes()) + ":" + 
			numToFullNum(myDate.getSeconds()) + "." + 
			numToFullNum2(myDate.getMilliseconds());
	};
	hzcommon.timeStampToString_bef = function(ns) {
		var myDate = new Date(parseInt(ns));
		myDate.getDate();       //获取当前日(1-31)
		myDate.getDay();        //获取当前星期X(0-6,0代表星期天)
		myDate.getTime();       //获取当前时间(从1970.1.1开始的毫秒数)
		myDate.getHours();      //获取当前小时数(0-23)
		myDate.getMinutes();    //获取当前分钟数(0-59)
		myDate.getSeconds();    //获取当前秒数(0-59)
		myDate.getMilliseconds();   //获取当前毫秒数(0-999)
		return myDate.getFullYear() + "-" +
			numToFullNum(myDate.getMonth() + 1) + "-" + 
			numToFullNum(myDate.getDate()) + " " + 
			numToFullNum(myDate.getHours()) + ":" + 
			numToFullNum(myDate.getMinutes()) + ":" + 
			numToFullNum(myDate.getSeconds());
		return ns;
	};

	/*
	*由于日期格式开始后台传的是毫秒数用上方的timeStampToString_bef(开始是timeStampToString)转日期格式正常，
	*但后来后台改成了date 日期格式，导致用上方的timeStampToString方法转换之后日期会异常，
	*调用的页面太多，故直接将此共用方法直接return传过来的日期值，涉及的页面就不一一做处理了
	*/
	hzcommon.timeStampToString= function(ns){
		return ns;
	};
	hzcommon.getKey = function(key){
		return top.parameter.get(key);
	};
	/*	
	 * 用途：设置全局的key值
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hx
	*/
	hzcommon.setKey = function(key, value) {
		top.parameter.put(key, value);
	};
	hzcommon.setCookie = function(name,value,days){		
		var exp = new Date();
		exp.setDate(exp.getDate()+days);
		document.cookie = name + "=" + escape(value) + ((days==null)?"":";expires="+exp.toGMTString());		
	};
	hzcommon.delCookie = function(name){
		var exp = new Date();  
   	    exp.setTime(exp.getTime() - 1);  
   	    var cval=getCookie(name);  
   	    if(cval!=null)  
   	        document.cookie= name + "="+cval+";expires="+exp.toGMTString();  
	};
	hzcommon.getCookie = function(name){
		if (document.cookie.length>0){
			var c_start=document.cookie.indexOf(name + "=");
			if (c_start!=-1){ 
				c_start=c_start + name.length+1;
				var c_end=document.cookie.indexOf(";",c_start);
				if (c_end==-1) 
  				c_end=document.cookie.length;
				return unescape(document.cookie.substring(c_start,c_end));
			}
		}
		return "";
	};
	/*	
	 * 用途：dataTables设置
	 * 说明：
	 * 返回： obj 指定的table对象，data指定的数据源，columns定义字段，defs自定义列
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.dataTable=function(obj,data,columns,defs){
		$(obj).DataTable({		
			"oLanguage": {
	            	"sProcessing":   "处理中...",
	 				"sLoadingRecords": "载入中...",
			    	"sLengthMenu": "每页显示 _MENU_ 条记录",
			    	"sZeroRecords": "抱歉， 没有找到",
			    	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			    	"sInfoEmpty": "没有数据",
			    	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
			    	"oPaginate": {
				    	"sFirst": "首页",
				    	"sEmptyTable":     "表中数据为空",
				    	"sPrevious": "前一页",
				    	"sNext": "后一页",
				    	"sLast": "尾页"
	    			},
	    			"sSearch": "查找",
	    			"sZeroRecords": "没有检索到数据"
	    	},
	    	"bLengthChange":true,  //是否可切换分页条数
	    	"data":data,
	    	destroy:true, //解决Cannot reinitialise DataTable 重新加载表格内容问题
			"columns": columns,  
			"columnDefs" : defs
		});
	};
	/*	
	 * 用途：获取url中的参数
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.getSingleRequest=function(strName){
		var strHref = window.document.location.href;
	    var intPos = strHref.indexOf("?");
	    if (intPos == -1)
	        return "";
	    var strRight = strHref.substr(intPos + 1);
	    var arrTmp = strRight.split("&");
	    for (var i = 0; i < arrTmp.length; i++) {
	        var arrTemp = arrTmp[i].split("=");
	        if (arrTemp[0].toUpperCase() == strName.toUpperCase()) return arrTemp[1];
	    }
	    return "";
	};
	/*	
	 * 用途：获取datetimepicker
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.timeInt= function(format,start,min){
		//日期
	    $('.form_datetime').datetimepicker({ 
	        language: 'zh-CN',
	        format:format,
	        weekStart: 0,
	        todayBtn:  1,
	        autoclose: 1,
	        todayHighlight: 1,
	        startView: start,
	        minView: min,
	        forceParse: 1
	    }); 
	};
	/*	
	 * 用途：将form表单原色的值序列化成对象
	 * 说明：
	 * 返回： 
	 * 引用：
	 * 作者：hex
	*/
	hzcommon.serializeObject= function(form){
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			}
		});
		return o;
	};
	//数字格式化 个位数前加0
	hzcommon.Appendzero = function(obj){  
        if(obj<10){
        	return "0" +""+ obj; 
        }else{
        	return obj;  
        }
	};
	//返回上一页（返回、取消操作）
	hzcommon.returnBack = function(obj){
		$(obj).click(function(event){
			window.history.back(-1);
	    });
	};
	//首页模块内容
	hzcommon.homeBlock = function(title,content){
		var html = '<div class="col-md-6 col-sm-12 col-xs-12">\
						<div class="panel panel-default">\
							<div class="panel-heading"><span class="title">'+title+'</span></div>\
							<div class="panel-body">'+content+'</div>\
						</div>\
					</div>';
		$('.page-inner').append(html);
	};
	//实时新消息提醒
	hzcommon.newMsg = function(title,msg){
		var html = '<div class="float-msg">\
						<div class="msg-header">'+title+'<span class="glyphicon glyphicon-remove close"></span></div>\
						<div class="msg-content">'+msg+'</div>\
					</div>';
		$('body').append(html);
		$('.float-msg').on('click','.close',function(){
			$('.float-msg').animate({'opacity':0,'width':0,'height':0},300);
		});
		//5秒后窗口自动消失
		var closePicker = setTimeout('autoClose()',5000);
		$('.float-msg').hover(function(){
			clearTimeout(closePicker);
		},function(){
			closePicker = setTimeout('autoClose()',5000);
		});	
	};
	/**
	 * 用途： modal模态窗口 
	 * 说明： title 窗口标题；content窗口内容；width:窗口宽度px
	 * 返回： 引用： 作者：hx
	 */
	hzcommon.openModal = function(title,content,width,okFunc){
		var html = '<div class="modal fade in bs-example-modal-lg modal-ind-cls" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:10086;">\
					   <div class="modal-backdrop fade in"></div>\
		        	   <div class="modal-dialog modal-lg" style="width:'+width+'">\
		        		   <div class="modal-content">\
			            	   <div class="modal-header">\
			                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
			                   <div class="top-name"><p class="title-two">'+title+'</p></div>\
	            		   </div>\
           				   <div class="modal-body">'+content+'</div>\
						   <div class="modal-footer">\
						   		<button type="button" id="btnOK" class="btn btn-primary"><span class=" glyphicon  glyphicon-ok"></span>&nbsp;&nbsp;确定</button>&nbsp;&nbsp;\
								<button type="button" id="btnCancel" class="btn btn-primary" data-dismiss="modal"><span class=" glyphicon  glyphicon-remove"></span>&nbsp;&nbsp;取消</button>\
						   </div>\
           				</div>\
           			</div>';
        $('body').append(html);
		$('#myModal').modal({
			keyboard:false
		});
		$('#myModal').on('click','button#btnCancel,button.close',function(){
			//关闭窗口并移除
			$('#myModal').remove();
			$('body').removeClass('modal-open').css({'padding-right':0});
		});
		$('#myModal').on('click','button#btnOK',function(){
            if(typeof(okFunc) == "function"){
                okFunc();
            }
        });
	};
	
})();

//新消息提醒浮动框
var	offsetBtm = -300;
function scroll(){			
	if(offsetBtm<0){
		offsetBtm +=10;
		$('.float-msg').css({'bottom':offsetBtm+'px','opacity':1,'width':'350px','height':'250px'});
		setTimeout('scroll()',20);
	}
}
//实时消息窗口自动消失
function autoClose(){
	$('.float-msg').animate({'opacity':0,'width':0,'height':0},300);
}

$(function(){
	$('.dropdown').on('click','.dropdown-menu li a',function(){
	    var $this = $(this),
	        txt = $this.html(),
	        name = $this.attr('name'),
	        inp= $this.parents('ul.dropdown-menu').prev('button').find('span.filter-option');
	    inp.text(txt).attr('name',name); 
	    $(this).attr('aria-selected',true).parent().addClass('selected').siblings().removeClass('selected').find('a').attr('aria-selected',false);
	});

	//时间控件禁止时间输入框点击弹出日期，而需要点击日历图标点击出来
	$('.form_datetime').find('input.form-control').attr('disabled','disabled');
});
