<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create Sales Invoice</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Create Sales Invoice</h1>
                    <form:form method="POST" action="/admin/sales-invoices/create" modelAttribute="salesInvoice">
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

                        <h2>Product Details</h2>
                        <div id="productDetailsContainer">
                            <div class="mb-3 row product-detail-row">
                                <div class="col-sm-3">
                                    <label for="productId" class="form-label">Product ID:</label>
                                    <input type="number" class="form-control" id="productId" name="productIds[]">
                                </div>
                                <div class="col-sm-2">
                                    <label for="quantity" class="form-label">Quantity:</label>
                                    <input type="number" class="form-control" id="quantity" name="quantities[]" min="1"
                                        value="1">
                                </div>
                                <div class="col-sm-1 align-self-end">
                                    <button type="button" class="btn btn-danger remove-product">Remove</button>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-secondary" id="addProduct">Add Product</button>

                        <h2>Promotions</h2>
                        <div class="mb-3">
                            <c:forEach items="${promotions}" var="promotion" varStatus="loop">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="promotionIds"
                                        value="${promotion.promotionId}" id="promotion_${loop.index}">
                                    <label class="form-check-label" for="promotion_${loop.index}">
                                        ${promotion.promotionName} -
                                        <c:choose>
                                            <c:when test="${promotion.promotionType == 'PERCENTAGE'}">Giảm
                                                ${promotion.value}%</c:when>
                                            <c:otherwise>Giảm
                                                <fmt:formatNumber value="${promotion.value}" pattern="#,###" /> VND
                                            </c:otherwise>
                                        </c:choose>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="mb-3">
                            <form:label path="totalAmount" class="form-label">Total Amount:</form:label>
                            <form:input path="totalAmount" class="form-control" type="number" step="0.01"
                                readonly="true" value="0" />
                        </div>
                        <div class="mb-3">
                            <form:label path="discount" class="form-label">Discount:</form:label>
                            <form:input path="discount" class="form-control" type="number" step="0.01" value="0" />
                        </div>
                        <div class="mb-3">
                            <form:label path="paymentMethod" class="form-label">Payment Method:</form:label>
                            <form:input path="paymentMethod" class="form-control" />
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                    <a href="/admin/sales-invoices" class="btn btn-secondary mt-3">Back to Sales Invoices</a>
                </div>

                <script>
                    document.getElementById('addProduct').addEventListener('click', function () {
                        const container = document.getElementById('productDetailsContainer');
                        const newRow = document.createElement('div');
                        newRow.classList.add('mb-3', 'row', 'product-detail-row');
                        newRow.innerHTML = `
                <div class="col-sm-3">
                    <label for="productId" class="form-label">Product ID:</label>
                    <input type="number" class="form-control" id="productId" name="productIds[]">
                </div>
                <div class="col-sm-2">
                    <label for="quantity" class="form-label">Quantity:</label>
                    <input type="number" class="form-control" id="quantity" name="quantities[]" min="1" value="1">
                </div>
                <div class="col-sm-1 align-self-end">
                    <button type="button" class="btn btn-danger remove-product">Remove</button>
                </div>
            `;
                        container.appendChild(newRow);

                        // Thêm event listener cho nút "Remove" mới được tạo
                        newRow.querySelector('.remove-product').addEventListener('click', function () {
                            container.removeChild(newRow);
                        });
                    });
                </script>
            </body>

            </html>