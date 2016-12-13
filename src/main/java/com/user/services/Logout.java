package com.user.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Logout extends HttpServlet {
    /**
     * Process get to Logout servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // remove user session object
        request.getSession().removeAttribute("user");

        // forward request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }
}
