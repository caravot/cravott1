
<%@ page import="com.user.model.User" %>

<%
    // get attributes from the request for sessionUser
    User sessionUser = (User)request.getSession().getAttribute("user");
    boolean loggedIn = false;

    // create default user
    if (sessionUser != null && sessionUser.getName() != null) {
        loggedIn = true;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="SHORTCUT ICON" type="image/x-icon" href="/assets/images/favicon.gif">

    <title>Recipeery</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <link rel="stylesheet" href="/assets/css/bootstrap-theme-cosmo.css">
    <link rel="stylesheet" href="/assets/css/main.css">

    <!-- JavaScript Files -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/assets/js/main.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Recipeery</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/users.jsp">User List</a></li>
                <li class="dropdown">
                    <%-- user is logged in --%>
                    <% if (loggedIn) { %>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">
                            <img src="http://lorempixel.com/25/25/abstract" class="img-circle" alt="User: Icon">
                            <%= sessionUser.getName() %>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/logout">Logout</a></li>
                            <li><a href="/user/profile.jsp">Profile</a></li>
                        </ul>
                    <%-- user is not logged in --%>
                    <% } %>
                    <% if (!loggedIn) { %>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">
                            User
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/login.jsp">Login</a></li>
                            <li><a href="/user/signup.jsp">Signup</a></li>
                        </ul>
                    <% } %>
                </li>
            </ul>
        </div>
    </div>
</nav>