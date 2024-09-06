<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Bảng Giá Vàng</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                        background-color: #f4f4f4;
                    }

                    .container {
                        margin-top: 50px;
                    }

                    h1 {
                        text-align: center;
                        margin-bottom: 30px;
                        color: #333;
                    }

                    .table {
                        background-color: #fff;
                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                        border-radius: 8px;
                    }

                    .table th {
                        background-color: #007bff;
                        color: #fff;
                        font-weight: bold;
                        text-align: center;
                    }

                    .table td {
                        text-align: center;
                        vertical-align: middle;
                    }

                    .table-striped tbody tr:nth-of-type(odd) {
                        background-color: rgba(0, 0, 0, 0.05);
                    }

                    .update-date {
                        font-size: 14px;
                        color: #666;
                        margin-bottom: 10px;
                    }
                </style>
            </head>

            <body>
                <div class="container">
                    <h1>Bảng Giá Vàng</h1>

                    <%-- Hiển thị giá mua và giá bán mới nhất --%>
                        <p class="update-date">Giá mua mới nhất:
                            <fmt:formatNumber value="${latestBuyingPrice}" pattern="#,###" /> VND
                        </p>
                        <p class="update-date">Giá bán mới nhất:
                            <fmt:formatNumber value="${latestSellingPrice}" pattern="#,###" /> VND
                        </p>

                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Tên Vàng</th>
                                    <th>Giá Mua</th>
                                    <th>Giá Bán</th>
                                    <th>Ngày Cập Nhật</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${goldPrices}" var="goldPrice">
                                    <tr>
                                        <td>${goldPrice.goldName}</td>
                                        <td>
                                            <fmt:formatNumber value="${goldPrice.buyingPrice}" pattern="#,###" /> VND
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${goldPrice.sellingPrice}" pattern="#,###" /> VND
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${goldPrice.updateDate}" pattern="dd/MM/yyyy" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>
            </body>

            </html>