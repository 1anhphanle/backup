<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Edit Customer</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </head>

        <body>
            <div class="container mt-5">
                <h1>Edit Customer</h1>
                <form:form method="POST" action="/admin/customers/edit/${customer.customerId}"
                    modelAttribute="customer">
                    <div class="mb-3">
                        <form:label path="customerName" class="form-label">Customer Name:</form:label>
                        <form:input path="customerName" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="phone" class="form-label">Phone:</form:label>
                        <form:input path="phone" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="address" class="form-label">Address:</form:label>
                        <form:input path="address" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="email" class="form-label">Email:</form:label>
                        <form:input path="email" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="loyaltyPoint" class="form-label">Loyalty Point:</form:label>
                        <form:input path="loyaltyPoint" class="form-control" type="number" />
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form:form>
                <a href="/admin/customers" class="btn btn-secondary mt-3">Back to Customers</a>
            </div>
        </body>

        </html>