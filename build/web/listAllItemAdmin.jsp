<%-- 
    Document   : listAllItemAdmin
    Created on : Aug 10, 2021, 11:38:45 PM
    Author     : cunpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <title>Admin</title>
    </head>
    <body>
         <h1 class="text-center fst-italic">This is Manager</h1>
        <form action="DispatchServlet">


            <!-- LOGIN AND LOGOUT -->
            <div class="position-absolute top-0 end-0">
                <font color ="red" class="fst-italic">
                Welcome, ${sessionScope.USER.fullName}
                <br>
                </font>
                <input type="submit" class="fst-italic" value="Logout" name="btnAction" />
            </div> 

            <c:if test="${empty sessionScope.USER}">
                <c:redirect url="login.jsp"/>
            </c:if>

            <c:if test="${not empty sessionScope.USER}">
                <c:if test="${sessionScope.USER.role eq 'US'}">
                    <c:redirect url="employeePage.jsp"/>
                </c:if>
            </c:if>




            <div class="d-flex justify-content-center fst-italic">
                <div class="col-xs-4">
                    <input type="text" class="form-control" name="txtSearchByName" value="" />
                </div>
                <input type="submit" class="btn btn-success mx-2" value="SearchRequest" name="btnAction" />
                <input type="submit" class="btn btn-warning mx-2" value="List All Item" name="btnAction" />

            </div>
            <br>

            <div class="d-flex justify-content-center">
                <!--<!--START OF ITEM -->
                <c:if test="${empty sessionScope.LIST_ALL_ITEM}">
                    <font color ="red" class="fst-italic">
                    Not found
                    </font>
                </c:if>

                <c:if test="${not empty sessionScope.LIST_ALL_ITEM}">
                    <table border="1" class="table table-striped table-fixed w-75">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Item Name</th>
                                <th>Color</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Item Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach  var="o" items="${sessionScope.LIST_ALL_ITEM}">
                            <form action="DispatchServlet">

                                <tr>
                                    <th>
                                        ${o.itemID}
                                        <input type="hidden" name="txtItemId" value="${o.itemID}" />
                                    </th>
                                    <th>
                                        ${o.itemName}
                                        <input type="hidden" name="txtItemName" value="${o.itemName}" />
                                    </th>
                                    <th>
                                        ${o.color}
                                    </th>
                                    <th>
                                        ${o.category}
                                    </th>
                                    <th>
                                        ${o.quantity}
                                    </th>
                                    <th>
                                        ${o.itemDate}</th>
                                  

                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </form>
    </body>
</html>
