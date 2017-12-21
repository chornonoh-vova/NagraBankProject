$("document").ready(function () {
	if (getCookie("login") === undefined) {
		document.location.href = "login.html";
	}

	let loginToSend = getCookie("login");
		client.openConnection();
		client.sendMessage("update", loginToSend);

	let answer = client.getArrayFromMessage();
	if (answer[0] === "success") {
			setCookie("login", loginToSend);
			setCookie("id", answer[1]);
			setCookie("balance", answer[2]);
			setCookie("question", answer[3]);
			setCookie("birthdate", answer[4]);
	}

	/*page initialization*/
	$("#login").text("Your login: " + getCookie("login"));
	$("#id").text("Your bank id: " + getCookie("id"));
	$("#balance").text("Balance on your card: " + getCookie("balance"));
	$("#birthdate").text("Your birthdate: " + getCookie("birthdate"));
	$("#question").text("Secret question: " + getCookie("question"));
	$(".login_exit p").html(getCookie("login") + " | <a href=\"login.html\">Exit</a>");

	/*when user wants to exit*/
	$(".login_exit p a").click(function() {
		deleteCookie("login");
		deleteCookie("id");
		deleteCookie("balance");
		deleteCookie("birthdate");
		deleteCookie("question");
	});
});