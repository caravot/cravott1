<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>

<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.BeerSearchResult" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get request parameter if available
    String q = request.getParameter("q");

    // Initialize variables for later use
    String showAll = "false";
    BeerSearchResult result = null;
    ArrayList<Beer> beerList = new ArrayList<Beer>();
    Integer previousPage = 0;
    Integer nextPage = 0;
    Integer currentPage = 1;
    Integer totalPages = 1;
    Integer totalResult = 0;

    if (request.getParameter("showAll") != null) {
        showAll = request.getParameter("showAll");
    }

    // get attributes from the request
    if (q != null && !q.equals("")) {
        // get beer results
        result = GetBeers.findBeers(q);

        // save result variables for ease of access
        beerList = result.getData();
        currentPage = result.getCurrentPage();
        totalPages = result.getNumberOfPages();
        totalResult = result.getTotalResults();

        // determine if we can paginate forward
        if (currentPage + 1 >= totalPages) {
            nextPage = currentPage;
        } else {
            nextPage = currentPage + 1;
        }

        // determine if we can paginate back
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

    <form action="" method="get">
        <div class="row">
            <div class="col-md-6">
                <label class="sr-only" for="q">Search Term</label>
                <input type="text" class="form-control" name="q" id="q" placeholder="Search Term" value="<%= q %>">
                <span class="help-block">By default only beers with food pairings listed are shown.</span>
            </div>
            <div class="col-md-6">
                <label>
                    <input type="checkbox" name="showAll" id="showAll" value="true"
                        <%=("true".equals(showAll) ? "checked": "")%>>
                    Show All Beers
                </label>

                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>

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
                <h2>Results</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th width="125">Name</th>
                            <th>Description</th>
                            <th>ABV</th>
                            <th width="250">Food Pairings</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Iterator<Beer> iter = beerList.iterator();
                            while (iter.hasNext()) {
                                Beer beer = iter.next();
                                String foodPairings = beer.getFoodPairings();

                                if (showAll == "true" || (beer.getFoodPairings() != null)) {
                        %>
                        <tr>
                            <td><a href="beer.jsp?id=<%= beer.getId() %>"><%= beer.getName() %></a></td>
                            <td><%= beer.getDescription() %></td>
                            <td><%= beer.getAbv() %></td>
                            <td><%= beer.getFoodPairings() %></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4">
                                <div class="pull-left">
                                    <a href="./beers.jsp?q=<%= q %>&currentPage=<%= previousPage %>"><<</a>
                                </div>
                                <div class="pull-right">
                                    <a href="./beers.jsp?q=<%= q %>&currentPage=<%= nextPage %>">>></a>
                                </div>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    <%
        }
    %>
</div>

<%@include file="../includes/footer.jsp" %>