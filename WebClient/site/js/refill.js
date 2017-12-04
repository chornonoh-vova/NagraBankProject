
$("document").ready(function () {
	let client = new Client();

	$("#confirm").click(function () {
		let userId = $("#userId").val();
		let money = $("#money").val();
		

		alert("Confirmed, id: " + userId + " money: " + money);

		client.openConnection();
		client.sendMessage("refill", userId, money);
		
		let answer = client.getArrayFromMessage();
			alert("Answer:" + answer);
		
	});
});