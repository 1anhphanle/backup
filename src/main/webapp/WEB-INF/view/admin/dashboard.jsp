<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Dashboard</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
            </head>

            <body>

                <jsp:include page="menu.jsp" /> <%-- Nhúng menu.jsp --%>

                    <div class="content">
                        <h1>Dashboard</h1>

                        <div class="row">
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <div class="card-header">
                                        Total Revenue
                                    </div>
                                    <div class="card-body d-flex flex-column align-items-center">
                                        <h2 class="card-title">
                                            <fmt:formatNumber value="${totalRevenue}" pattern="#,###" /> VND
                                        </h2>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <div class="card-header">
                                        Total Sales Invoices
                                    </div>
                                    <div class="card-body d-flex flex-column align-items-center">
                                        <h2 class="card-title">${totalSalesInvoices}</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <div class="card-header">
                                        Total Buyback Invoices
                                    </div>
                                    <div class="card-body d-flex flex-column align-items-center">
                                        <h2 class="card-title">${totalBuybackInvoices}</h2>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="card h-100">
                                    <div class="card-header">
                                        Revenue by Stall
                                    </div>
                                    <div class="card-body">
                                        <canvas id="revenueByStallChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <script>
                            const revenueByStallChart = document.getElementById('revenueByStallChart').getContext('2d');
                            const stallNames = [
                                <c:forEach items="${revenueByStall}" var="entry" varStatus="loop">
                                    "${entry.key.stallName}"${loop.last ? "" : ","}
                                </c:forEach>
                            ];
                            const revenueValues = [
                                <c:forEach items="${revenueByStall}" var="entry" varStatus="loop">
                                    ${entry.value}${loop.last ? "" : ","}
                                </c:forEach>
                            ];
                            new Chart(revenueByStallChart, {
                                type: 'bar',
                                data: {
                                    labels: stallNames,
                                    datasets: [{
                                        label: 'Revenue',
                                        data: revenueValues,
                                        backgroundColor: [
                                            'rgba(255, 99, 132, 0.2)',
                                            'rgba(54, 162, 235, 0.2)',
                                            'rgba(255, 206, 86, 0.2)',
                                            'rgba(75, 192, 192, 0.2)',
                                            'rgba(153, 102, 255, 0.2)',
                                            'rgba(255, 159, 64, 0.2)'
                                        ],
                                        borderColor: [
                                            'rgba(255, 99, 132, 1)',
                                            'rgba(54, 162, 235, 1)',
                                            'rgba(255, 206, 86, 1)',
                                            'rgba(75, 192, 192, 1)',
                                            'rgba(153, 102, 255, 1)',
                                            'rgba(255, 159, 64, 1)'
                                        ],
                                        borderWidth: 1
                                    }]
                                },
                                options: {
                                    scales: {
                                        y: {
                                            beginAtZero: true
                                        }
                                    }
                                }
                            });
                        </script>
                    </div>
            </body>

            </html>