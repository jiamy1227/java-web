package com.jiamy.servlet.jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getUser")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user1 = (User) req.getAttribute("user");
        User user = new User();
        user.setName("张三");
        user.setAge(30);

        req.setAttribute("user", user);

        req.getRequestDispatcher("/user/UserInfo.jsp").forward(req,resp);
    }
}
