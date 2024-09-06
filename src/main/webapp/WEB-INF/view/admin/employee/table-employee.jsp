<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Employees</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <link rel="stylesheet" href="/css/style-dashboard.css">
            <link rel="stylesheet" href="/css/mini.css">
        </head>

        <body>
            <jsp:include page="../menu.jsp" />

            <div class="container mt-5">
                <h1>Employees</h1>
                <a href="/admin/employees/create" class="btn btn-primary mb-3">Create New Employee</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Employee Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Position</th>
                            <th>Salary</th>
                            <th>Stall</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${employees}" var="employee">
                            <tr>
                                <td>${employee.employeeId}</td>
                                <td>${employee.employeeName}</td>
                                <td>${employee.phone}</td>
                                <td>${employee.address}</td>
                                <td>${employee.position}</td>
                                <td>${employee.salary}</td>
                                <td>${employee.stall.stallName}</td> <%-- Hiển thị stallName --%>
                                    <td>
                                        <a href="/admin/employees/edit/${employee.employeeId}"
                                            class="btn btn-warning btn-sm">Edit</a>
                                        <a href="/admin/employees/delete/${employee.employeeId}"
                                            class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
                                    </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>