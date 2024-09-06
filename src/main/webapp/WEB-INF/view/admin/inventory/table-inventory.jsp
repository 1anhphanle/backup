<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Inventories</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/styles.css">
        </head>

        <body>
            <div class="container mt-5">
                <h1>Inventories</h1>
                <a href="/admin/inventories/create" class="btn btn-primary mb-3">Create New Inventory</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Stall</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${inventories}" var="inventory">
                            <tr>
                                <td>${inventory.stall.stallName}</td>
                                <td>${inventory.product.productName}</td>
                                <td>${inventory.quantity}</td>
                                <td>
                                    <a href="/admin/inventories/edit/${inventory.id.stallId}/${inventory.id.productId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/inventories/delete/${inventory.id.stallId}/${inventory.id.productId}"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this inventory item?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>