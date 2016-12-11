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
import java.util.ArrayList;

/**
 * Created by carrie on 12/11/16.
 */
public class GetUsers extends HttpServlet {
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
