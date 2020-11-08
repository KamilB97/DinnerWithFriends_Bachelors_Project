
function goToRegistrationPage() {
 window.scrollTo(0, document.body.scrollHeight);
}

function goToLoginPage() {
  window.scrollTo(0, 0);
}

function login(){
	getLoginDataAndFormJson()
}
function getLoginDataAndFormJson(){
	var login = document.getElementById('loginEmail').value;
	var password = document.getElementById('loginPassword').value;
	
	return formLoginJson(login, password);
}
function formLoginJson(login, pass){
	
	var obj = {username: login, password: pass};
	var json = JSON.stringify(obj);
	console.log(json);
	
}
function register(){
	var json = getRegisterDataAndFormJson();
	
	
	postRequest('http://localhost:8080/register', json )
  .then(data => console.log(data)) // Result from the `response.json()` call
  
  .then((response) => {
  if (response.ok) {
	  console.log("json ok ");
    return response.json();
  } else {
	  console.log("json bad");
    throw new Error('Something went wrong');
  }
})
.then((responseJson) => {
  // Do something with the response
  console.log(responseJson);
})
.catch((error) => {
  console.log(error);
});
  
  
  //.catch(error => console.error(error))
  //.catch(err => new FetchError(err))
  
  
  function postRequest(url, data) {
  return fetch(url, {
    //credentials: 'same-origin', // 'include', default: 'omit'
    method: 'POST', // 'GET', 'PUT', 'DELETE', etc.
    body: data, // Coordinate the body type with 'Content-Type'
	mode: 'cors',
    headers: new Headers({
	  'Accept': 'application/json',
	  'Content-Type': 'application/json'
    }),
  })
  .then(response => response.json())
}
  
}
class FetchError extends Error {
    constructor(orig) {
        super();
        this.message = "fetch error";
        this.details = orig;
    }
}



function getRegisterDataAndFormJson(){
	var email = document.getElementById('email').value;
	var phone = document.getElementById('phone').value;
	var password = document.getElementById('password').value;
	var rePassword = document.getElementById('repeatPassword').value;
	
	console.log("register params: " + email +" " + phone + " " + password + " " + rePassword + ";"); 
	return formRegisterJson(email, phone, password);
}

function checkregisterInputCorrect(email, phone, password, rePassword){
	
	
	
}

function formRegisterJson(email, phone, pass){
	
	var obj = {email: email, phone: phone, password: pass };
	var json = JSON.stringify(obj);
	console.log(json);
	
	return json;
	
	
	
}

