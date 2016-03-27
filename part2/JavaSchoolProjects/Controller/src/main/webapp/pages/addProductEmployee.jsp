<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19.02.2016
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>login</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/pastel.jpg"/>);
            background-size: contain;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: contain;
            -moz-background-size: contain;
            -o-background-size: contain;
        }
        .layer {
            background-image:  linear-gradient(to right, rgba(0,0,0,0), rgba(255,255,255,1), rgba(0,0,0,0));
            height: 100%;
        }
    </style>
</head>
<body>
<div class="layer">
<div align="center">
        <form:form class="form-horizontal" action="addProduct" method="post"  modelAttribute="Product" enctype="multipart/form-data">
        <fieldset>
            <legend>Add product</legend>

                <div class="form-group">
                    <div>
                        <form:errors path="name" class="control-label has-error text-primary" id="inputNameErr"/>
                        <form:input path="name" class="form-control" id="inputName" placeholder="Name" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <form:errors path="price" class="control-label has-error text-primary" id="inputPriceErr"/>
                        <form:input path="price" class="form-control" id="price" placeholder="Price"/>
                    </div>
                </div>
            <div class="form-group">
                <div>
                    <form:errors path="category" class="control-label has-error text-primary" id="inputCategoryErr"/>
                    <form:input path="category" class="form-control" id="category" placeholder="Category"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <form:errors path="params" class="control-label has-error text-primary" id="inputParamErr"/>
                    <form:input path="params" class="form-control" id="params" placeholder="Params"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <form:errors path="weight" class="control-label has-error text-primary" id="inputWeightErr"/>
                    <form:input path="weight" class="form-control" id="weight" placeholder="Weight"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <form:errors path="volume" class="control-label has-error text-primary" id="inputVolumeErr"/>
                    <form:input path="volume" class="form-control" id="volume" placeholder="Volume"/>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <form:errors path="amount" class="control-label has-error text-primary" id="inputAmountErr"/>
                    <form:input path="amount" class="form-control" id="amount" placeholder="Amount"/>
                </div>
            </div>
                <div class="form-group">
                    <div>
                        <c:if test="${not empty fileUploadError}">
                            <label class="control-label">Sorry, couldn't upload a file. Please, try one more time.</br> Jpeg is supported only yet.</label>
                        </c:if>
                        <c:if test="${not empty fileToBig}">
                            <label class="control-label">File is too big. Size is limited < 10Mb.</label>
                        </c:if>
                        <form:errors path="pic" id="picEr" class="control-label has-error"/>
                        <form:input path="pic" id="pic" type="file"/>
                    </div>
                </div>
            <div class="form-group">
                <div>
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
    </div>
</body>
</html>

