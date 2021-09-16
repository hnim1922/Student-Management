<%-- 
    Document   : info
    Created on : Feb 18, 2021, 9:00:22 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information</title>
    <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:set var="s" value="${requestScope.Student}"/>
        <p>ID: ${s.id}</p>
        <p>First name: ${s.lastName}</p>
        <p>Last name: ${s.firstName}</p>
        <p>Major Id: ${s.majorId}</p>
        <p>Semester: ${s.semester}</p>
        <p>Class name: ${s.classes.name}</p>
        <p>Email: ${s.email}</p>
    </body>
</html>
