package com.user.services;

import com.database.DatabaseSQLite;
import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUser extends HttpServlet {
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        String id = request.getParameter("id");
        Connection connection = null;

        try {
            // create a database connection and prepare statement
            connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String sql = "DELETE FROM person WHERE id = " + id;
            System.out.println(sql);
            // execute SQL statement
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DatabaseSQLite.closeConnection(connection);
        }

        // forward request and response to the view
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("/users.jsp");
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}