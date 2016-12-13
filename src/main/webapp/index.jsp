<%@include file="includes/header.jsp" %>

<%@page import="java.util.*"%>

<%--<jsp:useBean id="tmp" type="com.user.services.GetUsers" scope="request" />--%>

<%
    // get attributes from the request
    String userList = (String) request.getAttribute("userList");

    // handle null values
    if (userList == null) {
        userList = "";
    }
%>

<div class="container">
    <div align="center">
        <h1>Recipeery</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h3>User</h3>
            <ul>
                <li><a href="/user/signup.jsp">Signup</a></li>
                <li><a href="/user/profile.jsp">Profile</a></li>
                <li><a href="/user/users.jsp">User List</a></li>
            </ul>
            <h3>Admin</h3>
            <ul>
                <li><a href="/admin/users.jsp">User List</a></li>
                <li><a href="/admin/addUser.jsp">Add User</a></li>
            </ul>
            <h3>Beer</h3>
            <ul>
                <li><a href="/beer/beers.jsp">Beers</a></li>
            </ul>
            <h3>Recipes</h3>
            <ul>
                <li><a href="/food/recipes.jsp">Recipes</a></li>
            </ul>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>