<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 22.02.2016
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/err.jpg"/>);
            background-size: contain;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: contain;
            -moz-background-size: contain;
            -o-background-size: contain;
        }
        .layer {
            background:  rgba(0,0,0,1);
            position: absolute;
            top:0;
            bottom: 0;
            left: 0;
            right: 0;
            width: 50%;
            height: 20%;
            margin: auto;
            align-items: center;
        }
    </style>
</head>
<body>
   <div align="center" class="layer">
        We are sorry, but there are something wrong with database=((
    </div>
</body>
</html>
