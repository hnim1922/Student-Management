<%-- 
    Document   : deleteclassconfirm
    Created on : Feb 13, 2021, 11:36:20 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE?</title>
    </head>
    <body>
        <% String id= (String) request.getAttribute("c"); %>
        <c:set var="id" value="${requestScope.c}"/>
        <h1>DELETE CLASS ID ${id}?</h1>
        <a href="ClassServlet?action=deleteclass&cid=${id}">Delete</a>
        <a href="ClassServlet">No</a>
    </body>
</html>
