function validate(elm) {
	var value = elm.value;
	var regex = /^[a-z-€]+$/;

	if ((regex.test(value) || value == "€") && value.length == 1 && isNaN(value) ) {
		send(elm);
	}
	else {
		alert("Inserire carattere valido!")
		elm.value = '-';
	}
	return
}

function send(elm) {

	var request = elm.id + " " + elm.value;
	console.log("Sending: " + request);
	sendReqPOST(request, "CruciverbaServlet")

}

function getResult() {
	sendReqGET("CruciverbaServlet");


}

function sendReqGET(uri) {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() { getCurrentResult(xhr); };

	try {
		xhr.open("get", uri, true);
	}
	catch (e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive
	xhr.setRequestHeader("connection","close");
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.overrideMimeType('text; charset=UTF-8');
	xhr.send();
}

function getCurrentResult(xhr) {

	if (xhr.readyState === 2) {
		//theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if (xhr.readyState === 3) {
		//	theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if (xhr.readyState === 4) {
		// verifica della risposta da parte del server
		if (xhr.status === 200) {
			// operazione avvenuta con successo
			//printResult(xhr.responseText);
			//console.log(xhr.responseText);
			showResult(xhr.responseText);

			//location.reload();
		}// if 200

		else {
			// errore di caricamento
			console.error("Impossibile effettuare l'operazione richiesta.");
			//	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			//	        	theElement.innerHTML += "Errore riscontrato: " + xhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



function sendReqPOST(req, uri) {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() { getCurrentResult(xhr); };

	try {
		xhr.open("post", uri, true);
	}
	catch (e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.send(req);
}


function showResult(result) {
	console.log("Result: " + result);
	var lista = result.split(" ");
	for (var i = 0; i < lista.length; i++) {
		var elem = myGetElementById("A" + i);
		elem.value = lista[i];
	}

}