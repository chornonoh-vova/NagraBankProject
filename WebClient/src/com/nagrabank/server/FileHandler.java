package com.nagrabank.server;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.util.EntityUtils;

public class FileHandler implements HttpRequestHandler {

  public FileHandler() {
    super();
  }

  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context)
      throws HttpException, IOException {
    String method = request.getRequestLine().getMethod().toUpperCase(Locale.ROOT);
    if (!method.equals("GET") && !method.equals("HEAD")) {
        throw new MethodNotSupportedException(method + " method not supported");
    }
    String target = request.getRequestLine().getUri();

    if (request instanceof HttpEntityEnclosingRequest) {
        HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
        byte[] entityContent = EntityUtils.toByteArray(entity);
        System.out.println("Incoming entity content (bytes): " + entityContent.length);
    }

    target = target.replaceFirst("/", "");
    
    final File file = new File("site", URLDecoder.decode(target, "UTF-8"));
    if (!file.exists()) {

        response.setStatusCode(HttpStatus.SC_NOT_FOUND);
        StringEntity entity = new StringEntity(
                "<html><body><h1>File" + file.getPath() +
                " not found</h1></body></html>",
                ContentType.create("text/html", "UTF-8"));
        response.setEntity(entity);
        System.out.println("File " + file.getPath() + " not found");

    } else if (!file.canRead() || file.isDirectory()) {

        response.setStatusCode(HttpStatus.SC_FORBIDDEN);
        StringEntity entity = new StringEntity(
                "<html><body><h1>Access denied</h1></body></html>",
                ContentType.create("text/html", "UTF-8"));
        response.setEntity(entity);
        System.out.println("Cannot read file " + file.getPath());

    } else {
        HttpCoreContext coreContext = HttpCoreContext.adapt(context);
        HttpConnection conn = coreContext.getConnection(HttpConnection.class);
        response.setStatusCode(HttpStatus.SC_OK);
        FileEntity body = null;
        if (target.endsWith(".html")) {
          body = new FileEntity(file, ContentType.create("text/html", "UTF-8"));
        } else if (target.endsWith(".css")) {
          body = new FileEntity(file, ContentType.create("text/css", "UTF-8"));
        } else if (target.endsWith(".js")) {
          body = new FileEntity(file, ContentType.create("application/javascript", "UTF-8"));
        } else if (target.endsWith(".jpg")) {
          body = new FileEntity(file, ContentType.create("image/jpeg", (Charset)null));
        } else if (target.endsWith(".png")) {
          body = new FileEntity(file, ContentType.create("image/png", (Charset)null));
        } else {
          body = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
        }
        response.setEntity(body);
        System.out.println(conn + ": serving file " + file.getPath());
    }
  }

}
