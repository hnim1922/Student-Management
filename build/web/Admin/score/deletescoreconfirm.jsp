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
        <c:set var="sid" value="${requestScope.s}"/>
        <c:set var="suid" value="${requestScope.su}"/>
        <h1>DELETE SCORE WITH STUDENT ID ${sid} AND SUBJECT ID ${suid}?</h1>
        <a href="ScoreServlet?action=deletescore&sid=${sid}&suid=${suid}">Delete</a>
        <a href="ScoreServlet">No</a>
    </body>
</html>
