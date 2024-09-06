<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Buyback Invoices</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Buyback Invoices</h1>
                    <a href="/admin/buyback-invoices/create" class="btn btn-primary mb-3">Create New Buyback Invoice</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Created Date</th>
                                <th>Customer</th>
                                <th>Employee</th>
                                <th>Stall</th>
                                <th>Sales Invoice ID</th> <%-- Thêm cột Sales Invoice ID --%>
                                    <th>Total Amount</th>
                                    <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${buybackInvoices}" var="buybackInvoice">
                                <tr>
                                    <td>${buybackInvoice.buybackInvoiceId}</td>
                                    <td>
                                        <fmt:formatDate value="${buybackInvoice.createdDate}"
                                            pattern="yyyy-MM-dd HH:mm:ss" />

                                    </td>
                                    <td>${buybackInvoice.customer.customerName}</td>
                                    <td>${buybackInvoice.employee.employeeName}</td>
                                    <td>${buybackInvoice.stall.stallName}</td>
                                    <td>${buybackInvoice.salesInvoice.salesInvoiceId}</td> <%-- Hiển thị Sales Invoice
                                        ID --%>
                                        <td>
                                            <fmt:formatNumber value="${buybackInvoice.totalAmount}" pattern="#,###" />
                                            VND
                                        </td>
                                        <td>
                                            <a href="/admin/buyback-invoices/download/${buybackInvoice.buybackInvoiceId}"
                                                class="btn btn-primary btn-sm">Download PDF</a>
                                            <a href="/admin/buyback-invoices/delete/${buybackInvoice.buybackInvoiceId}"
                                                class="btn btn-danger btn-sm"
                                                onclick="return confirm('Are you sure you want to delete this buyback invoice?')">Delete</a>
                                        </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>