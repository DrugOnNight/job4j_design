package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    StringBuilder request = new StringBuilder();
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        request.append(str);
                    }
                    String parameter = request.toString().split(" ")[1];
                    if ("/?msg=Hello".equals(parameter)) {
                        out.write("Hello, dear friend!".getBytes());
                    } else if ("/?msg=Bye".equals(parameter)) {
                        server.close();
                    } else if (parameter.contains("/?msg=")) {
                        out.write("What?".getBytes());
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Bot error", e);
        }
    }
}