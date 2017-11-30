package com.nagrabank.server;

import java.net.SocketTimeoutException;

import org.apache.http.ConnectionClosedException;
import org.apache.http.ExceptionLogger;

public class ErrorLogger implements ExceptionLogger {

  @Override
  public void log(Exception ex) {
    if (ex instanceof SocketTimeoutException) {
      System.err.println("Connection timed out");
    } else if (ex instanceof ConnectionClosedException) {
      System.err.println(ex.getMessage());
    } else {
      ex.printStackTrace();
    }
  }
}
