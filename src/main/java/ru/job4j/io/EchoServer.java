package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    Pattern pattern = Pattern.compile("\\bmsg=\\S+\\b");
                    Matcher matcher = pattern.matcher(input.readLine());
                    if (matcher.find()) {
                        String text = matcher.group();
                        if (text.equals("msg=Exit")) {
                            server.close();
                        } else {
                            String[] separatorText = text.split("=");
                            output.write(separatorText[1].getBytes());
                        }
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Port 9000 is already in use", e);
        }
    }
}