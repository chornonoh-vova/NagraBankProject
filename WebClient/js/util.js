function verifyLogin(login) {
	let regExp = /^[a-zA-Z]+([/._]?[a-zA-Z0-9]+)*$/;
	return regExp.test(login);
}

function verifyPinCode(pin) {
	let regExp = /^[0-9]{4}$/;
	return regExp.test(pin);
}

function verifyDate(date) {
	let regExp = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	return regExp.test(date);
}

function getMd5Hash(input) {
	return CryptoJS.MD5(input).toString();
}