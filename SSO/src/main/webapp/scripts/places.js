function HEREPlaces (map, platform) {
  this.map = map;
  this.placeSearch = new H.places.Search (platform.getPlacesService());
  this.searchResults = [];
}

HEREPlaces.prototype.searchPlaces = function(query) {
  this.getPlaces(query, function(places) {
    this.updatePlaces(places);
  }.bind(this));
};

HEREPlaces.prototype.getPlaces = function(query, onSuccessCallback) {
  var onSuccess, onError;
  
  onSuccess = function(data) {
    if (data.results && data.results.items) {
      var places = data.results.items.map(function(place){
        place.coordinates = {
          lat: place.position[0],
          lng: place.position[1]
        };
        return place;
      });

      var ricette = [];
      ricette =  $.ajax({
        type: "GET",
        url: "http://localhost:8080/SSO_war_exploded/api/pazienti/1/ricette?evaseonly=false&nonevaseonly=true"
      });
      console.log(ricette);

      if(data.results.items[0].distance < 900){
        alert('Vai a comprare le medicine');
      }
      
      onSuccessCallback(data.results.items);
      
    } else {
      onError(data);
    }
  };

  onError = function(error) {
    console.error('Error happened when fetching places!', error);
  };

  this.placeSearch.request(query, {}, onSuccess, onError);
};

HEREPlaces.prototype.clearSearch = function() {
  this.searchResults.forEach(function(marker){
    this.map.removeObject(marker);
  }.bind(this));
  this.searchResults = [];
};

HEREPlaces.prototype.updatePlaces = function(places) {
  this.clearSearch();
  this.searchResults = places.map(function(place){
    
    var iconUrl = './images/1.jpg';

    var iconOptions = {
      // The icon's size in pixel:
      size: new H.math.Size(46, 58),
      // The anchorage point in pixel, 
      // defaults to bottom-center
      anchor: new H.math.Point(14, 34)
    };

    var markerOptions = {
      icon: new H.map.Icon(iconUrl, iconOptions)
    };
    
    var marker = new H.map.Marker(place.coordinates, markerOptions);
    this.map.addObject(marker,'red');
    return marker;
  }.bind(this));
};
