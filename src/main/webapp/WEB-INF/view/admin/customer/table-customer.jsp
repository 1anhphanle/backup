<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Customers</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />


            <div class="container mt-5">
                <h1>Customers</h1>
                <a href="/admin/customers/create" class="btn btn-primary mb-3">Create New Customer</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Customer Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Loyalty Point</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${customers}" var="customer">
                            <tr>
                                <td>${customer.customerId}</td>
                                <td>${customer.customerName}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.address}</td>
                                <td>${customer.email}</td>
                                <td>${customer.loyaltyPoint}</td>
                                <td>
                                    <a href="/admin/customers/edit/${customer.customerId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/customers/delete/${customer.customerId}"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>