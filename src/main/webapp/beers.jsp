<%@include file="header.jsp" %>

<%@page import="java.util.*"%>
<%@ page import="com.brewerydb.models.Beer" %>

<jsp:useBean id="GetBeers" class="com.brewerydb.services.BeerSearchService" scope="page" />

<%
    // get attributes from the request
    ArrayList<Beer> beerList = GetBeers.searchResults("flying");

%>

<div class="container">
    <div align="center">
        <h1>Get User List</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                <tr><td>ID</td><td>Name</td><td>Email</td><td>&nbsp;</td></tr>
                </thead>
                <tbody>
                <%
                    Iterator<Beer> iter = beerList.iterator();
                    while (iter.hasNext()) {
                        Beer beer = iter.next();
                %>
                <tr>
                    <td><a href="addUser.jsp?id=<%= beer.getId() %>"><%= beer.getId() %></a></td>
                    <td><%= beer.getName() %></td>
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