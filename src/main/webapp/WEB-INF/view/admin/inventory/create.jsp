<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create Inventory</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <h1>Create Inventory</h1>
                    <form:form method="POST" action="/admin/inventories/create" modelAttribute="inventory">
                        <div class="mb-3">
                            <form:label path="stall" class="form-label">Stall:</form:label>
                            <form:select path="stall" class="form-control">
                                <form:option value="">-- Select Stall --</form:option>
                                <c:forEach items="${stalls}" var="stall">
                                    <form:option value="${stall.stallId}">${stall.stallName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="product" class="form-label">Product:</form:label>
                            <form:select path="product" class="form-control">
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
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                    <a href="/admin/inventories" class="btn btn-secondary mt-3">Back to Inventories</a>
                </div>
            </body>

            </html>