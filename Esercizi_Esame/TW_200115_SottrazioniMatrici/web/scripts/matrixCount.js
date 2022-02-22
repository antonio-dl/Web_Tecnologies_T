function disableSend() {
	var single = myGetElementById("Single");
	var multi = myGetElementById("Multi");

	single.disabled = true;
	multi.disabled = true;


}


function validate() {

	for (var i = 0; i <= 7; i++) {
		var cellA = myGetElementById("a" + i).value;
		var cellB = myGetElementById("b" + i).value;

		if (cellA == "" || isNaN(cellA) || cellB == "" || isNaN(cellB)) {
			return;
		}
	}
	enableSend();
}

function enableSend() {

	var single = myGetElementById("Single");
	var multi = myGetElementById("Multi");

	single.disabled = false;
	multi.disabled = false;

}

function sendSingle() {
	var req = extractRequest();

	sendReq(req,"SingleMatrixServlet");
}

function sendMulti() {
	var req = extractRequest();

	sendReq(req,"MultiMatrixServlet");
}


function extractRequest() {
	var A = extractValue("a");
	var B = extractValue("b");

	var req = {
		matA: A,
		matB: B
	};
	return req;
}

function sendReq(req,uri) {
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
	console.log(JSON.stringify(req));
	xhr.send(JSON.stringify(req));
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
			showResult(xhr.responseText);
			console.log(xhr.responseText);
			
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


function showResult(str){
	var result = JSON.parse(str);
	for(var i = 0; i < result.length; i++){
		myGetElementById("r" + i).value= result[i];
		
	}
	
	
}

function extractValue(str) {
	var result = [];
	for (var i = 0; i <= 7; i++) {
		var cell = myGetElementById(str + i).value;
		result.push(parseInt(cell));
	}
	return result;
}



