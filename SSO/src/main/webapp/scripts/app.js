var mapContainer = document.getElementById('mapContainer');

var platform = new H.service.Platform({
  //apikey: '9ZZLQaMKUi1q7AIY9burFElLlUtLunmqcYc6w4JbQN8'
  app_id: 'eXyeKXjLMDyo92pFfzNf', // // <-- ENTER YOUR APP ID HERE
  app_code: 'QuU-fH5ZjNfHHzf2IZHEkg' // <-- ENTER YOUR APP CODE HERE
});

var coordinates = {
  lat: 52.530974,
  lng: 13.384944
};

var mapOptions = {
  center: coordinates,
  zoom: 14
};

var map = new HEREMap(mapContainer, platform, mapOptions);