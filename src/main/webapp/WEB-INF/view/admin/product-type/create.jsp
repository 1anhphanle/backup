<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Create Product Type</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </head>

        <body>
            <div class="container mt-5">
                <h1>Create Product Type</h1>
                <form:form method="POST" action="/admin/product-types/create" modelAttribute="productType">
                    <div class="mb-3">
                        <form:label path="productTypeName" class="form-label">Product Type Name:</form:label>
                        <form:input path="productTypeName" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
                <a href="/admin/product-types" class="btn btn-secondary mt-3">Back to Product Types</a>
            </div>
        </body>

        </html>