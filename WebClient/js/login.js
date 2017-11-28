
$("document").ready(function () {
	let client = new Client();

	$("#confirm").click(function () {
		let loginToSend = $("#inputLogin").val();
		let pinToSend = getMd5Hash($("#inputPin").val());
		
		if (!verifyLogin(loginToSend) || !verifyPinCode($("#inputPin").val())) {
			alert("Wrong login or pin-code");
			return;
		}

		alert("Confirmed, login: " + loginToSend + " pin: " + pinToSend);

		client.openConnection();
		client.sendMessage("login", loginToSend, pinToSend);
		
		let answer = client.getMessage();
		
		alert("Answer from server: " + answer);
	});
});
