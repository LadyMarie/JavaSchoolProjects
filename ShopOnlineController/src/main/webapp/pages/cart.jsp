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
<html>
<head>
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        .table-bordered td{
            padding: 5px;
            vertical-align: top;
        }
    </style>
</head>
<body>
        <c:forEach items="${cartKeySet}" step="3" varStatus="status">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="itemCartBlock.jsp">
                        <jsp:param name="productId" value="${cartKeySet[status.index]}" />
                    </jsp:include>
                </div>
                <c:if test="${status.index + 1 < fn:length(cartKeySet)}">
                    <div class="col-md-4">
                        <jsp:include page="itemCartBlock.jsp">
                            <jsp:param name="productId" value="${cartKeySet[status.index + 1]}" />
                        </jsp:include>
                    </div>
                </c:if>
                <c:if test="${status.index + 2 < fn:length(cartKeySet)}">
                    <div class="col-md-4">
                        <jsp:include page="itemCartBlock.jsp">
                            <jsp:param name="productId" value="${cartKeySet[status.index + 2]}" />
                        </jsp:include>
                    </div>
                </c:if>
            </div>
        </c:forEach>

</body>
</html>
