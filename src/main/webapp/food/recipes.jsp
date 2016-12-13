<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>
<%@page import="org.apache.commons.lang3.*"%>

<%@page import="com.yummly.models.*" %>

<jsp:useBean id="GetRecipes" class="com.yummly.services.RecipeSearchService" scope="page" />

<%
    // get request parameter if available
    String q = "";
    SearchResult result = null;
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    Integer totalResult = 0;

    // get attributes from the request
    if (request.getParameter("q") != null) {
        q = request.getParameter("q");
        result = GetRecipes.findRecipes(q);
        totalResult = result.getTotalMatchCount();
        recipeList = result.getMatches();
    }
%>

<div class="container">
    <%-- do not continue if no search results to show --%>
    <%
        if (q == null || q.equals("") || q.length() == 0) {
    %>
    <div class="row">
        <div class="col-md-12">
            <h2>Error</h2>
        </div>
        <div class="col-md-12">
            <p>You must provide a search term</p>
        </div>
    </div>
    <%-- do not continue if no query string passed in --%>
    <%
    } else if (q != "" && totalResult == 0) {
    %>
    <div class="row">
        <div class="col-md-12">
            <h2>Results</h2>
        </div>
        <div class="col-md-12">
            <h3>No Results Found</h3>
        </div>
    </div>
    <%
    } else if (q != "") {
    %>
    <div class="row">
        <div class="col-md-12">
            <h2>Results for "<%= q %>"</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th>Name</th>
                    <th width="250">Courses</th>
                    <th width="75">Rating</th>
                    <th width="75">Time</th>
                    <th width="250">Source</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Iterator<Recipe> iter = recipeList.iterator();
                    while (iter.hasNext()) {
                        Recipe recipe = iter.next();
                        List<String> courses = recipe.getAttributes().getCourse();
                        Number timeToMake = recipe.getTotalTimeInSeconds();
                        Source source = recipe.getSource();
                        Attribution attribution = recipe.getAttribution();
                %>
                    <tr>
                        <td><a href="recipe.jsp?id=<%= recipe.getId() %>"><%= recipe.getName() %></a></td>
                        <td><%= StringUtils.join(courses, "<br/>") %></td>
                        <td><%= recipe.getRating() %></td>
                        <td><%= timeToMake != null ? (Integer)timeToMake / 60 : 0 %> min</td>
                        <td><%= source.getSourceDisplayName() %></td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <%
        }
    %>
</div>

<%@include file="../includes/footer.jsp" %>