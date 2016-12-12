<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.user.model.User" %>

<jsp:useBean id="GetUsers" class="com.user.services.GetUsers" scope="page" />

<%
    // get attributes from the request
    ArrayList<User> userList = GetUsers.getAllusers();

%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                    <tr><td>ID</td><td>Name</td><td>Email</td><td>&nbsp;</td></tr>
                </thead>
                <tbody>
                    <%
                        Iterator<User> iter = userList.iterator();
                        while (iter.hasNext()) {
                            User user = iter.next();
                    %>
                        <tr>
                                <td><a href="../admin/addUser.jsp?id=<%= user.getId() %>"><%= user.getId() %></a></td>
                                <td><%= user.getName() %></td>
                                <td><%= user.getEmail() %></td>
                                <td><a href="/deleteUser/?id=<%= user.getId() %>">Delete</a></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp" %>