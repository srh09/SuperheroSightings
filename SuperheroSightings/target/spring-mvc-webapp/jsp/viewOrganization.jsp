<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>viewOrganization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">     
    </head>
    <body id="bootstrap-overrides">
        <div class="container">
            
            <h1>View Organization</h1>
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
                <sf:form class="form-horizontal" role="form" modelAttribute="organization"
                      action="createOrganization" method="POST">
                    <div class="form-group">
                        <label for="add-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-name" path="name" placeholder="Enter Name" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-contact-info" class="col-md-4 control-label">Address:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-contact-info" path="contactInfo" placeholder="Enter Contact Info" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">
                            Description:
                        </label>
                        <div class="col-md-8">
                            <sf:textarea class="form-control" id="add-description" path="description" rows="4" placeholder="Enter Description" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="superTable" class="col-md-4 control-label">
                            Superheroes:
                        </label>
                        <div id="scrollTable" name="superTable" class="col-md-4 row">
                            <table id="createSuperheroTable" class="table table-striped">
                                <thead class="thead-light">
                                    <tr>
                                        <th width="20%">ID</th>
                                        <th width="80%">Name</th>
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
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
