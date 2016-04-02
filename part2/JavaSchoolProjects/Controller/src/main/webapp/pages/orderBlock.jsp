<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 28.02.2016
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>


<c:set var="orderIndex" value="${param.orderIndex}" />
<c:set var="order" value="${orders[orderIndex]}" />
<div class = "panel panel-default">
    <div class = "panel-heading">
        <div class="row">
            <div class="col-md-8">
                <p class="text-success">Status: ${order.status}</p>
            </div>
            <div class="col-md-4 text-right">
               <a href="repeat?id=${order.id}" class="btn btn-primary btn-xs">Repeat</a>
            </div>
        </div>
    </div>
    <div class = "panel-body">
        <table class = "table-items" align="center">
            <tr>
                <td><small>Delivery method: </small></td>
                <td><small>${order.deliveryMethod}</small></td>
            </tr>
            <tr>
                <td><small>Pay method:</small></td>
                <td><small>${order.payMethod}</small></td>
            </tr>
            <tr>
                <td><small>Address:</small></td>
                <td><small>${order.address}</small></td>
            </tr>
        </table>
        <div align="center">
            <a href="viewCart?id=${order.id}" class="btn btn-default btn-xs">Products</a>
        </div>
    </div>
</div>
