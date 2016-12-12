<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.BeerSearchResult" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get request parameter if available
    String q = "";
    String foodOnly = "false";
    BeerSearchResult result = null;
    ArrayList<Beer> beerList = new ArrayList<Beer>();
    Integer previousPage = 0;
    Integer nextPage = 0;
    Integer currentPage = 1;
    Integer totalPages = 1;
    Integer totalResult = 0;

    if (request.getParameter("foodOnly") != null) {
        foodOnly = request.getParameter("foodOnly");
    }

    // get attributes from the request
    if (request.getParameter("q") != null) {
        q = request.getParameter("q");
        result = GetBeers.findBeers(q);

        beerList = result.getData();
        currentPage = result.getCurrentPage();
        totalPages = result.getNumberOfPages();
        totalResult = result.getTotalResults();

        if (currentPage + 1 >= totalPages) {
            nextPage = currentPage;
        } else {
            nextPage = currentPage + 1;
        }

        if (currentPage - 1 <= 0) {
            previousPage = currentPage;
        } else {
            previousPage = currentPage - 1;
        }
    }
%>

<div class="container">
    <div>
        <h1>Search Beers</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form method="get">
                <label for="q">Search Term: <input type="text" name="q" id="q" value="<%= q %>"></label>
                <label>
                    Show All Beers even without Food Pairings?
                    <input type="checkbox" name="foodOnly" id="foodOnly" value="true"
                        <%=("true".equals(foodOnly) ? "checked": "")%>>
                </label>

                <button type="submit" class="btn btn-default btn-sm">Submit</button>
            </form>
        </div>
    </div>

    <%-- do not continue if no search results to show --%>
    <%
        if (q != "" && totalResult == 0) {
    %>
    <div class="row">
        <div class="col-md-12">
            <h3>No Results Found</h3>
        </div>
    </div>
    <%
        } else if (q != "") {
    %>
        <div class="row">
            <div class="col-md-12">
                <h3>Results</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 text-left">
                <a href="./beers.jsp?q=<%= q %>&currentPage=<%= previousPage %>">Previous Page</a>
            </div>
            <div class="col-md-8 text-center">
                <strong>Total Records:</strong> <%= totalResult %> |
                <strong>Current Page:</strong> <%= currentPage %> |
                <strong>Total Pages:</strong> <%= totalPages %>
            </div>
            <div class="col-md-2 text-right">
                <a href="./beers.jsp?q=<%= q %>&currentPage=<%= nextPage %>">Next Page</a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="30">Name</th>
                        <th width="40">Description</th>
                        <th width="5">ABV</th>
                        <th width="25">Food Pairings</th>
                        <th width="5">Recipes</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Iterator<Beer> iter = beerList.iterator();
                        while (iter.hasNext()) {
                            Beer beer = iter.next();
                            String foodPairings = beer.getFoodPairings();

                            if (foodOnly == "false" || (foodOnly == "true" && beer.getFoodPairings() != null)) {
                    %>
                    <tr>
                        <td><a href="beer.jsp?id=<%= beer.getId() %>"><%= beer.getName() %></a></td>
                        <td><%= beer.getDescription() %></td>
                        <td><%= beer.getAbv() %></td>
                        <td><%= beer.getFoodPairings() %></td>
                        <td><a href="/food/recipes.jsp?q=<%= foodPairings %>" target="_blank">View</a></td>
                    </tr>
                    <%
                            }
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