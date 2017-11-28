package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoadFile implements HttpHandler {

  @Override
  public void handle(HttpExchange arg0) throws IOException {
    arg0.sendResponseHeaders(200, 922);
    String path = arg0.getRequestURI().toString();
    path = path.replaceFirst("/", "");
    System.out.println("Request to load file: " + path);
    if (path.endsWith("js")) {
      arg0.setAttribute("Content-type", "application/javascript");
      arg0.sendResponseHeaders(200, 0);
    } else if (path.endsWith("css")) {
      arg0.setAttribute("Сontent-type", "text/css");
    }
    BufferedReader br = new BufferedReader(new FileReader(path));
    PrintWriter out = new PrintWriter(arg0.getResponseBody());
    String currentLine = new String();
    while ((currentLine = br.readLine()) != null) {
      if (path.equals("js/client.js")) {
        currentLine = currentLine.replaceFirst("localhost", InetAddress.getLocalHost().getHostAddress());
      }
      out.print(currentLine);
    }
    br.close();
    out.close();
    arg0.close();
  }

}
