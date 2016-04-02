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
                <c:if test="${User.role == 'Employee' && not empty editMode}">
                    <a href="product?id=${id}" class="btn btn-primary btn-xs">Edit product</a>
                </c:if>
                <c:if test="${empty editMode}">
                  <a href="javascript:addToCart(${item.id})" class="btn btn-primary btn-xs">Add to cart</a>
                </c:if>
            </div>
        </div>
    </div>
    <div class = "panel-body">
        <table class = "table-items">
            <tr>
                <td rowspan="5">
                    <img src="<c:url value="/uploads/${item.id}.jpg"/>" style="max-width: 190px; max-height: 200px;" onerror="this.src='<c:url value="/uploads/default.jpg"/>'"/>
                </td>
                <td><small>Price: </small></td>
                <td><small>${item.price}$</small></td>
            </tr>
            <tr>
                <td><small>Category:</small></td>
                <td><small>${item.category}</small></td>
            </tr>
            <tr>
                <td><small>Weight:</small></td>
                <td><small>${item.weight}</small></td>
            </tr>
            <tr>
                <td><small>Volume:</small></td>
                <td><small>${item.volume}</small></td>
            </tr>
            <tr>
                <td><small>Amount:</small></td>
                <td><small>${item.amount}</small></td>
            </tr>
        </table>
    </div>
</div>
