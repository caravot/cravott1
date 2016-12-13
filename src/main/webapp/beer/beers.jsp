<%@include file="../includes/header.jsp" %>

<%@page import="java.util.*"%>

<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.BeerSearchResult" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get request parameter if available
    String q = "";
    String showAll = "false";
    BeerSearchResult result = null;
    ArrayList<Beer> beerList = new ArrayList<Beer>();
    Integer previousPage = 0;
    Integer nextPage = 0;
    Integer currentPage = 1;
    Integer totalPages = 1;
    Integer totalResult = 0;
    Integer totalVisibleResults = 0;

    if (request.getParameter("showAll") != null) {
        showAll = request.getParameter("showAll");
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
                    <input type="checkbox" name="showAll" id="showAll" value="true"
                        <%=("true".equals(showAll) ? "checked": "")%>>
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
            <div class="col-md-12">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th width="45">Name</th>
                            <th width="20">Description</th>
                            <th width="5">ABV</th>
                            <th width="20">Food Pairings</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Iterator<Beer> iter = beerList.iterator();
                            while (iter.hasNext()) {
                                Beer beer = iter.next();
                                String foodPairings = beer.getFoodPairings();

                                if (showAll == "true" || (beer.getFoodPairings() != null)) {
                                    totalVisibleResults++;
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