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
	private static Client uniqueInstance;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client(String ip) {
		try {
			Socket socket = new Socket(ip, 4444);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.socket = socket;
			this.out = out;
			this.in = in;
			uniqueInstance = this;
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public String getMessage() throws IOException {
	  if (isClosed()) {
	    return "[\"error\",\"connection closed\"]";
	  }
		return in.readLine();
	}

	public String[] getArrayFromMessage() throws IOException {
		Gson gson = new Gson();
		return gson.fromJson(getMessage(), String[].class);
	}

	public void sendMessage(String... args) {
	  if (isClosed()) {
	    return;
	  }
		Gson gson = new Gson();
		out.println(gson.toJson(args));
	}

	public static Client getInstance() {
		return uniqueInstance;
	}
	
	public boolean isClosed() {
	  return socket.isClosed();
	}
}
