<%-- 
    Document   : Login
    Created on : May 26, 2021, 7:42:14 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="assets/loginStylesheet.css"/>
        <!--         CSS only 
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        
                 JavaScript Bundle with Popper 
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        -->

    </head>
    <body>

        <div class="login">
            <div class="heading">
                <h2>Sign in</h2>
                <form action="DispatchServlet">

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" name="Username" value="" class="form-control" placeholder="Username or email">
                    </div>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" name="Password" value="" class="form-control" placeholder="Password">
                    </div>
                    <div class ="g-recaptcha" 
                         data-sitekey="6LeoIN8aAAAAAJeBlwu-4_58pMpY9d2tTX-5h1NO"></div>

                    <button type="submit" name="btnAction" value="Login" class="float">Login</button>
                    <c:if test="${not empty sessionScope.USERNOTACTIVE}">
                        <br>
                        <font color ="red">
                        <c:out value="${sessionScope.USERNOTACTIVE}"/>
                        <br>
                        <a href="activePage.jsp">Do you want to Verify your Account! CLick Here</a>
                        </font>

                    </c:if>
                    <br>

                    <a href="DispatchServlet?btnAction=RegisterPage" class="btn btn-success">Have an Account?</a>

                </form>
            </div>
        </div>

    </body>
</html>
