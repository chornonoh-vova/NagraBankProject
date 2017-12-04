$("document").ready(function () {
	let client = new Client();

	$("#confirm").click(function () {
		let userIdFrom = $("#userIdFrom").val();
		let userIdTo = $("#userIdTo").val();
		let money = $("#money").val();
		

		alert("Confirmed, userIdFrom: " + userIdFrom + "userIdTo" +userIdTo+ " money: " + money);

		client.openConnection();
		client.sendMessage("transfer", userIdFrom, userIdTo, money);
		
		let answer = client.getArrayFromMessage();
			alert("Answer:" + answer);
		
	});
});