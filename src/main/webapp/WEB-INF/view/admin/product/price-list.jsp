<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Bảng Giá Hàng Hóa</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <style>
                    /* ... (CSS styles) */
                </style>
            </head>

            <body>
                <div class="container mt-5">
                    <h1>Bảng Giá Hàng Hóa</h1>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Tên Sản Phẩm</th>
                                <th>Loại Sản Phẩm</th>
                                <th>Giá Bán</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td>${product.productName}</td>
                                    <td>${product.productType}</td>
                                    <td>
                                        <fmt:formatNumber value="${product.sellingPrice}" pattern="#,###" /> VND
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>