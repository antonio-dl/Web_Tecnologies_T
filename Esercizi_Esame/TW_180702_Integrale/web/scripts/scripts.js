var counter = 0; // Variabili "globali"
var totale = 0;



function sendRequest() {

	var A = parseFloat(myGetElementById("A").value);
	var B = parseFloat(myGetElementById("B").value);
	console.log(A,B);
	var inc = (B-A)/4;
	
	var interv0 = [A, A+inc ];
	var interv1 = [A + inc, A + 2 * inc ];
	var interv2 = [A + 2* inc , A + 3 * inc];
	var interv3 = [A + 3 * inc, B];


	sendXHR("IntegServlet0", interv0);
	sendXHR("IntegServlet1", interv1);
	sendXHR("IntegServlet2", interv2);
	sendXHR("IntegServlet3", interv3);

}

function sendXHR(uri, intervallo) {
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
	console.log(JSON.stringify(intervallo[0]), JSON.stringify(intervallo[1]));
	xhr.send(JSON.stringify(intervallo[0]) + " " + JSON.stringify(intervallo[1]));

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
			showResult(xhr.responseText);

			//location.reload();
		}// if 200

		else {
			// errore di caricamento
			console.error("Impossibile effettuare l'operazione richiesta.");
		}// if 4

	} // callback();
}

function showResult(result) {
	counter++;
	totale += JSON.parse(result);
	if (counter > 3) {
		var show = myGetElementById("result");
		show.value = totale;
		totale = 0;
		counter = 0;
	}


}
