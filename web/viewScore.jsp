<%-- 
    Document   : viewScore
    Created on : Feb 18, 2021, 3:25:32 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="DTO.Score"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Score Page</title>
        <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>Your Score</h2>
        <c:set var="scList" value="${requestScope.score}"/>
        <c:if test="${fn:length(scList)!=0}">
            <table width="600px" border="1px solid">
                <tr>
                    <th>Subject ID </th> <th>Score </th>  <th>Semester </th> <th>Status </th>
                </tr>
                <c:forEach items="${scList}" var="sc">
                <tr>
                    <td>${sc.subject.id}</td>
                    <td>${sc.score}</td>
                    <td>${sc.semester}</td>
                    <td>${sc.status}</td>
                </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${fn:length(scList)==0}">
            <h3>You have no score</h3>
        </c:if>
    </body>
</html>
