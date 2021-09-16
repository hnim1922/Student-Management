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
        <title>Subject List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>List of Subject</h2>
        <a href="SubjectServlet?action=addsubjectform"> Add new Subject </a> <br> <br> 
             <a href="SubjectServlet?action=setAvailableSubjectForm">Set Available Subject</a>
         <table width="600px" border="1px solid">
            <tr>
                <th>Subject ID </th> <th>Subject Name </th><th>Available</th>
                <th> </th> <th> </th>
            </tr>
            <c:set var="suList" value="${requestScope.data}"/>
            <c:forEach items="${suList}" var="su">
            <tr>
                <td>${su.id}</td>
                <td>${su.name}</td>
                <td>${su.ava}</td> 
                <td><a href="SubjectServlet?action=updatesubjectform&suid=${su.id}" > Edit </td>
                <td><a href="SubjectServlet?action=deletesubjectconfirm&suid=${su.id}" > Delete </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
