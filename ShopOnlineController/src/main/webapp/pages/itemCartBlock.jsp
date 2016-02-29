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
<c:set var="item" value="${cart[id]}"/>

<div class = "panel panel-default">
    <div class = "panel-heading">
        <div class="row">
            <div class="col-md-8">
                ${item.product.name}
                </div>
            <!--Todo:make user able to input amount of product-->
       <!-- <div class="col-md-4 text-right">
            <div class="form-group">
                <div>
                    <input name="amount" class="form-control" id="amount" placeholder="${item.amount}" type="text">
                </div>
            </div>
        </div> -->
        </div>
    </div>

    <div class = "panel-body">
        <table class = "table-bordered">
            <tr>
                <td rowspan="5">
                    <img src="/uploads/${item.product.id}.jpg" style="max-width: 200px; max-height: 200px;" onerror="this.src='/uploads/default.jpg'"/>
                </td>
                <td><small>Price: </small></td>
                <td><small>${item.product.price}$</small></td>
            </tr>
            <tr>
                <td><small>Category:</small></td>
                <td><small>${item.product.category}</small></td>
            </tr>
            <tr>
                <td><small>Weight:</small></td>
                <td><small>${item.product.weight}</small></td>
            </tr>
            <tr>
                <td><small>Volume:</small></td>
                <td><small>${item.product.volume}</small></td>
            </tr>
            <tr>
                <td><small>Amount:</small></td>
                <td><small>${item.product.amount}</small></td>
            </tr>
            <tr>
                <td><small>Parametrs:</small></td>
                <td><small>${item.product.params}</small></td>
            </tr>
        </table>
    </div>
</div>
