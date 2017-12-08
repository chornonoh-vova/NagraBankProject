$("document").ready(function () {
	if (getCookie("login") === undefined) {
		document.location.href = "login.html";
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