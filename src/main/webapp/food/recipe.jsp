<%@include file="../includes/header.jsp" %>

<%@ page import="com.yummly.models.*" %>
<%@page import="org.apache.commons.lang3.*"%>
<%@ page import="java.util.List" %>

<jsp:useBean id="GetRecipes" class="com.yummly.services.RecipeSearchService" scope="page" />

<%
    // get attributes from the request
    Recipe recipe = GetRecipes.getRecipeById(request.getParameter("id"));
    Source source = recipe.getSource();
    Number timeToMake = recipe.getTotalTimeInSeconds();
    List<Images> images  = recipe.getImages();
    com.yummly.models.Images image = new com.yummly.models.Images();

    // use recipes image if available
    if (images.size() > 0) {
        image = images.get(0);
    }
%>

<div class="container yummly-recipe">
    <div>
        <h1><%= recipe.getName() %></h1>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="<%= image.getHostedLargeUrl() %>" alt="Image of Food">
        </div>
        <div class="col-md-8">
            <p><strong>Basic Information</strong></p>
            <p>
                <%= recipe.getIngredientLines().size() %> Ingredients |
                <%= timeToMake != null ? (Integer)timeToMake / 60 : 0 %> Minutes |
                <%= recipe.getRating() %> Stars |
                <%= recipe.getNumberOfServings() %> Yield
            </p>

            <p><strong>Ingredients</strong></p>
            <%= StringUtils.join(recipe.getIngredientLines(), "<br/>") %>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            View the Recipe at <a href="<%= source.getSourceRecipeUrl() %>"><%= source.getSourceDisplayName() %></a>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp" %>