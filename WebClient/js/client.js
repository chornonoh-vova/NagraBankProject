class Client {
	constructor() {
		this.xhr = new XMLHttpRequest();
	}

	openConnection() {
		this.xhr.open("post", "http://localhost/server", true);
	}

	closeConnection() {
		this.xhr.abort();
	}

	sendMessage(...args) {
		let message = JSON.stringify(args);
		this.xhr.send(message);
	}

	getMessage() {
		return this.xhr.responseText;
	}

	getArrayFromMessage() {
		let message = this.getMessage();
		return JSON.parse(message);
	}
}