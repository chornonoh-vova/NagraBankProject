package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import com.google.gson.Gson;

/**
 * Class for sending data to and from server
 *
 * @see java.net.Socket
 */
public class Client{
    public static String getMessage(BufferedReader in) throws IOException {
        return in.readLine();
    }

    public static String[] getArrayFromMessage(BufferedReader in) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(getMessage(in), String[].class);
    }

    public static void sendMessage(PrintWriter out, String... args) {
        Gson gson = new Gson();
        out.println(gson.toJson(args));
    }

    public static void close(PrintWriter out) {
        sendMessage(out, "close");
    }
}
