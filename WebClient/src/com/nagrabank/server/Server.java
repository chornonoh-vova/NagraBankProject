package com.nagrabank.server;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;

import com.nagrabank.database.Database;

public final class Server {

  public static void main(String[] args) throws Exception {
    SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(15000).setTcpNoDelay(true)
        .build();
    System.out.println("Server started at ip: " + InetAddress.getLocalHost().getHostAddress());
    Database db = new Database();
    final HttpServer server = ServerBootstrap.bootstrap().setListenerPort(80)
        .setServerInfo("NagrabankServer/1.1").setSocketConfig(socketConfig).setSslContext(null)
        .setExceptionLogger(new ErrorLogger()).registerHandler("*", new FileHandler())
        .registerHandler("/query", new QueryHandler(db)).create();

    server.start();
    server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      server.shutdown(0, TimeUnit.SECONDS);
    }, "server-close"));
  }
}
