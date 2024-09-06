<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create Employee</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <link rel="stylesheet" href="/css/style-dashboard.css">
                <link rel="stylesheet" href="/css/mini.css">
            </head>

            <body>
                <jsp:include page="../menu.jsp" />

                <div class="container mt-5">
                    <h1>Create Employee</h1>
                    <form:form method="POST" action="/admin/employees/create" modelAttribute="employee">
                        <div class="mb-3">
                            <form:label path="employeeName" class="form-label">Employee Name:</form:label>
                            <form:input path="employeeName" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="phone" class="form-label">Phone:</form:label>
                            <form:input path="phone" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="address" class="form-label">Address:</form:label>
                            <form:input path="address" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="position" class="form-label">Position:</form:label>
                            <form:input path="position" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="salary" class="form-label">Salary:</form:label>
                            <form:input path="salary" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="stall" class="form-label">Stall:</form:label>
                            <form:select path="stall" class="form-control">
                                <form:option value="">-- Select Stall --</form:option> <%-- Thêm option mặc định --%>
                                    <c:forEach items="${stalls}" var="stall">
                                        <form:option value="${stall.stallId}">${stall.stallName}</form:option> <%-- Hiển
                                            thị stallName --%>
                                    </c:forEach>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                    <a href="/admin/employees" class="btn btn-secondary mt-3">Back to Employees</a>
                </div>
            </body>

            </html>