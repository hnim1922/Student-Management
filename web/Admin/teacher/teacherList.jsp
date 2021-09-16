<%-- 
    Document   : classList
    Created on : Feb 13, 2021, 11:05:12 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>List of Teachers</h2>
        <a href="TeacherServlet?action=addteacherform"> Add new Teacher </a> <br> <br> 
         <table width="600px" border="1px solid">
            <tr>
                <th>Teacher ID </th> <th>Teacher Last Name </th> <th>Teacher First Name </th>
                <th> </th> <th> </th>
            </tr>
            <c:set var="tList" value="${requestScope.data}"/>
            <c:forEach items="${tList}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.ln}</td>
                <td>${t.fn}</td>
                <td><a href="TeacherServlet?action=updateteacherform&tid=${t.id}" > Edit </td>
                <td><a href="TeacherServlet?action=deleteteacherconfirm&tid=${t.id}" > Delete </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
