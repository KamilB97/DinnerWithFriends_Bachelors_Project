var LOGIN_URL = "http://localhost:8080/login";
var REGISTRATION_URL = "http://localhost:8080/register";
var isLogin = null;
function goToRegistrationPage() {
  window.scrollTo(0, document.body.scrollHeight);
}

function goToLoginPage() {
  window.scrollTo(0, 0);
}

function login() {
  isLogin = true;
  var json = formLoginJson();
  var token = postRequest("http://localhost:8080/login", json);
  //changePage();
  return token;
}
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function changePage() {
  var name = JSON.parse(window.localStorage.getItem("name"));
  var surname = JSON.parse(window.localStorage.getItem("surname"));
  console.log("CHANGE PAGE: " + name + " " + surname);
  console.log(name == "nie ustawiono");
  console.log(surname == "nie ustawiono");
  //window.localStorage.setItem("newUser", "newUser");
  if (name == "nie ustawiono" && surname == "nie ustawiono") {
    console.log("myProfilePage");
    window.location.href = "myProfilePage.html";
  } else {
    console.log("mainMenu");
    window.location.href = "mainMenu.html";
  }
  //"file:///D:/WebDevelopment/inż/mainMenu.html";
  //window.location.assign("D:/WebDevelopment/inż/mainMenu.html")
  //history.pushState({},"","mainMenu.html");

  //console.log("po zmianie strony");
}

function formLoginJson() {
  var data = getLoginData();
  var obj = { username: data.login, password: data.password };
  var json = JSON.stringify(obj);
  console.log(json);
  return json;
}

function getLoginData() {
  var login = document.getElementById("loginEmail").value;
  var password = document.getElementById("loginPassword").value;

  return { login: login, password: password };
}

async function register() {
  //goToLoginPage();
  if (await validateInput()) {
    console.log("REGISTERED PASSED VALIDATION");
    var json = formRegisterJson();
    postRequest("http://localhost:8080/register", json);
  }
}

function formRegisterJson() {
  var data = getRegisterData();
  var obj = { email: data.email, phone: data.phone, password: data.password };
  var json = JSON.stringify(obj);
  console.log(json);
  return json;
}

function getRegisterData() {
  var email = document.getElementById("email").value;
  var phone = document.getElementById("phone").value;
  var password = document.getElementById("password").value;
  var repeat = document.getElementById("repeatPassword").value;
  return { email: email, phone: phone, password: password, repeat: repeat };
}
function postRequest(url, json) {
  sendPostRequest(url, json)
    .then(function(response) {
      if (response.ok) {
        console.log("json ok ");
        var jsonResponse = response.json();
        return jsonResponse;
      } else {
        console.log("json bad");
        var loginWarning = document.getElementById("loginWarning");
        loginWarning.style.visibility = "visible";
        loginWarning.innerHTML = "*Wprowadzone dane są niepoprawne";

        throw new Error("Something went wrong");
      }
    })
    .then(responseJson => {
      console.log("after condition check");
      console.log(responseJson);
      window.localStorage.setItem("token", JSON.stringify(responseJson.token));
      window.localStorage.setItem(
        "profileId",
        JSON.stringify(responseJson.profileId)
      );
      window.localStorage.setItem("name", JSON.stringify(responseJson.name));
      window.localStorage.setItem(
        "surname",
        JSON.stringify(responseJson.surname)
      );
      window.localStorage.setItem(
        "userId",
        JSON.stringify(responseJson.userId)
      );

      var token = window.localStorage.getItem("token");
      console.log("string token");
      console.log("check for quotes" + token);
      var profile = window.localStorage.getItem("profileId");
      console.log("string profileId");
      console.log(profile);

      var name = window.localStorage.getItem("name");
      console.log("string name");
      console.log(name);

      var surname = window.localStorage.getItem("surname");
      console.log("string surname");
      console.log(surname);

      if (isLogin) {
        changePage();
      }
      isLogin = false;

      //var stringToken = JSON.stringify(responseJson);
      //window.localStorage.setItem('token',stringToken );
      return responseJson;
    })
    .catch(error => {
      console.log(error);
    });
}

async function validateInput() {
  var passwordRegExp = new RegExp(
    "^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{7,})"
  );

  var data = getRegisterData();
  console.log(data.email + " " + data.password + " " + data.repeat);

  var emailAvailability = await checkEmailAvailability(
    makeEmailJson(data.email)
  );
  console.log("check email answer2: " + emailAvailability.available);
  if (!emailAvailability.available) {
    var warning = document.getElementById("warning");
    warning.innerHTML = "*Podany email jest zajęty";
    showWarning();
    var availabilityIcon = document.getElementById("emailValid");
    availabilityIcon.style.visibility = "visible";
    availabilityIcon.src = "unavailable.png";

    return false;
  } else {
    var warning = document.getElementById("warning");
    warning.style.visibility = "hidden";
    var availabilityIcon = document.getElementById("emailValid");
    availabilityIcon.style.visibility = "visible";
    availabilityIcon.src = "available.png";
  }
  if (
    data.email == null ||
    data.password == null ||
    data.repeat == null ||
    data.email == "" ||
    data.password == "" ||
    data.repeat == ""
  ) {
    var warning = document.getElementById("warning");
    warning.innerHTML = "*Pola nie mogą być puste";
    showWarning();
    return false;
  } else if (!passwordRegExp.test(data.password)) {
    var warning = document.getElementById("warning");
    warning.innerHTML =
      "*hasło musi zawierać co najmniej 8 znaków, cyfrę, małą oraz dużą literę ";
    showWarning();
    return false;
  } else if (!matchInputPasswords(data.password, data.repeat)) {
    var warning = document.getElementById("warning");
    warning.innerHTML = "*Hasła nie pasują do siebie";
    showWarning();
    return false;
  } else {
    var warning = (document.getElementById("warning").style.visibility =
      "hidden");
    return true;
  }
}

function matchInputPasswords(password, repeat) {
  if (password === repeat) {
    return true;
  } else {
    return false;
  }
}

function showWarning() {
  var warning = document.getElementById("warning");
  if (warning.style.visibility != "visible")
    warning.style.visibility = "visible";
}

function makeEmailJson(email) {
  var json = {};
  json.email = email;
  return JSON.stringify(json);
}

async function checkEmailAvailability(json) {
  var responseJson = await emailPostRequest(
    "http://localhost:8080/emails",
    json
  );
  console.log(
    "W metodzie check email availability: " + JSON.stringify(await responseJson)
  );
  return await responseJson;
}

function sendPostRequest(url, json) {
  console.log("postRequestBody method");
  return fetch(url, {
    //credentials: 'same-origin', // 'include', default: 'omit'
    method: "POST", // 'GET', 'PUT', 'DELETE', etc.
    body: json, // Coordinate the body type with 'Content-Type'
    mode: "cors",
    headers: new Headers({
      Accept: "application/json",
      "Content-Type": "application/json"
      // "X-Authorization":
      //   "Token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYW1pbGJyenlja2lAZ21haWwuY29tIiwidXNlcklkIjoiMzgifQ.xIni6vRkXPzPKurjqG1YIa5peXLRfu9kBRkTwLu5S2E"
    })
  });
  //.then(response => response.json())
}
async function emailPostRequest(url, json) {
  console.log("w metodzie postRequest");
  const response = await fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json"
      // "X-Authorization":
      //   "Token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYW1pbGJyenlja2lAZ21haWwuY29tIiwidXNlcklkIjoiMzgifQ.xIni6vRkXPzPKurjqG1YIa5peXLRfu9kBRkTwLu5S2E"
    },
    body: json,
    redirect: "follow", // manual, *follow, error
    referrer: "no-referrer" // no-referrer, *client
  });
  return await response.json();
}
