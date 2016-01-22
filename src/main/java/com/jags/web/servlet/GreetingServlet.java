package com.jags.web.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by JParvathaneni on 1/21/16.
 */
public class GreetingServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(GreetingServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("in doGet method...");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<form method=\"post\">");
        writer.println("Please enter your name: ");
        writer.println("<input type=\"text\" name=\"greetingName\">");
        writer.println("</form>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("in doPost method...");
        response.setContentType("text/html");
        String name = request.getParameter("greetingName");
        PrintWriter writer = response.getWriter();
        writer.println("<h1 style=\"color:red\">Hello, " + name + "</h1>");
    }
}
