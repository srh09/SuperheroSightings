<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>createSighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    <hr/>
</head>
<body>
    <div class="container">
        <h1>Edit Sighting</h1>
        <div class="col-md-12 row navbar">
            <hr/>
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displayMainPage">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/superhero/displaySuperheroes">Superheroes</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/location/displayLocations">Locations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organization/displayOrganizations">Organizations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displaySightings">Sightings</a></li>
            </ul>
        </div>
        <div class="col-md-6 row">
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting" 
                     action="editSighting" method="POST">

                <div class="form-group">
                    <label for="date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="date"/>
                        <sf:errors path="date" cssClass="error"></sf:errors>
                        <sf:hidden path="sighting_id"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="scrollTable" class="col-md-offset-4 col-md-8 row">
                            <table id="superheroSightingTable" class="table table-striped">
                                <thead class="thead-light">
                                    <tr>
                                        <th width="10%">ID</th>
                                        <th width="50%">Superhero</th>
                                        <th width="40%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="currentSuperhero" items="${superheroList}">
                                    <tr>
                                        <td>
                                            <c:out value="${currentSuperhero.superhero_id}"/>
                                        </td>
                                        <td>
                                            <c:out value="${currentSuperhero.name}"/>
                                        </td>
                                        <td>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="superhero_id" value="${currentSuperhero.superhero_id}" required="">
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <div id="scrollTable" class="col-md-offset-4 col-md-8 row">
                        <table id="locationSightingTable" class="table table-striped">
                            <thead class="thead-light">
                                <tr>
                                    <th width="10%">ID</th>
                                    <th width="50%">Location</th>
                                    <th width="40%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="currentLocation" items="${locationList}">
                                    <tr>
                                        <td>
                                            <c:out value="${currentLocation.location_id}"/>
                                        </td>
                                        <td>
                                            <c:out value="${currentLocation.name}"/>
                                        </td>
                                        <td>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="location_id" value="${currentLocation.location_id}" required="">
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Edit Sighting"/>
                    </div>
                </div>
            </sf:form> 
        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
