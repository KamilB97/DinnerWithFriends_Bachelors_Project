window.onload = function loadContIcons() {
  var profileId = window.localStorage.getItem("profileId");
  setupProposedArray(profileId);
  //loadProposedProfileToScreen();
};
var status = 0; // 0 = pierwsze odpalenie matchowania
var proposedProfilesArray = new Array(); // ładuje 5 gdy użytkownik załaduje piątą osobę pobieram kolejne
var interestingsArray = new Array();
var dietaryPreferencesArray = new Array();

// oraz usuwam dwie poprzednie // w zasadzie moge na bieżąco usuwać
// po załadowaniu drugiej usuwam 1 // tylko jakoś muszę to zliczać

console.log(" pod proposed ");
console.log(proposedProfilesArray);
class ProposedProfile {
  constructor(
    profileId,
    firstName,
    lastName,
    age,
    gender,
    cityName,
    about,
    image
  ) {
    this._profileId = profileId;
    this._firstName = firstName;
    this._lastName = lastName;
    this._age = age;
    this._gender = gender;
    this._cityName = cityName;
    this._about = about;
    this._image = image;
    this._listOfInterestings = [];
    this._listOfDietaryPreferences = [];
  }

  get profileId() {
    return this._profileId;
  }
  get firstName() {
    return this._firstName;
  }
  get lastName() {
    return this._lastName;
  }
  get age() {
    return this._age;
  }
  get gender() {
    return this._gender;
  }
  get cityName() {
    return this._cityName;
  }
  get about() {
    return this._about;
  }
  get image() {
    return this._image;
  }
  set listOfInterestings(interesting) {
    this._listOfInterestings = interesting;
  }
  set listOfDietaryPreferences(dietary) {
    this._listOfDietaryPreferences = dietary;
  }
  get listOfInterestings() {
    return this._listOfInterestings;
  }
  get listOfDietaryPreferences() {
    return this._listOfDietaryPreferences;
  }
  addInteresting(interesting) {
    this._listOfInterestings.push(interesting);
  }
  addDietaryPreference(dietary) {
    this._listOfDietaryPreferences.push(dietary);
  }
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
  var informationsBox = document.getElementById("informations-profile");

  // nameSpan = document.createElement("span");
  var nameSpan = document.getElementById("name-span");
  //nameSpan.className = 'leftbar-entity';
  //nameSpan.id = "name-span";
  nameSpan.innerHTML = firstName + " " + lastName;
  // informationsBox.appendChild(nameSpan);

  // br = document.createElement("br");
  //informationsBox.appendChild(br);
  var ageSpan = document.getElementById("age-span");
  //ageSpan = document.createElement("span");
  //ageSpan.id = "age-span";
  ageSpan.innerHTML = age;
  //informationsBox.appendChild(ageSpan);

  //br = document.createElement("br");
  //informationsBox.appendChild(br);

  //genderSpan = document.createElement("span");
  // genderSpan.id = "gender-span";
  var genderSpan = document.getElementById("gender-span");
  genderSpan.innerHTML = gender;
  //informationsBox.appendChild(genderSpan);

  //br = document.createElement("br");
  //informationsBox.appendChild(br);
  var citySpan = document.getElementById("city-span");
  //citySpan = document.createElement("span");
  //citySpan.id = "city-span";
  citySpan.innerHTML = city;
  //informationsBox.appendChild(citySpan);
}

async function setupProfileInterestings(interestings) {
  if (document.getElementById("interestings-div") != null) {
    clearInterestings();
  }
  var bottomBodyDiv = document.getElementById("bottom-body");
  var interestingsDiv;
  var interestingsTitleBox;
  var interestingsTitle;
  var interestingsBody;

  interestingsDiv = document.createElement("div");
  interestingsDiv.id = "interestings-div"; // to usuwać
  bottomBodyDiv.appendChild(interestingsDiv);

  interestingsTitleBox = document.createElement("div");
  interestingsTitleBox.id = "interestings-titlebox";
  interestingsTitleBox.className = "titlebox";
  interestingsDiv.appendChild(interestingsTitleBox);

  interestingsTitle = document.createElement("span");
  interestingsTitle.id = "interestings-title";
  interestingsTitle.className = "title";
  interestingsTitle.innerHTML = "Zainteresowania:";
  interestingsTitleBox.appendChild(interestingsTitle);

  interestingsBody = document.createElement("div");
  interestingsBody.id = "interestings-body";
  interestingsBody.className = "profilePageBody";
  interestingsDiv.appendChild(interestingsBody);

  var interestingsTempl;
  var interestingsBox = document.getElementById("interestings-div");
  for (i = 0; i < interestings.length; i++) {
    interestingsTempl = document.createElement("span");
    interestingsTempl.id = "interesting" + interestings[i].id; //11 interesting
    interestingsTempl.innerHTML = interestings[i].name;
    interestingsBody.appendChild(interestingsTempl);
    br = document.createElement("br");
    interestingsBody.appendChild(br);
    //interestingsArray.push(interestingsTempl.id);
  }
}

async function setupProfilePreferences(preferences) {
  if (document.getElementById("dietary-div") != null) {
    clearDietaryPreferences();
  }
  var bottomBodyDiv = document.getElementById("bottom-body");
  var DietaryPreferencesDiv;
  var DietaryPreferencesTitleBox;
  var DietaryTitle;
  var dietaryTempl;
  var dietaryBody;

  DietaryPreferencesDiv = document.createElement("div");
  DietaryPreferencesDiv.id = "dietary-div"; // to usuwać
  bottomBodyDiv.appendChild(DietaryPreferencesDiv);

  DietaryPreferencesTitleBox = document.createElement("div");
  DietaryPreferencesTitleBox.id = "dietary-titlebox";
  DietaryPreferencesTitleBox.className = "titlebox";
  DietaryPreferencesDiv.appendChild(DietaryPreferencesTitleBox);

  DietaryTitle = document.createElement("span");
  DietaryTitle.id = "dietary-title";
  DietaryTitle.className = "title";
  DietaryTitle.innerHTML = "Zainteresowania:";
  DietaryPreferencesTitleBox.appendChild(DietaryTitle);

  dietaryBody = document.createElement("div");
  dietaryBody.id = "dietary-body";
  dietaryBody.className = "profilePageBody";
  DietaryPreferencesDiv.appendChild(dietaryBody);

  var dietaryBox = document.getElementById("dietary-div");
  for (i = 0; i < preferences.length; i++) {
    dietaryTempl = document.createElement("span");
    dietaryTempl.id = "dietary" + preferences[i].id; // 7 dietary
    dietaryTempl.innerHTML = preferences[i].name;
    dietaryBody.appendChild(dietaryTempl);
    br = document.createElement("br");
    dietaryBody.appendChild(br);
    // dietaryPreferencesArray.push(dietaryTempl.id);
  }
}
function clearInterestings() {
  var interestingsBox = document.getElementById("interestings-div");
  interestingsBox.remove();
  // if (interestingsBox.children.length > 1) {
  //   console.log("interestings ma children");
  //   for (i = 0; i < interestingsArray.length; i++) {
  //     var child = document.getElementById(interestingsArray[i]);
  //     console.log(interestingsArray[i]);
  //     // interestingsBox.parentNode.removeChild(child);
  //     child.remove();
  //   }
  // } else {
  //   console.log("nie ma dzieci");
  // }
}
function clearDietaryPreferences() {
  var dietaryBox = document.getElementById("dietary-div");
  dietaryBox.remove();
  // if (dietaryBox.children.length > 1) {
  //   console.log("dietary ma children");
  //   for (i = 0; i < dietaryPreferencesArray.length; i++) {
  //     var child = document.getElementById(dietaryPreferencesArray[i]);
  //     console.log(dietaryPreferencesArray[i]);
  //     child.remove();
  //     //  dietaryBox.parentNode.removeChild(child);
  //   }
  // } else {
  //   console.log("nie ma dzieci");
  // }
}

async function setupProfileAboutNote(about) {
  var topBody = document.getElementById("top-body");

  //var aboutDiv = document.createElement("div");
  var aboutDiv = document.getElementById("about-profile");
  // aboutDiv.id = "about-profile"; //11 interesting
  //aboutDiv.className = "box";
  // topBody.appendChild(aboutDiv);

  //var aboutTitle = document.createElement("span");
  //aboutTitle.id = "about-title";
  //aboutTitle.innerHTML = "O sobie";
  //aboutDiv.appendChild(aboutTitle);
  // var br = document.createElement("br");
  //aboutDiv.appendChild(br);

  //var textArea = document.createElement("textarea");
  var textArea = document.getElementById("about-textarea");
  //textArea.readOnly = true;
  //textArea.id = "about-textarea";
  textArea.innerHTML = about;
  //aboutDiv.appendChild(textArea);
}
async function setupProfileImage(imageUrl) {
  console.log("W metodzie setupProfileImage");
  var topBody = document.getElementById("top-body");
  var newImage = document.createElement("img");
  console.log(imageUrl);

  if (imageUrl != null && imageUrl != undefined) {
    newImage.src = imageUrl;
  } else {
    newImage.src = "nophoto.jpg";
  }

  newImage.id = "profile-img";
  topBody.appendChild(newImage);

  //var profileImage = document.getElementById("profile-img");
  //profileImage.setAttribute("src", imageUrl);
  console.log("po set Atribute");
}
async function clearProfileImage() {
  var image = document.getElementById("profile-img");
  image.remove();
}

function loadProposedProfileToScreen() {
  setupProfileInformations(
    proposedProfilesArray[0].firstName,
    proposedProfilesArray[0].lastName,
    proposedProfilesArray[0].age,
    proposedProfilesArray[0].gender,
    proposedProfilesArray[0].cityName
  );
  console.log("przed wywołaniem setupProfileImage");
  console.log(proposedProfilesArray[0].image);
  setupProfileInterestings(proposedProfilesArray[0].listOfInterestings);
  setupProfilePreferences(proposedProfilesArray[0].listOfDietaryPreferences);
  setupProfileAboutNote(proposedProfilesArray[0].about);

  setupProfileImage(proposedProfilesArray[0].image);
}

async function setupProposedArray(profileId) {
  var json = await getRequest(
    "http://localhost:8080/api/matcher/candidates/" + profileId
  );

  console.log("Profile informations");
  console.log(JSON.stringify(json));

  //proposedProfilesArray
  console.log(json[0]);
  console.log(json[1]);

  json.forEach(element => {
    var proposedProfile = new ProposedProfile(
      element.profileId,
      element.firstName,
      element.lastName,
      element.age,
      element.gender,
      element.cityName,
      element.about,
      element.image
    );
    console.log(
      "proposedProfile image element: " +
        proposedProfile.firstName +
        " " +
        proposedProfile.lastName
    );
    console.log(proposedProfile.image);
    proposedProfile.listOfInterestings = element.listOfInterestings;
    proposedProfile.listOfDietaryPreferences = element.listOfDietaryPreferences;

    // console.log("profileId " + proposedProfile.profileId);
    // console.log(proposedProfile.age);
    // console.log(proposedProfile.cityName);
    // console.log(proposedProfile.firstName);
    // console.log(proposedProfile.lastName);
    // console.log(proposedProfile.gender);
    // console.log(proposedProfile.about);

    // proposedProfile.listOfInterestings.forEach(item => {
    //   console.log("name " + item.name);
    //   console.log("id " + item.id);
    // });
    // proposedProfile.listOfDietaryPreferences.forEach(item => {
    //   console.log("name " + item.name);
    //   console.log("id " + item.id);
    // });
    proposedProfilesArray.push(proposedProfile);
  });

  //console.log(proposedProfilesArray);

  if (status == 0) {
    loadProposedProfileToScreen();
    status = 1;
  }
}

function interestedButtonEvent() {
  var profileId = window.localStorage.getItem("profileId");
  var targetId = proposedProfilesArray[0].profileId;
  var liked = 2;
  console.log(profileId + " " + targetId + " " + liked);
  updateSwipeInformations(profileId, targetId, liked);

  deleteActuallProposedProfile();
  loadProposedProfileToScreen();
  clearProfileImage();
  if (proposedProfilesArray.length == 1) {
    setupProposedArray(profileId);
  }
}

function notInterestedButtonEvent() {
  var profileId = window.localStorage.getItem("profileId");
  var targetId = proposedProfilesArray[0].profileId;
  var notLiked = 1;
  console.log(profileId + " " + targetId + " " + notLiked);
  updateSwipeInformations(profileId, targetId, notLiked);

  deleteActuallProposedProfile();
  loadProposedProfileToScreen();
  clearProfileImage();

  if (proposedProfilesArray.length == 1) {
    setupProposedArray(profileId);
  }
}

function deleteActuallProposedProfile() {
  proposedProfilesArray.shift();
}

async function updateSwipeInformations(profileId, targetId, liked) {
  // liked = 2 oznacza ze zainteresowany
  console.log(
    "http://localhost:8080/api/matcher/updateswipe/" +
      profileId +
      "/" +
      targetId +
      "/" +
      profileId +
      "/" +
      liked
  );
  var responseJson = await getRequest(
    "http://localhost:8080/api/matcher/updateswipe/" +
      profileId +
      "/" +
      targetId +
      "/" +
      profileId +
      "/" +
      liked
  );

  // setupDietaryPreferencesCheckBoxes(responseJson);
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
}
