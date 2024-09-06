<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Sales Invoices</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <style>
                    .container {
                        max-width: 1100px;
                        /* Giới hạn chiều rộng tối đa của container */
                        margin: 0 auto;
                        /* Căn giữa container */
                    }

                    .content {
                        margin-left: 0;
                        /* Loại bỏ khoảng cách cho sidebar */
                        padding: 20px;
                    }

                    /* Thêm padding cho form để nội dung không bị sát mép */
                    .form-control {
                        padding: 10px;
                    }

                    /* Giảm kích thước font chữ cho các phần tử trong form */
                    .form-label,
                    .form-select,
                    .btn {
                        font-size: 14px;
                    }
                </style>
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Sales Invoices</h1>
                    <a href="/admin/sales-invoices/create" class="btn btn-primary mb-3">Create New Sales Invoice</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Created Date</th>
                                <th>Customer</th>
                                <th>Employee</th>
                                <th>Stall</th>
                                <th>Total Amount</th>
                                <th>Discount</th>
                                <th>Final Amount Payable</th>
                                <th>Payment Method</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${salesInvoices}" var="invoice">
                                <tr>
                                    <td>${invoice.salesInvoiceId}</td>
                                    <td>
                                        <fmt:formatDate value="${invoice.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </td>
                                    <td>${invoice.customer.customerName}</td>
                                    <td>${invoice.employee.employeeName}</td>
                                    <td>${invoice.stall.stallName}</td>
                                    <td>
                                        <fmt:formatNumber value="${invoice.totalAmount}" pattern="#,###" /> VND
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${invoice.discount}" pattern="#,###" /> VND
                                    </td>
                                    <td>
                                        <%-- Tính toán và hiển thị "Final Amount Payable" --%>
                                            <fmt:formatNumber value="${invoice.totalAmount.subtract(invoice.discount)}"
                                                pattern="#,###" /> VND
                                    </td>
                                    <td>${invoice.paymentMethod}</td>
                                    <td>
                                        <a href="/admin/sales-invoice-details/invoice/${invoice.salesInvoiceId}"
                                            class="btn btn-info btn-sm">Details</a> <%-- Thêm link xem chi tiết --%>
                                            <a href="/admin/sales-invoices/download/${invoice.salesInvoiceId}"
                                                class="btn btn-primary btn-sm">Invoice PDF</a>
                                            <a href="/admin/sales-invoices/download-warranty/${invoice.salesInvoiceId}"
                                                class="btn btn-secondary btn-sm">Warranty PDF</a>
                                            <a href="/admin/sales-invoices/delete/${invoice.salesInvoiceId}"
                                                class="btn btn-danger btn-sm"
                                                onclick="return confirm('Are you sure you want to delete this sales invoice?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>