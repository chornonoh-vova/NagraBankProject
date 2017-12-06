$("document").ready(function () {
	if (getCookie("login") === undefined) {
		document.location.href = "login.html";
	}
	
	let client = new Client();

	$("#confirm").click(function () {
		let money = $("#money").val();
		//TODO: verificate money
		client.openConnection();
		client.sendMessage("withdrawal", getCookie("id"), money);
		
		let answer = client.getArrayFromMessage();
		alert("Answer:" + answer);
	});
});