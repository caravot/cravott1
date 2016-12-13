<%@include file="../includes/header.jsp" %>

<%@ page import="com.user.model.User" %>

<div class="container">
    <div align="center">
        <h1>Signup</h1>
    </div>
    <%
        // get attributes from the request
        String errorMessage = (String) request.getAttribute("errorMessage");
        User user = (User)request.getSession().getAttribute("user");

        // create default user
        if (user == null) {
            user = new User();
        }

        // handle null values
        if (errorMessage != null) {
    %>
    <div class="row">
        <div class="col-md-12 error-message">
            <p>Warning! <%= errorMessage %></p>
        </div>
    </div>

    <%
        }
    %>
    <div class="row">
        <div class="col-md-12">
            <form action="/addUser" method="post" class="form-horizontal top15 user-profile" name="mod3">
                <input type="hidden" id="id" name="id" value="0">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Username (Required)</label>
                    <div class="col-sm-10">
                        <input type="name" class="form-control" name="name" id="name" value="<%= user.getName() %>">
                    </div>
                </div>
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