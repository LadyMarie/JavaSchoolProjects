<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        label {
            display: inline-block;
        }
    </style>
</head>
<body>
    <dv class="container">
        <div>
           <jsp:include page="Nav.jsp"/>
        </div>


     <!--   <div class="form-group has-feedback" align="center">
            <label class="control-label ssv-small-label">Filter:</label>
            <div class="input-group">
                <form action="addFilter" method="post" class="input-group">
                    <span class="input-group"><label class="control-label ssv-small-label">Category:</label>
                    <input type="text" name="category" class="form-control input-sm" placeholder="${category}">
                   <label class="control-label ssv-small-label">Price:</label>
                    <input type="text" name="fromPrice" class="form-control input-sm" placeholder="${fromPrice}">
                    <label class="control-label ssv-small-label">-</label>
                    <input type="text" name="toPrice" class="form-control input-sm" placeholder="${toPrice}">
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-default btn-xs">Ok</button>
                    </span>
                </form>
            </div>
        </div>

        <div>-->
        <c:if test="${not empty showFilter}">
        <div align="center">
           <!-- <label class="control-label ssv-small-label">Filter:</label>-->
            <c:if test="${empty editMode}">
                <form:form action="addFilter" method="post" modelAttribute="Filter">
                    <form:input path="category" type="text" placeholder="Category"></form:input>
                    <form:errors path="fromPrice" class="control-label has-error text-primary" id="fromPriceErr"/>
                    <form:input path="fromPrice" type="text" placeholder="Price:from" id="fromPrice"></form:input>$
                    <form:errors path="toPrice" class="control-label has-error text-primary" id="toPriceErr"/>
                    <form:input path="toPrice" type="text" placeholder="Price:to"></form:input>$
                    <button type="submit" class="btn btn-primary btn-xs">Filter</button>
                </form:form>
            </c:if>
            <c:if test="${not empty editMode}">
                <form:form action="addFilter?editMode=true" method="post" modelAttribute="Filter">
                    <form:input path="category" type="text" placeholder="Category"></form:input>
                    <form:errors path="fromPrice" class="control-label has-error text-primary" id="fromPriceErr"/>
                    <form:input path="fromPrice" type="text" placeholder="Price:from" id="fromPrice"></form:input>$
                    <form:errors path="toPrice" class="control-label has-error text-primary" id="toPriceErr"/>
                    <form:input path="toPrice" type="text" placeholder="Price:to"></form:input>$
                    <button type="submit" class="btn btn-primary btn-xs">Filter</button>
                </form:form>
            </c:if>
            </c:if>
        </div>
        </div>


          <jsp:include page="catalog.jsp" />
        </div>
    </div>

  <!-- Bootstrap core JavaScript
      ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script>window.jQuery || document.write('<script src="<c:url value="/pages/Scripts/jquery.min.js"/>"><\/script>')</script>
  <script src="<c:url value="/pages/Scripts/bootstrap.min.js"/>"></script>
</body>
</html>
