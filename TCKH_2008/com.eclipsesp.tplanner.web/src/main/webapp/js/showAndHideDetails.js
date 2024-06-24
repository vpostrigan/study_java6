function showDetails(){
	document.getElementById("showDetailsLinkDiv").style.display = "none";
	document.getElementById("hideDetailsLinkDiv").style.display = "";
	document.getElementById("stackTraceDiv").style.display = "";
}

function hideDetails(){
	document.getElementById("showDetailsLinkDiv").style.display = "";
	document.getElementById("hideDetailsLinkDiv").style.display = "none";
	document.getElementById("stackTraceDiv").style.display = "none";
}