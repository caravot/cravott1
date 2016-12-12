<%@include file="header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.brewerydb.models.Beer" %>
<%@ page import="com.brewerydb.models.SingleBeerSearchResult" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get attributes from the request
    SingleBeerSearchResult singleBeer = GetBeers.getBeerById(request.getParameter("id"));
    Beer beer = singleBeer.getData();
%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <%= beer.getId() %><br/>
            <%= beer.getName() %><br/>
            <%= beer.getDescription() %><br/>
            <%= beer.getAbv() %><br/>
            <%= beer.getFoodPairings() %><br/>
            <%= beer.getStatus() %><br/>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>