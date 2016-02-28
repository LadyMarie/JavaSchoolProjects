<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 28.02.2016
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="id" value="${param.productId}" />
<c:set var="item" value="${products[id]}"/>

<div class = "panel panel-default">
    <div class = "panel-heading">
        <div class="row">
            <div class="col-md-8">
                ${item.name}
                </div>
        <div class="col-md-4 text-right">
            <a href="javascript:addToCart(${item.id})" class="btn btn-primary btn-xs">Add to cart</a>
            </div>
        </div>
    </div>

    <div class = "panel-body">
        <table class = "table-bordered">
            <tr>
                <td rowspan="2">
                    <img src="/uploads/${item.id}.jpg" style="max-width: 200px; max-height: 200px;" onerror="this.src='/uploads/default.jpg'"/>
                </td>
                <td>Цена: </td>
                <td>${item.price}$</td>
            </tr>
            <tr>
                <td>Weight:</td>
                <td>${item.weight}</td>
            </tr>
        </table>
    </div>
</div>
