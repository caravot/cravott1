package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

public class AddUser extends HttpServlet {
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String twitter = request.getParameter("twitter");
        String description = request.getParameter("description");

        //User user = new User(name, email, twitter, description);
        Connection connection = null;

        // redirect url
        String url = "/user/profile.jsp";

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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DatabaseSQLite.closeConnection(connection);
        }

        // get inserted user
        User user = GetUsers.getUserByName(name);

        // store the user object in the session
        request.getSession().setAttribute("user", user);

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