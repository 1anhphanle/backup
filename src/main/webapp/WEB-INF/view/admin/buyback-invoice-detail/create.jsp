<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create Buyback Invoice Detail</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <h1>Create Buyback Invoice Detail</h1>
                    <form:form method="POST" action="/admin/buyback-invoice-details/create"
                        modelAttribute="buybackInvoiceDetail">
                        <div class="mb-3">
                            <form:label path="buybackInvoice" class="form-label">Buyback Invoice:</form:label>
                            <form:select path="buybackInvoice" class="form-select">
                                <form:option value="">-- Select Buyback Invoice --</form:option>
                                <c:forEach items="${buybackInvoices}" var="buybackInvoice">
                                    <form:option value="${buybackInvoice.buybackInvoiceId}">
                                        ${buybackInvoice.buybackInvoiceId}
                                    </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="product" class="form-label">Product:</form:label>
                            <form:select path="product" class="form-select">
                                <form:option value="">-- Select Product --</form:option>
                                <c:forEach items="${products}" var="product">
                                    <form:option value="${product.productId}">${product.productName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="quantity" class="form-label">Quantity:</form:label>
                            <form:input path="quantity" class="form-control" type="number" />
                        </div>
                        <div class="mb-3">
                            <form:label path="unitPrice" class="form-label">Unit Price:</form:label>
                            <form:input path="unitPrice" class="form-control" type="number" step="0.01" />
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                    <a href="/admin/buyback-invoice-details" class="btn btn-secondary mt-3">Back to Buyback Invoice
                        Details</a>
                </div>
            </body>

            </html>