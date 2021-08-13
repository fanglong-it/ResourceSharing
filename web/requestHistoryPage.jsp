<%-- 
    Document   : requestHistoryPage
    Created on : Aug 10, 2021, 9:55:35 PM
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

        <title>Employee</title>
    </head>
    <body>
        <h1 class="text-center fst-italic">This is Employee</h1>
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
                <c:if test="${sessionScope.USER.role eq 'AD'}">
                    <c:redirect url="managerPage.jsp"/>
                </c:if>
            </c:if>




            <div class="d-flex justify-content-center fst-italic">
                <div class="col-xs-4">
                    <input type="text" class="form-control" name="txtSearchByName" value="" />
                </div>
                <input type="submit" class="btn btn-success mx-2" value="Search" name="btnAction" />
                <input type="submit" class="btn btn-warning mx-2" value="Request History" name="btnAction" />
            </div>
            <br>

            <div class="d-flex justify-content-center">
                <!--<!--START OF ITEM -->
                <c:if test="${empty sessionScope.REQUEST_HISTORY}">
                    <font color ="red" class="fst-italic">
                    Not found request now..
                    </font>
                </c:if>

                <c:if test="${not empty sessionScope.REQUEST_HISTORY}">
                    <table border="1" class="table table-striped table-fixed w-75">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Item Name</th>
                                <th>Item Id</th>
                                <th>Date Request</th>
                                <th>Date Accept</th>
                                <th>Status</th>
                                <th>Quantity</th>
                                <th>Action</th>


                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach  var="o" items="${sessionScope.REQUEST_HISTORY}">
                            <form action="DispatchServlet">

                                <tr>
                                    <th>
                                        ${o.userID}
                                        <input type="hidden" name="txtUserId" value="${o.userID}" />
                                    </th>
                                    <th>${o.itemName}</th>
                                    <th>
                                        ${o.itemId}

                                        <input type="hidden" name="txtItemId" value="${o.itemId}" />
                                    </th>

                                    <th>${o.dateRequest}</th>
                                    <th>${o.dateAccept}</th>
                                    <th>
                                        ${o.status}
                                    </th>
                                    <th>
                                        ${o.quantity}
                                        <input type="hidden" name="txtQuantity" value="${o.quantity}" />
                                    </th>
                                    <th>
                                        <c:if test="${o.status eq 'New'}">
                                            <a class="text">Waiting for admin Approve!</a>
                                        </c:if>

                                        <c:if test="${o.status eq 'Accept'}">
                                            <a class="text">Have been Accepted From Admin!</a>
                                            <button class="btn btn-danger" name="btnAction" value="Return">Return</button> 
                                        </c:if>
                                        <c:if test="${o.status eq 'Delete'}">
                                            <a class="text">Have been Reject from Admin!</a>
                                        </c:if>

                                        <c:if test="${o.status eq 'Done'}">
                                            <a class="text">You have done this Request!</a>
                                        </c:if>
                                    </th>

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
