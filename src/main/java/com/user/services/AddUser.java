package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;
import com.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

public class AddUser extends HttpServlet {
    /**
     * Get post method actions from servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // remove any existing session objects
        request.removeAttribute("user");
        request.removeAttribute("errorMessage");

        // redirect url
        String url = "/";

        //User user = new User(name, email, twitter, description);
        Connection connection = null;

        // get parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String twitter = request.getParameter("twitter");
        String description = request.getParameter("description");

        // string to hold the error message
        String errorMessage = "";

        // Create new user
        User user = new User(name, email);

        // verify that the required forms are valid
        if (!Utils.validInputString(name) || !Utils.validInputString(email)) {
            errorMessage = "You must provide a name and email address";

            url = "/user/signup.jsp";
        } else {
            // check if the username already exists
            User result = GetUsers.getUserByName(name);

            // if the id is not null a user was found; send error
            if (result != null) {
                errorMessage = "The username '" + name + "' has already been taken.";

                url = "/user/signup.jsp";
            }
        }

        // continue to create a new user if no errors encountered
        if (errorMessage.equals("")) {
            try {
                // create a database connection and prepare statement
                connection = DatabaseSQLite.getConnection();
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // create insert statement for database
                String sql = "INSERT INTO person VALUES(";
                sql += "null, ";
                sql += "'" + name + "', ";
                sql += "'" + email + "', ";
                sql += "'" + twitter + "', ";
                sql += "'" + description + "' ";
                sql += ")";

                // execute SQL statement
                statement.executeUpdate(sql);

                // get inserted user
                user = GetUsers.getUserByName(name);

                // store the user object in the session
                request.getSession().setAttribute("user", user);

                // will redirect the user to profile page
                url = "/user/profile.jsp";
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // close database connection
        DatabaseSQLite.closeConnection(connection);

        // store the user object in the session
        request.setAttribute("user", user);

        // store the error message
        request.setAttribute("errorMessage", errorMessage);

        System.out.print("errorMessage: " + errorMessage);

        // forward request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Forward all HTTP GET to POST method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}