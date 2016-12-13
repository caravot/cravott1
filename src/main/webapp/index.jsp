<%@include file="includes/header.jsp" %>

<div class="container">
    <div align="center">
        <h1>Recipeery</h1>
    </div>
    <div class="row">
        <div class="col-md-12 text-center">
            <p>Ever have beer left over from a party that you don't know what to do with? Trying to think of what
                you can make for dinner that will pair well with your beer? Look no futher! Recipeery lets you search
                for beer, find recipes that pair with it, and then shows you the recipe!
            </p>
            <p>Search for beer to get started: </p>

            <form method="get" action="/beer/beers.jsp">
                <label for="q">Search Term: <input type="text" name="q" id="q" value=""></label>

                <button type="submit" class="btn btn-default btn-sm">Submit</button>
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
</div>

<%@include file="includes/footer.jsp" %>