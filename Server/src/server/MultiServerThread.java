package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Thread to handle one client requests
 */
public class MultiServerThread implements Runnable {
	private Socket socket = null;
	private Database db = null;

	public MultiServerThread(Socket socket, Database db) {
		this.socket = socket;
		this.db = db;
		System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());
	}

	@Override
	public void run() {
		/*
		 * try with resources (out - to client, in - to server)
		 */
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			String inputLine, outputLine;

			while (!socket.isClosed()) {
				// process message from client (execute specific query)
			  inputLine = in.readLine();
				System.out.println("from user:" + inputLine);
				if (inputLine.equals("[\"logout\"]") || inputLine.equals("[\"close\"]")) {
					System.out.println("Closing connection with " + socket.getInetAddress().getHostAddress());
					break;
				}
				outputLine = db.processInput(inputLine);
				// send message to client
				System.out.println("to user:" + outputLine);
				out.println(outputLine);
			}
			// close session
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}