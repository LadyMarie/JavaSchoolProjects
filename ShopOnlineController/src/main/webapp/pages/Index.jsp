<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
  <div class="container">
    <noscript>
        <p class="text-warning">
            JavaScript is turned off in your web browser. Turn it on to take full advantage of this site, then refresh the page.
        </p>
    </noscript>
    <nav class="navbar navbar-default">
        <c:if test="${not empty User}">
            <p class="text-center">Hi, ${User.name}!</p>
        </c:if>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty User}">
                    <li><a href="/ShopOnline/login">Sign in</a></li>
                </c:if>
                <c:if test="${not empty User}">
                    <li><a href="/ShopOnline/signup">Edit profile</a></li>
                </c:if>
                <c:if test="${empty User}">
                    <li><a href="/ShopOnline/signup">Sign up</a></li>
                </c:if>
                <li><a href="/ShopOnline/logout">Log out</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menu <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/ShopOnline/order" class="list-group-item active">Make order</a></li>
                        <li><a href="#">Anybody can do it</a></li>
                        <c:if test="${User.role.name == 'Employee'}">
                           <li><a href="#">Employee only action</a></li>
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
                      <a href="/ShopOnline/order" class="list-group-item active" width="20%">
                          Make order
                      </a>
                      <a href="#" class="list-group-item" width="auto">Anybody can do it
                      </a>
                      <c:if test="${User.role.name == 'Employee'}">
                          <a href="#" class="list-group-item" width="auto">Employee only action</a>
                      </c:if>
                  </div>
              </div>
          </div>
      </noscript>
  </div>
  <!-- Bootstrap core JavaScript
      ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script>window.jQuery || document.write('<script src="<c:url value="/pages/Scripts/jquery.min.js"/>"><\/script>')</script>
  <script src="<c:url value="/pages/Scripts/bootstrap.min.js"/>"></script>
</body>
</html>
