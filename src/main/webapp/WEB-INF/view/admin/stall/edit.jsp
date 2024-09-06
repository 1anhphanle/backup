<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Edit Stall</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />

            <div class="container mt-5">
                <h1>Edit Stall</h1>
                <form:form method="POST" action="/admin/stalls/edit/${stall.stallId}" modelAttribute="stall">
                    <div class="mb-3">
                        <form:label path="stallName" class="form-label">Stall Name:</form:label>
                        <form:input path="stallName" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="location" class="form-label">Location:</form:label>
                        <form:input path="location" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form:form>
                <a href="/admin/stalls" class="btn btn-secondary mt-3">Back to Stalls</a>
            </div>
        </body>

        </html>