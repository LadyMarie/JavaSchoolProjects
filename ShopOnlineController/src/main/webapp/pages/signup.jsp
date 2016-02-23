<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 21.02.2016
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Signup</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/pages/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
    <c:if test="${not empty User}">
        <p class="text-center">Hi, ${User.name}!</p>
    </c:if>
    <form class="form-horizontal" action="signup" method="post">
        <fieldset>
            <c:if test="${empty sessionScope.User}">
                 <legend>Sign up</legend>
            </c:if>
            <c:if test="${not empty sessionScope.User}">
                <legend>Edit profile</legend>
            </c:if>

            <p class="text-warning">N.B.! Orange fields are obligatory.</p>
            <c:if test="${empty NoEmail}">
            <div class="form-group has-warning">
                <c:if test="${empty User.email}">
                <div>
                    <input name="email" class="form-control" id="inputEmail" placeholder="Email" type="text">
                </div>
                </c:if>
                <c:if test="${not empty User.email}">
                    <div>
                        <input name="email" class="form-control" id="inputUsEmail" placeholder="${User.email}" type="text">
                    </div>
                </c:if>
            </div>
            </c:if>
            <c:if test="${not empty NoEmail}">
                <div class="form-group has-error">
                    <label class="control-label" for="inputNoEmail">Please, write your email. It should look like 'name@domain'.</label>
                    <div>
                        <input name="email" class="form-control" id="inputNoEmail" placeholder="Email" type="text">
                    </div>
                </div>
            </c:if>
            <c:if test="${empty NoPassword}">
            <div class="form-group has-warning">
                    <div>
                        <input name="password" class="form-control" id="inputPassword" placeholder="Password" type="password">
                    </div>
            </div>
        </c:if>
            <c:if test="${not empty NoPassword}">
                <div class="form-group has-error">
                    <label class="control-label" for="inputNoEmail">Please, write your password. It should be correct and only contain letters and digits.</label>
                    <div>
                        <input name="password" class="form-control" id="inputNoPassword" placeholder="Password" type="password">
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <c:if test="${empty User.name}">
                    <div>
                        <input name="name" class="form-control" id="inputName" placeholder="Name" type="text">
                    </div>
                </c:if>
                <c:if test="${not empty User.name}">
                    <div>
                        <input name="name" class="form-control" id="inputUsName" placeholder="${User.name}" type="text">
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <c:if test="${empty User.surname}">
                    <div>
                        <input name="surname" class="form-control" id="inputsurname" placeholder="Surname" type="text">
                    </div>
                </c:if>
                <c:if test="${not empty User.surname}">
                    <div>
                        <input name="surname" class="form-control" id="inputUssurname" placeholder="${User.surname}" type="text">
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div>
                    <input name="birthDay" class="form-control" id="inputBirthDay" placeholder="Day of birth">
                </div>
            </div>
            <div class="form-group">
                <div>
                    <select class="form-control" id="select">
                        <option>December</option>
                        <option>February</option>
                        <option>March</option>
                        <option>April</option>
                        <option>May</option>
                        <option>June</option>
                        <option>July</option>
                        <option>August</option>
                        <option>September</option>
                        <option>October</option>
                        <option>December</option>
                        <option>January</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <input name="birthYear" class="form-control" id="inputBirthYear" placeholder="Year of birth">
                </div>
            </div>
            <div class="form-group">
                <!--So long conditions, because sessionScope.User was got from db by previous requests,
                 where field role is a table. And requestScope.User is a data, just saved from this form,
                 during current request. Servlet haven't called db methods yet(because before successfull
                 validation it is temporary data and don't need to be saved to db), so he only can
                 set a string role field in a User-->
            <c:if test="${sessionScope.User.role.name == 'Employee' || requestScope.User.strRole == 'Employee'}">
                <input type="checkbox" name="isEmployee" id="isEmployee" checked="true"> You are employee</input>
            </c:if>
            <c:if test="${sessionScope.User.role.name != 'Employee' && requestScope.User.strRole != 'Employee'}">
                <input type="checkbox" name="isEmployee" id="isEmployee"> You are employee</input>
            </c:if>
            </div>

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
