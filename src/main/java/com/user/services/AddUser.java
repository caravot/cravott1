package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

public class AddUser extends HttpServlet {
    String addSQL = "INSERT INTO person VALUES(?,?)";

    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User("Carrie", "carrie.peary@gmail.com");

        String sql = "INSERT INTO person VALUES (" +
                "NULL, " +
                "'" + user.getName() + "', " +
                "'" + user.getEmail() + "'" +
                ")";

        try {
            DatabaseSQLite.executeNoReturnStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}