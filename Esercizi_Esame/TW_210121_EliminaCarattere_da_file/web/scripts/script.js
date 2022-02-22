function checkSend(textarea){
	var string = textarea.value;
	
	
	if(string.includes('*')){
		console.log('invio submit!')
		string = string.replace('*','');
		textarea.value = string;
		myGetElementById('form').submit();
	}
	var regex = /^[a-zA-Z0-9.]*$/ ;
	if(!regex.test(string)){
		alert('Inserito carattere sbagliato!');
		textarea.value = '';
	}
	
	
}