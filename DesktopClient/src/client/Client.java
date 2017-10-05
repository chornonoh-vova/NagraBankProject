package client;

import java.net.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("192.168.1.78", 3128)){
			Scanner input = new Scanner(System.in);
			//input.useDelimiter("/");
			
			//String queryToServer = "login/test/1234";
			socket.getOutputStream().write(input.next().getBytes());
			socket.getOutputStream().write(input.next().getBytes());
			socket.getOutputStream().write(String.valueOf(input.nextInt()).getBytes());
			
			byte buf[] = new byte[64 * 1024];
			
			int realRead = socket.getInputStream().read(buf);
			
			String data = new String(buf, 0, realRead);
			
			System.out.println(data);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
