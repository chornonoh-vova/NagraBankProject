package program;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.Database;
import server.MultiServerThread;

/**
 * Main server class<br>
 * Accepts te connection and launches every new client in a new thread
 * 
 * @see server.MultiServerThread
 */
public class Program {
  static ExecutorService executor = Executors.newFixedThreadPool(10);

  public static void main(String[] args) {
    int portNumber = 4444;
    Database db = new Database();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        System.out.println("Closing connection with database...");
        db.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, "db-close"));

    try (ServerSocket serverSocket = new ServerSocket(portNumber);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

      System.out.println("Server started at ip: " + InetAddress.getLocalHost().getHostAddress());
      System.out.println("Avalaible commands:\n\tquit to exit from program");
      System.out.println("\t(one client will be accepted and then server will be closed)");

      while (!serverSocket.isClosed()) {
        if (br.ready()) {
          String serverCommand = br.readLine();
          if (serverCommand.equalsIgnoreCase("quit")) {
            System.out.println("Closing server...");
            executor.shutdown();
            br.close();
            serverSocket.close();
            System.exit(0);
          }
        }
        // waiting for a new client to connect
        executor.execute(new MultiServerThread(serverSocket.accept(), db));
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
