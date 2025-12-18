package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/hello-servlet")
public class SimpleHelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            res.setContentType("text/plain");
            res.getWriter().write("Hello from Simple Servlet");
        } catch (Exception ignored) {}
    }

    public String getServletInfo() {
        return "SimpleHelloServlet";
    }
}
