<%@include file="header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.yummly.models.Recipe" %>
<%@ page import="com.yummly.models.SearchResult" %>

<jsp:useBean id="GetRecipes" class="com.yummly.services.RecipeSearchService" scope="page" />

<%
    // get attributes from the request
    SearchResult result = GetRecipes.findRecipes("chicken");
    ArrayList<Recipe> recipeList = result.getMatches();

%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Description</td>
                </tr>
                </thead>
                <tbody>
                <%
                    Iterator<Recipe> iter = recipeList.iterator();
                    while (iter.hasNext()) {
                        Recipe recipe = iter.next();
                %>
                <tr>
                    <td><a href="recipe.jsp?id=<%= recipe.getId() %>"><%= recipe.getId() %></a></td>
                    <td><%= recipe.getName() %></td>
                    <td><%= recipe.getFlavors() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>