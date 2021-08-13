<%-- 
    Document   : activePage
    Created on : Aug 10, 2021, 1:14:28 PM
    Author     : cunpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Active code</h1>
        <form action="DispatchServlet">
            <input type="text" name="userId" value="${sessionScope.ACTIVE_ACCOUNT}"  readonly=""/>
            <input type="text" name="subCode" value="" />
            <input type="submit" value="submitCode" name="btnAction" />
            
            <c:if test="${not empty requestScope.CODE_MESSAGE_ERROR}">
                ${requestScope.CODE_MESSAGE_ERROR}
            </c:if>
            
        </form>
    </body>
</html>
