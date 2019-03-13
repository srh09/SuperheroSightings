<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>editSuperhero</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">     
    </head>
    <body>
        <div class="container">
            <h1>Edit Superhero</h1>
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
                <sf:form class="form-horizontal" role="form" modelAttribute="superhero" 
                         action="editSuperhero" method="POST">
                    
                    <div class="form-group">
                        <label for="add-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-name" path="name"/>
                            <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-superpower" class="col-md-4 control-label">Superpower:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-superpower" path="superpower"/>
                            <sf:errors path="superpower" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">
                            Description:
                        </label>
                        <div class="col-md-8">
                            <sf:textarea class="form-control" id="add-description" path="description" rows="4"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="scrollTable" class="col-md-offset-4 col-md-8 row">
                            <table id="createSuperheroTable" class="table table-striped">
                                <thead class="thead-light">
                                    <tr>
                                        <th width="10%">ID</th>
                                        <th width="50%">Name</th>
                                        <th width="40%"></th>
                                    </tr>
                                </thead>
                                <c:forEach var="currentOrganization" items="${organizationList}">
                                    <tr>
                                        <td>
                                            <c:out value="${currentOrganization.organization_id}"/>
                                        </td>
                                        <td>
                                            <c:out value="${currentOrganization.name}"/>
                                        </td>
                                        <td>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="orgId" value="${currentOrganization.organization_id}">
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-primary" value="Edit Superhero"/>
                            <input type="hidden" name="superhero_id" value="${superhero.superhero_id}" />
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
