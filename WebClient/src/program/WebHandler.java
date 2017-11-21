package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.sun.net.httpserver.*;

import server.Database;

public class WebHandler implements HttpHandler{
  private Database db = null;
  
  public WebHandler(Database db) {
    this.db = db;
  }
  
  @Override
  public void handle(HttpExchange arg0) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(arg0.getRequestBody()));
    String request = br.readLine();
    System.out.println("From user: " + request);
    String outputLine = db.processInput(request);
    System.out.println("Answer to user: " + outputLine);
    PrintWriter out = new PrintWriter(arg0.getResponseBody());
    arg0.sendResponseHeaders(200, 0);
    out.print(outputLine);
    br.close();
    out.close();
    arg0.close();
  }

}
