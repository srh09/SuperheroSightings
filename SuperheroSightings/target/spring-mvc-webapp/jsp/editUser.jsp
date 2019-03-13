<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit User</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Edit User</h1>
            <hr/>
            <div class="col-md-12 row navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displayMainPage">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/superhero/displaySuperheroes">Superheroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location/displayLocations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization/displayOrganizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sight/displaySightings">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/user/displayUserList">Sidekicks</a></li>
                    </sec:authorize>
                </ul>
            </div>
            
            <div class="col-md-6 row">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <p>Hello : ${pageContext.request.userPrincipal.name}
                        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                    </p>
                </c:if>
                <sf:form class="form-horizontal" role="form" modelAttribute="user" action="editUser" method="POST">
                    <div class="form-group">
                        <label for="add-username" class="col-md-4 control-label">Username: </label>
                        <div class="col-md-8">
                            <sf:hidden path="user_id"/>
                            <sf:input type="text" class="form-control" id="add-username" path="username"/>
                            <sf:errors path="username" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-password" class="col-md-4 control-label">Password: </label>
                            <div class="col-md-8">
                            <sf:input type="password" class="form-control" id="add-password" path="password"/>
                            <sf:errors path="password" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        
                        <div class="form-check col-md-offset-2">
                            <label class="form-check-label" for="adminCheck">Admin User?  </label>
                            <input class="form-check-input" type="checkbox" id="adminCheck" name="isAdmin" value="yes">
                        </div>
                            <br><input type="submit" class="btn btn-primary col-md-offset-2" value="Edit User"/><br/>
                </sf:form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
