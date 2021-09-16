<%-- 
    Document   : viewClass
    Created on : Feb 16, 2021, 8:24:20 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Subject"%>
<%@page import="DTO.Class"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class</title>
        <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>Your class</h2>
        <table width="600px" border="1px solid">
            <tr>
                <th>Classroom ID </th> <th>Classroom Number</th>  <th> Attendant </th> <th> </th>
            </tr>
            <c:set var="c" value="${requestScope['class']}"/>
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.attendants}</td>
                <td><a href="${pageContext.request.contextPath}/UserServlet?action=viewClassMate&cid=${c.id}" > View Classmate </td>
            </tr>
        </table>
        <h2>Your Subject</h2>
        <table width="600px" border="1px solid">
            <tr>
                <th> ID </th> <th> Name </th>
            </tr>
            <c:set var="suList" value="${requestScope.subject}"/>
            <c:forEach items="${suList}" var="su">
            <tr>
                <td>${su.id}</td>
                <td>${su.name}</td>
            </tr>
            </c:forEach>
    </body>
</html>
