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
        <c:set var="id" value="${requestScope.su}"/>
        <h1>DELETE SUBJECT ID ${id}?</h1>
        <a href="SubjectServlet?action=deletesubject&suid=${id}">Delete</a>
        <a href="SubjectServlet">No</a>
    </body>
</html>
