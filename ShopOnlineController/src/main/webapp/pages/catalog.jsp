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
        <c:forEach items="${products}" step="3" varStatus="status">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="itemBlock.jsp">
                        <jsp:param name="index" value="${status.index}" />
                    </jsp:include>
                </div>
                <c:if test="${status.index + 1 < fn:length(products)}">
                    <div class="col-md-4">
                        <jsp:include page="itemBlock.jsp">
                            <jsp:param name="index" value="${status.index + 1}" />
                        </jsp:include>
                    </div>
                </c:if>
                <c:if test="${status.index + 2 < fn:length(products)}">
                    <div class="col-md-4">
                        <jsp:include page="itemBlock.jsp">
                            <jsp:param name="index" value="${status.index + 2}" />
                        </jsp:include>
                    </div>
                </c:if>
            </div>
        </c:forEach>






   <%-- <table class="table"><thead>
        <tr>
            <th>
                name
            </th>
            <th>
                price
            </th>
            <th>
                category
            </th>

            <th>
                params
            </th>
            <th>
                weight
            </th>
            <th>
               volume
            </th>
            <th>
                amount
            </th>
            <th>
                amount
            </th>
        </tr>

    </thead>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>
                ${product.name}
            </td>
            <td>
                ${product.price}
            </td>
            <td>
                ${product.category}
            </td>

            <td>
                    ${product.params}
            </td>
            <td>
                    ${product.weight}
            </td>
            <td>
                    ${product.volume}
            </td>
            <td>
                    ${product.amount}
            </td>
            <td>
                    ${product.amount}
            </td>
        </tr>
    </c:forEach>
    </table>--%>
</body>
</html>
