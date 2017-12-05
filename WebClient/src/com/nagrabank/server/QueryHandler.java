package com.nagrabank.server;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.util.EntityUtils;

import com.nagrabank.database.Database;

public class QueryHandler implements HttpRequestHandler {
  private Database db;
  
  public QueryHandler(Database db) {
    this.db = db;
  }
  
  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context)
      throws HttpException, IOException {
    String method = request.getRequestLine().getMethod().toString();
    
    if (!method.equals("POST")) {
      throw new MethodNotSupportedException(method + " method not supported");
    }
    
    HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
    
    String requestBody = EntityUtils.toString(entity, "UTF-8");
    System.out.println("request from user: " + requestBody);
    
    String responseBody = db.processInput(requestBody);
    response.setEntity(new StringEntity(responseBody, ContentType.create("application/json")));

    System.out.println("response from server: " + responseBody);
  }

}
