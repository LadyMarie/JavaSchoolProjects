<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19.02.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>login</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
        <form:form class="form-horizontal" action="loginPerson" method="post" modelAttribute="User">
        <fieldset>
            <legend>Login</legend>
            <div class="form-group has-warning">
                <div>
                    <form:errors path="email" class="control-label has-error" id="inputUsEmailErr"/>
                    <form:input path="email" class="form-control" id="inputUsEmail"  type="text" placeholder="Email"/>
                </div>
            </div>
            <div class="form-group has-warning">
                <div>
                    <form:errors name="password" path="password" class="control-label has-error" id="inputPasswordErr" placeholder="Password"/>
                    <form:password name="password" path="password" class="form-control" id="inputPassword" placeholder="Password"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
            <a href="/ShopOnline/signup">Sign up!</a>
        </fieldset>
    </form:form>
</div>
</body>
</html>

