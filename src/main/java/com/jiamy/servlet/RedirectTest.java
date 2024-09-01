package com.jiamy.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_MOVED_PERMANENTLY;

@WebServlet(urlPatterns = "/hi")
public class RedirectTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setStatus(SC_MOVED_PERMANENTLY);
        resp.setHeader("Location", "hello");
        //resp.sendRedirect("/hello?name="+name);
    }
}
