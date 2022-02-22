function checkSend(){
	var textArea = myGetElementById("textarea");
	var text = textArea.value;
	if(text.includes("€") || text.lenght >= 5000)
		myGetElementById("form").submit();
}