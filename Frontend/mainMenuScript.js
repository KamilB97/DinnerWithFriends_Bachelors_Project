var actuallFirstFriendEntity;
var actuallFirstMatchedEntity;
var actuallFirstConversationEntity;
var actuallMessageIds = new Array();
var actuallConversationId = undefined;
var previousButton = undefined;
var previousButtonTag = undefined;
var actualConversationName = undefined;
var mapOfFriendsAndMatched = new Array();
var friendsMap = new Map();
var matchedPersonInActualConversation = undefined;
const interval = setInterval(function() {
  if (actuallConversationId != undefined) {
    console.log("W aktualnej konwersacji: " + actuallConversationId);
    var profileId = window.localStorage.getItem("profileId");
    var check = checkForMessages(actuallConversationId, profileId);
    console.log("check val: " + check);
  } else {
    console.log("nie ma otwartej konwersacji");
  }
}, 2000);

const undefinedInterval = setInterval(function() {
  console.log("W aktualnej sprawdzam unreaded message For all conv");
  var profileId = window.localStorage.getItem("profileId");
  var check = getUnreadConversations(profileId);
  // console.log("check val: " + check);
  // console.log("nie ma otwartej konwersacji");
}, 5000);

//clearInterval(interval);

window.onload = function() {
  document.getElementById("conversationDropdown").style.display = "none";
  var profileId = window.localStorage.getItem("profileId");
  //loadMessagesForConversation(13);
  getFriendList(profileId);
  getMatchedList(profileId);
  getConversationList(profileId);

  var chatBody = document.getElementById("chat-body");
  chatBody.setAttribute("style", "overflow:auto;height:77.5%;width:956px");
};
class Friend {
  constructor(firstName, lastName, id) {
    this._name = firstName + " " + lastName;
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

async function setupFriendsLeftSideList(friendsArray) {
  var friendsArrayLength = friendsArray.length;

  console.log("friends array lengh" + friendsArrayLength);
  console.log(friendsArray);
  console.log(friendsArray[0]);
  console.log(friendsArray[0].friendshipId);
  //%%%%%%%%%%%%%%%%%%%%%%%%
  var myFunctionReference = function(value) {
    console.log("clicked friends leftPanel - conversation: " + value);
  };

  var friendsTemp;
  var friendsImg;
  var friendsSpan;
  var friendBoxLenght = 666;

  console.log(friendsArrayLength * 50 - 22);
  if (friendsArrayLength * 50 > 344) {
    console.log("inside if statement");
    friendsBoxLenght = 333;
    document
      .getElementById("friends-scrollbox")
      .setAttribute("style", "overflow:auto;height:196px;width:250px");
  } else {
    document
      .getElementById("friends-scrollbox")
      .setAttribute("style", "overflow:auto;height:610px;width:250px");
  }
  // id zaczynają się od fr potem conversationId				//friendshipId
  for (i = 0; i < friendsArrayLength; i++) {
    console.log("for loop: " + i);
    friendsTemp = document.createElement("div");
    friendsTemp.className = "leftbar-entity";
    friendsTemp.id = "fr" + friendsArray[i].conversationId;
    if (i == 0) {
      actuallFirstFriendEntity = "fr" + friendsArray[i].conversationId;
    }

    var friend = new Friend(
      friendsArray[i].profile.name,
      friendsArray[i].profile.surname,
      friendsArray[i].profile.profileId
    );

    mapOfFriendsAndMatched.push(friend);
    friendsMap.set(
      friendsArray[i].profile.name + " " + friendsArray[i].profile.surname,
      friendsArray[i].profile.profileId
    );

    //friendsTemp

    console.log("printuje friendsId: " + "fr" + friendsArray[i].conversationId); //- git
    document.getElementById("friends-box").appendChild(friendsTemp);
    console.log("after create container");
    friendsImg = document.createElement("img");
    friendsImg.className = "dropdownProfile-image";
    friendsImg.id = "frimg" + friendsArray[i].profile.profileId;
    friendsImg.onclick = function() {
      window.localStorage.setItem("checkUserProfile", this.id.substring(5));
      location.replace("profilePage.html");
    };
    if (friendsArray[i].profile.image != null) {
      friendsImg.src = friendsArray[i].profile.image; // = friendsArray[i].profile.image
    } else {
      friendsImg.src = "nophoto.jpg";
    }
    document
      .getElementById("fr" + friendsArray[i].conversationId)
      .appendChild(friendsImg);

    console.log("after create friendsImg");
    friendsSpan = document.createElement("span");
    friendsSpan.className = "entity-txt";
    friendsSpan.id = "frspn" + friendsArray[i].profile.profileId;
    friendsSpan.innerHTML =
      friendsArray[i].profile.name + " " + friendsArray[i].profile.surname;
    //friendsSpan.addEventListener('click', myFunctionReference , false);
    document
      .getElementById("fr" + friendsArray[i].conversationId)
      .appendChild(friendsSpan);

    friendsSpan.onclick = function() {
      window.localStorage.setItem("checkUserProfile", this.id.substring(5));
      location.replace("profilePage.html");
    };

    console.log("after create span");

    var messageImage = document.createElement("img");
    messageImage.id = "frmsg" + friendsArray[i].conversationId;
    messageImage.className = "leftbar-msg-btn";
    messageImage.src = "chat_message_image.png";
    document
      .getElementById("fr" + friendsArray[i].conversationId)
      .appendChild(messageImage);

    messageImage.addEventListener(
      "click",
      function() {
        clearMessages();
        //document.getElementById("fr" + (this.id).substring(2)).focus;
        //actuallConversationId = this.id.substring(2);
        console.log(
          "eventListenerMatched - actual conversationId: " +
            actuallConversationId
        );
        previousButton = document.getElementById(
          "frmsg" + actuallConversationId
        );

        if (
          previousButton == this &&
          document.getElementById("middlebar").style.visibility == "visible"
        ) {
          console.log("ZAMKNIJ KONWESACJE!!!!");
          document.getElementById("middlebar").style.visibility = "hidden";
          document.getElementById("chat-bottom").style.visibility = "hidden";
          this.src = "chat_message_image.png";
        } else {
          if (actuallConversationId != undefined) {
            if (previousButtonTag == "fr") {
              previousButton = document.getElementById(
                "frmsg" + actuallConversationId
              );
              previousButton.src = "chat_message_image.png";
            } else if (previousButtonTag == "ma") {
              previousButton = document.getElementById(
                "mamsg" + actuallConversationId
              );
              previousButton.src = "chat_message_image.png";
            } else if (previousButtonTag == "cv") {
              previousButton = document.getElementById(
                "cv" + actuallConversationId
              );
              previousButton.style.backgroundColor = "#e7ece7";
            }
          }
          if (document.getElementById("participantsBar") != null) {
            document
              .getElementById("middlebar")
              .removeChild(document.getElementById("participantsBar"));
            var chatBody = document.getElementById("chat-body");
            chatBody.setAttribute(
              "style",
              "overflow:auto;height:77.5%;width:956px"
            );
          }

          previousButtonTag = "fr";
          this.src = "chat_message_image_green.png";
          myFunctionReference(this.id);
          loadMessagesForConversation(this.id.substring(5));
          getConversationMapCordsForActuallConversation();
        }
      },
      false
    );
  }
  window.localStorage.setItem(
    "friendList",
    JSON.stringify(mapOfFriendsAndMatched)
  );
}
// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
async function setupMatchedLeftSideList(matchedArray) {
  var myFunctionReference = function(value) {
    console.log("clicked friends leftPanel - conversation: " + value);
  };

  var matchedArrayLength = matchedArray.length;
  var matchedTemp;
  var matchedImg;
  var matchedSpan;
  //var friendBoxLenght= 640;
  //console.log(matchedArrayLength * 50 -22);
  if (matchedArrayLength * 50 > 344) {
    console.log("inside if statement");
    friendsBoxLenght = 320;
    document
      .getElementById("matched-scrollbox")
      .setAttribute("style", "overflow:auto;height:196px;width:250px");
  } else {
    document
      .getElementById("matched-scrollbox")
      .setAttribute("style", "overflow:auto;height:638px;width:250px");
  }
  // id zaczyna się od "ma" a potem jest profile1 id +"id" + "_" + profile 2
  for (i = 0; i < matchedArrayLength; i++) {
    console.log("for loop: " + i);
    matchedTemp = document.createElement("div");
    matchedTemp.className = "leftbar-entity";
    matchedTemp.id = "ma" + matchedArray[i].conversationId;
    matchedTemp.name = "ma" + matchedArray[i].profile.profileId;
    if (i == 0) {
      actuallFirstMatchedEntity = "ma" + matchedArray[i].conversationId;
    }
    document.getElementById("matched-box").appendChild(matchedTemp);

    console.log("after create container");

    matchedImg = document.createElement("img");
    matchedImg.id = "maimg" + matchedArray[i].profile.profileId;
    matchedImg.className = "dropdownProfile-image";
    if (matchedArray[i].profile.image != null) {
      matchedImg.src = matchedArray[i].profile.image; // = friendsArray[i].profile.image
    } else {
      matchedImg.src = "nophoto.jpg";
    }
    document
      .getElementById("ma" + matchedArray[i].conversationId)
      .appendChild(matchedImg);

    matchedImg.onclick = function() {
      window.localStorage.setItem("checkUserProfile", this.id.substring(5));
      location.replace("profilePage.html");
    };

    console.log("after create matchedImg");
    matchedSpan = document.createElement("span");
    matchedSpan.id = "maspn" + matchedArray[i].profile.profileId;
    matchedSpan.className = "entity-txt";
    matchedSpan.innerHTML =
      matchedArray[i].profile.name + " " + matchedArray[i].profile.surname;
    document
      .getElementById("ma" + matchedArray[i].conversationId)
      .appendChild(matchedSpan);
    console.log("after create span");

    matchedSpan.onclick = function() {
      window.localStorage.setItem("checkUserProfile", this.id.substring(5));
      location.replace("profilePage.html");
    };

    var messageImage = document.createElement("img");
    messageImage.id = "mamsg" + matchedArray[i].conversationId;
    messageImage.className = "leftbar-msg-btn";
    messageImage.src = "chat_message_image.png";
    document
      .getElementById("ma" + matchedArray[i].conversationId)
      .appendChild(messageImage);

    messageImage.addEventListener(
      "click",
      function() {
        clearMessages();
        //document.getElementById("fr" + (this.id).substring(2)).focus;
        //actuallConversationId = this.id.substring(2);
        console.log(
          "eventListenerMatched - actual conversationId: " +
            actuallConversationId
        );
        previousButton = document.getElementById(
          "mamsg" + actuallConversationId
        );
        if (
          previousButton == this &&
          document.getElementById("middlebar").style.visibility == "visible"
        ) {
          console.log("ZAMKNIJ KONWESACJE!!!!");
          document.getElementById("middlebar").style.visibility = "hidden";
          document.getElementById("chat-bottom").style.visibility = "hidden";
          this.src = "chat_message_image.png";
        } else {
          if (actuallConversationId != undefined) {
            if (previousButtonTag == "fr") {
              previousButton = document.getElementById(
                "frmsg" + actuallConversationId
              );
              previousButton.src = "chat_message_image.png";
            } else if (previousButtonTag == "ma") {
              previousButton = document.getElementById(
                "mamsg" + actuallConversationId
              );
              previousButton.src = "chat_message_image.png";
            } else if (previousButtonTag == "cv") {
              previousButton = document.getElementById(
                "cv" + actuallConversationId
              );
              previousButton.style.backgroundColor = "#e7ece7";
            }
          }
          if (document.getElementById("participantsBar") != null) {
            document
              .getElementById("middlebar")
              .removeChild(document.getElementById("participantsBar"));
            var chatBody = document.getElementById("chat-body");
            chatBody.setAttribute(
              "style",
              "overflow:auto;height:77.5%;width:956px"
            );
          }

          previousButtonTag = "ma";
          this.src = "chat_message_image_green.png";
          myFunctionReference(this.id);
          loadMessagesForConversation(this.id.substring(5));
          getConversationMapCordsForActuallConversation();
        }
      },
      false
    );
  }
}

// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
async function setupConversationLeftSideList(conversationsArray) {
  var myFunctionReference = function(value) {
    console.log("clicked friends leftPanel - conversation: " + value);
  };
  var conversationsArrayLength = conversationsArray.length;
  var conversationTemp;
  var conversationSpan;

  if (conversationsArrayLength * 50 > 344) {
    console.log("inside if statement");
    friendsBoxLenght = 320;
    document
      .getElementById("conversation-scrollbox")
      .setAttribute("style", "overflow:auto;height:170px;width:250px");
  } else {
    document
      .getElementById("conversation-scrollbox ")
      .setAttribute("style", "overflow:auto;height:638px;width:250px");
  }
  for (i = 0; i < conversationsArrayLength; i++) {
    console.log("for loop: " + i);
    conversationTemp = document.createElement("div");
    conversationTemp.className = "leftbar-entity";
    conversationTemp.id = "cv" + conversationsArray[i].id;
    conversationTemp.name = "cv" + conversationsArray[i].id;
    if (i == 0) {
      actuallFirstConversationEntity = "cv" + conversationsArray[i].id;
    }

    //conversationTemp

    document.getElementById("conversation-box").appendChild(conversationTemp);

    // console.log("after create container");
    // matchedImg = document.createElement("div");
    // matchedImg.className = "profile-image";
    // document
    //   .getElementById("ma" + matchedArray[i].conversationId)
    //   .appendChild(matchedImg);

    //console.log("after create matchedImg");

    console.log("@$$@$@$@$@$@$@$@$@$@$");
    console.log(conversationsArray[0]);
    conversationSpan = document.createElement("span");
    conversationSpan.id = "cvname" + conversationsArray[i].id;
    conversationSpan.className = "leftbar-conversation-name";
    conversationSpan.innerHTML = conversationsArray[i].name;
    document
      .getElementById("cv" + conversationsArray[i].id)
      .appendChild(conversationSpan);

    conversationSpan.addEventListener(
      "click",
      function() {
        clearMessages();
        // actuallConversationId = this.id.substring(2);
        console.log(
          "eventListenerMatched - actual conversationId: " +
            actuallConversationId
        );

        previousButton = document.getElementById("cv" + actuallConversationId);
        if (actuallConversationId != undefined) {
          if (previousButtonTag == "fr") {
            previousButton = document.getElementById(
              "frmsg" + actuallConversationId
            );
            previousButton.src = "chat_message_image.png";
          } else if (previousButtonTag == "ma") {
            previousButton = document.getElementById(
              "mamsg" + actuallConversationId
            );
            previousButton.src = "chat_message_image.png";
          } else if (previousButtonTag == "cv") {
            previousButton = document.getElementById(
              "cv" + actuallConversationId
            );
            previousButton.style.backgroundColor = "#e7ece7";
          }
        }
        previousButtonTag = "cv";

        document.getElementById(
          "cv" + this.id.substring(6)
        ).style.backgroundColor = "#b5b8b5";
        //  idOfActuallMatchedConversationPerson = this.name; // @@@@@@@@@@@@@@@@@ ????? @@@@@@@@@@

        actualConversationName = this.innerHTML;
        console.log("CONVERSATION substrID: " + this.id.substring(6));

        if (document.getElementById("participantsBar") != null) {
          document
            .getElementById("middlebar")
            .removeChild(document.getElementById("participantsBar"));
        }

        loadMessagesForConversation(this.id.substring(6));
        getConversationMapCordsForActuallConversation();
      },
      false
    );

    console.log("after create span");
  }
}

function getActualConversationId() {
  return actuallConversationId;
}
async function getConversationMapCordsForActuallConversation() {
  console.log("@@@@@@@@@@@@ actuall conv id: " + actuallConversationId);
  var conversationId = actuallConversationId;

  var responseJson = await newGetRequest(
    "http://localhost:8080/api/conversations/participants/longlat/" +
      conversationId
  );
  window.localStorage.setItem("latitude", responseJson.latitude);
  window.localStorage.setItem("longitude", responseJson.longitude);
  window.localStorage.setItem("dietaryName", responseJson.dietaryName);
  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
  console.log(window.localStorage.getItem("latitude"));
  console.log(window.localStorage.getItem("longitude"));
  console.log(window.localStorage.getItem("dietaryName"));
  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
}

async function setupMessages(messageArray) {
  var messageArrayLength = messageArray.length;
  var possitionTemp;
  var messageTemp;
  //var matchedImg;
  var messageSenderSpan;
  var messageDateSpan;
  var messageSpan;
  var br;

  actuallMessageIds = new Array();
  console.log("CONVERSATION ID VARIABLE VALUE: " + actuallConversationId);
  if (document.getElementById("middlebar").style.visibility != "visible") {
    document.getElementById("middlebar").style.visibility = "visible";
    document.getElementById("chat-bottom").style.visibility = "visible";
  }
  clearHeader();

  if (previousButtonTag == "ma") {
    var addFriendButton = document.getElementById("addFriend-btn");
    addFriendButton.style.display = "";
    var addToConv = document.getElementById("addToConv-btn");
    addToConv.style.display = "none";
    setupHeaderParticipants(actuallConversationId);
    //var headerName = new Friend(); // **************************************************************************
  }
  if (previousButtonTag == "fr") {
    var addFriendButton = document.getElementById("addFriend-btn");
    addFriendButton.style.display = "none";
    var addToConv = document.getElementById("addToConv-btn");
    addToConv.style.display = "none";

    setupHeaderParticipants(actuallConversationId);
  }
  if (previousButtonTag == "cv") {
    var chatBody = document.getElementById("chat-body");
    chatBody.setAttribute("style", "overflow:auto;height:77.5%;width:776px");

    var addFriendButton = document.getElementById("addFriend-btn");
    addFriendButton.style.display = "none";
    var addToConv = document.getElementById("addToConv-btn");
    addToConv.style.display = "";

    setupHeaderName(actualConversationName);
    setupConvBarParticipants(actuallConversationId);

    var convParticipantsBar = document.createElement("div");
    convParticipantsBar.id = "participantsBar";
    document.getElementById("middlebar").appendChild(convParticipantsBar);
  }

  for (i = 0; i < messageArrayLength; i++) {
    // TEST console.log("for loop: " + i);

    actuallMessageIds.push(messageArray[i].messageInfo.messageId);
    // TEST console.log("actualMessageIds from array for i=" + i + " : " + actuallMessageIds[i]);
    possitionTemp = document.createElement("div");
    possitionTemp.className = "position-div";
    possitionTemp.id =
      "positionMessage" + messageArray[i].messageInfo.messageId;

    if (
      messageArray[i].profile.profileId ==
      window.localStorage.getItem("profileId")
    ) {
      // possitionTemp.style.cssFloat = "right";
      //possitionTemp.style.marginLeft = "50px";
    }
    document.getElementById("chat-body").appendChild(possitionTemp);

    messageTemp = document.createElement("div");
    if (
      messageArray[i].profile.profileId ==
      window.localStorage.getItem("profileId")
    ) {
      messageTemp.className = "message-box-this";
    } else {
      messageTemp.className = "message-box";
    }
    messageTemp.id = "message" + messageArray[i].messageInfo.messageId;

    document
      .getElementById("positionMessage" + messageArray[i].messageInfo.messageId)
      .appendChild(messageTemp);

    /*console.log("after create container");
		matchedImg = document.createElement('div');
		matchedImg.className = 'profile-image';
		document.getElementById("ma" + matchedArray[i].conversationId).appendChild(matchedImg); */

    //console.log("after create message-box");
    messageSenderSpan = document.createElement("span");
    messageSenderSpan.className = "message-sender";
    messageSenderSpan.id =
      "messageSender" + messageArray[i].messageInfo.messageId;
    messageSenderSpan.name =
      "messageSender" + messageArray[i].profile.profileId;
    messageSenderSpan.innerHTML =
      messageArray[i].profile.name + " " + messageArray[i].profile.surname;
    document
      .getElementById("message" + messageArray[i].messageInfo.messageId)
      .appendChild(messageSenderSpan);
    if (
      messageArray[i].profile.profileId !=
      window.localStorage.getItem("profileId")
    ) {
      messageSenderSpan.onclick = function() {
        window.localStorage.setItem(
          "checkUserProfile",
          this.name.substring(13)
        );
        location.replace("profilePage.html");
      };

      messageSenderSpan.onmouseover = function() {
        this.style.color = "#2c6d24";
        this.style.cursor = "pointer";
      };
      messageSenderSpan.onmouseleave = function() {
        this.style.color = "black";
      };
    }

    //console.log("after create message-sender");

    //console.log("after create message-box");
    messageDateSpan = document.createElement("span");
    messageDateSpan.className = "message-date";
    messageDateSpan.id = "messageDate" + messageArray[i].messageInfo.messageId;
    messageDateSpan.innerHTML = messageArray[i].messageInfo.date.substr(11);
    document
      .getElementById("message" + messageArray[i].messageInfo.messageId)
      .appendChild(messageDateSpan);

    //console.log("after create messageDate");

    br = document.createElement("br");
    br.id = "messageBr" + messageArray[i].messageInfo.messageId;
    document
      .getElementById("message" + messageArray[i].messageInfo.messageId)
      .appendChild(br);
    //console.log("after br");

    messageSpan = document.createElement("span");
    messageSpan.className = "message";
    messageSpan.id = "messageSpan" + messageArray[i].messageInfo.messageId;
    messageSpan.innerHTML = messageArray[i].messageInfo.messageTxt;
    document
      .getElementById("message" + messageArray[i].messageInfo.messageId)
      .appendChild(messageSpan);
    //console.log("after create message");

    /* br = document.createElement('br');
		document.getElementById("messageSender" + messageArray[i].messageInfo.messageId).appendChild(br);
		console.log("after br") */
  }
  var chatBody = document.getElementById("chat-body");
  chatBody.scrollTop = chatBody.scrollHeight;

  // chat-body i chat-bottom
}

function setupChatHeader(headerFulfillment) {
  var chatHeader = document.getElementById("chat-header");

  if (previousButtonTag == "ma" || previousButtonTag == "fr") {
    var chatWith;

    for (i = 0; i < headerFulfillment.length; i++) {
      if (
        headerFulfillment[i].profileId !=
        window.localStorage.getItem("profileId")
      ) {
        chatWith = headerFulfillment[i];
      }
    }

    var participant = document.createElement("span");

    participant.innerHTML = "" + chatWith.name + " " + chatWith.surname;
    participant.id = "participant" + 1;
    participant.className = "conversationHeaderTitle";
    participant.name = "participant" + chatWith.profileId;
    chatHeader.appendChild(participant);
  } else if (previousButtonTag == "cv") {
    var conversationParticipantsBar = document.getElementById(
      "participantsBar"
    );

    for (i = 0; i < headerFulfillment.length; i++) {
      var participantDiv = document.createElement("div");
      participantDiv.className = "participantsBar-entity";
      participantDiv.id = "convparticipant" + headerFulfillment[i].profileId;
      conversationParticipantsBar.appendChild(participantDiv);

      participantDiv.onclick = function() {
        window.localStorage.setItem("checkUserProfile", this.id.substring(15));
        location.replace("profilePage.html");
      };

      var participantImage = document.createElement("img");
      participantImage.id = "participantImg" + headerFulfillment[i].profileId;
      participantImage.src = headerFulfillment[i].image;
      participantDiv.appendChild(participantImage);

      var participant = document.createElement("span");
      participant.innerHTML =
        "" + headerFulfillment[i].name + " " + headerFulfillment[i].surname;
      var j = i + 2;
      participant.id = "participant" + j;
      participant.name = "participant" + headerFulfillment[i].profileId;
      participant.className = "conversationParticipantsNames";
      participantDiv.appendChild(participant);
    }
  }
}
function setupHeaderName(conversationName) {
  console.log("ACTUAL CONVERSATION NAME: " + actualConversationName);
  var header = document.getElementById("chat-header");
  var headerConversationTitle = document.createElement("span");
  headerConversationTitle.className = "conversationHeaderTitle";
  headerConversationTitle.id = "participant" + 1;
  headerConversationTitle.innerHTML = conversationName;
  header.appendChild(headerConversationTitle);
}
function clearHeader() {
  var header = document.getElementById("chat-header");
  var headerElementsCount = header.childElementCount;

  var spanCountInHeader = header.getElementsByTagName("span").length;
  console.log(
    "CHAT HEADER NODE ELEM LENGTH: " +
      headerElementsCount +
      "SPAN: " +
      spanCountInHeader
  );

  for (var i = 1; i <= spanCountInHeader; i++) {
    header.removeChild(document.getElementById("participant" + i));
  }
}

async function clearMessages() {
  for (i = 0; i < actuallMessageIds.length; i++) {
    var messageSpan = document.getElementById(
      "messageSpan" + actuallMessageIds[i]
    );
    messageSpan.remove();
    var messageBr = document.getElementById("messageBr" + actuallMessageIds[i]);
    messageBr.remove();
    var messageDate = document.getElementById(
      "messageDate" + actuallMessageIds[i]
    );
    messageDate.remove();
    var messageSender = document.getElementById(
      "messageSender" + actuallMessageIds[i]
    );
    messageSender.remove();
    var message = document.getElementById("message" + actuallMessageIds[i]);
    message.remove();
    var positionMessage = document.getElementById(
      "positionMessage" + actuallMessageIds[i]
    );
    positionMessage.remove();
  }
}
// @@@@@@@@###########$$$$$$$$$$$!!!!!!!!!!!! DOKOŃCZ
function sendService() {
  var inputText = getMessageFromInput();

  var profileId = window.localStorage.getItem("profileId");
  var name = window.localStorage.getItem("name");
  var surname = window.localStorage.getItem("surname");

  console.log("string profileId: " + profileId);
  console.log("string name: " + name);
  console.log("string surname: " + surname);

  var conversationId = actuallConversationId;
  console.log("ConversationId: " + conversationId);

  var json = formMessageJSON(
    profileId,
    name,
    surname,
    conversationId,
    inputText
  );

  //console.log("JSON");
  //console.log(JSON.stringify(json));
  clearMessages();
  var response = sendMessage(json);
  console.log("clear messages");

  console.log("load all messages");
  console.log("current conversationId is: " + actuallConversationId);
  // loadMessagesForConversation(actuallConversationId);

  //console.log("response from service");
  //console.log(JSON.stringify(response));
}

async function sendMessage(json) {
  var responseJson = await postRequest(
    "http://localhost:8080/api/messages",
    json
  );
  console.log("Sent message");
  loadMessagesForConversation(actuallConversationId);
}
async function checkForMessages(conversationId, profileId) {
  var responseJson = await newGetRequest(
    "http://localhost:8080/api/messages/unread/" +
      conversationId +
      "/" +
      profileId // @@@@@@@@@@@@@@@@@@@@ zmienić na profileID !!!!!!!!!!
  );
  console.log("check response: " + responseJson);
  if (responseJson.unread == true) {
    clearMessages();
    loadMessagesForConversation(actuallConversationId);
  }
  //loadMessagesForConversation(actuallConversationId);
}
async function getUnreadConversations(profileId) {
  var responseJson = await newGetRequest(
    "http://localhost:8080/api/messages/unread/" + profileId
  );
  console.log("mark conversation");
  markUnreadConversations(responseJson);
}

function markUnreadConversations(json) {
  console.log("$$ array.length: " + json.length);
  console.log("JSON: " + json);

  if (json != null && json.length > 0) {
    for (var i = 0; i < json.length; i++) {
      if (json[i].customCreated == 1) {
        // cv

        var convColor = document.getElementById("cv" + json[i].id).style
          .backgroundColor;
        console.log("COLOR KONWERSACJI" + convColor);

        if (convColor != "rgb(74, 187, 74)" || convColor == undefined) {
          console.log("Kolor różny od zielonego");
          var conversationBox = document.getElementById("conversation-box");
          console.log("unreaded w cv id:" + json[i].id);
          var conversation = document.getElementById("cv" + json[i].id);

          conversation.style.backgroundColor = "#4abb4a";
          conversationBox.insertBefore(
            conversation,
            document.getElementById(actuallFirstConversationEntity)
          );
          actuallFirstConversationEntity = "cv" + json[i].id;
        } else {
          console.log("NIEODCZYTANA KONWERSACJA JEST JUŻ POMALOWANA !!!");
        }
      } else {
        var conversation = document.getElementById("fr" + json[i].id);
        if (conversation != null) {
          if (conversation != "rgb(74, 187, 74)" || conversation == undefined) {
            console.log("unreaded w fr id:" + json[i].id);
            var friendsBox = document.getElementById("friends-box");
            friendsBox.insertBefore(
              conversation,
              document.getElementById(actuallFirstFriendEntity)
            );
            actuallFirstFriendEntit = "fr" + json[i].id;
            conversation.style.backgroundColor = "#4abb4a";

            console.log("zmieniono color");
          }
        } else if (conversation == null) {
          console.log("unreaded w ma id:" + json[i].id);
          conversation = document.getElementById("ma" + json[i].id);
          if (conversation != "rgb(74, 187, 74)" || conversation == undefined) {
            var matchedBox = document.getElementById("matched-box");
            matchedBox.insertBefore(
              conversation,
              document.getElementById(actuallFirstMatchedEntity)
            );
            actuallFirstMatchedEntity = "ma" + json[i].id;
            conversation.style.backgroundColor = "#4abb4a";
            console.log("zmieniono color");
          }
        }

        // fr ma
      }
    }
  }
}

function getMessageFromInput() {
  var inputMessage = document.getElementById("message-box").value;
  console.log("input Message: " + inputMessage);

  //formMessageJSON(15, "kunekunda", "jakastam", 13, "txt: blablablalbalalbalbalbal:");

  return inputMessage;
}
function clearMessageInput() {
  var inputElement = document.getElementById("message-box");
  inputElement.value = "";
}
function formMessageJSON(profileId, name, surname, conversationId, txt) {
  var inputText = getMessageFromInput();

  var json = {};
  var profile = {};
  profile.profileId = profileId;
  profile.name = name;
  profile.surname = surname;

  json.profile = profile;
  json.conversationId = conversationId;
  var messageInfo = {};
  messageInfo.messageTxt = inputText;
  messageInfo.date = "";
  messageInfo.messageId = "";
  json.messageInfo = messageInfo;

  console.log("form MessageJson");
  console.log(json);

  clearMessageInput();
  return JSON.stringify(json);
}

function getToken() {
  var token = window.localStorage.getItem("token");
  console.log("TOKEN FROM LOCAL DB");
  console.log(token);
  var profile = window.localStorage.getItem("profileId");
  console.log("PROFILE_ID FROM LOCAL DB");
  console.log(profile);
}

async function loadMessagesForConversation(conversationId) {
  actuallConversationId = conversationId;
  var profileId = window.localStorage.getItem("profileId");

  var responseJson = await newGetRequest(
    "http://localhost:8080/api/messages/" + conversationId + "/" + profileId
  );
  console.log("before in messages");
  setupMessages(responseJson);
}

function getFriendList(profileId) {
  let response = new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(newGetRequest("http://localhost:8080/api/friends/" + profileId));
    }); // ,1000 opoznienie
  });
  response.then(message => {
    console.log("before in friends");
    setupFriendsLeftSideList(message);
    //console.log("Friends! "+ JSON.stringify(message));
  });
}

function getMatchedList(profileId) {
  let response = new Promise((resolve, reject) => {
    setTimeout(function() {
      resolve(
        newGetRequest("http://localhost:8080/api/matcher/matches/" + profileId)
      );
    }); // ,1000 opoznienie
  });
  response.then(message => {
    console.log("before in matched");
    setupMatchedLeftSideList(message);
    //console.log("Matched! "+ JSON.stringify(message));
  });
}

async function getConversationList(profileId) {
  var responseJson = await newGetRequest(
    "http://localhost:8080/api/conversations/custom/" + profileId
  );
  console.log("created Custom conv");
  console.log("ConversationList! " + JSON.stringify(responseJson));
  setupConversationLeftSideList(responseJson);
}

async function setupHeaderParticipants(conversationId) {
  var responseJson = await newGetRequest(
    "http://localhost:8080/api/conversations/participants/" + conversationId
  );
  console.log("created Custom conv");
  console.log("ConversationList! " + JSON.stringify(responseJson));
  setupChatHeader(responseJson);
}

async function setupConvBarParticipants(conversationId) {
  var responseJson = await newGetRequest(
    "http://localhost:8080/api/conversations/participants/image/" +
      conversationId
  );
  console.log("created Custom conv");
  console.log("ConversationList! " + JSON.stringify(responseJson));
  setupChatHeader(responseJson);
}

// %%%%%%%%%%%%%%%%%% FRIENDS %%%%%%%%%%%%%%%%%%%%%%%%

function addFriend() {
  console.log("$$$$$$$$$$$$$$$$$");
  console.log("AddFriends, id: " + matchedPersonInActualConversation.substr(2));
  var receiverId = matchedPersonInActualConversation;
  var senderId = window.localStorage.getItem("profileId");
  sendFriendInvitation(senderId, receiverId);
}

async function sendFriendInvitation(sender, receiver) {
  console.log("sender/rec: " + sender + "/" + receiver);
  var responseJson = await getRequestNoReturn(
    "http://localhost:8080/api/invitations/" + sender + "/" + receiver
  );
  alert("wysłano zaproszenie");
  console.log("send friend invitation");
}

// %%%%%%%%%%%%%%% END OF FRIENDS %%%%%%%%%%%%%%%%%%%%

async function newGetRequest(url) {
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
async function getRequestNoReturn(url) {
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
  return await response.json();
}

function checkUrls() {
  console.log("friendsURL: " + friendsURL);
}

function friendsList() {
  // for (var i = 0; i < mapOfFriendsAndMatched.length; i++) {
  //   // console.log(
  //   //   "all friends and matched array: " +
  //   //     mapOfFriendsAndMatched[i].name +
  //   //     " " +
  //   //     mapOfFriendsAndMatched[i].id
  //   // );
  // }
  return mapOfFriendsAndMatched;
}
function getparticipantId(name) {
  var id = friendsMap.get(name);
  //console.log("getparticipantID: " + id);
  return id;
}

function addToConvChat() {
  console.log("pressed add participant button");
  var convDropdown = document.getElementById("addToConvChatPanel");

  if (convDropdown.style.visibility != "visible") {
    convDropdown.style.visibility = "visible";
  } else {
    convDropdown.style.visibility = "hidden";
  }
}
