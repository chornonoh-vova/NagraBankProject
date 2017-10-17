package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import client.Client;

public class Test {

	public static void main(String[] args) throws IOException {
		try (
	            Socket kkSocket = new Socket("192.168.1.124", 4444);
	            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(
	                new InputStreamReader(kkSocket.getInputStream()));
	        ) {
//	            Scanner input = new Scanner(System.in);
//	            String fromServer;
//	            String[] fromUser = {"registry", "11111", "test", "1985-12-30", "errerer", "trdfdfdf"};
//
//                Gson gson = new Gson();
//                out.println(gson.toJson(fromUser));
//                
//	            while ((fromServer = in.readLine()) != null) {
//	                System.out.println("Server: " + fromServer);
//	                if (fromServer.equals("Bye."))
//	                    break; 
//	            }
			Client client = new Client(kkSocket, out, in);
			client.sendMessage("update", "1");
			String fromServer;
			while ((fromServer = client.getMessage()) != null) {
	            System.out.println("Server: " + fromServer);
	            if (fromServer.equals("Bye."))
	                break;
	        }
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host ");
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to ");
	            System.exit(1);
	        }
		
	}

}
