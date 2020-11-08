function goToRegistrationPage() {
  window.scrollTo(0, document.body.scrollHeight);
}

function goToLoginPage() {
  window.scrollTo(0, 0);
}

function test() {
  console.log("tests started");

  //http://localhost:8080/test
  fetch("http://localhost:8080/test")
    .then(function(response) {
      if (response.status !== 200) {
        console.log(
          "Looks like there was a problem. Status Code: " + response.status
        );
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(response.headers.get("Content-Type"));
        console.log(response.headers.get("Date"));

        console.log(response.status);
        console.log(response.statusText);
        console.log(response.type);
        console.log(response.url);
        console.log(data);
      });
    })
    .catch(function(err) {
      console.log("Fetch Error :-S", err);
    });
}

function test1() {
  console.log("tests started");
  fetch("http://localhost:8080/test")
    .then(response => {
      console.log(response.status);
      if (!response.ok) {
        throw response;
      }
      console.log("no error");
      console.log(response.json);
      return response.json(); //we only get here if there is no error
    })
    .then(json => {
      this.props.dispatch(doSomethingWithResult(json));
    })
    .catch(err => {
      err.text().then(errorMessage => {
        this.props.dispatch(displayTheError(errorMessage));
      });
    });
}

function doSomethingWithResult(json) {
  console.log("test " + json);
}

function login() {
  formLoginJson();
}
function formLoginJson() {
  var data = getLoginData();

  var obj = { username: data.login, password: data.password };
  var json = JSON.stringify(obj);
  console.log(json);
}

function getLoginData() {
  var login = document.getElementById("loginEmail").value;
  var password = document.getElementById("loginPassword").value;

  return { login: login, password: password };
}

function register() {}

function getRegisterDataAndFormJson() {
  var email = document.getElementById("email").value;
  var phone = document.getElementById("phone").value;
  var password = document.getElementById("password").value;
  var rePassword = document.getElementById("repeatPassword").value;

  console.log(
    "register params: " +
      email +
      " " +
      phone +
      " " +
      password +
      " " +
      rePassword +
      ";"
  );
  return formRegisterJson(email, phone, password);
}

function checkregisterInputCorrect(email, phone, password, rePassword) {}

function formRegisterJson(email, phone, pass) {
  var obj = { email: email, phone: phone, password: pass };
  var json = JSON.stringify(obj);
  console.log("form data " + json);

  return json;
}
