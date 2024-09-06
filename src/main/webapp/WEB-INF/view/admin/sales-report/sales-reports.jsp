<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Sales Reports</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Sales Reports</h1>

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" href="/admin/sales-reports/stall">Stall Sales</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/sales-reports/employee">Employee Sales</a>
                        </li>
                    </ul>

                    <div class="tab-content mt-3">
                        <div class="tab-pane fade show active" id="stall-sales" role="tabpanel">
                            <h2>Stall Sales</h2>

                            <c:if test="${!empty errorMessage}">
                                <div class="alert alert-danger" role="alert">${errorMessage}</div>
                            </c:if>

                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Stall ID</th>
                                        <th>Stall Name</th>
                                        <th>Total Sales</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${stalls}" var="stall">
                                        <tr>
                                            <td>${stall.stallId}</td>
                                            <td>${stall.stallName}</td>
                                            <td>
                                                <fmt:formatNumber value="${stallSales[stall.stallId]}"
                                                    pattern="#,###" /> VND
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="tab-pane fade" id="employee-sales" role="tabpanel">
                            <h2>Employee Sales</h2>

                            <c:if test="${!empty errorMessage}">
                                <div class="alert alert-danger" role="alert">${errorMessage}</div>
                            </c:if>

                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Employee ID</th>
                                        <th>Employee Name</th>
                                        <th>Total Sales</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${employees}" var="employee">
                                        <tr>
                                            <td>${employee.employeeId}</td>
                                            <td>${employee.employeeName}</td>
                                            <td>
                                                <fmt:formatNumber value="${employeeSales[employee.employeeId]}"
                                                    pattern="#,###" /> VND
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </body>

            </html>