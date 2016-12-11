package com.database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DatabaseTest extends HttpServlet {
    // forward all get actions to the post method
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DatabaseSQLite.main(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
