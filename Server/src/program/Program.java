package program;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.Database;
import server.MultiServerThread;

/**
 * Main server class<br>
 * Accepts the connection and launches every new client in a new thread
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

    try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

      System.out.println("Server started at ip: " + InetAddress.getLocalHost().getHostAddress());
      System.out.println("Press Ctrl+C to close");

      while (!serverSocket.isClosed()) {
        // waiting for a new client to connect
        executor.execute(new MultiServerThread(serverSocket.accept(), db));
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
