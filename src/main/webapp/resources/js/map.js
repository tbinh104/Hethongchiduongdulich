function reloadDIV() {
	var elem = document.getElementById("mapid");
	elem.remove();
}

function MapClick(kd, vd) {
	var attr_osm = 'Map data &copy; <a href="http://openstreetmap.org/">OpenStreetMap</a> contributors';
	var attr_overpass = 'POI via <a href="http://www.overpass-api.de/">Overpass API</a>';
	var tamp = document.getElementById("mapid");

	if (tamp == null) {
		var tag = document.createElement("div");
		tag.id = 'mapid';
		var element = document.getElementById("parenta");
		element.appendChild(tag);
	}

	navigator.geolocation.getCurrentPosition(function(location) {

		//Tao map
		var mymap = L.map('mapid').setView([kd, vd], 17);
		//console.log(mymap);
		L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			maxZoom: 20,
			attribution: '© OpenStreetMap'
		}).addTo(mymap);

		//Tao marker
		 var taxiIcon = L.icon({
			iconUrl: '/resources/pictures/minibus.png',
			iconSize: [40, 40]
		})

		var marker = L.marker([location.coords.latitude, location.coords.longitude], { icon: taxiIcon }).addTo(mymap);
	    
		
		//Thiet lap dan duong
		var control = L.Routing.control({
			waypoints: [
				L.latLng(location.coords.latitude, location.coords.longitude),
				L.latLng(kd, vd)
			],
			router: new L.Routing.osrmv1({
				language: 'en',
				profile: 'car'
			}),
			geocoder: L.Control.Geocoder.nominatim({})
		}).on('routesfound', function(e) { // Hien thi duy chuyen
			var routes = e.routes;
			//console.log(routes);
			e.routes[0].coordinates.forEach(function(coord, index) {
				setTimeout(function() {
					marker.setLatLng([coord.lat, coord.lng]);
				}, 5 * index)
			})
		}).addTo(mymap);

		var popup = L.popup();
		function onMapClick(e) {
			popup
				.setLatLng(e.latlng)
				.setContent("You clicked the map at " + e.latlng.toString())
				.openOn(mymap);
		}
		mymap.on('click', onMapClick);
		function createButton(label, container) {
			var btn = L.DomUtil.create('button', '', container);
			btn.setAttribute('type', 'button');
			btn.innerHTML = label;
			return btn;
		}

		//Tao chuc nang chon diem den, diem di bang cach click
		mymap.on('click', function(e) {
			//var marker = L.marker([e.latlng[0], e.latlng[1]]).addTo(mymap);
			var container = L.DomUtil.create('div'), startBtn = createButton('Pick start', container), destBtn = createButton('Pick destination', container);
			L.popup().setContent(container).setLatLng(e.latlng).openOn(mymap);

			L.DomEvent.on(startBtn, 'click', function() {
				control.spliceWaypoints(0, 1, e.latlng);
				mymap.closePopup();
			});

			L.DomEvent.on(destBtn, 'click', function() {
				control.spliceWaypoints(control.getWaypoints().length - 1, 1, e.latlng);
				mymap.closePopup();
			});					
			mymap.removeLayer(marker);
			
		});

	

		var restaurantIcon = L.icon({
			iconUrl: '/resources/pictures/restauran1t.png',
			iconSize: [28, 36],
			iconAnchor: [22, 94],
			popupAnchor: [-3, -76],
			shadowSize: [68, 95],
			shadowAnchor: [22, 94]
		});

		opl = new L.OverPassLayer({
			minZoom: 10,
			endPoint: 'https://overpass-api.de/api/',
			query: 'node(around:500.0,' + kd + ',' + vd + ')["amenity"="restaurant"];out;',
			onSuccess: function(data) {
				var grupo = L.markerClusterGroup({ showCoverageOnHover: true, disableClusteringAtZoom: 19 });
				for (i = 0; i < data.elements.length; i++) {
					e = data.elements[i];
					var pos = new L.LatLng(e.lat, e.lon);
					console.info(e.tags);
					L.marker(pos, {
						icon: restaurantIcon,
						title: e.tags.name, //shows restaurants names
						tipus: e.tags.amenity
					}).on('click', markerOnClick).addTo(grupo); //add markers to the cluster
				}
				mymap.addLayer(grupo); //to add the cluster to the map
				function markerOnClick() { // xu ly su kien o day			
				}
			},
		});
		mymap.addLayer(opl);
	});
}