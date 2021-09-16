<%-- 
    Document   : classerror
    Created on : Mar 6, 2021, 3:01:12 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.error}"/>
        <h1>${error}</h1>
        <a href="ClassServlet">back</a>
    </body>
</html>
