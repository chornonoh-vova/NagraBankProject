package server;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
public class Server extends Thread{
  Socket socket;
  int number;
  Database database = new Database("jdbc:mysql://localhost:3306/bank_users", "root", "5787"); 
  public Server(int i, Socket accept) {
    number = i;
    socket = accept;
    
    setDaemon(true);
    setPriority(NORM_PRIORITY);
    start();
  }
  
  @Override
  public void run() {
    try {
      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();
      
      byte buf[] = new byte[64 * 1024];
      int realRead = inputStream.read(buf);
      String question = new String(buf, 0, realRead);
      String answer = "";
     switch (question) {
	case "login":
	    realRead = inputStream.read(buf);
	    String parameter1 = new String(buf, 0, realRead);
	    realRead = inputStream.read(buf);
	    String parameter2 = new String(buf, 0, realRead);
	    if (database.logIn(parameter1, Integer.valueOf(parameter2))) {
	    	answer += database.getUserInfo(parameter1).toString();
	    } else {
	    	answer += "error";
	    }
		break;

	default:
		break;
	}
      outputStream.write(answer.getBytes());
      System.out.println(answer);
      database.closeConnection();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(3128)){
      int i = 0;
      System.out.println("Server started");
      
      while (true) {
        new Server(i, server.accept());
        ++i;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}