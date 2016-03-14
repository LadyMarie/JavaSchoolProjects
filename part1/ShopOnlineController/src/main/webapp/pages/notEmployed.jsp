<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>makeOrder</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
   We're sorry, ${User.name} ${not empty User.name? ',' : ''} but this page can only be seen by employees of our shop=(( <br>
P.S. But you can send us your resume;)
</body>
</html>
