<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Sales Invoice Details</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/styles.css">
        </head>

        <body>
            <div class="container mt-5">
                <h1>Sales Invoice Details</h1>
                <a href="/admin/sales-invoice-details/create" class="btn btn-primary mb-3">Create New Sales Invoice
                    Detail</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Sales Invoice</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${salesInvoiceDetails}" var="detail">
                            <tr>
                                <td>${detail.salesInvoice.salesInvoiceId}</td>
                                <td>${detail.product.productName}</td>
                                <td>${detail.quantity}</td>
                                <td>${detail.unitPrice}</td>
                                <td>
                                    <a href="/admin/sales-invoice-details/edit/${detail.id.salesInvoiceId}/${detail.id.productId}"
                                        class="btn btn-warning btn-sm">Edit</a>
                                    <a href="/admin/sales-invoice-details/delete/${detail.id.salesInvoiceId}/${detail.id.productId}"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this sales invoice detail?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>