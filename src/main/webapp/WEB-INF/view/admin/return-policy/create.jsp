<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Create Return Policy</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />

            <div class="container mt-5">
                <h1>Create Return Policy</h1>
                <form:form method="POST" action="/admin/return-policies/create" modelAttribute="returnPolicy">
                    <div class="mb-3">
                        <form:label path="cdt" class="form-label">Condition:</form:label>
                        <form:textarea path="cdt" class="form-control" rows="5" />
                    </div>
                    <div class="mb-3">
                        <form:label path="duration" class="form-label">Duration (days):</form:label>
                        <form:input path="duration" class="form-control" type="number" />
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
                <a href="/admin/return-policies" class="btn btn-secondary mt-3">Back to Return Policies</a>
            </div>
        </body>

        </html>