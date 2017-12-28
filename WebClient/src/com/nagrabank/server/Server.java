package com.nagrabank.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;

import com.nagrabank.database.Database;

public final class Server {
  public static String address = null;
  
  public static void main(String[] args) throws Exception {
    SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(15000).setTcpNoDelay(true)
        .build();
    Database db = new Database();
    Enumeration<InetAddress> e = NetworkInterface.getByName("wlp3s0").getInetAddresses();
    while (e.hasMoreElements()) {
    	address = e.nextElement().getHostAddress();
    }
    System.out.println("Server started at ip: " + address);
    System.out.println("Press Ctrl+С to close");
    final HttpServer server = ServerBootstrap.bootstrap().setListenerPort(8080)
        .setServerInfo("NagrabankServer/1.1").setSocketConfig(socketConfig).setSslContext(null)
        .setExceptionLogger(new ErrorLogger()).registerHandler("*", new FileHandler())
        .registerHandler("/query", new QueryHandler(db)).create();

    server.start();
    server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        db.close();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      server.shutdown(0, TimeUnit.SECONDS);
    }, "server-close"));
  }
}
