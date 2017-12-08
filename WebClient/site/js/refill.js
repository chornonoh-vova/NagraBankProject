
$("document").ready(function () {
	if (getCookie("login") === undefined) {
		document.location.href = "login.html";
	}
	
	let client = new Client();

	$("#confirm").click(function () {
		let money = $("#money").val();
		if (filterFloat(money) === undefined) {
			alert("Money amount wrong");
			return;
		}
		client.openConnection();
		client.sendMessage("refill", getCookie("id"), money);
		
		let answer = client.getArrayFromMessage();
		alert("Answer:" + answer);
	});
});