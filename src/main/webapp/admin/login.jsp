<%@include file="../includes/header.jsp" %>

<div class="container">
    <div>
        <h1>Login</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form action="j_security_check" method="post" class="form-horizontal top15" name="mod3">
                <div class="form-group">
                    <label for="j_username" class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type="name" class="form-control" name="j_username" id="j_username" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="j_password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="j_password" id="j_password" value="">
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