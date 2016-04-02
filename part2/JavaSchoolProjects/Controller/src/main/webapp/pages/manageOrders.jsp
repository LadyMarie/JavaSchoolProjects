<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 28.02.2016
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        .table-items td{
            padding: 5px;
            padding-right: 5px;
            vertical-align: top;
        }
        body{
            background-image: url(<c:url value="/icons/frida.jpg"/>);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
        .layer {
            background-image:  linear-gradient(to bottom, rgba(255,255,255,1), rgba(0,0,0,0));
            height: 100%;
        }
        .menuButton {
            margin-top: 5px;
            margin-left: 5px;
        }
    </style>

</head>
<body>
       <div class="container layer">
           <div class="menuButton">
               <a href="${context}/Main" class="btn btn-primary" class="btn btn-primary">Main</a>
           </div>
       <div align="center">
          <legend>Manage orders</legend>
       </div>

        <c:forEach items="${orders}" step="3" varStatus="status">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="orderEmployeeBlock.jsp">
                        <jsp:param name="orderIndex" value="${status.index}" />
                    </jsp:include>
                </div>
                <c:if test="${status.index + 1 < fn:length(orders)}">
                    <div class="col-md-4">
                        <jsp:include page="orderEmployeeBlock.jsp">
                            <jsp:param name="orderIndex" value="${status.index + 1}" />
                        </jsp:include>
                    </div>
                </c:if>
                <c:if test="${status.index + 2 < fn:length(orders)}">
                    <div class="col-md-4">
                        <jsp:include page="orderEmployeeBlock.jsp">
                            <jsp:param name="orderIndex" value="${status.index + 2}" />
                        </jsp:include>
                    </div>
                </c:if>
            </div>
        </c:forEach>
       </div>


</body>
</html>
