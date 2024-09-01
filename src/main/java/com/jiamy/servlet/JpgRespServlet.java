package com.jiamy.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/download/jpg")
public class JpgRespServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        File file = new File("C:\\Users\\10494\\OneDrive\\桌面\\test.png");
        InputStream inputStream = Files.newInputStream(file.toPath());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bArray = new byte[inputStream.available()];
        bufferedInputStream.read(bArray);
        OutputStream outputStream = resp.getOutputStream();
        outputStream.write(bArray);
        outputStream.flush();


    }
}
