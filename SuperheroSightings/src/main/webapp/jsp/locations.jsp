<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>locations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">     
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Locations</h1>
            <div class="col-md-12 row navbar">
                <hr/>
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displayMainPage">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superhero/displaySuperheroes">Superheroes</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location/displayLocations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization/displayOrganizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displaySightings">Sightings</a></li>
                </ul>
            </div>
            <div id="scrollTable" class="col-md-12 row">
                <table id="locationsTable" class="table table-striped">
                    <thead class="thead-light">
                        <tr>
                            <th width="10%">ID</th>
                            <th width="20%">Name</th>
                            <th width="25%">Address</th>
                            <th width="25%">Coordinates</th>
                            <th width="5%"></th>
                            <th width="5%"></th>
                            <th width="10%"></th>
                        </tr>
                    </thead>

                    <c:forEach var="currentLocation" items="${locationList}">
                        <tr>
                            <td><c:out value="${currentLocation.location_id}"/></td>
                            <td><c:out value="${currentLocation.name}"/></td>
                            <td><c:out value="${currentLocation.address}"/></td>
                            <td><c:out value="${currentLocation.coordinates}"/></td>
                            <td><a href="displayViewLocation?location_id=${currentLocation.location_id}">View</a></td>
                            <td><a href="displayEditLocation?location_id=${currentLocation.location_id}">Edit</a></td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td><a href="deleteLocation?location_id=${currentLocation.location_id}">Delete</a></td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>                  
            </div>
            <a href="${pageContext.request.contextPath}/location/displayCreateLocation" type="default" class="btn btn-primary" >Create Location</a>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
