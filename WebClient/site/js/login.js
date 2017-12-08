
$("document").ready(function () {
	let client = new Client();

	/*if already have cookies-delete them*/
	deleteCookie("login");
	deleteCookie("id");
	deleteCookie("balance");
	deleteCookie("birthdate");
	deleteCookie("question");

	$("#confirm").click(function () {
		let loginToSend = $("#inputLogin").val();
		let pinToSend = getMd5Hash($("#inputPin").val());
		
		if (!verifyLogin(loginToSend) || !verifyPinCode($("#inputPin").val())) {
			alert("Wrong login or pin-code");
			return;
		}

		client.openConnection();
		client.sendMessage("login", loginToSend, pinToSend);
		
		let answer = client.getArrayFromMessage();
		
		if (answer[0] === "success") {
			setCookie("login", loginToSend);
			setCookie("id", answer[1]);
			setCookie("balance", answer[2]);
			setCookie("question", answer[3]);
			setCookie("birthdate", answer[4]);
			document.location.href = "main.html"
		} else if (answer[0] === "error") {
			alert(answer[0] + " : " + answer[1]);
		}
	});
});
