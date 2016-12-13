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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateUser extends HttpServlet {
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String twitter = request.getParameter("twitter");
        String description = request.getParameter("description");

        // get the variable of where we came from so we redirect correctly
        String page = request.getParameter("page");
        System.out.println("Page: " + page);

        User user = null;
        String url = "/";

        try {
            // create a database connection and prepare statement
            Connection connection = DatabaseSQLite.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String sql = "UPDATE person SET ";
            sql += "name = '" + name + "', ";
            sql += "twitter = '" + twitter + "', ";
            sql += "description = '" + description + "', ";
            sql += "email = '" + email + "' ";
            sql += "WHERE id = " + id;
            System.out.println("com.user.services.updateuser:sql=" + sql);
            // execute SQL statement
            statement.executeUpdate(sql);

            // get inserted user
            user = GetUsers.getUserById(id);

            if ("profile".equals(page)) {
                url = "/user/profile.jsp";

                // update session variable
                System.out.println(user.getName());
                request.getSession().setAttribute("user", user);
            } else {
                url = "/admin/addUser.jsp";
            }

            // forward request and response to the view
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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