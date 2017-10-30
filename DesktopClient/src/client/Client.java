package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;

/**
 * Class for sending data to and from server
 * 
 * @see java.net.Socket
 */
public class Client {
	private static Client uniqueInstance = new Client();
	@SuppressWarnings("unused")
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private Client() {
		try {
			Socket socket = new Socket("192.168.82.110", 4444);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.socket = socket;
			this.out = out;
			this.in = in;
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public String getMessage() throws IOException {
		return in.readLine();
	}

	public String[] getArrayFromMessage() throws IOException {
		Gson gson = new Gson();
		return gson.fromJson(getMessage(), String[].class);
	}

	public void sendMessage(String... args) {
		Gson gson = new Gson();
		out.println(gson.toJson(args));
	}

	public static Client getInstance() {
		return uniqueInstance;
	}
}
