package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.DriverManager;
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

    public static ArrayList<User> getAllusers() {
        String sql = "SELECT * FROM person";
        ArrayList<User> userList =  new ArrayList<User>();
        Connection connection = null;

        try {
            // create a database connection and prepare statement
            connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            System.out.println(sql);
            // execute SQL statement
            ResultSet rs  = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("twitter"),
                        rs.getString("description")
                );
                user.setId(rs.getInt("id"));
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

    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get path that we came from
        String pathInfo = request.getServletPath();

        // redirect url
        String url = "/user/users.jsp";

        // get list of all users
            String sql = "select * from person";
            ArrayList<User> userList;

            try {
                userList = DatabaseSQLite.executeReturnStatement(sql);

                request.setAttribute("userList", userList);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

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
