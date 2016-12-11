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
        <h1>Recipeery</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <%
                Iterator<User> iter = userList.iterator();
                while (iter.hasNext()) {
                    User user = iter.next();
            %>
                ID: <%= user.getId() %> | Name: <%= user.getName() %> | Email: <%= user.getEmail() %><br />
            <%
                }
            %>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>