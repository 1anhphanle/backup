<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Edit Product</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Edit Product</h1>
                    <form:form method="POST" action="/admin/products/edit/${product.productId}"
                        modelAttribute="product">
                        <div class="mb-3">
                            <form:label path="productName" class="form-label">Product Name:</form:label>
                            <form:input path="productName" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="productType" class="form-label">Product Type:</form:label>
                            <form:select path="productType" class="form-control">
                                <form:option value="">-- Select Product Type --</form:option>
                                <form:option value="Nhẫn">Nhẫn</form:option>
                                <form:option value="Dây Chuyền">Dây Chuyền</form:option>
                                <form:option value="Bông tai">Bông tai</form:option>
                                <form:option value="Vòng tay">Vòng tay</form:option>
                                <form:option value="Mặt dây chuyền">Mặt dây chuyền</form:option>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="weight" class="form-label">Weight:</form:label>
                            <form:input path="weight" class="form-control" type="number" step="0.01" />
                        </div>
                        <div class="mb-3">
                            <form:label path="material" class="form-label">Material:</form:label>
                            <form:input path="material" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="makingFee" class="form-label">Making Fee:</form:label>
                            <form:input path="makingFee" class="form-control" type="number" step="0.01" />
                        </div>
                        <div class="mb-3">
                            <form:label path="gemstonePrice" class="form-label">Gemstone Price:</form:label>
                            <form:input path="gemstonePrice" class="form-control" type="number" step="0.01" />
                        </div>
                        <div class="mb-3">
                            <form:label path="costPrice" class="form-label">Cost Price:</form:label>
                            <form:input path="costPrice" class="form-control" type="number" step="0.01" />
                        </div>
                        <div class="mb-3">
                            <form:label path="markupRatio" class="form-label">Markup Ratio:</form:label>
                            <form:input path="markupRatio" class="form-control" type="number" step="0.01" />
                        </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form:form>
                    <a href="/admin/products" class="btn btn-secondary mt-3">Back to Products</a>
                </div>
            </body>

            </html>