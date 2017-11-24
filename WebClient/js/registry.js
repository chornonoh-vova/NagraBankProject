$("document").ready(function() {
	let client = new Client();

	$("#confirm").click(function() {
		let loginToSend = $("#login").val();
		let password = $("#pin").val();
		let password_confirm = $("#confirm_pin").val();
		let birthdate = $("#birthdate").val();
		let secret_question = $("#question").val();
		let secret_answer = $("#answer").val();

		if (!verifyLogin(loginToSend)) {
			alert("Wrong login,try again");
			return;
		} else if (!verifyPinCode(password)) {
			alert("Wrong pin,try again");
			return;
		} else if (password !== password_confirm) {
			alert("Paswords don't match,try again");
			return;
		} else if (!verifyDate(birthdate)) {
			alert("Wrong birthdate,try again");
			return;
		} else {
			let hashedPin = getMd5Hash(password);
			client.openConnection();
			client.sendMessage("registry", hashedPin, loginToSend, birthdate, secret_question, secret_answer);
			let answer = client.getMessage();
			alert("Answer from server: " + answer);
		}
	});
});