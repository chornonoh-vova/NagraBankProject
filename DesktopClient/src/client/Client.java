package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	public Client() throws UnknownHostException, IOException {
		socket = new Socket("8.8.8.8", 4444);
		
	}
	
	
}
