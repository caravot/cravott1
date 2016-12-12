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
            System.out.println("SQL: " + sql);
            // execute SQL statement
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("email"));
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
                user = new User(rs.getString("name"), rs.getString("email"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String sql = "select * from person";
        ArrayList<User> userList;
        String url = "/users.jsp";

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
