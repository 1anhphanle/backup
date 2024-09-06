<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Product Types</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/styles.css">
        </head>

        <body>
            <div class="container mt-5">
                <h1>Product Types</h1>
                <a href="/admin/product-types/create" class="btn btn-primary mb-3">Create New Product Type</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product Type Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${productTypes}" var="productType">
                            <tr>
                                <td>${productType.productTypeId}</td>
                                <td>${productType.productTypeName}</td>
                                <td>
                                    <a href="/admin/product-types/edit/${productType.productTypeId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/product-types/delete/${productType.productTypeId}"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this product type?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>