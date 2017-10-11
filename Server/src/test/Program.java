package test;

import java.io.IOException;
import java.net.ServerSocket;

import server.MultiServerThread;

/**
 * Main server class<br>
 * Accepts te connection and launches every new client in a new thread
 * @see server.MultiServerThread
 */
public class Program {
	public static void main(String[] args) {
		int portNumber = 4444;
		boolean listening = true;	
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				//waiting for a new client to connect
				new MultiServerThread(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			//error if port is unavailable
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
}
