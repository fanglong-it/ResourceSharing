<%-- 
    Document   : CreateAccount
    Created on : May 25, 2021, 3:45:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="loginStylesheet.css"/>


    </head>
    <body>

        <div class="login">
            <div class="heading">
                <form action="DispatchServlet" method="POST">
                    <h1>Create Account Page</h1>

                    <c:set var="err" value="${requestScope.ERRORS}"/>
                    <c:set var = "Dup" value="${requestScope.DUPLICATE}"/>

                    <div class="input-group input-group-lg"> 

                        Username:      <input type="email" name="txtUserName" value="" required/>
                        <c:if test="${not empty err.usernameErrs}">
                            <br>
                            <font color ="red">${err.usernameErrs} </font>
                        </c:if>
                    </div>

                    <div class="input-group input-group-lg"> 

                        PassWord:      <input type="password" name="txtPassword" value="" required/>
                        <c:if test="${not empty err.passwordErrs}">
                            <br>
                            <font color ="red">${err.passwordErrs} </font>
                        </c:if>
                    </div>
                    <div class="input-group input-group-lg"> 
                        Re-Password:   <input type="password" name="txtRePassword" value="" required />
                        <c:if test="${not empty err.rePasswordErrs}">
                            <br>
                            <font color ="red">${err.rePasswordErrs} </font>
                        </c:if>
                    </div>


                    <div class="input-group input-group-lg"> 
                        Phone Number:  <input type="Phone" name="txtPhoneNumber" value="" required=""/>
                        <c:if test="${not empty err.phoneNumberErrs}">
                            <br>
                            <font color ="red">${err.phoneNumberErrs} </font>
                        </c:if>
                    </div>

                    <div class="input-group input-group-lg"> 
                        Name:          <input type="text" name="txtName" value="" required=""/>
                        <c:if test="${not empty err.nameErrs}">
                            <br>
                            <font color ="red">${err.nameErrs} </font>
                        </c:if>
                    </div>

                    <div class="input-group input-group-lg"> 
                        Address:       <input type="text" name="txtAddress" value="" required=""/>
                        <c:if test="${not empty err.addressErrs}">
                            <br>
                            <font color ="red">${err.addressErrs} </font>
                        </c:if>
                    </div>

                    <br>
                    <input type="submit" value="Register" name="btnAction" />
                    <br>

                    <c:if test="${not empty Dup}">
                        <br>
                        <font color ="red">${Dup} </font>

                    </c:if>



                </form>
            </div>
        </div>
    </body>
</html>
