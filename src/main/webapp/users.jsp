<%@include file="header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.user.model.User" %>

<%--<jsp:useBean id="tmp" type="com.user.services.GetUsers" scope="request" />--%>

<%
    // get attributes from the request
    ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");

%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                    <tr><td>ID</td><td>Name</td><td>Email</td></tr>
                </thead>
                <tbody>
                    <%
                        Iterator<User> iter = userList.iterator();
                        while (iter.hasNext()) {
                            User user = iter.next();
                    %>
                        <tr>
                                <td><a href="addUser.jsp?id=<%= user.getId() %>"><%= user.getId() %></a></td>
                                <td><%= user.getName() %></td>
                                <td><%= user.getEmail() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>