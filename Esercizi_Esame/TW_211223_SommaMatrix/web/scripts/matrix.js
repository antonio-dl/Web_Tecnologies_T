function validate (){
	for (var i = 0; i<=63; i++ ){
		var CellA = myGetElementById("A" + i).value;
		var CellB = myGetElementById("B" + i).value;
		
		if (CellA == '' || isNaN(CellA) || CellB == '' || isNaN(CellB)){
			//disableSend();
			return
		}
	}
	//enableSend();
	send();
}

function send(){
	var modalita = $("#modalita").val();
	if(modalita === "Single"){
		sendSingle();
	} else if (modalita === "2"){
		send2Multi();
	} else if (modalita === "4"){
		send4Multi();
	}
}

function disableSend (){
	var sendSingle = myGetElementById("single");
	var send2Multi = myGetElementById("multi2");
	var send4Multi = myGetElementById("multi4");
	sendSingle.style.visibility = "hidden";
	send2Multi.style.visibility = "hidden";
	send4Multi.style.visibility = "hidden";
	
}

function enableSend (){
	var sendSingle = myGetElementById("single");
	var send2Multi = myGetElementById("multi2");
	var send4Multi = myGetElementById("multi4");
	sendSingle.style.visibility = "visible";
	send2Multi.style.visibility = "visible";
	send4Multi.style.visibility = "visible";
	
}

function sendSingle(){
	var req = getValoreMatrice("A",0,63) + "\n" + getValoreMatrice("B",0,63);
	console.log("Send single" + req);
	sendReq(req,"MatriciServlet",0,63);
	
}

function send2Multi(){
	var first = getValoreMatrice("A",0,31) + "\n" + getValoreMatrice("B",0,31);
	var second = getValoreMatrice("A",32,63) + "\n" + getValoreMatrice("B",32,63);
	
	;
	
	sendReq(first,"MatriciServlet",0,31);
	sendReq(second,"MatriciServlet",32,63);
	
}

function send4Multi(){
	var first = getValoreMatrice("A",0,15)+ "\n" + getValoreMatrice("B",0,15);
	var second = getValoreMatrice("A",16,31) + "\n" + getValoreMatrice("B",16,31);
	var third = getValoreMatrice("A",32,47) + "\n" + getValoreMatrice("B",32,47);
	var fourth = getValoreMatrice("A",48,63) + "\n" + getValoreMatrice("B",48,63);
	
	;
	
	sendReq(first,"MatriciServlet",0,15);
	sendReq(second,"MatriciServlet",16,31);
	sendReq(third,"MatriciServlet",32,47);
	sendReq(fourth,"MatriciServlet",48,63);
	
}

function getValoreMatrice(Matrice,index,end){
	var result = "";
		for( ; index<=end; index++){
			var cell = myGetElementById(Matrice + index);
			result+= " " + cell.value;
		}
		return result;
}


function sendReq(req,uri,index,end) {
	var xhr = new XMLHttpRequest();

	console.log("Index " + index + " End " + end+ "Request " + req);
	xhr.onreadystatechange = function() { callback(xhr,index,end); };

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

function callback(xhr,index,end) {
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
			showResult(xhr.responseText,index,end);
			
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

function showResult(result,index,end){
	console.log("Index " + index + " End " + end+ "result " + result);
	var lista = result.split(" ");
	for(var i = 0; index <= end; index++, i++){
		var elem = myGetElementById("C" + index);
		elem.value = lista[i];
	}
	
}