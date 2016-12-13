<%@include file="includes/header.jsp" %>


<div class="jumbotron">
    <div class="container text-center">
        <h1>
            <i class="fa fa-beer" aria-hidden="true"></i> +
            <i class="fa fa-cutlery" aria-hidden="true"></i> =
            Recipeery
        </h1>
        <p>Ever have beer left over from a party that you don't know what to do with? Trying to think of what
            you can make for dinner that will pair well with your beer? Look no futher! Recipeery lets you search
            for beer, find recipes that pair with it, and then shows you the recipe!
        </p>

        <form method="get" action="/beer/beers.jsp" class="form-inline">
            <label class="sr-only" for="q">Search Term:</label>
            <input type="text" class="form-control" name="q" id="q" value="" placeholder="Search Term">

            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>
    <%--<div class="row">--%>
        <%--<div class="col-md-12">--%>
            <%--<h3>Admin</h3>--%>
            <%--<ul>--%>
                <%--<li><a href="/admin/users.jsp">User List</a></li>--%>
                <%--<li><a href="/admin/addUser.jsp">Add User</a></li>--%>
            <%--</ul>--%>
            <%--<h3>Beer</h3>--%>
            <%--<ul>--%>
                <%--<li><a href="/beer/beers.jsp">Beers</a></li>--%>
            <%--</ul>--%>
            <%--<h3>Recipes</h3>--%>
            <%--<ul>--%>
                <%--<li><a href="/food/recipes.jsp">Recipes</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>

<%@include file="includes/footer.jsp" %>