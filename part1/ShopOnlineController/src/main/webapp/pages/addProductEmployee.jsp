<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19.02.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
        <form class="form-horizontal" action="addProduct" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Add product</legend>

                <div class="form-group">
                    <div>
                        <input name="name" class="form-control" id="inputEmail" placeholder="Name" type="text">
                    </div>
                </div>
                <c:choose>
                    <c:when test="${wrongNumber == 'price'}">
                        <div class="form-group has-error">
                            <div>
                                <label class="control-label" for="priceError">Please, enter a number.</label>
                                <input name="price" class="form-control" id="priceError" placeholder="Price">
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <div>
                                <input name="price" class="form-control" id="price" placeholder="Price">
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            <div class="form-group">
                <div>
                    <input name="category" class="form-control" id="category" placeholder="Category">
                </div>
            </div>
            <div class="form-group">
                <div>
                    <input name="params" class="form-control" id="params" placeholder="Params">
                </div>
            </div>
            <div class="form-group">
                <div>
                    <input name="weight" class="form-control" id="weight" placeholder="Weight">
                </div>
            </div>
            <div class="form-group">
                <div>
                    <input name="volume" class="form-control" id="volume" placeholder="Volume">
                </div>
            </div>
            <c:choose>
               <c:when test="${wrongNumber == 'amount'}">
                   <div class="form-group has-error">
                       <div>
                           <label class="control-label" for="amountError">Please, enter a number.</label>
                           <input name="amount" class="form-control" id="amountError" placeholder="Amount">
                       </div>
                   </div>
               </c:when>
                <c:otherwise>
                    <div class="form-group">
                       <div>
                          <input name="amount" class="form-control" id="amount" placeholder="Amount">
                       </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty fileUploadError}">
                <div class="form-group">
                    <div>
                        <input name="pic"  id="pic" type="file">
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty fileUploadError}">
                <div class="form-group has-error">
                    <div>
                        <br class="control-label" for="picError">Sorry, couldn't upload a file. Please, try one more time.</br> Jpeg is supported only yet.</label></label>
                        </label>
                        <input name="pic"  id="picError" type="file">
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <div>
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>

