<%-- 
    Document   : error
    Created on : Mar 17, 2021, 12:41:54 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <c:if test="${requestScope.ERROR!=null}">
        <h1 color="red">${requestScope.ERROR}</h1>
        </c:if>
        <c:if test="${requestScope.ERROR==null}">
            <h1 color="red">You not supposed to do that</h1>
        </c:if>
        <a href="header.jsp">Return</a>
    </body>
</html>
