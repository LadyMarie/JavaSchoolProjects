<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 22.02.2016
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Error</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/cartEmpty.jpg"/>);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
        .layer {
            background:  rgba(255,255,255,0.7);
            position: absolute;
            top:0;
            bottom: 0;
            left: 0;
            right: 0;
            width: 50%;
            height: 25%;
            margin: auto;
            align-items: center;
        }
        .layer1 {
            background-image:  linear-gradient(to bottom, rgba(255,255,255,1), rgba(0,0,0,0));
            height: 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<div class="layer1">
   <div align="center" class="layer">
        <b><p class="lead">We are sorry, but your cart is empty.</br>
            Please, add something to your cart before making order.</p></b>
   <br>
        <a href="${context}/Main" class="btn btn-primary" class="btn btn-primary">Catalog</a>
    </div>
</div>
</body>
</html>
