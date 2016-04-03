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
        .table-items td{
            padding: 5px;
            padding-right: 5px;
            vertical-align: top;
        }
    </style>
    <script>
        function addToCart(productId) {

            $.ajax({
                url: 'cart?id='+productId,
                success: function(data) {
                    $('.cart').html(data);
                }
            });



        }


    </script>
</head>
<body>
<div class="container">
        <c:forEach items="${productsKeySet}" step="3" varStatus="status">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="itemBlock.jsp">
                        <jsp:param name="productId" value="${productsKeySet[status.index]}" />
                    </jsp:include>
                </div>
                <c:if test="${status.index + 1 < fn:length(productsKeySet)}">
                    <div class="col-md-4">
                        <jsp:include page="itemBlock.jsp">
                            <jsp:param name="productId" value="${productsKeySet[status.index + 1]}" />
                        </jsp:include>
                    </div>
                </c:if>
                <c:if test="${status.index + 2 < fn:length(productsKeySet)}">
                    <div class="col-md-4">
                        <jsp:include page="itemBlock.jsp">
                            <jsp:param name="productId" value="${productsKeySet[status.index + 2]}" />
                        </jsp:include>
                    </div>
                </c:if>
            </div>
        </c:forEach>
</div>

</body>
</html>
