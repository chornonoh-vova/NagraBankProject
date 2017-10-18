package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;

/**
 * Class for sending data to and from server
 * @see java.net.Socket
 */
public class Client {
	private Socket socket;
	private PrintWriter out;
	public BufferedReader in;

	

	public Client(Socket socket, PrintWriter out, BufferedReader in) {
		super();
		this.socket = socket;
		this.out = out;
		this.in = in;
	}

	public String getMessage() throws IOException {
		return in.readLine();
	}

	public void sendMessage(String... args) {
		Gson gson = new Gson();
		out.println(gson.toJson(args));
	}
}
