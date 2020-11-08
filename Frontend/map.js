var map;
var infowindow;
var searchBox;
var radius = 500;
setTimeout(clickHandler, 500);

function initMap() {
  console.log("init");
  var latitude = window.localStorage.getItem("latitude");
  var longitude = window.localStorage.getItem("longitude");
  var dietaryPref = "kuchnia " + window.localStorage.getItem("dietaryName");
  var center = new google.maps.LatLng(latitude, longitude);
  var options = {
    center: center,
    zoom: 13
  };

  //console.log("center: " + center.lat());
  // var request = {
  //   location: center,
  //   radius: 500,
  //   types: ["restaurant"]
  // };
  map = new google.maps.Map(document.getElementById("map"), options);
  infowindow = new google.maps.InfoWindow();

  var marker = new google.maps.Marker({
    position: center,
    map: map,
    zIndex: 100,
    title: "Środkowa odległość pomiędzy użytkownikami konwersacji",
    icon: {
      url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
    }
  });
  google.maps.event.addListener(marker, "click", function() {
    infowindow.setContent("Punkt środkowy pomiędzy pomiędzy użytkownikami");
    infowindow.open(map, this);
  });

  // var service = new google.maps.places.PlacesService(map);
  // service.nearbySearch(request, callback);

  // function callback(result, status) {
  //   if (status == google.maps.places.PlacesServiceStatus.OK) {
  //     for (var i = 0; i < result.length; i++) {
  //       createMarker(result[i]);
  //     }
  //   }
  // }

  // function createMarker(place) {
  //   var placeLoc = place.geometry.location;
  //   var marker = new google.maps.Marker({
  //     map: map,
  //     position: place.geometry.location
  //   });
  //   google.maps.event.addListener(marker, "click", function() {
  //     infowindow.setContent(
  //       "<strong>" +
  //         place.name +
  //         "</strong>" +
  //         `<br>` +
  //         "Ocena: " +
  //         place.rating.toFixed(2) +
  //         "<br>"
  //     );
  //     infowindow.open(map, this);
  //   });
  // }

  var input = document.getElementById("search");
  searchBox = new google.maps.places.SearchBox(input);
  input.value = dietaryPref;

  map.addListener("bounds_changed", function() {
    console.log("bounds changed");
    searchBox.setBounds(map.getBounds());
  });

  var markers = [];

  searchBox.addListener("places_changed", function() {
    console.log("places changed");
    var places = searchBox.getPlaces();

    if (places.length === 0) {
      return;
    }

    markers.forEach(function(m) {
      m.setMap(null);
    });
    markers = [];

    var bounds = new google.maps.LatLngBounds();

    places.forEach(function(p) {
      if (!p.geometry) return;

      var distance = getDistanceBetweenPoints(
        p.geometry.location.lat(),
        p.geometry.location.lng(),
        latitude,
        longitude
      );
      // console.log("calculated distance: " + distance);  TEST POKAZ

      if (distance < 3000) {
        console.log("calculated distance: " + distance); // TEST POKAZ
        // tutaj
        var searchMarker = new google.maps.Marker({
          map: map,
          title: p.name,
          position: p.geometry.location
        });
        //////////////////
        google.maps.event.addListener(searchMarker, "click", function() {
          infowindow.setContent(
            "<strong>" +
              p.name +
              "</strong>" +
              `<br>` +
              "Ocena: " +
              p.rating +
              "<br>"
          );
          infowindow.open(map, this);
        });

        ////////////

        markers.push(searchMarker);
      }
      if (p.geometry.viewport) {
        bounds.union(p.geometry.viewport);
      } else {
        bounds.extend(p.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });
}

function clickHandler() {
  console.log(" handler");
  var test = document.getElementById("search");
  google.maps.event.trigger(test, "focus", {});
  google.maps.event.trigger(test, "keydown", { keyCode: 13 });
  google.maps.event.trigger(this, "focus", {});
}

function degreesToRadians(degrees) {
  return (degrees * Math.PI) / 180;
}

function getDistanceBetweenPoints(lat1, lng1, lat2, lng2) {
  // The radius of the planet earth in meters
  let R = 6378137;
  let dLat = degreesToRadians(lat2 - lat1);
  let dLong = degreesToRadians(lng2 - lng1);
  let a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(degreesToRadians(lat1)) *
      Math.cos(degreesToRadians(lat1)) *
      Math.sin(dLong / 2) *
      Math.sin(dLong / 2);

  let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  let distance = R * c;

  return distance;
}
