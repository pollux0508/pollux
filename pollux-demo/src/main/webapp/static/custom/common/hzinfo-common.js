$(function () {
	$('*[required]').parent().prev().prepend('<span style="color:red">*&nbsp;</span>');
});
