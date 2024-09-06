<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create Buyback Invoice</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />
                <div class="container mt-5">
                    <h1>Create Buyback Invoice</h1>
                    <form:form method="POST" action="/admin/buyback-invoices/create" modelAttribute="buybackInvoice">
                        <div class="mb-3">
                            <form:label path="customer" class="form-label">Customer:</form:label>
                            <form:select path="customer" class="form-select">
                                <form:option value="">-- Select Customer --</form:option>
                                <c:forEach items="${customers}" var="customer">
                                    <form:option value="${customer}">${customer.customerName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="employee" class="form-label">Employee:</form:label>
                            <form:select path="employee" class="form-select">
                                <form:option value="">-- Select Employee --</form:option>
                                <c:forEach items="${employees}" var="employee">
                                    <form:option value="${employee}">${employee.employeeName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="stall" class="form-label">Stall:</form:label>
                            <form:select path="stall" class="form-select">
                                <form:option value="">-- Select Stall --</form:option>
                                <c:forEach items="${stalls}" var="stall">
                                    <form:option value="${stall}">${stall.stallName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <label for="salesInvoiceId" class="form-label">Sales Invoice ID:</label>
                            <input type="number" class="form-control" id="salesInvoiceId" name="salesInvoiceId">
                        </div>

                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                    <a href="/admin/buyback-invoices" class="btn btn-secondary mt-3">Back to Buyback Invoices</a>
                </div>
            </body>

            </html>