package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoadPage implements HttpHandler {

  @Override
  public void handle(HttpExchange arg0) throws IOException {
    arg0.sendResponseHeaders(200, 0);
    StringBuilder path = new StringBuilder(arg0.getRequestURI().toString());
    path.deleteCharAt(0);
    path.append(".html");
    System.out.println("Request to load page: " + path);
    BufferedReader br = new BufferedReader(new FileReader(path.toString()));
    PrintWriter out = new PrintWriter(arg0.getResponseBody());
    String currentLine = null;
    StringBuilder sb = new StringBuilder();
    while ((currentLine = br.readLine()) != null) {
      sb.append(currentLine);
    }
    br.close();
    out.print(sb.toString());
    out.close();
    arg0.close();
  }

}
