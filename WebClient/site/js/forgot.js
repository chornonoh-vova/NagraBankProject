$("document").ready(function () {
	let client = new Client();
	$("#userLogin").keypress(function(e){
		if(e.keyCode==13){
			let loginToSend = $("#userLogin").val();
			if (!verifyLogin(loginToSend)) {
				alert("Wrong login or pin-code");
				return;
			}

			client.openConnection();
			client.sendMessage("getQuestion", loginToSend);

			let answer = client.getArrayFromMessage();
			if (answer[0] === "success") {
				$("#question").text(answer[1]);
			} else if(answer[0] === "error") {
				alert("Wrong login");
			}
		}
	});

	$("#answer").keypress(function(e){
		if(e.keyCode==13){
			let loginToSend = $("#userLogin").val();
			let answerForLogin = $("#answer").val();

			client.openConnection();
			client.sendMessage("checkQuestion", loginToSend, answerForLogin);

			let answer1 = client.getArrayFromMessage();
			if (answer1[0] === "error") {
				alert("Wrong answer!");
				$("#confirm").click(function () {
					alert("Your answer wrong!");
				});
			} else if (answer1[0] === "success") {
				alert("Answer is correct!");
				$("#confirm").click(function () {
					let loginToSend = $("#userLogin").val();
					let firstPin = getMd5Hash($("#pin").val());
					let secondPin = getMd5Hash($("#confirm_pin").val());
					if ($("#pin").val() !== $("#confirm_pin").val()) {
						alert("Not equal pins!!!");
						return;
					}

					client.openConnection();
					client.sendMessage("changePin", loginToSend, firstPin);
					let answer2 = client.getArrayFromMessage();
					if(answer2[0] === "success") {
						alert("Pin changed");
					}

				});
			}
		}
	});
});