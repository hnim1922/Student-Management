<%-- 
    Document   : studentList
    Created on : Feb 11, 2021, 3:08:33 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List</title>
        <style type="text/css">
            body{
                   background: url("data:image/jpeg;base64,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");
                   background-repeat:no-repeat; 
                   background-size: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <a href="StudentServlet?action=addstudentform"> Add new Student </a> <br> <br> 
        <h2>Search Student ID</h2>
        <form action="StudentServlet" method="POST" name="f1">
            <input type="hidden" name ="action" value="Search_Student">
            <input type="text" name="search" value="" /> 
            <input type="submit" value="Search_Student" />
        </form>
        <br>
         <font color="red">${requestScope.IDNOTFOUND}</font>
         <table width="600px" border="1px solid">
             <td colspan="9" style="text-align: center;"><h2>List of Student</h2></td>
            <tr>
                <th>Student ID </th> <th>Last Name </th>  <th>First Name </th><th>Major </th> <th>Semester </th>
                <th>Class </th> <th>Email</th>
                <th> </th> <th> </th>
            </tr>
            <c:set var="sList" value="${requestScope.data}"/>
            <c:forEach items="${sList}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.lastName}</td>
                <td>${s.firstName}</td>
                <td>${s.majorId}</td>
                <td>${s.semester}</td>
                <td>${s.classes.name}</td>
                <td>${s.email}</td>
                <td><a href="StudentServlet?action=updatestudentform&sid=${s.id}" > Edit </td>
                <td><a href="StudentServlet?action=deletestudentconfirm&sid=${s.id}" > Delete </td>
            </tr>
            </c:forEach>
           
        </table>
            
    </body>
</html>
