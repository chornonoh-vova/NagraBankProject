package program;

import java.net.Inet4Address;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import server.Database;

public class ServerHttp {

  public static void main(String[] args) throws Exception {
    Database db = new Database();
    HttpServer server = HttpServer.create(new InetSocketAddress(80), 10);
    System.out.println("Server started, type to connect in browser: " + "http://"
        + Inet4Address.getLocalHost().getHostAddress() + "/login");
    server.createContext("/registry", new LoadPage());
    server.createContext("/login", new LoadPage());
    server.createContext("/server", new WebHandler(db));
    server.createContext("/js/client.js", new LoadFile());
    server.createContext("/js/md5.js", new LoadFile());
    server.createContext("/js/util.js", new LoadFile());
    server.createContext("/js/jquery-3.2.1.min.js", new LoadFile());
    server.createContext("/js/login.js", new LoadFile());
    server.createContext("/js/registry.js", new LoadFile());
    server.createContext("/styles.css", new LoadFile());
    server.start();
  }

}
