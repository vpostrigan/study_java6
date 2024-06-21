	var map;
	var map2;
	
	function instanceMap() {
		if (map == null) {
			map = new GMap2(document.getElementById("map_canvas"));
		}
	}
	
	function instanceMap2() {
		if (map2 == null) {
			map2 = new GMap2(document.getElementById("map_canvas2"));
		}
	}
	
	function initialize2() {
		if (GBrowserIsCompatible()) {
			instanceMap2();
			
			/* Создание элементов навигации, масштабная линейка */
			var map_ctrl       = new GLargeMapControl();
			var map_type_ctrl  = new GMapTypeControl();
			var map_scale_ctrl = new GScaleControl();
			/*map.setCenter(new GLatLng(50.0, 30.5), 13, G_NORMAL_MAP);*/
			map2.addControl(map_ctrl);
			
			/* Кнопки выбора типа карты*/
			map2.addControl(map_type_ctrl);
			
			/* Добавление масштабной линейки */
			map2.addControl(map_scale_ctrl);
			
			// Координаты
			GEvent.addListener(map2, "moveend", function() {
  	        	var center = map2.getCenter();
  	       		document.getElementById("message").innerHTML = center.toString();
  	        });
		}
	}
    
	function initialize() {
		if (GBrowserIsCompatible()) {
			instanceMap();
			
			/* Создание элементов навигации, масштабная линейка */
			var map_ctrl       = new GLargeMapControl();
			var map_type_ctrl  = new GMapTypeControl();
			var map_scale_ctrl = new GScaleControl();
			/*map.setCenter(new GLatLng(50.0, 30.5), 13, G_NORMAL_MAP);*/
			map.addControl(map_ctrl);
			
			/* Кнопки выбора типа карты*/
			map.addControl(map_type_ctrl);
			
			/* Добавление масштабной линейки */
			map.addControl(map_scale_ctrl);
			
			// Координаты
			GEvent.addListener(map, "moveend", function() {
  	        	var center = map.getCenter();
  	       		document.getElementById("message").innerHTML = center.toString();
  	        });
		}
	}
	
	function setMapCenter(i, j, zoom) {
		instanceMap();
		map.setCenter(new GLatLng(i, j), parseInt(zoom), G_NORMAL_MAP);
	}

	function setMapCenter_2(i, j, zoom) {
		instanceMap2();
		map2.setCenter(new GLatLng(i, j), parseInt(zoom), G_NORMAL_MAP);
	}
	
	function setMapCenterLittle2(point, index) {
		instanceMap2();
		//map.setCenter(new GLatLng(i, j), parseInt(zoom), G_NORMAL_MAP);
		
		// Create a base icon for all of our markers that specifies the
	    // shadow, icon dimensions, etc.
	    var baseIcon = new GIcon(G_DEFAULT_ICON);
	    baseIcon.shadow = "http://www.google.com/mapfiles/shadow50.png";
	    baseIcon.iconSize = new GSize(20, 34);
	    baseIcon.shadowSize = new GSize(37, 34);
	    baseIcon.iconAnchor = new GPoint(9, 34);
	    baseIcon.infoWindowAnchor = new GPoint(9, 2);
		
		var letter = String.fromCharCode("A".charCodeAt(0) + index);
        var letteredIcon = new GIcon(baseIcon);
        letteredIcon.image = "http://www.google.com/mapfiles/marker" + letter + ".png";

        // Set up our GMarkerOptions object
        markerOptions = { icon:letteredIcon };
        var marker = new GMarker(point, markerOptions);

        GEvent.addListener(marker, "click", function() {
          marker.openInfoWindowHtml("Marker <b>" + letter + "</b>");
        });
        
        map2.addOverlay(marker);
	}

	function setMapLocation(currentCity, tableWithLocation) {
		var v = document.getElementById(currentCity);
		var vName;
	
		var massive = document.getElementById(tableWithLocation);
	
		if ( v.selectedIndex != -1 ) {
			//Если есть выбранный элемент, отобразить его значение (свойство value)
			vName = v.options[v.selectedIndex].value;
		}
	
		for (var i = 0; i < massive.rows.length; i++) {
			if (massive.rows[i].cells[0].innerHTML == vName){				
				setMapCenter(massive.rows[i].cells[1].innerHTML, 
						massive.rows[i].cells[2].innerHTML, 
						massive.rows[i].cells[3].innerHTML);
			}
		}
	}
	
	function setMapLittleLocationMy(tableWithLocation) {
	
		var massive = document.getElementById(tableWithLocation);
	
		for (var i = 0; i < massive.rows.length; i++) {
			if (i==0){
				setMapCenter_2(massive.rows[i].cells[0].innerHTML, 
						massive.rows[i].cells[1].innerHTML, 
						massive.rows[i].cells[2].innerHTML);
			} else {
				var latlng = new GLatLng(massive.rows[i].cells[1].innerHTML,
						massive.rows[i].cells[0].innerHTML);
				setMapCenterLittle2(latlng, i-1);
			}
			 	
		}
	}