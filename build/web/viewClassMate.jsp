<%-- 
    Document   : viewClassMate
    Created on : Feb 16, 2021, 8:59:47 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List</title>
        <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:set var="cName" value="${requestScope.ClassName}"/>
        <h2>List of Students in ${cName}</h2>
        <table width="600px" border="1px solid">
            <tr>
                <th>Student ID </th> <th>Last Name </th>  <th>First Name </th><th>Major </th> <th>Semester </th>
                <th>Class </th> <th>Email</th>
            </tr>
            <c:set var="cmList" value="${requestScope.classMate}"/>
            <c:forEach items="${cmList}" var="cm">
            <tr>
                <td>${cm.id}</td>
                <td>${cm.lastName}</td>
                <td>${cm.firstName}</td>
                <td>${cm.majorId}</td>
                <td>${cm.semester}</td>
                <td>${cm.classes.name}</td>
                <td>${cm.email}</td>
            </tr>
            </c:forEach>
        </table>
        <a href="UserServlet?action=viewClass">Back</a>
    </body>
</html>
