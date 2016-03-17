<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Signup</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
    <form:form class="form-horizontal" action="addPerson" modelAttribute="User" method="post">
        <p class="text-warning">N.B.! Orange fields are obligatory.</p>
        <div class="form-group has-warning">
            <div>
                <form:errors path="email" class="control-label has-error" id="inputUsEmailErr"/>
                <form:input path="email" class="form-control" id="inputUsEmail"  type="text"/>
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
    </form:form>
</div>
</body>
</html>
