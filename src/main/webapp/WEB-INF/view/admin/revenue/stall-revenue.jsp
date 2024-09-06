<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Revenue by Stall</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Revenue by Stall</h1>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Stall Name</th>
                                <th>Total Revenue</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${revenueByStall}" var="entry">
                                <tr>
                                    <td>${entry.key.stallName}</td>
                                    <td>
                                        <fmt:formatNumber value="${entry.value}" pattern="#,###" /> VND
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>