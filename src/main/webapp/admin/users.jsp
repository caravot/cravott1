<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*" %>
<%@ page import="com.user.model.User" %>

<jsp:useBean id="GetUsers" class="com.user.services.GetUsers" scope="page"/>

<%
    // get attributes from the request
    ArrayList<User> userList = GetUsers.getAllusers();
%>

<div class="container">
    <div>
        <h1>User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Twitter</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Iterator<User> iter = userList.iterator();
                    while (iter.hasNext()) {
                        User user = iter.next();
                %>
                <tr>
                    <td>
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <%= user.getName() %>
                    </td>
                    <td>
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                        <a href="<%= user.getEmail() %>" title="Send Email">
                            <%= user.getEmail() %>
                        </a>
                    </td>
                    <td>
                        <i class="fa fa-twitter" aria-hidden="true"></i>
                        <a href="https://twitter.com/<%= user.getTwitter() %>" target="_blank" title="Twitter Account">
                            <%= user.getTwitter() %>
                        </a>
                    </td>
                    <td>
                        <%= user.getDescription() %>
                    </td>
                    <td>
                        <a href="/admin/profile.jsp?id=<%= user.getId() %>" title="Update User"><i class="fa fa-pencil"></i></a>
                        <a href="/deleteUser/?id=<%= user.getId() %>" title="Delete User" class="delete-user"><i class="fa fa-trash"></i></a>
                    </td>
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



