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
				$("#question").text(answer[2]);
			}
		   }
	});

	$("#answer").keypress(function(e){
		   if(e.keyCode==13){
			   let answer = $("#answer").val();

				client.openConnection();
				client.sendMessage("checkQuestion", loginToSend, answer);

				let answer1 = client.getArrayFromMessage();
				if (answer1[0] === "error") {
					alert(answer1[0] + " : " + answer1[2])
				}
			   }
		});

	$("#confirm").click(function () {
		let firstPin = getMd5Hash($("#pin").val());
		let secondPin = getMd5Hash($("#confirm_pin").val());
			if (firstPin !== secondPin) {
				alert("not equal pins!!!");
				return;
			}

				client.openConnection();
				client.sendMessage("changePin", loginToSend, firstPin);
				let answer2 = client.getArrayFromMessage();
				if(answer2[0] === "success") {
					alert("pin change");
				}

	});

	});