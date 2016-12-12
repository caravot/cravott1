<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.yummly.models.*" %>

<jsp:useBean id="GetRecipes" class="com.yummly.services.RecipeSearchService" scope="page" />

<%
    // get request parameter if available
    String q = "";
    SearchResult result = null;
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

    // get attributes from the request
    if (request.getParameter("q") != null) {
        q = request.getParameter("q");
        result = GetRecipes.findRecipes(q);

        recipeList = result.getMatches();
    }

%>

<div class="container">
    <div>
        <h1>Get Recipes</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form method="get">
                <label for="q">Search Term: <input type="text" name="q" id="q" size="100" value="<%= q %>"></label>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td>Name</td>
                    <td>Courses</td>
                    <td>Rating</td>
                    <td>Ingredients</td>
                    <td>Time</td>
                    <td>Source/Link</td>
                </tr>
                </thead>
                <tbody>
                <%
                    Iterator<Recipe> iter = recipeList.iterator();
                    while (iter.hasNext()) {
                        Recipe recipe = iter.next();
                        List<String> courses = recipe.getAttributes().getCourse();
                        List<String> ingredientLines = recipe.getIngredientLines();
                        Number timeToMake = recipe.getTotalTimeInSeconds();
                        Source source = recipe.getSource();
                %>
                <tr>
                    <td><a href="recipe.jsp?id=<%= recipe.getId() %>"><%= recipe.getName() %></a></td>
                    <td><%= courses %></td>
                    <td><%= recipe.getRating() %></td>
                    <td><%= ingredientLines %></td>
                    <td><%= (Integer)timeToMake / 60 %> min</td>
                    <td><%= source.getSourceDisplayName() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp" %>