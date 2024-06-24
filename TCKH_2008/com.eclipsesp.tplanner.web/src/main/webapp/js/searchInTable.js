function searchInTable(searchId, tableId){		
				
	var v = window.document.getElementById(searchId).value;		
				
	var massive = window.document.getElementById(tableId);
	
	var cell, re;
	
	var v_color;
	v_color = false
	
	document.getElementById(searchId).style.color = "#020202"
			
	// Search
	for (var i = 1; i < massive.rows.length; i++) {
 		for (var j = 0; j < massive.rows[i].cells.length; j++) {
  			
  			if (navigator.appName == 'Netscape') {
   				cell = massive.rows[i].cells[j].textContent;
			} else {
  				cell = massive.rows[i].cells[j].innerText;
			}  			
  			
  			if(cell == "&nbsp;" || cell == ""){
  				continue;  				
  			}  			
  			
  			if(cell.indexOf(v) >= 0){
  				massive.rows[i].style.display = "";
  				v_color = true;  				
  				break;
  			}else{
  				massive.rows[i].style.display = "none";
  			}
 		}
	}
	
	// If something found then search value painting in green
	if(v_color){
		document.getElementById(searchId).style.color = "#03aa4a";		
	}
}