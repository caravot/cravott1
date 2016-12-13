package com.user.services;

import com.database.DatabaseSQLite;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUser extends HttpServlet {
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

        // get parameters from the request
        String id = request.getParameter("id");

        // database connection
        Connection connection = null;

        try {
            // create a database connection and prepare statement
            connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // create delete statement for database
            String sql = "DELETE FROM person WHERE id = " + id;

            // execute SQL statement
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // close database connection
        DatabaseSQLite.closeConnection(connection);

        // redirect to the view
        response.sendRedirect("/admin/users.jsp");
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