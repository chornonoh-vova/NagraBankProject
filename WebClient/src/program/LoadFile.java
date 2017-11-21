package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoadFile implements HttpHandler {

  @Override
  public void handle(HttpExchange arg0) throws IOException {
    arg0.sendResponseHeaders(200, 0);
    String path = arg0.getRequestURI().toString();
    path = path.replaceFirst("/", "");
    System.out.println("Request to load file: " + path);
    BufferedReader br = new BufferedReader(new FileReader(path));
    PrintWriter out = new PrintWriter(arg0.getResponseBody());
    String currentLine = null;
    String file = new String();
    while ((currentLine = br.readLine()) != null) {
      file += currentLine;
    }
    br.close();
    if (path.equals("js/client.js")) {
      file = file.replaceFirst("localhost", Inet4Address.getLocalHost().getHostAddress());
    }
    out.print(file);
    out.close();
    arg0.close();
  }

}
