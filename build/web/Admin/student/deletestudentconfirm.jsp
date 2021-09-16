<%-- 
    Document   : deleteconfirm
    Created on : Feb 8, 2021, 7:31:46 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Student?</title>
    </head>
    <body>
        <c:set var="sid" value="${requestScope.s}"/>
        <h1>DELETE STUDENT ID ${sid}?</h1>
        <a href="StudentServlet?action=deletestudent&sid=${sid}">Delete</a>
        <a href="StudentServlet">No</a>
    </body>
</html>
