<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Create Gold Price</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />

            <div class="container mt-5">
                <h1>Create Gold Price</h1>
                <form:form method="POST" action="/admin/gold-prices/create" modelAttribute="goldPrice">
                    <div class="mb-3">
                        <form:label path="updateDate" class="form-label">Update Date:</form:label>
                        <form:input path="updateDate" class="form-control" type="date" />
                    </div>
                    <div class="mb-3">
                        <form:label path="goldName" class="form-label">Gold Name:</form:label> <%-- Thêm trường goldName
                            --%>
                            <form:input path="goldName" class="form-control" /> <%-- Thêm trường goldName --%>
                    </div>
                    <div class="mb-3">
                        <form:label path="buyingPrice" class="form-label">Buying Price:</form:label> <%-- Thêm trường
                            buyingPrice --%>
                            <form:input path="buyingPrice" class="form-control" type="number" step="0.01" /> <%-- Thêm
                                trường buyingPrice --%>
                    </div>
                    <div class="mb-3">
                        <form:label path="sellingPrice" class="form-label">Selling Price:</form:label> <%-- Đổi tên
                            goldPrice thành sellingPrice --%>
                            <form:input path="sellingPrice" class="form-control" type="number" step="0.01" /> <%-- Đổi
                                tên goldPrice thành sellingPrice --%>
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
                <a href="/admin/gold-prices" class="btn btn-secondary mt-3">Back to Gold Prices</a>
            </div>
        </body>

        </html>