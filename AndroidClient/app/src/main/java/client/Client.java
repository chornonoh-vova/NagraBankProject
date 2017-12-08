package client;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class for sending data to and from server
 *
 * @see java.net.Socket
 */
public class Client {
  private int port = 4444;

  private Socket socket = null;
  private BufferedReader in = null;
  private PrintWriter out = null;

  public Gson gson = new Gson();

  public Client() {
  }

  public void openConnection(@NonNull String ip) throws Exception {
//    if (socket != null) {
//      sendMessage("close");
//      closeConnection();
//    }
    try {
      this.socket = new Socket(ip, port);
      this.out = new PrintWriter(this.socket.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    } catch (IOException e) {
      throw new Exception("Cannot create socket: " + e.getMessage());
    }
  }

  public void closeConnection() throws Exception {
    if (this.socket != null && !this.socket.isClosed()) {
      try {
        this.socket.close();
      } catch (IOException e) {
        throw new Exception("Cannot close socket: " + e.getMessage());
      } finally {
        this.socket = null;
      }
    }
    this.socket = null;
  }

  public String getMessage() throws IOException {
    return in.readLine();
  }

  public String[] getArrayFromMessage() throws Exception {
    return gson.fromJson(getMessage(), String[].class);
  }

  public void sendMessage(String... args) throws Exception {
    if (this.socket == null || this.socket.isClosed()) {
      throw new Exception("Cannot send data. Socket not created or closed");
    }
    out.println(this.gson.toJson(args));
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    sendMessage("close");
    closeConnection();
  }
}
