<%@include file="header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.yummly.models.Recipe" %>

<jsp:useBean id="GetRecipes" class="com.yummly.services.RecipeSearchService" scope="page" />

<%
    // get attributes from the request
    Recipe recipe = GetRecipes.getRecipeById(request.getParameter("id"));
%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <img src="<%= recipe.getAttribution().getLogo() %>">
            getId: <%= recipe.getId() %><br/>
            getName: <%= recipe.getName() %><br/>
            getAttribution: <%= recipe.getAttribution().getText() %><br/>
            getIngredientLines: <%= recipe.getIngredientLines() %><br/>
            getTotalTime: <%= recipe.getTotalTime() %><br/>
            getRating: <%= recipe.getRating() %><br/>
            getNumberOfServings: <%= recipe.getNumberOfServings() %><br/>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>