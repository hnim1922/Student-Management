<%-- 
    Document   : classList
    Created on : Feb 13, 2021, 11:05:12 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Score"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script type="text/javascript">
//                var variable = '${cla.name}';
//                var variableFromServer = 'variableFromServer';
//                var variable1 = document.getElementById("selectClassName").value
                function listbyClass(obj) {
                     var value = obj.value;
                 
           
                 } 
            </script>
        <title>Score List</title>
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
        <h2>List of Score</h2>
         <c:set var="clList" value="${requestScope.ClassData}"/>
        <a href="ScoreServlet?action=addscoreform"> Add new Score </a> <br> <br>
        
        <form action="ScoreServlet" method="POST" name="f1">
            <label for="className">Choose a class:</label>
            <select  onchange="this.form.submit()" name="classToList" >
                <option disabled selected value> -- select an option -- </option>
            <c:forEach items="${clList}" var="cla">             
                <option value="${cla.name}" <c:if test="${cla.name == requestScope.chekedClass}">selected</c:if>>${cla.name}</option>          
            </c:forEach>    
               </select>  
           <input type="hidden" name ="action" value="ListScoreByClass">
          </form>  
         <table id="printTable" width="600px" border="1px solid">         
            <tr>
                <th>Student Id </th> <th>Student Name </th><th>Class Name</th> <th>Subject Name </th> <th>Score </th> <th>Semester </th> <th>Status </th>
                <th> </th> <th> </th>
            </tr>
           <c:set var="scList" value="${requestScope.ScoreData}"/>
            <c:forEach items="${scList}"  var="sc">
            <tr>
                <td>${sc.student.id}</td>
                <td>${sc.student.firstName}</td>
                <td>${sc.student.classes.name}</td>
                <td>${sc.subject.name}</td>
                <td>${sc.score}</td>
                <td>${sc.semester}</td>
                <td>${sc.status}</td>
                <td><a href="ScoreServlet?action=updatescoreform&sid=${sc.student.id}&suid=${sc.subject.id}" > Edit </td>
                <td><a href="ScoreServlet?action=deletescoreconfirm&sid=${sc.student.id}&suid=${sc.subject.id}" > Delete </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
