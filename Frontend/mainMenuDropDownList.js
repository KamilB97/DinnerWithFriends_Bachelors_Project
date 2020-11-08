var participantsInAddContainer = new Array();
var participantsContainer = new Array();

class ProfileId {
  constructor(id) {
    this.profileId = id;
  }
  get getId() {
    return this.profileId;
  }
  set setId(id) {
    this.profileId = id;
  }
}

function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
    var a,
      b,
      i,
      val = this.value;
    /*close any already open lists of autocompleted values*/
    closeAllLists();
    if (!val) {
      return false;
    }
    currentFocus = -1;
    /*create a DIV element that will contain the items (values):*/
    a = document.createElement("DIV");
    a.setAttribute("id", this.id + "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");
    /*append the DIV element as a child of the autocomplete container:*/
    this.parentNode.appendChild(a);
    /*for each item in the array...*/
    for (i = 0; i < arr.length; i++) {
      /*check if the item starts with the same letters as the text field value:*/
      if (
        arr[i].name.substr(0, val.length).toUpperCase() == val.toUpperCase()
      ) {
        /*create a DIV element for each matching element:*/
        b = document.createElement("DIV");
        /*make the matching letters bold:*/
        b.innerHTML =
          "<strong>" + arr[i].name.substr(0, val.length) + "</strong>";
        b.innerHTML += arr[i].name.substr(val.length);
        /*insert a input field that will hold the current array item's value:*/
        b.innerHTML += "<input type='hidden' value='" + arr[i].name + "'>";
        /*execute a function when someone clicks on the item value (DIV element):*/
        b.addEventListener("click", function(e) {
          /*insert the value for the autocomplete text field:*/
          inp.value = this.getElementsByTagName("input")[0].value;
          /*close the list of autocompleted values,
                (or any other open lists of autocompleted values:*/
          closeAllLists();
        });
        a.appendChild(b);
      }
    }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
    var x = document.getElementById(this.id + "autocomplete-list");
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode == 40) {
      /*If the arrow DOWN key is pressed,
          increase the currentFocus variable:*/
      currentFocus++;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 38) {
      //up
      /*If the arrow UP key is pressed,
          decrease the currentFocus variable:*/
      currentFocus--;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 13) {
      /*If the ENTER key is pressed, prevent the form from being submitted,*/
      e.preventDefault();
      if (currentFocus > -1) {
        /*and simulate a click on the "active" item:*/
        if (x) x[currentFocus].click();
      }
    }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = x.length - 1;
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
      except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function(e) {
    closeAllLists(e.target);
  });
}
function addParticipantsToCustomConversation(id) {
  var participantsDiv = document.getElementById("addParticipants-div");
  var inputTxt = document.getElementById("myInput");
  var span = document.createElement("span");
  span.innerHTML = inputTxt.value;
  participantsDiv.appendChild(span);

  participantsInAddContainer.push(inputTxt.value);

  var br = document.createElement("br");
  participantsDiv.appendChild(br);
  participantsContainer.push(id);
  listContainer();
}

function addParticipantsToChatConversation(conversationId, profileId) {
  var participantsDiv = document.getElementById("addParticipantsChat-div");
  var inputTxt = document.getElementById("convParticipantInput");
  var span = document.createElement("span");
  span.innerHTML = inputTxt.value;
  participantsDiv.appendChild(span);

  participantsInAddContainer.push(inputTxt.value);

  var br = document.createElement("br");
  participantsDiv.appendChild(br);
  participantsContainer.push(profileId);
  listContainer();

  addParticipantToConversation(conversationId, profileId);
}
function addParticipantToConversation(conversationId, profileId) {
  var json = {};
  json.conversationId = conversationId;
  json.profileId = profileId;
  json.active = 2;
  console.log("ADD CONV JSON: " + JSON.stringify(json));

  postParticipantToConversation(JSON.stringify(json));
}

function listContainer() {
  for (var i = 0; i < participantsContainer.length; i++) {
    //console.log("participant: " + participantsInAddContainer[i]);
    console.log("participantsId: " + participantsContainer[i]);
  }
}
function initFriendsMap(friendsArray) {
  mapOfFriends;
}

function showAddConversationPanel() {
  var conversationPanel = document.getElementById("conversationDropdown");

  // var addParticipatnDiv = document.getElementById("addParticipants-div");

  // for (var i = 0; i < mapOfFriendsAndMatched.length; i++) {
  //   var participant = document.createElement("span");
  //   participant.innerHTML = mapOfFriendsAndMatched[i].name;
  //   partticipant.id = "fm" + mapOfFriendsAndMatched[i].id;

  //   addParticipatnDiv.appendChild(participant);
  // }

  if (
    conversationPanel.style.display == "none" ||
    conversationPanel.style.display == undefined
  ) {
    document.getElementById("conversation-scrollbox").style.display = "none";
    console.log("widać!");
    conversationPanel.style.display = "";
  } else if (conversationPanel.style.display == "") {
    document.getElementById("conversation-scrollbox").style.display = "";
    console.log("NIE widać!");
    var json = JSON.stringify(createCustomConversationJSON());
    createCustomConversation(json);
    conversationPanel.style.display = "none";
    clearCustomConversationBox();
  }
}

function createCustomConversationJSON() {
  var participantsArray = new Array();
  for (var i = 0; i < participantsContainer.length; i++) {
    participantsArray.push(new ProfileId(participantsContainer[i]));
  } // ####################@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$$$$$$$$$$$$%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@
  // participantsArray.push(
  //   new ProfileId(window.localStorage.getItem("profileId"))
  // );
  participantsArray.push(new ProfileId(38));
  console.log(participantsArray);

  var conversationName = document.getElementById("conversationName").value;
  console.log("conversation Name: " + conversationName);
  var customCreated = 1;
  var json = {};
  json.customConversation = {
    name: conversationName,
    customCreated: customCreated
  };
  json.participants = participantsArray;
  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@");
  console.log(JSON.stringify(json));

  return json;
}
function clearCustomConversationBox() {
  console.log("clearCustom method");
  var participantsDiv = document.getElementById("addParticipants-div");
  var convNameInput = document.getElementById("conversationName");
  convNameInput.value = "";
  participantsContainer = new Array();
  while (participantsDiv.hasChildNodes()) {
    console.log("usuwanie noda");
    participantsDiv.removeChild(participantsDiv.lastChild);
  }
}
// @@@@@@@@@@@@@@@@@@@@@  INVITATION DROPDOWN LIST  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
async function setupInvitationsDropdownList(invitationArray) {
  console.log("setupInvitationsDropdownList method");
  var invitationArrayLength = invitationArray.length;
  var invitationTemp;
  var invitationImg;
  var invitationSpan;

  var buttonsDiv;
  var acceptBtn;
  var denialBtn;

  var dropdownList = document.createElement("div");
  dropdownList.id = "myDropdown";
  dropdownList.className = "dropdown-content";
  dropdownList.style.visibility = "visible";
  document.getElementById("invitations-div").appendChild(dropdownList);

  for (i = 0; i < invitationArrayLength; i++) {
    //console.log("for loop: " + i);
    invitationTemp = document.createElement("div");
    invitationTemp.className = "topBarDropDown-entity";
    invitationTemp.id = "inv" + invitationArray[i].invitationId;
    invitationTemp.name = "inv" + invitationArray[i].profile.profileId;

    dropdownList.appendChild(invitationTemp);

    invitationImg = document.createElement("img");
    invitationImg.className = "dropdownInv-image"; //"dropdownProfile-image";
    if (invitationArray[i].profile.image != null) {
      invitationImg.src = invitationArray[i].profile.image; // = friendsArray[i].profile.image
    } else {
      invitationImg.src = "nophoto.jpg";
    }
    invitationTemp.appendChild(invitationImg);

    //console.log("after create invitationImg");
    invitationSpan = document.createElement("span");
    invitationSpan.className = "dropdownEntity-txt";
    invitationSpan.innerHTML =
      invitationArray[i].profile.name +
      " " +
      invitationArray[i].profile.surname;
    invitationTemp.appendChild(invitationSpan);
    //console.log("after create span");

    // @@@@@@@@@@@@@@@@@@@@@@@ dorobić reszte
    buttonsDiv = document.createElement("div");
    buttonsDiv.className = "accDen-div";
    buttonsDiv.id = "ibd" + invitationArray[i].invitationId; //ibd + invId//  - invbtndiv
    buttonsDiv.name = "ibd" + invitationArray[i].profile.profileId;
    invitationTemp.appendChild(buttonsDiv);

    denialBtn = document.createElement("img");
    denialBtn.className = "inv-btn denialInv-btn";
    denialBtn.id = "dinvbtn" + invitationArray[i].invitationId;
    denialBtn.src = "delete.png";
    denialBtn.addEventListener(
      "click",
      function() {
        denialInvitation(this.id.substr(7));
      },
      false
    );
    buttonsDiv.appendChild(denialBtn);

    acceptBtn = document.createElement("img");
    acceptBtn.className = "inv-btn acceptInv-btn";
    acceptBtn.id = "ainvbtn" + invitationArray[i].invitationId;
    acceptBtn.src = "accept.png";
    acceptBtn.addEventListener(
      "click",
      function() {
        console.log("przycisk:substr invId: " + this.id.substr(7));
        acceptInvitation(this.id.substr(7));
      },
      false
    );
    buttonsDiv.appendChild(acceptBtn);
  }
}

async function denialInvitation(invId) {
  document
    .getElementById("myDropdown")
    .removeChild(document.getElementById("inv" + invId));
  var responseJson = await getRequest(
    "http://localhost:8080/api/invitations/denial/" + invId
  );

  console.log("odmówiono inv o id: " + invId);
}

async function acceptInvitation(invId) {
  var responseJson = await getRequest(
    "http://localhost:8080/api/invitations/accept/" + invId
  );
  console.log("akceptowano inv o id: " + invId);
  // document
  //   .getElementById("myDropdown")
  //   .removeChild(document.getElementById("inv" + invId));
}

async function createCustomConversation(json) {
  var responseJson = await postRequest(
    "http://localhost:8080/api/conversations/custom",
    json
  );
  console.log("created Custom conv");
}

async function postParticipantToConversation(json) {
  var responseJson = await postRequest(
    "http://localhost:8080/api/conversations/participants",
    json
  );
  console.log("Added participant to CONV");
}

async function getInvitesList(profileId) {
  var responseJson = await getRequest(
    "http://localhost:8080/api/invitations/" + profileId
  );
  // console.log(JSON.stringify(responseJson));
  setupInvitationsDropdownList(responseJson);
}
function initInviteList() {
  console.log("initINV method");
  var profileId = window.localStorage.getItem("profileId");
  showInviteList(profileId);
  //getInvitesList(profileId);
}

function showInviteList(profileId) {
  console.log("showtInviteList method");
  var dropdownElement = document.getElementById("myDropdown");
  //console.log("DROPDOWN VISIBILITY: " + dropdownElement.style.visibility);
  if (
    dropdownElement != null &&
    dropdownElement.style.visibility != "hidden" &&
    dropdownElement.style.visibility != "" &&
    dropdownElement.style.visibility != "undefined"
  ) {
    //dropdownElement.style.visibility = "hidden";
    clearInviteList();
    console.log("Was visible is hidden");
  } else {
    //dropdownElement.style.visibility = "visible";
    console.log("Was hidden is visible");

    getInvitesList(profileId);
  }
}

function clearInviteList() {
  console.log("CLEAR INVITELSIT");
  document.getElementById("myDropdown").remove();
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
      "X-Authorization":
        "Token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYW1pbGJyenlja2lAZ21haWwuY29tIiwidXNlcklkIjoiMzgifQ.xIni6vRkXPzPKurjqG1YIa5peXLRfu9kBRkTwLu5S2E"
    },
    body: json,
    redirect: "follow", // manual, *follow, error
    referrer: "no-referrer" // no-referrer, *client
  });
  return await response.json();
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
}

function showSettingsDropbar() {
  console.log("showSettingsList method");
  var dropdownElement = document.getElementById("settingsDropdown");
  //console.log("DROPDOWN VISIBILITY: " + dropdownElement.style.visibility);
  if (
    dropdownElement != null &&
    dropdownElement.style.visibility != "hidden" &&
    dropdownElement.style.visibility != "" &&
    dropdownElement.style.visibility != "undefined"
  ) {
    dropdownElement.style.visibility = "hidden";
    console.log("Was visible is hidden");
  } else {
    dropdownElement.style.visibility = "visible";
    console.log("Was hidden is visible");
  }
}
