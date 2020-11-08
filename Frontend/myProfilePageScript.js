window.onload = function loadContIcons() {
  var profileId = window.localStorage.getItem("profileId");
  try {
    getAndSetupProfile(profileId);
  } catch (error) {
    console.log(error);
  }
  var name = JSON.parse(window.localStorage.getItem("name"));
  var surname = JSON.parse(window.localStorage.getItem("surname"));
  if (name == "nie ustawiono" && surname == "nie ustawiono") {
    this.replaceAboutProfileWithEditBox;
  }

  //getAllAndSetupInterestings();
  //getAllAndSetupDietaryPreferences();
  // pobierz profil i potem edytuj
};
var cityMap = new Map();
//getCitiesList(profileId);

class City {
  constructor(name, id) {
    this._name = name;
    this._id = id;
  }
  get name() {
    return this._name;
  }
  get id() {
    return this._id;
  }
  set name(name) {
    this._name = name;
  }
  set id(id) {
    this._id = id;
  }
}

//@@@@@@@@@@@@@@@@@@@@@ dodaj po zapisz update na serverze
function makeAboutTextareaEditable() {
  console.log("makeAboutTextareaEditable()");
  var textareaElement = document.getElementById("about-textarea");
  var editbutton = document.getElementById("about-editbutton");
  if (textareaElement.readOnly == true) {
    textareaElement.readOnly = false;
    editbutton.innerHTML = "Zapisz";
  } else if (textareaElement.readOnly == false) {
    var about = document.getElementById("about-textarea").value;
    updateAbout(about);

    textareaElement.readOnly = true;
    editbutton.innerHTML = "Edytuj";
  }
}

function replaceAboutProfileWithEditBox() {
  if (
    document.getElementById("about-title").style.display == "" ||
    document.getElementById("about-title").style.display == "block"
  ) {
    document.getElementById("about-title").style.display = "none";
    document.getElementById("about-textarea").style.display = "none";
    document.getElementById("about-editbutton").style.display = "none";
    document.getElementById("about-profile").style.display = "none";
    setupEditProfileInformationsBox();
  } else if (document.getElementById("about-title").style.display == "none") {
    document.getElementById("about-title").style.display = "";
    document.getElementById("about-textarea").style.display = "";
    document.getElementById("about-editbutton").style.display = "";
    document.getElementById("about-profile").style.display = "";
    clearEditProfileInformationsBox();
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  }
}

function ValidateInterestingsSelection(validationName) {
  var checkboxes = document.getElementsByName(validationName);
  var numberOfCheckedItems = 0;
  for (var i = 0; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) numberOfCheckedItems++;
  }
  if (numberOfCheckedItems > 3) {
    alert("Nie możesz wybrać więcej niż trzy!");
    return false;
  }
}

async function setupProfileInformations(
  firstName,
  lastName,
  age,
  gender,
  city,
  street
) {
  var nameSpan;
  var ageSpan;
  var genderSpan;
  var citySpan;
  var informationsBox = document.getElementById("informations-body");

  nameSpan = document.createElement("span");
  //nameSpan.className = 'leftbar-entity';
  nameSpan.id = "name-span";
  if (
    firstName != "null" &&
    firstName != "nie ustawiono" &&
    lastName != "null" &&
    lastName != "nie ustawiono"
  ) {
    nameSpan.innerHTML = "Imię i nazwisko: " + firstName + " " + lastName;
  } else {
    nameSpan.innerHTML = "Imię i nazwisko: " + firstName;
  }

  informationsBox.appendChild(nameSpan);

  br = document.createElement("br");
  informationsBox.appendChild(br);

  ageSpan = document.createElement("span");
  ageSpan.id = "age-span";
  if (age != "null" && age != 0 && age != null) {
    ageSpan.innerHTML = "Wiek: " + age;
  } else {
    ageSpan.innerHTML = "Wiek: " + "nie ustawiono";
  }

  informationsBox.appendChild(ageSpan);

  br = document.createElement("br");
  informationsBox.appendChild(br);

  genderSpan = document.createElement("span");
  genderSpan.id = "gender-span";
  if (gender != null && gender != "nie ustawiono") {
    if (gender == "female") {
      genderSpan.innerHTML = "Płeć: " + "kobieta";
    } else {
      genderSpan.innerHTML = "Płeć: " + "mężczyzna";
    }
  } else {
    genderSpan.innerHTML = "Płeć: " + "nie ustawiono";
  }

  informationsBox.appendChild(genderSpan);

  br = document.createElement("br");
  informationsBox.appendChild(br);

  citySpan = document.createElement("span");
  citySpan.id = "city-span";
  citySpan.innerHTML = "Miasto: " + city;
  informationsBox.appendChild(citySpan);

  br = document.createElement("br");
  informationsBox.appendChild(br);

  streetSpan = document.createElement("span");
  streetSpan.id = "street-span";
  if (street != "null" && street != "nie ustawiono" && street != null) {
    streetSpan.innerHTML = "Ulica: " + street;
  } else {
    streetSpan.innerHTML = "Ulica: " + "nie ustawiono";
  }
  informationsBox.appendChild(streetSpan);
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
  imageTempl = document.getElementById("image-preview_image");
  if (imageUrl != null && imageUrl != undefined) {
    imageTempl.setAttribute("src", imageUrl);
  }
}

async function setupProfileAboutNote(about) {
  var topBody = document.getElementById("top-body");

  var aboutDiv = document.createElement("div");
  aboutDiv.id = "about-profile"; //11 interesting
  aboutDiv.className = "box";
  topBody.appendChild(aboutDiv);

  var aboutTitle = document.createElement("span");
  aboutTitle.id = "about-title";
  aboutTitle.innerHTML = "O sobie";
  aboutDiv.appendChild(aboutTitle);
  var br = document.createElement("br");
  aboutDiv.appendChild(br);

  var textArea = document.createElement("textarea");
  textArea.readOnly = true;
  textArea.id = "about-textarea";
  textArea.innerHTML = about;
  aboutDiv.appendChild(textArea);

  var editbutton = document.createElement("button");
  editbutton.id = "about-editbutton";
  editbutton.innerHTML = "Edytuj";
  editbutton.onclick = function() {
    if (editbutton.innerHTML == "Zapisz") {
      console.log("innerHTML = zapisz");
      var aboutNote = document.getElementById("");
      updateAboutOnServer();
      makeAboutTextareaEditable();
    } else {
      console.log("innerHTML != zapisz");
      makeAboutTextareaEditable();
    }
  };

  aboutDiv.appendChild(editbutton);
}
// @@@@@@@@@@@@@@@@@@@@@ TODO 19.10.2019 post do update aboutNote
function updateAboutOnServer(aboutNote) {
  console.log("Updating on server");
  var json = {};
  json.profileId = window.localStorage.getItem("profileId");
  json.about = aboutNote;
  var jsonToPost = JSON.stringify(json);
  console.log(jsonToPost);
}
async function updateImage(imageUrl) {
  var json = {};
  json.profileId = window.localStorage.getItem("profileId"); // zmienic do update zdj
  json.imageUrl = imageUrl;
  var jsonToPost = JSON.stringify(json);
  // console.log(jsonToPost);

  updateRequest("http://localhost:8080/api/images", jsonToPost);
}

async function setupEditProfileInformationsBox() {
  await getCitiesList(window.localStorage.getItem("profileId"));
  createEditProfileDiv();
  setupEditInformationsBox();
  setupEditInterestingsBox();
  setupEditDietaryPreferencesBox();
  getAllAndSetupInterestings();
  getAllAndSetupDietaryPreferences();
  createSubmitEditProfileButton();
}
function clearEditProfileInformationsBox() {
  var editProfileBox = document.getElementById("edit-profile");
  editProfileBox.remove();
}

function createEditProfileDiv() {
  var topBody;
  var editProfileBox;
  topBody = document.getElementById("top-body");

  editProfileBox = document.createElement("div");
  //editProfileBox = document.getElementById('about-profile');
  editProfileBox.id = "edit-profile";
  editProfileBox.className = "box";
  topBody.appendChild(editProfileBox);
}
function setupEditInformationsBox() {
  //	var topBody;
  var editProfileBox;
  var profileInformationsDiv;

  editProfileBox = document.getElementById("edit-profile");
  //topBody= document.getElementById('top-body');

  //editProfileBox = document.createElement('div');
  ////editProfileBox = document.getElementById('about-profile');
  //editProfileBox.id = 'edit-profile';
  //editProfileBox.className = 'box';
  //topBody.appendChild(editProfileBox);

  profileInformationsDiv = document.createElement("div");
  profileInformationsDiv.id = "edit-profile-informations";
  editProfileBox.appendChild(profileInformationsDiv);

  var firstNameSpan = document.createElement("span");
  firstNameSpan.innerHTML = "Imię:";
  profileInformationsDiv.appendChild(firstNameSpan);

  var firstNameInput = document.createElement("input");
  firstNameInput.type = "text";
  var nameSurname = document
    .getElementById("name-span")
    .innerHTML.substring(17);
  var res = nameSurname.split(" ");

  if (res[0] == "nie") {
    firstNameInput.value = "nie ustawiono";
  } else {
    firstNameInput.value = res[0];
  }

  firstNameInput.id = "firstName";
  firstNameInput.name = "fname";
  profileInformationsDiv.appendChild(firstNameInput);

  var br = document.createElement("br");
  profileInformationsDiv.appendChild(br);
  //------------------------------------
  var lastNameSpan = document.createElement("span");
  lastNameSpan.innerHTML = "Nazwisko:";
  profileInformationsDiv.appendChild(lastNameSpan);

  var lastNameInput = document.createElement("input");
  lastNameInput.type = "text";
  lastNameInput.id = "lastName";
  if (res[1] == "ustawiono") {
    lastNameInput.value = "nie ustawiono";
  } else {
    lastNameInput.value = res[1];
  }

  lastNameInput.name = "lname";
  profileInformationsDiv.appendChild(lastNameInput);

  br = document.createElement("br");
  profileInformationsDiv.appendChild(br);
  //---------------------------------------------------
  var ageSpan = document.createElement("span");
  ageSpan.innerHTML = "Age:";
  profileInformationsDiv.appendChild(ageSpan);

  var ageInput = document.createElement("input");
  ageInput.type = "text";
  ageInput.id = "age";
  ageInput.value = document.getElementById("age-span").innerHTML.substring(6);
  ageInput.name = "age";
  profileInformationsDiv.appendChild(ageInput);

  br = document.createElement("br");
  profileInformationsDiv.appendChild(br);
  //---------------------------------------------------
  var genderSpan = document.createElement("span");
  genderSpan.innerHTML = "Płeć:";
  profileInformationsDiv.appendChild(genderSpan);

  var genderSelect = document.createElement("select");
  genderSelect.id = "genderSelect";
  profileInformationsDiv.appendChild(genderSelect);
  var genderArray = ["mężczyzna", "kobieta"];
  for (var i = 0; i < genderArray.length; i++) {
    var option = document.createElement("option");
    option.value = genderArray[i];
    option.text = genderArray[i];
    genderSelect.appendChild(option);
  }
  br = document.createElement("br");
  profileInformationsDiv.appendChild(br);
  //-------------------------------------------
  var citySpan = document.createElement("span");
  citySpan.innerHTML = "Miasto:";
  profileInformationsDiv.appendChild(citySpan);

  var citySelect = document.createElement("select");
  citySelect.id = "citySelect";
  profileInformationsDiv.appendChild(citySelect);

  // var cityArray = ["wrocław", "poznan", "warszawa", "rzeszów"]; //= ["male", "female"];
  // for (var i = 0; i < cityMap.length; i++) {
  //   var cityOption = document.createElement("option");
  //   //console.log(cityArray[i] + " city");
  //   cityOption.value = cityArray[i];
  //   cityOption.text = cityArray[i];
  //   citySelect.appendChild(cityOption);
  // }
  var mapIter = cityMap.keys();

  // var cityArray = new Array(); //= ["male", "female"];

  // for (var i = 0; i < cityMap.size; i++) {
  //   var cityName = mapIter.next().value;
  //   console.log("MapEntries in loop: " + cityName);
  //   cityArray.push(cityName);
  // }

  for (var i = 0; i < cityMap.size; i++) {
    var cityOption = document.createElement("option");
    var cityName = mapIter.next().value;
    cityOption.value = cityName;
    cityOption.text = cityName;
    citySelect.appendChild(cityOption);
  }
  // for (var key in cityMap) {
  //   var id = cityMap.get(key);
  //   console.log("MapEntries in loop: " + key + " " + id);
  //   var cityOption = document.createElement("option");
  //   console.log(id + " city " + key);
  //   cityOption.value = key;
  //   cityOption.text = key;
  //   cityOption.id = "city" + id;
  //   citySelect.appendChild(cityOption);
  // }
  br = document.createElement("br");
  profileInformationsDiv.appendChild(br);

  var streetSpan = document.createElement("span");
  streetSpan.innerHTML = "Ulica:";
  profileInformationsDiv.appendChild(streetSpan);

  var streetInput = document.createElement("input");
  streetInput.type = "text";
  streetInput.id = "street";
  streetInput.value = document
    .getElementById("street-span")
    .innerHTML.substring(7);
  streetInput.name = "street";
  profileInformationsDiv.appendChild(streetInput);

  br = document.createElement("br");
  profileInformationsDiv.appendChild(br);
  //-------------------------------------------
  //var submitButton = document.createElement('input');
  //submitButton.type = "submit";
  //submitButton.id = "profileInformationsSubmit:";
  //submitButton.value = "Zatwierdź";
  //profileInformationsDiv.appendChild(submitButton);
}
function setupEditInterestingsBox() {
  var editProfileBox;
  var profileInterestingsDiv;
  editProfileBox = document.getElementById("edit-profile");

  profileInterestingsDiv = document.createElement("div");
  profileInterestingsDiv.id = "edit-profile-interestings";
  editProfileBox.appendChild(profileInterestingsDiv);

  var interestingsSpan = document.createElement("span");
  interestingsSpan.innerHTML = "Zainteresowania";
  interestingsSpan.id = "interestingsTitle";
  profileInterestingsDiv.appendChild(interestingsSpan);
  var br = document.createElement("br");
  profileInterestingsDiv.appendChild(br);
}
function setupEditDietaryPreferencesBox() {
  var editProfileBox;
  var profilePreferencesDiv;
  editProfileBox = document.getElementById("edit-profile");

  profilePreferencesDiv = document.createElement("div");
  profilePreferencesDiv.id = "edit-profile-dietary";
  editProfileBox.appendChild(profilePreferencesDiv);

  var preferencesSpan = document.createElement("span");
  preferencesSpan.innerHTML = "Preferencje kulinarne";
  preferencesSpan.id = "preferenceTitle";
  profilePreferencesDiv.appendChild(preferencesSpan);
  var br = document.createElement("br");
  profilePreferencesDiv.appendChild(br);
}

function createSubmitEditProfileButton() {
  var editProfileBox = document.getElementById("edit-profile");

  var submitButton = document.createElement("input");
  submitButton.type = "submit";
  submitButton.id = "submitEditProfileButton";
  submitButton.value = "Zatwierdź";
  submitButton.onclick = function() {
    // @@@@@@@@@@@ submit button do edit

    var informations = getInformationsInput();
    var interestings = getCheckedInterestingCheckboxesId();
    var preferences = getCheckedPreferenceCheckboxesId();

    var json = createUpdateProfileJSON(informations, interestings, preferences); // potem to jako json do posta
    updateInformations(JSON.stringify(json));

    //clearEditProfileInformationsBox();
    replaceAboutProfileWithEditBox();
  };
  editProfileBox.appendChild(submitButton);
}

async function setupProfileImage(imageUrl) {
  console.log("W metodzie setupProfileImage");
  var imgPreview = document.getElementById("imagePreview");
  var newImage = document.createElement("img");
  newImage.src = imageUrl;
  newImage.id = "profile-img";
  // newImage.className = "image-preview_image";
  newImage.alt = "Image Preview";
  imgPreview.appendChild(newImage);

  //var profileImage = document.getElementById("profile-img");
  //profileImage.setAttribute("src", imageUrl);
}

function getAndSetupProfile(profileId) {
  let response = new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(
        getRequest("http://localhost:8080/api/profiles/myprofile/" + profileId)
      );
    }); // ,1000 opoznienie
  });
  response.then(json => {
    // console.log("Profile informations");
    //console.log(JSON.stringify(json));

    setupProfileInformations(
      json.firstName,
      json.lastName,
      json.age,
      json.gender,
      json.cityName,
      json.street
    );
    setupProfileInterestings(json.listOfInterestings);
    setupProfilePreferences(json.listOfDietaryPreferences);
    setupProfileAboutNote(json.about);
    // console.log("przed ustawieniem Image");
    //console.log(json.image);
    setupProfileImage(json.image);
  });
}
async function getCitiesList(profileId) {
  var responseJson = await getRequest("http://localhost:8080/api/cities");
  responseJson.forEach(element => {
    // var city = new City();
    // city.name(element.name);
    // city.id(element.id);
    // city;
    // citiesList.push(city);
    console.log("setCity: " + element.name + " " + element.id);
    cityMap.set(element.name, element.id);
  });
}
function getAllAndSetupInterestings() {
  let response = new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(getRequest("http://localhost:8080/api/interestings"));
    }); // ,1000 opoznienie
  });
  response.then(json => {
    console.log("Profile informations");
    console.log(JSON.stringify(json));

    setupInterestingCheckBoxes(json);
  });
}
async function updateAbout(aboutNote) {
  console.log("update method");
  var json = {};
  json.profileId = window.localStorage.getItem("profileId");
  json.about = aboutNote;
  var jsonToPost = JSON.stringify(json);
  console.log(jsonToPost);

  var responseJson = await postRequest(
    "http://localhost:8080/api/profiles/about",
    jsonToPost
  );

  // setupDietaryPreferencesCheckBoxes(responseJson);
}
async function getAllAndSetupDietaryPreferences() {
  var responseJson = await getRequest(
    "http://localhost:8080/api/dietaryPreferences"
  );
  setupDietaryPreferencesCheckBoxes(responseJson);
}

function setupInterestingCheckBoxes(interestings) {
  console.log(interestings);
  console.log("test");
  console.log(interestings[0]);

  var interestingsBox = document.getElementById("edit-profile-interestings");
  var checkbox;
  var label;
  var br;
  for (i = 0; i < interestings.length; i++) {
    console.log(interestings[i].name);
    console.log(interestings[i].id);

    checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.name = "interestings";
    checkbox.value = interestings[i].name;
    checkbox.id = "interesting" + interestings[i].id;
    checkbox.style.marginTop = "6px";
    checkbox.style.cursor = "pointer";
    checkbox.className = "interestingsCheckbox";
    checkbox.onclick = function() {
      return ValidateInterestingsSelection("interestings");
    };

    label = document.createElement("label");
    label.htmlFor = interestings[i].id;
    label.appendChild(document.createTextNode(interestings[i].name));

    interestingsBox.appendChild(checkbox);
    interestingsBox.appendChild(label);
    var br = document.createElement("br");
    interestingsBox.appendChild(br);
  }
}
// id checkbox = "dietary{numer}
function setupDietaryPreferencesCheckBoxes(preferences) {
  console.log(preferences);
  console.log("test");

  console.log(preferences[0]);
  var preferencesBox = document.getElementById("edit-profile-dietary");
  var checkbox;
  var label;
  var br;
  for (i = 0; i < preferences.length; i++) {
    console.log(preferences[i].name);
    console.log(preferences[i].id);

    checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.name = "dietaryPreferences";
    checkbox.value = preferences[i].name;
    checkbox.id = "dietary" + preferences[i].id;
    checkbox.style.marginTop = "6px";
    checkbox.style.cursor = "pointer";
    checkbox.className = "dietaryPreferenceCheckbox";
    checkbox.onclick = function() {
      return ValidateInterestingsSelection("dietaryPreferences");
    };

    label = document.createElement("label");
    label.htmlFor = preferences[i].id;
    label.appendChild(document.createTextNode(preferences[i].name));

    preferencesBox.appendChild(checkbox);
    preferencesBox.appendChild(label);

    var br = document.createElement("br");
    preferencesBox.appendChild(br);
  }
}
function getCheckedInterestingCheckboxesId() {
  var checkedValue = [];
  var inputElements = document.getElementsByClassName("interestingsCheckbox");
  for (var i = 0; inputElements[i]; ++i) {
    if (inputElements[i].checked) {
      checkedValue.push(inputElements[i].id.substr(11));
    }
  }
  console.log("checked interestings: " + checkedValue);
  return checkedValue;
}
function getCheckedPreferenceCheckboxesId() {
  var checkedValue = [];
  var inputElements = document.getElementsByClassName(
    "dietaryPreferenceCheckbox"
  );
  for (var i = 0; inputElements[i]; ++i) {
    if (inputElements[i].checked) {
      checkedValue.push(inputElements[i].id.substr(7));
    }
  }
  console.log("checked Preferences: " + checkedValue);
  return checkedValue;
}
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
function createUpdateProfileJSON(informations, interestings, preferences) {
  var json = {};
  json.profileId = window.localStorage.getItem("profileId");
  json.userId = window.localStorage.getItem("userId"); //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  json.firstName = informations.get("firstName");
  json.lastName = informations.get("lastName");
  json.gender = informations.get("gender");
  json.age = informations.get("age");
  json.street = informations.get("street");
  var city = informations.get("city");
  var langLong = getCoordinates("" + city + ", " + street);
  json.langitude = langLong.langitude;
  json.longitude = langLong.longitude;

  console.log("create update: " + json.langitude);
  console.log("create update: " + json.longitude);
  var cityId = cityMap.get(informations.get("city"));
  //json.cityId = informations.get("city");
  console.log("CreateUpdateProfilJson cityId " + city + " " + cityId);
  json.cityId = cityId;

  json.interestings = interestings;
  json.dietary = preferences;
  console.log(json);

  console.log(JSON.stringify(json));
  return json;
}
async function getRequest(url) {
  const response = await fetch(url, {
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "X-Authorization":
        "Token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYW1pbGJyenlja2lAZ21haWwuY29tIiwidXNlcklkIjoiMzgifQ.xIni6vRkXPzPKurjqG1YIa5peXLRfu9kBRkTwLu5S2E"
    },
    redirect: "follow", // manual, *follow, error
    referrer: "no-referrer" // no-referrer, *client
  });
  return await response.json();
} // id checkbox = "interesting{numer}
//________________________________________---------------------------------------------_________________________________________________

async function updateProfileCoordinatesLatLong(city, street) {
  var responseJson = await getCoordinates(city, street);
  // setupDietaryPreferencesCheckBoxes(responseJson);
  // var json = {};
  // json.langitude = responseJson.langitude;
  // json.longitude = responseJson.longitude;
  // console.log("create update: " + json.langitude);
  // console.log("create update: " + json.longitude);

  //var responseJson = await updateInformations(json);
}

async function updateInformations(json) {
  console.log("POSZŁO");
  var responseJson = await updateRequest(
    "http://localhost:8080/api/profiles",
    json
  );

  // setupDietaryPreferencesCheckBoxes(responseJson);
}

async function getCoordinates(city, street) {
  var geocoder = new google.maps.Geocoder();
  var address = "" + city + ", " + street;

  geocoder.geocode(
    { address: address },
    await function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        var latitude = results[0].geometry.location.lat();
        var longitude = results[0].geometry.location.lng();
        console.log("lat: " + latitude + " long: " + longitude);

        var json = {};
        json.latitude = latitude;
        json.longitude = longitude;
        json.profileId = window.localStorage.getItem("profileId");
        json.userId = window.localStorage.getItem("userId");
        console.log("create update: " + json.langitude);
        console.log("create update: " + json.longitude);

        var responseJson = updateInformations(JSON.stringify(json));

        return { latitude: latitude, longitude: longitude };
        //alert("latitude: " + latitude + "\n longitude: " + longitude);
      }
    }
  );
}

async function updateRequest(url, json) {
  console.log("w metodzie postRequest");
  const response = await fetch(url, {
    method: "PUT", // *GET, POST, PUT, DELETE, etc.
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

  //return await response.json();
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
