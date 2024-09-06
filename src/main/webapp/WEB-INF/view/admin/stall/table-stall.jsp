<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Stalls</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />

            <div class="container mt-5">
                <h1>Stalls</h1>
                <a href="/admin/stalls/create" class="btn btn-primary mb-3">Create New Stall</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Stall Name</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${stalls}" var="stall">
                            <tr>
                                <td>${stall.stallId}</td>
                                <td>${stall.stallName}</td>
                                <td>${stall.location}</td>
                                <td>
                                    <a href="/admin/stalls/${stall.stallId}" class="btn btn-primary btn-sm">View
                                        Products</a>
                                    <a href="/admin/stalls/edit/${stall.stallId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/stalls/delete/${stall.stallId}" class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this stall?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>