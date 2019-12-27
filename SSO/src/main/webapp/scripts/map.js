function HEREMap (mapContainer, platform, mapOptions) {
  this.platform = platform;
  this.position = mapOptions.center;

  var defaultLayers = platform.createDefaultLayers();

  // Instantiate wrapped HERE map
  this.map = new H.Map(mapContainer, defaultLayers.normal.map, mapOptions);

  // Basic behavior: Zooming and panning
  var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(this.map));

  // Watch the user's geolocation and display it
  navigator.geolocation.watchPosition(this.updateMyPosition.bind(this));

  // Resize the map when the window is resized
  window.addEventListener('resize', this.resizeToFit.bind(this));

  this.places = new HEREPlaces(this.map, this.platform);

  
}

HEREMap.prototype.updateMyPosition = function(event) {

  if (event.coords.latitude < (this.position.lat-0.0001) ||
      event.coords.latitude > (this.position.lat+0.0001) ||
      event.coords.longitude < (this.position.lng-0.0001) ||
      event.coords.longitude > (this.position.lng+0.0001)) {
    
    this.position = {
      lat: event.coords.latitude,
      lng: event.coords.longitude
    };

    if (this.myLocationMarker) {
      this.removeMarker(this.myLocationMarker);
    }

    this.myLocationMarker = this.addMarker(this.position);
    this.map.setCenter(this.position);
    this.searchForPharmacies();
      
  }
};

HEREMap.prototype.addMarker = function(coordinates) {
  var marker = new H.map.Marker(coordinates);
  this.map.addObject(marker);

	return marker;
};

HEREMap.prototype.removeMarker = function(marker) {
  this.map.removeObject(marker);
};

HEREMap.prototype.resizeToFit = function() {
  this.map.getViewPort().resize();
};

HEREMap.prototype.searchForPharmacies = function(){

  var query = {
    'q': 'pharmacies',
    'at': this.position.lat + ',' + this.position.lng
  };

  this.places.searchPlaces(query);
};