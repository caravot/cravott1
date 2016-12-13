<%@include file="../includes/header.jsp" %>

<%@ page import="com.user.model.User" %>

<%
    // global variables for ease of use
    User user = (User)session.getAttribute("user");
%>
<div class="container">
    <div align="center">
        <h1>Profile for <%= user.getName() %></h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form action="/updateUser" method="post" class="form-horizontal top15 user-profile" name="mod3">
                <input type="hidden" id="id" name="id" value="<%= user.getId() %>">
                <input type="hidden" id="name" name="name" value="<%= user.getName() %>">
                <input type="hidden" id="page" name="page" value="profile">

                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email (Required)</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="email" value="<%= user.getEmail() %>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="twitter" class="col-sm-2 control-label">Twitter</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="twitter" id="twitter" value="<%= user.getTwitter() %>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">About Me/Bio</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="description" id="description"><%= user.getDescription() %></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                        <button type="reset" class="btn btn-default">Reset</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp" %>