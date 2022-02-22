function prototype() {

    //do stuff...

    sendXHR("nomeServlet");

}

function sendXHR(uri) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () { callback(xhr); };

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
    xhr.send();
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
    textArea = myGetElementById("textarea");
    textArea.innerHTML = result;


}
