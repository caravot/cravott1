
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
            <a class="navbar-brand" href="/">
                <i class="fa fa-beer" aria-hidden="true"></i> +
                <i class="fa fa-cutlery" aria-hidden="true"></i> =
                Recipeery
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/users.jsp"><i class="fa fa-users" aria-hidden="true"></i> User List</a></li>
                <li><a href="/admin/users.jsp"><i class="fa fa-lock" aria-hidden="true"></i> Admin</a></li>
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
                            <li><a href="/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a></li>
                            <li><a href="/user/profile.jsp"><i class="fa fa-id-card" aria-hidden="true"></i>Profile</a></li>
                        </ul>
                    <%-- user is not logged in --%>
                    <% } %>
                    <% if (!loggedIn) { %>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user" aria-hidden="true"></i> User
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/login.jsp"><i class="fa fa-sign-in" aria-hidden="true"></i> Login</a></li>
                            <li><a href="/user/signup.jsp"><i class="fa fa-user-plus" aria-hidden="true"></i> Signup</a></li>
                        </ul>
                    <% } %>
                </li>
            </ul>
            <form action="/beer/beers.jsp" method="get" class="navbar-form navbar-right">
                <div class="form-group">
                    <label for="q" class="sr-only">Search Term:</label>
                    <input type="text" class="form-control" id="q" name="q" placeholder="Beer Search">
                </div>
            </form>
        </div>
    </div>
</nav>