function validate (){
	for (var i = 0; i<=7; i++ ){
		var CellA = myGetElementById("A" + i).value;
		var CellB = myGetElementById("B" + i).value;
		
		if (CellA == '' || isNaN(CellA) || CellB == '' || isNaN(CellB)){
			disableSend();
			return
		}
	}
	enableSend();
}


function disableSend (){
	var sendSingle = myGetElementById("single");
	var sendMulti = myGetElementById("multi");
	sendSingle.style.visibility = "hidden";
	sendMulti.style.visibility = "hidden";
	
}

function enableSend (){
	var sendSingle = myGetElementById("single");
	var sendMulti = myGetElementById("multi");
	sendSingle.style.visibility = "visible";
	sendMulti.style.visibility = "visible";
	
}

function sendSingle(){
	var req = "single\n" + getValoreMatrice("A") + "\n" + getValoreMatrice("B");
	console.log(req);
	sendReq(req,"MatriciServlet");
	
}

function sendMulti(){
	var req = "multi\n" + getValoreMatrice("A") + "\n" + getValoreMatrice("B");
	console.log(req);
	sendReq(req,"MatriciServlet");
	
}

function getValoreMatrice(Matrice){
	var result = "";
		for(var i = 0; i<=7; i++){
			var cell = myGetElementById(Matrice + i);
			result+= " " + cell.value;
		}
		return result;
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

function showResult(result){
	var lista = result.split(" ");
	for(var i = 0; i <= 7; i++){
		var elem = myGetElementById("C" + i);
		elem.value = lista[i];
	}
	
}