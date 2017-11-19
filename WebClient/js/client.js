class Client {
	constructor(socket) {
		this.socket = socket;
	}

	openConnection() {
		//TODO: open connection
	}

	closeConnection() {
		sendMessage("close");
		//TODO: close connection
	}

	sendMessage(...args) {
		let message = JSON.stringify(args);
		//TODO: send message to a server
	}

	getMessage() {
		//TODO: get message from server
	}

	getArrayFromMessage() {
		let message = this.getMessage();
		return JSON.parse(message);
	}
}