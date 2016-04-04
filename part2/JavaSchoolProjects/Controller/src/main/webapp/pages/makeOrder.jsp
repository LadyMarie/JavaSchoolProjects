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
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>login</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/untouchable.jpg"/>);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
        .layer {
            background-image:  linear-gradient(to bottom left, rgba(255,255,255,1), rgba(0,0,0,0));
            height: 100%;
        }
    </style>
</head>
<body>
<div class="layer">
   <div align="center">
        <c:if test="${not empty User}">
            <p class="text-center">Hi, ${User.name}!</p>
        </c:if>
        <form:form class="form-horizontal" action="addOrder" method="post" modelAttribute="Order">
        <fieldset>
            <legend>Make order</legend>

                <div class="form-group">
                    <div>
                        <form:input path="payMethod" name="pay" class="form-control" id="inputPay" placeholder="Pay method" type="text"/>
                    </div>
                </div>
                  <div class="form-group">
                      <div>
                          <form:input path="deliveryMethod" name="delivery" class="form-control" id="inputDelivery" placeholder="Delivery method"/>
                      </div>
                  </div>
            <div class="form-group">
                <div>
                    <form:input path="address" name="address" class="form-control" id="address" placeholder="Address"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <a href="${context}/Main" class="btn btn-default">Main</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
    </div>
</body>
</html>

