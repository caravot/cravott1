<%@include file="/includes/header.jsp" %>

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
            <form method="get">
                <label for="q">Search Term</label> <input type="text" name="q" id="q">
            </form>
        </div>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>