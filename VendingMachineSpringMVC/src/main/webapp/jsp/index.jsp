<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Vending Machine with Spring</h1>
            <hr/>
            <div id="boxDiv" class="col-md-9">
                <c:forEach var="item" items="${itemList}">
                    
                        <div class="col-md-4">
                            <form action="inputItemNumber" method="POST">
                            <div class="panel panel-default" type="button" id="${item.code}">
                                <input name="itemNumber" 
                                       class="btn btn-light pull-left" 
                                       type="submit" 
                                       value="<c:out value="${item.code}"/>">
                                <br>
                                <h3 class="item-name"><c:out value="${item.name}"/></h3>
                                <h3 class="item-cost">$<c:out value="${item.cost}"/></h3>
                                <br>
                                <h3 class="item-inventory">Quantity Left: <c:out value="${item.inventory}"/></h3>
                                <br>
                            </div>
                                </form>
                        </div>
                    
                </c:forEach>
            </div>
            <div id="infoDiv" class="col-md-3" align="center">
                <h2>Total $ In</h2>
                <input class="form-control"
                       value="${totalMoneyDisplay}" 
                       type="text" 
                       id="total-money-display" 
                       readonly/>
                <br>
                <form action="inputMoney" method="POST">
                    <div class="row">
                        <input name="moneySelection"
                            class="add-money btn btn-secondary btn-lg"
                            value="Add Dollar"
                            type="submit"
                            id="dollar-button"/> 
                        <input name="moneySelection"
                            class="add-money btn btn-secondary btn-lg"
                            value="Add Quarter"
                            type="submit"
                            id="quarter-button"/> 
                    </div>
                    <br>
                    <div class="row">
                        <input name="moneySelection"
                            class="add-money btn btn-secondary btn-lg"
                            value="Add Dime"
                            type="submit"
                            id="dime-button"/> 
                        <input name="moneySelection"
                            class="add-money btn btn-secondary btn-lg"
                            value="Add Nickel"
                            type="submit"
                            id="nickel-button"/> 
                    </div>
                </form>
                <hr/>
                <h2>Messages</h2>
                <input class="form-control"
                       value="${messageDisplay}"
                       type="text"
                       id="message-display"
                       readonly>
                <div class="form-horizontal" id="edit-form">
                    <br>
                    <form action="makePurchase" method="POST">
                    <div class="form-group">
                        <label for="item-display" class="col-md-4 control-label">
                            Item:
                        </label>
                        <div class="col-md-8">
                            <input name="orderNumber"
                                   class="form-control"
                                   value="${orderNumber}"
                                   type="text" 
                                   id="item-display"
                                   readonly/>
                            </div>
                    </div>
                        <input name="moneySelection"
                            class="add-money btn btn-secondary btn-lg btn-block"
                            value="Make Purchase"
                            type="submit"
                            id="purchase-button"/> 
                    </form>
                    <br>
                    <hr/>
                    <h2>Change</h2>
                    <form action="makeChange" method="POST">
                    <input name="changeDisplay"
                           value="${changeDisplay}"
                           class="form-control" 
                           type="text"
                           id="change-display"
                           readonly/>
                    <br>
                    <input name="moneySelection"
                            class="btn btn-secondary btn-lg"
                            value="Change Return"
                            type="submit"
                            id="change-button"/> 
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

