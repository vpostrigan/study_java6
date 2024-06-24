// for INPUT
sfFocus = function() {
	var sfEls = document.getElementsByTagName("INPUT");
		
	for (var i=0; i<sfEls.length; i++) {
		sfEls[i].onfocus=function() {
			this.className+=" sffocus";
		}
		sfEls[i].onblur=function() {
			this.className=this.className.replace(new RegExp(" sffocus\\b"), "");
		}
	}
}

if (window.attachEvent) window.attachEvent("onload", sfFocus);

// for TEXTAREA
sfFocus2 = function() {
	var sfEls2 = document.getElementsByTagName("TEXTAREA");
	
	for (var i=0; i<sfEls2.length; i++) {
		sfEls2[i].onfocus=function() {
			this.className+=" sffocus2";
		}
		sfEls2[i].onblur=function() {
			this.className=this.className.replace(new RegExp(" sffocus2\\b"), "");
		}
	}
}

if (window.attachEvent) window.attachEvent("onload", sfFocus2);