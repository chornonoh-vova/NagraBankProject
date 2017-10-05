package test;

import java.io.IOException;
import java.net.ServerSocket;

import server.MultiServerThread;

//class for server daemon
public class Program {
	//starting server and starts executing every new client in a new thread
	public static void main(String[] args) throws IOException {
		int portNumber = 4444;
		boolean listening = true;
		//try with resources	
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
