<%@include file="../includes/header.jsp" %>

<div class="container">
    <div align="center">
        <h1>Login</h1>
    </div>
    <%
        // get attributes from the request
        String errorMessage = (String) request.getAttribute("errorMessage");

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
            <form action="/login" method="post" class="form-horizontal top15 user-login" name="mod3">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type="name" class="form-control" name="name" id="name" value="">
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