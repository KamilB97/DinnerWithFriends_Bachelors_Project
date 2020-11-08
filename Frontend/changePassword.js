function changePassword() {
  if (validateInput()) {
    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("password").value;
    //  updatePassword(oldPassword, newPassword, localStorage.getItem("userId"));
    updatePassword(
      oldPassword,
      newPassword,
      window.localStorage.getItem("userId")
    );
    //var person = confirm("Hasło zostało zmienione");
  }
}

function validateInput() {
  var passwordRegExp = new RegExp(
    "^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{7,})"
  );

  var oldPassword = document.getElementById("oldPassword").value;
  var newPassword = document.getElementById("password").value;
  var repeat = document.getElementById("repeatPassword").value;
  //console.log(oldPassword + " " + newPassword + " " + repeat);

  if (
    oldPassword == null ||
    newPassword == null ||
    repeat == null ||
    oldPassword == "" ||
    newPassword == "" ||
    repeat == ""
  ) {
    var warning = document.getElementById("warning");
    warning.innerHTML = "*Pola nie mogą być puste";
    showWarning();
    return false;
  } else if (!passwordRegExp.test(newPassword)) {
    var warning = document.getElementById("warning");
    warning.innerHTML =
      "*hasło musi zawierać co najmniej 8 znaków, cyfrę, małą oraz dużą literę ";
    showWarning();
    return false;
  } else if (!matchInputPasswords(newPassword, repeat)) {
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

async function updatePassword(oldPassword, newPassword, userId) {
  var json = {};
  json.oldPassword = oldPassword;
  json.newPassword = newPassword;
  json.profileId = userId;
  var jsonToPost = JSON.stringify(json);
  console.log(jsonToPost);

  var responseJson = await updateRequest(
    "http://localhost:8080/api/images",
    jsonToPost
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
  return await response.json();
}
