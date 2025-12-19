// src/main/java/com/example/demo/servlet/SimpleHelloServlet.java
package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleHelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello from Simple Servlet");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello from Simple Servlet");
    }

    @Override
    public String getServletInfo() {
        return "SimpleHelloServlet";
    }
}
