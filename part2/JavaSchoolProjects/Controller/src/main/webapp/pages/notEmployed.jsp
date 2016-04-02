<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>makeOrder</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/error.jpg"/>);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
        .layer {
            background:  rgba(0,0,0,1);
            position: absolute;
            top:0;
            bottom: 0;
            left: 0;
            right: 0;
            width: 50%;
            height: 30%;
            margin: auto;
            align-items: center;
        }
    </style>
</head>
<body>
  <div class="layer" align="center">
      <b><p class="lead text-primary">We're sorry, ${User.name} ${not empty User.name? ',' : ''} this page can only be seen by employees of our shop=(( <br>
P.S. But you can send us your resume;)</p></b>
      <br>
          <a href="${context}/pages/Index.jsp" class="btn btn-primary" class="btn btn-primary">Main</a>
      </div>
</body>
</html>
