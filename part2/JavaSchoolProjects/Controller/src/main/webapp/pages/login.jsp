<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19.02.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
        <form class="form-horizontal" action="login" method="post">
        <fieldset>
            <legend>Login</legend>
            <c:if test="${empty LoginError}">
                <div class="form-group">
                    <div>
                        <input name="email" class="form-control" id="inputEmail" placeholder="Email" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <input name="password" class="form-control" id="inputPassword" placeholder="Password" type="password">
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty LoginError}">
                <div class="form-group has-error">
                    <label class="control-label" for="inputError">Pair Email\Password is incorrect</label>
                    <input name="email" type="text" class="form-control" placeholder="Email" id="inputError">
                </div>
                <div class="form-group has-error">
                    <input name="password" type="password" placeholder="Password" class="form-control">
                </div>
            </c:if>
            <div class="form-group">
                <div>
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
            <a href="/ShopOnline/signup">Sign up!</a>
        </fieldset>
    </form>
</div>
</body>
</html>

