<%-- 
    Document   : viewClassSubject
    Created on : Feb 28, 2021, 9:49:32 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Class_Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:set var="c" value="${requestScope.cObject}"/>
        <h2>List of Class ${c.name} Subject</h2>
        <a href="ClassServlet?action=addsubjectform&cid=${c.id}" > Add Subject To This Class </a> <br> <br> 
        <c:set var="csList" value="${requestScope.csList}"/>
        <c:if test="${fn:length(csList)>0}">
        <table width="600px" border="1px solid">
            <tr>
                <th>Subject Name </th>  <th>Semester </th> <th>Teacher Name</th>
                <th> </th>
            </tr>
            <c:forEach items="${csList}" var="cs">
            <tr>
                <td>${cs.subject.name}</td>
                <td>${cs.semester}</td>
                <td>${cs.teacher.fn}</td>
                <td><a href="ClassServlet?action=deletesubjectform&cid=${c.id}&suid=${cs.subject.id}" > Delete </td>
            </tr>
            </c:forEach>
        </table>
        </c:if>
        <c:if test="${fn:length(csList)==0}">
        <p>this class have no subject</p>
        </c:if>
        <a href="ClassServlet">Class list</a>
    </body>
</html>
