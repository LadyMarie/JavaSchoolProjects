<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

    <noscript>
        <p class="text-warning">
            JavaScript is turned off in your web browser. Turn it on to take full advantage of this site, then refresh the page.
        </p>
    </noscript>
    <nav class="navbar navbar-default">
        <c:if test="${not empty User.name}">
            <p class="text-center">Hi, ${User.name}!</p>
        </c:if>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty noCart}">
                    <c:if test="${not empty cartsize}">
                      <li>
                          <a href="<c:url value="/pages/cart.jsp"/>">
                            <img src="<c:url value="/icons/shoping_cart.png"/>" style="height: 30px; width: 30px;">
                            <span class="cart">
                                <p class="text-info"><small>Total: ${cartsize}.</small></p>
                            </span>
                        </a>
                      </li>
                    </c:if>
                    <c:if test="${empty cartsize}">
                        <li><a href="<c:url value="/pages/cart.jsp"/>">
                            <img src="<c:url value="/icons/shoping_cart.png"/>" style="height: 30px; width: 30px;">
                            <span class="cart"></span>
                        </a>
                        </li>
                    </c:if>
                </c:if>
                <c:if test="${not empty noCart}">
                    <li><p class="text-primary"><small>sorry, cart not working</small></p></li>
                </c:if>
                <c:if test="${empty User}">
                    <li><a href="${context}/login">Sign in</a></li>
                </c:if>
                <c:if test="${not empty User}">
                    <li><a href="${context}/editProfile">Edit profile</a></li>
                </c:if>
                <c:if test="${empty User}">
                    <li><a href="${context}/signup">Sign up</a></li>
                </c:if>
                <li><a href="${context}/logout">Log out</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menu <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="order" class="list-group-item active">Make order</a></li>
                        <c:if test="${User.role == 'Employee'}">
                            <li><a href="product">Add product</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <noscript>
        <div class="col-lg-4">
            <div class="bs-component">
                <div class="list-group" width="20%">
                    <a href="order" class="list-group-item active" width="20%">
                        Make order
                    </a>
                    <c:if test="${User.role == 'Employee'}">
                        <a href="product" class="list-group-item" width="auto">Add product</a>
                    </c:if>
                </div>
            </div>
        </div>
    </noscript>
