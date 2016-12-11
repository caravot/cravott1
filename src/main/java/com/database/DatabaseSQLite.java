package com.database;

import java.sql.*;

public class DatabaseSQLite {

    public static void executeNoReturnStatement(String sql) throws SQLException, ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        // prepared statement to help make SQL statements easier to manage
        Connection connection = null;

        try {
            // create a database connection and prepare statement
            connection = DriverManager.getConnection("jdbc:sqlite::resource:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            System.out.println("SQL: " + sql);

            // execute SQL statement
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.err.println(e.getMessage());
        } finally {
            // close database connection
            if (connection != null) {
                connection.close();
            }
        }

        //return rs;
    }

    public static String executeReturnStatement(String sql) throws SQLException, ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        // prepared statement to help make SQL statements easier to manage
        Connection connection = null;
        String str = "";

        try {
            // create a database connection and prepare statement
            connection = DriverManager.getConnection("jdbc:sqlite::resource:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                // read the result set
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("name = " + rs.getString("name"));
                System.out.println("email = " + rs.getString("email"));

                str += ":" + rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.err.println(e.getMessage());
        } finally {
            // close database connection
            if (connection != null) {
                connection.close();
            }
        }

        return str;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite::resource:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");

            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");

            ResultSet rs = statement.executeQuery("select * from person");

            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}