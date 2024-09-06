<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Edit Customer Policy</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </head>

        <body>
            <div class="container mt-5">
                <h1>Edit Customer Policy</h1>
                <form:form method="POST" action="/admin/customer-policies/edit/${customerPolicy.policyId}"
                    modelAttribute="customerPolicy">
                    <div class="mb-3">
                        <form:label path="policyName" class="form-label">Policy Name:</form:label>
                        <form:input path="policyName" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="customerType" class="form-label">Customer Type:</form:label>
                        <form:input path="customerType" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="discountRate" class="form-label">Discount Rate:</form:label>
                        <form:input path="discountRate" class="form-control" type="number" step="0.01" />
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form:form>
                <a href="/admin/customer-policies" class="btn btn-secondary mt-3">Back to Customer Policies</a>
            </div>
        </body>

        </html>