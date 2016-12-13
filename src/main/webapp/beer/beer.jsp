<%@include file="../includes/header.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.SingleBeerSearchResult" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get attributes from the request
    SingleBeerSearchResult singleBeer = GetBeers.getBeerById(request.getParameter("id"));
    Beer beer = singleBeer.getData();
%>

<div class="container">
    <div align="center">
        <h1>Beer Details</h1>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>ID:</strong></div>
        <div class="col-md-10"><%= beer.getId() %></div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>Name:</strong></div>
        <div class="col-md-10"><%= beer.getName() %></div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>Description:</strong></div>
        <div class="col-md-10"><%= beer.getDescription() %></div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>ABV:</strong></div>
        <div class="col-md-10"><%= beer.getAbv() %></div>
    </div>
    <div class="row">
        <div class="col-md-2"><strong>Food Pairings:</strong></div>
        <div class="col-md-10">
            <ul>
            <%
                List<String> foodList = beer.getFoodPairingsAsList();
                for (int i = 0; i < foodList.size(); i++) {
                    String food = foodList.get(i);
            %>
                    <li><a href="/food/recipes.jsp?q=<%= URLEncoder.encode(food, "UTF-8") %>"><%= food %></a></li>
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
</div>

<%@include file="../includes/footer.jsp" %>