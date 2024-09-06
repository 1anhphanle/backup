<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Promotions</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Promotions</h1>
                    <a href="/admin/promotions/create" class="btn btn-primary mb-3">Create New Promotion</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Promotion Name</th>
                                <th>Promotion Type</th>
                                <th>Condition</th>
                                <th>Value</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${promotions}" var="promotion">
                                <tr>
                                    <td>${promotion.promotionId}</td>
                                    <td>${promotion.promotionName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${promotion.promotionType == 'PERCENTAGE'}">Giảm theo phần
                                                trăm</c:when>
                                            <c:otherwise>Giảm theo số tiền</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${promotion.cdt}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${promotion.promotionType == 'PERCENTAGE'}">
                                                ${promotion.value}%</c:when>
                                            <c:otherwise>
                                                <fmt:formatNumber value="${promotion.value}" pattern="#,###" /> VND
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${promotion.startDate}" pattern="yyyy-MM-dd" />
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${promotion.endDate}" pattern="yyyy-MM-dd" />
                                    </td>
                                    <td>
                                        <a href="/admin/promotions/edit/${promotion.promotionId}"
                                            class="btn btn-warning btn-sm">Edit</a>
                                        <a href="/admin/promotions/delete/${promotion.promotionId}"
                                            class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this promotion?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>