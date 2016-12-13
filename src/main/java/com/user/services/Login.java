package com.user.services;

import com.Utils;
import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        String name = request.getParameter("name");

        // holding variables for later use
        User user = null;
        String url = "/";

        // string to hold the error message
        String errorMessage = "";

        // verify that the required forms are valid
        if (!Utils.validInputString(name)) {
            errorMessage = "You must provide a name";

            url = "/user/login.jsp";
        } else {
            // check if the username already exists
            User result = GetUsers.getUserByName(name);

            // if the id is not null a user was found; redirect to profile
            if (result != null) {
                // store the user object in the session
                request.getSession().setAttribute("user", result);

                url = "/user/profile.jsp";
            }
            // user not found; send error
            else {
                errorMessage = "The username '" + name + "' was not found";

                url = "/user/login.jsp";
            }
        }

        // store the user object in the session
        request.setAttribute("user", user);

        // store the error message
        request.setAttribute("errorMessage", errorMessage);

        // forward request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
