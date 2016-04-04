<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Signup</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
    <style>
        body{
            background-image: url(<c:url value="/icons/signup.jpg"/>);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
        .layer {
            background-image:  linear-gradient(to right, rgba(0,0,0,0), rgba(255,255,255,1), rgba(0,0,0,0));
            height: 100%;
            background-size: cover;
            background-attachment: fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
        }
    </style>
</head>
<body>
<div class="layer">
    <div align="center">
    <c:if test="${not empty User.name}">
        <p class="text-center">Hi, ${User.name}!</p>
    </c:if>
    <form:form class="form-horizontal" method="post" action="addPerson" modelAttribute="User">
        <fieldset>
            <c:if test="${empty sessionScope.User}">
                 <legend>Sign up</legend>
            </c:if>
            <c:if test="${not empty sessionScope.User}">
                <legend>Edit profile</legend>
            </c:if>

            <p class="text-warning">N.B.! Orange fields are obligatory.</p>
            <div class="form-group has-warning">
                    <div>
                        <form:errors path="email" class="control-label has-error" id="inputUsEmailErr"/>
                        <form:input path="email" class="form-control" id="inputUsEmail"  type="text" placeholder="Email"/>
                    </div>
            </div>
            <div class="form-group has-warning">
                    <div>
                        <form:errors name="password" path="password" class="control-label has-error" id="inputPasswordErr" placeholder="Password"/>
                        <form:password name="password" path="password" class="form-control" id="inputPassword" placeholder="Password"/>
                    </div>
            </div>
            <div class="form-group">
                    <div>
                        <form:errors name="name" path="name" class="control-label has-error" id="inputUsNameErr"/>
                        <form:input name="name" path="name" class="form-control" id="inputUsName" placeholder="Name"  type="text"/>
                    </div>
            </div>
            <div class="form-group">
                    <div>
                        <form:errors name="surname" path="surname" class="control-label has-error" id="inputUssurnameErr"/>
                        <form:input name="surname" path="surname" class="form-control" id="inputUssurname" placeholder="Surname" type="text"/>
                    </div>
            </div>
                <div class="form-group">
                    <div>
                        <form:errors name="birthDay" path="birthDay" class="control-label has-error  text-primary" id="inputUsBirthDayEr"/>
                        <form:input name="birthDay" path="birthDay" class="form-control" id="inputUsBirthDay" placeholder="Birth day" type="text"/>
                    </div>
                </div>
            <div class="form-group">
                <div>
                    <form:select class="form-control" path="birthMonth" name="month">
                        <option name="January">January</option>
                        <option name="February">February</option>
                        <option name="March">March</option>
                        <option name="April">April</option>
                        <option name="May">May</option>
                        <option name="June">June</option>
                        <option name="July">July</option>
                        <option name="August">August</option>
                        <option name="September">September</option>
                        <option name="October">October</option>
                        <option name="November">November</option>
                        <option name="December">December</option>
                    </form:select>
                </div>
            </div>
                <div class="form-group">
                    <div>
                        <form:errors name="birthYear" path="birthYear" class="control-label has-error  text-primary" id="inputUsBirthYearEr"/>
                        <form:input name="birthYear" path="birthYear" class="form-control" id="inputUsBirthYear" placeholder="Birth year" type="text"/>
                    </div>
                </div>
            <div class="form-group">
                <label>You are employee</label>
                <form:checkbox path="isEmployee" id="isEmployee"/>
            </div>

            <div class="form-group">
                <div>
                    <a href="${context}/Main"  class="btn btn-default">Main</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>

        </fieldset>
    </form:form>
        </div>
</div>
</body>
</html>
