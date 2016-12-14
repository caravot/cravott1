<%@include file="../includes/header.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.SingleBeerSearchResult" %>
<%@ page import="java.net.URLEncoder" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    String id = request.getParameter("id");
    Beer beer = null;

    // get attributes from the request
    if (id != null && !id.equals("") || id.length() == 0) {
        SingleBeerSearchResult singleBeer = GetBeers.getBeerById(id);
        beer = singleBeer.getData();
    }
%>

<div class="container">

    <%-- do not continue if no search results to show --%>
    <%
        if (id == null || id.equals("") || id.length() == 0) {
    %>
    <div class="row">
        <div class="col-md-12">
            <h2>Error</h2>
        </div>
        <div class="col-md-12">
            <p>You must provide an id or the id provided does not match any beer.</p>
        </div>
    </div>
    <%
        } else {
    %>
        <div>
            <h1>Beer Details</h1>
        </div>
        <div class="row">
            <div class="col-md-2"><strong>Name:</strong></div>
            <div class="col-md-10"><a href="http://www.brewerydb.com/beer/<%= beer.getId() %>" title="View on BreweryDB"><%= beer.getName() %></a></div>
        </div>
        <div class="row">
            <div class="col-md-2"><strong>Description:</strong></div>
            <div class="col-md-10"><%= beer.getDescription() %></div>
        </div>
        <div class="row">
            <div class="col-md-2"><strong>ABV:</strong></div>
            <div class="col-md-10"><%= beer.getAbv() %>%</div>
        </div>
        <div class="row">
            <div class="col-md-2"><strong>Food Pairings:</strong></div>
            <div class="col-md-10">
                <ul>
                <%
                    // Only split if there is food pairings
                    if (beer.getFoodPairings() != null) {
                        // Split the list of food pairing up by commas
                        List<String> foodList = beer.getFoodPairingsAsList();

                        // Create recipe search for each word
                        for (int i = 0; i < foodList.size(); i++) {
                            String food = foodList.get(i);
                %>
                        <li><a href="/food/recipes.jsp?q=<%= URLEncoder.encode(food, "UTF-8") %>"><%= food %></a></li>
                <%
                        }
                    } else {
                %>
                    <li>No Food Pairings Listed</li>
                <%
                    }
                %>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"><strong>Date Updated:</strong></div>
            <div class="col-md-10"><%= beer.getUpdateDate() %></div>
        </div>
    <%
        }
    %>
</div>

<%@include file="../includes/footer.jsp" %>