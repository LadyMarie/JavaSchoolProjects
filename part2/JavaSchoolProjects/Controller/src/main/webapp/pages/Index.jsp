<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="Nav.jsp"/>
      <div>
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
