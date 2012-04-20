
(function($) {
	if(!$.browser.msie) {
		var els = $('input.number');
		for(var i = 0; i < els.size(); i++) {
			els[i].type = "number";
		}
	}
	
	var el = $('.topic-input');
	if(el.val() == "") {
		el.focus();
	}
})(jQuery);