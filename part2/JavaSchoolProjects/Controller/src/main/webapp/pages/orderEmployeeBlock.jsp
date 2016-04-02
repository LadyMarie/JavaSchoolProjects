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
                <div class="form-group has-warning">
                    <label class="control-label ssv-small-label">Status:</label>
                    <div class="input-group">
                        <form action="status?id=${order.id}" method="post" class="input-group">
                           <input type="text" name="status" class="form-control input-sm" placeholder="${order.status}">
                           <span class="input-group-btn">
                              <button type="submit" class="btn btn-default btn-xs">Ok</button>
                           </span>
                        </form>
                    </div>
                </div>
          <!--  <div class="form-group has-success">
                <form class="form-horizontal"  action="status?id=${order.id}" method="post">
                <fieldset>
                        <input class="form-control" name="status" id="status"  type="text" placeholder="${order.status}"/>
                        <button type="submit" class="btn btn-default btn-xs">Submit</button>
                </fieldset>
                </form>
            </div>-->
            </div>
        </div>
    </div>
    <div class = "panel-body">
        <table class = "table-items">
            <tr>
                <td><small>User:</small></td>
                <td><small>${order.user.email}</small></td>
            </tr>
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
