package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by carrie on 12/11/16.
 */
public class GetUsers extends HttpServlet {
    public static User getUserByName(String name) {
        User user = null;

        try {
            // create a database connection and prepare statement
            Connection connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String sql = "SELECT * FROM person WHERE name = '" + name + "'";

            // execute SQL statement
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("twitter"),
                        rs.getString("description")
                );
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getUserById(String id) {
        User user = null;

        try {
            // create a database connection and prepare statement
            Connection connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // execute SQL statement
            ResultSet rs = statement.executeQuery("SELECT * FROM person WHERE id = " + id);

            while (rs.next()) {
                user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("twitter"),
                        rs.getString("description")
                );
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Get All Users from the database
     * @return ArrayList<User>
     */
    public static ArrayList<User> getAllusers() {
        // query to perform
        String sql = "SELECT * FROM person";

        // return variable
        ArrayList<User> userList =  new ArrayList<User>();

        // database connection
        Connection connection = null;

        try {
            // create a database connection and prepare statement
            connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // execute SQL statement
            ResultSet rs  = statement.executeQuery(sql);

            // loop over return
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("twitter"),
                        rs.getString("description")
                );

                // manually add the userid
                user.setId(rs.getInt("id"));

                // add user to result set
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DatabaseSQLite.closeConnection(connection);
        }

        return userList;
    }

    /**
     * Process post to GetUsers servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // get all users
        ArrayList<User> userList = GetUsers.getAllusers();

        // append userlist to request
        request.setAttribute("userList", userList);

        // forward request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/users.jsp");
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
