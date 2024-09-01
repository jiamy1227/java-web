package com.jiamy.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {

    private static final Logger logger = Logger.getLogger(HttpServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new SocketHandlerThread(socket).start();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "服务错误");
        }
    }
}

class SocketHandlerThread extends Thread {

    private final Socket socket;

    public SocketHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try (InputStream inputStream = this.socket.getInputStream();
             OutputStream outputStream = this.socket.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String firstLine = reader.readLine();
            boolean requestOk = false;
            if ("GET / HTTP/1.1".equals(firstLine)) {
                requestOk = true;
            }
            while(true){
                String header = reader.readLine();
                if(header.isEmpty()) {
                    // 空行以后开始请求体 读取流的时候不可以
                    //System.out.println(reader.readLine());
                    break;
                }
                System.out.println(header);
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            String resultData = "hello jiamingyang";
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + resultData.length() + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(resultData);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
