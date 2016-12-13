<%@include file="../includes/header.jsp" %>

<div class="container">
    <div align="center">
        <h1>Signup</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form action="/addUser" method="post" class="form-horizontal top15" name="mod3">
                <input type="hidden" id="id" name="id" value="0">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="name" class="form-control" name="name" id="name" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="email" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="twitter" class="col-sm-2 control-label">Twitter</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="twitter" id="twitter" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">About Me/Bio</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="description" id="description"></textarea>
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