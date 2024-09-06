<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Customer Policies</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/styles.css">
        </head>

        <body>
            <div class="container mt-5">
                <h1>Customer Policies</h1>
                <a href="/admin/customer-policies/create" class="btn btn-primary mb-3">Create New Policy</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Policy Name</th>
                            <th>Customer Type</th>
                            <th>Discount Rate</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${customerPolicies}" var="policy">
                            <tr>
                                <td>${policy.policyId}</td>
                                <td>${policy.policyName}</td>
                                <td>${policy.customerType}</td>
                                <td>${policy.discountRate}</td>
                                <td>
                                    <a href="/admin/customer-policies/edit/${policy.policyId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/customer-policies/delete/${policy.policyId}"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this customer policy?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>