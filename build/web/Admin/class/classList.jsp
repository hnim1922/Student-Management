<%-- 
    Document   : classList
    Created on : Feb 13, 2021, 11:05:12 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Class"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>List of Classes</h2>
        <a href="ClassServlet?action=addclassform"> Add new Class </a> <br> <br> 
        <table width="600px" border="1px solid">
            <tr>
                <th>Class ID </th> <th>Class Name </th>  <th>Class attendant </th>
                <th> </th> <th> </th> <th> </th>
            </tr>
            <c:set var="clList" value="${requestScope.data}"/>
            <c:forEach items="${clList}" var="cl">
            <tr>
                <td>${cl.id}</td>
                <td>${cl.name}</td>
                <td>${cl.attendants}</td>
                <td><a href="ClassServlet?action=classsubject&cid=${cl.id}" > View Subject </td>
                <td><a href="ClassServlet?action=updateclassform&cid=${cl.id}" > Edit </td>
                <td><a href="ClassServlet?action=deleteclassconfirm&cid=${cl.id}" > Delete </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
