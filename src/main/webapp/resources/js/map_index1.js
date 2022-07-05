
var attr_osm = 'Map data &copy; <a href="http://openstreetmap.org/">OpenStreetMap</a> contributors';
var attr_overpass = 'POI via <a href="http://www.overpass-api.de/">Overpass API</a>';

navigator.geolocation.getCurrentPosition(function(location) {

	var mymap = L.map('mapid1').setView([location.coords.latitude, location.coords.longitude], 13);
	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		maxZoom: 19,
		attribution: '© OpenStreetMap'
	}).addTo(mymap);

	//Tao marker
	var marker = L.marker([location.coords.latitude, location.coords.longitude]).addTo(mymap);
	
	var control = L.Routing.control({
		waypoints: [
			L.latLng(location.coords.latitude, location.coords.longitude),
			L.latLng(location.coords.latitude, location.coords.longitude)
		],
		router: new L.Routing.osrmv1({
			language: 'en',
			profile: 'car'
		}),
		geocoder: L.Control.Geocoder.nominatim({})
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

	mymap.on('click', function(e) {
		mymap.removeLayer(marker);
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
		query: 'node(around:500.0,' + location.coords.latitude + ',' + location.coords.longitude + ')["amenity"="restaurant"];out;',
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