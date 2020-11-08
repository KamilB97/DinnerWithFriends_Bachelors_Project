window.onload = function loadContIcons() {
  var profileId = window.localStorage.getItem("checkUserProfile");
  getAndSetupProfile(profileId);
  //getAllAndSetupInterestings();
  //getAllAndSetupDietaryPreferences();
  // pobierz profil i potem edytuj
};

async function setupProfileInformations(
  firstName,
  lastName,
  age,
  gender,
  city
) {
  var nameSpan;
  var ageSpan;
  var genderSpan;
  var citySpan;
  var informationsBox = document.getElementById("informations-body");

  nameSpan = document.getElementById("name-span");
  //nameSpan.className = 'leftbar-entity';
  nameSpan.innerHTML = firstName + " " + lastName;

  ageSpan = document.getElementById("age-span");
  ageSpan.innerHTML = age;

  genderSpan = document.getElementById("gender-span");
  genderSpan.innerHTML = gender;

  citySpan = document.getElementById("city-span");
  citySpan.innerHTML = city;
}

async function setupProfileInterestings(interestings) {
  var interestingsTempl;
  var interestingsBox = document.getElementById("interestings-body");
  for (i = 0; i < interestings.length; i++) {
    interestingsTempl = document.createElement("span");
    interestingsTempl.id = "interesting" + interestings[i].id; //11 interesting
    interestingsTempl.innerHTML = interestings[i].name;
    interestingsBox.appendChild(interestingsTempl);
    br = document.createElement("br");
    interestingsBox.appendChild(br);
  }
}

async function setupProfilePreferences(preferences) {
  var dietaryTempl;
  var dietaryBox = document.getElementById("dietary-body");
  for (i = 0; i < preferences.length; i++) {
    dietaryTempl = document.createElement("span");
    dietaryTempl.id = "dietary" + preferences[i].id; // 7 dietary
    dietaryTempl.innerHTML = preferences[i].name;
    dietaryBox.appendChild(dietaryTempl);
    br = document.createElement("br");
    dietaryBox.appendChild(br);
  }
}
async function setupProfileImage(imageUrl) {
  var imageTempl;
  imageTempl = document.getElementById("profile-img");
  if (imageUrl != null && imageUrl != undefined) {
    imageTempl.setAttribute("src", imageUrl);
  }
}

async function setupProfileAboutNote(about) {
  var textArea = document.getElementById("about-textarea");
  textArea.readOnly = true;
  textArea.innerHTML = about;
}

function getAndSetupProfile(profileId) {
  let response = new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(getRequest("http://localhost:8080/api/profiles/" + profileId));
    }); // ,1000 opoznienie
  });
  response.then(json => {
    console.log("Profile informations");
    console.log(JSON.stringify(json));

    setupProfileInformations(
      json.firstName,
      json.lastName,
      json.age,
      json.gender,
      json.cityName
    );
    setupProfileInterestings(json.listOfInterestings);
    setupProfilePreferences(json.listOfDietaryPreferences);
    setupProfileAboutNote(json.about);
    console.log("przed ustawieniem Image");
    console.log(json.image);
    setupProfileImage(json.image);
  });
}

async function getRequest(url) {
  const response = await fetch(url, {
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "X-Authorization": window.localStorage.getItem("token")
    },
    redirect: "follow", // manual, *follow, error
    referrer: "no-referrer" // no-referrer, *client
  });
  return await response.json();
} // id checkbox = "interesting{numer}

function getInformationsInput() {
  // Put info on server
  var firstName = document.getElementById("firstName").value;
  var lastName = document.getElementById("lastName").value;
  var age = document.getElementById("age").value;
  var gender = document.getElementById("genderSelect").value;
  var city = document.getElementById("citySelect").value;
  var street = document.getElementById("street").value;

  let map = new Map();
  map.set("firstName", firstName);
  map.set("lastName", lastName);
  map.set("age", age);
  if (gender == "kobieta") {
    console.log("kobieta");
    map.set("gender", "female");
  } else if (gender == "mężczyzna") {
    console.log("mężczyzna");
    map.set("gender", "male");
  }
  //map.set("gender", gender);
  map.set("city", city);
  map.set("street", street);

  console.log(
    "inputs: " +
      map.get("firstName") +
      " " +
      map.get("lastName") +
      " " +
      map.get("age") +
      " " +
      map.get("gender") +
      " " +
      map.get("city") +
      " " +
      map.get("street")
  );
  return map;
}

async function postRequest(url, json) {
  console.log("w metodzie postRequest");
  const response = await fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
      "X-Authorization": window.localStorage.getItem("token")
    },
    body: json,
    redirect: "follow", // manual, *follow, error
    referrer: "no-referrer" // no-referrer, *client
  });

  // return await response.json();
}
