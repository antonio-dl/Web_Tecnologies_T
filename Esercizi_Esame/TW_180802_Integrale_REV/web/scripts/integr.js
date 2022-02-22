function sendRequest() {
	myGetElementById("ris").value = 0;
	var a = myGetElementById("a").value;
	var b = myGetElementById("b").value;
	a = parseFloat(a);
	b = parseFloat(b);
	if (isNaN(a) || isNaN(b))  {
		alert("Inserisci numeri!");
		return
	}

	var intervallo = ((b - a) / 4.0);
		console.log("Intervallo: " + intervallo);
	
	
	for (var i = 0; i < 4; i ++) {
		var estremoInf = a + (intervallo * i);
		var estremoSup = a + (intervallo * (i + 1));
		var req = JSON.stringify([estremoInf, estremoSup]);
		console.log("Sending: " + req);
		sendXHR(req, "IntegraleServlet");
	}

}

function sendXHR(req, uri) {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() { callback(xhr); };

	try {
		xhr.open("post", uri, true);
	}
	catch (e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	xhr.setRequestHeader("connection", "close");
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.send(req);
}


function callback(xhr) {
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
			console.log(xhr.responseText);
			var response = JSON.parse(xhr.responseText);
			var elemRis = myGetElementById("ris");
			var parsed = parseFloat(elemRis.value);
			elemRis.value = parsed + response;

			//location.reload();
		}// if 200

		else {
			// errore di caricamento
			console.error("Impossibile effettuare l'operazione richiesta.");
		}// if 4

	} // callback();
}

