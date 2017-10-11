package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public Client() throws IOException {
		socket = new Socket("8.8.8.8", 4444);
		out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public String[] getMessage(String fromServer) {
		Gson gson = new Gson();
		return gson.fromJson(fromServer, String[].class);
	}

	public void sendMessage(String... args) {
		Gson gson = new Gson();
		out.println(gson.toJson(args));
	}
}
