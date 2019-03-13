<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MainPage</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="col-md-12 row navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sight/displayMainPage">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superhero/displaySuperheroes">Superheroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location/displayLocations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization/displayOrganizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displaySightings">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/user/displayUserList">Sidekicks</a></li>
                    </sec:authorize>
                </ul>
            </div>
                    <div class="col-md-12">
                        <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <p>Hello : ${pageContext.request.userPrincipal.name}
                                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/login" />" > Login</a>
                        </c:otherwise>
                    </c:choose>
                    </div>
                <div class="col-md-4" id="welcomeParagraph">
                    <p>
                        Welcome to Superhero Sightings, a collective database of all superhero related activity.  
                        Please use the above menu to navigate to your desired pages.  
                        Note that a superhero and location must exist before a sighting can be made.
                    </p>
                </div>
                <div class="col-md-8">
                    <h3 align="center">News Feed of Recent Sightings</h3>
                    <div id="mainTable" class="col-md-12 row">
                <table id="superheroesTable" class="table table-striped">
                    <thead class="thead-light">
                        <tr>
                            <th width="10%">ID</th>
                            <th width="20%">Date</th>
                            <th width="20%">Name</th>
                            <th width="20%">Location</th>
                        </tr>
                    </thead>
                    <c:forEach var="currentSighting" items="${sightingList}">
                        <tr>
                            <td>
                                <c:out value="${currentSighting.sighting_id}"/>
                            </td>
                            <td>
                                <c:out value="${currentSighting.date}"/>
                            </td>
                            <td>
                                <c:out value="${currentSighting.superhero.name}"/>
                            </td>
                            <td>
                                <c:out value="${currentSighting.location.name}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>                  
            </div>
                </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
