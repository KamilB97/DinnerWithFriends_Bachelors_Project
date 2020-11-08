function test1(){
	//var json = getRegisterDataAndFormJson();
	//postRequest('http://localhost:8080/register', json )
	getRequest('http://localhost:8080/test')	
		.then(function(response) {
		
			if (response.ok) {
				console.log("json ok ");
				return response.json();
			} else {
				console.log("json bad");
				throw new Error('Something went wrong');
			}
		})
		.then((responseJson) => {
			console.log("after condition check");
			console.log(responseJson);
		})
		.catch((error) => {
			console.log(error);
		});
  
  function getRequest(url) {
	  console.log("getRequest method")
  return fetch(url, {
    //credentials: 'same-origin', // 'include', default: 'omit'
    method: 'GET', // 'GET', 'PUT', 'DELETE', etc.
    //body: data, // Coordinate the body type with 'Content-Type'
	mode: 'cors',
    headers: new Headers({
	  'Accept': 'application/json',
	  'Content-Type': 'application/json'
    }),
  })
  //.then(response => response.json())
}
  
}
