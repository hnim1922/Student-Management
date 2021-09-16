<%-- 
    Document   : AvailableForm
    Created on : Mar 19, 2021, 10:55:32 AM
    Author     : anime
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AVAForm</title>
    </head>
    <body>
        <h1>${requestScope.msg}</h1>
        <c:set var="subList" value="${requestScope.data1}"/>   
        <form action="SubjectServlet" method="POST" name="f1" enctype="multipart/form-data">
            <label for="sub">Choose a subject:</label>
            <select name="sub" > 
             
           <c:forEach items="${subList}" var="cla">             
                <option value="${cla.id}-${cla.name}">${cla.name}</option>          
            </c:forEach> 
            </select>  
            <br>
            <p>Available</p>
            <input type="radio" id="True" name="ava" value="True">
            <label for="True">Yes</label><br>
            <input type="radio" id="False" name="ava" value="False">
            <label for="False">No</label><br>
                <label for ="imageFile">Choose a image:</label>
                <input type="file" name="Photo">
           <input type="hidden" name ="action" value="setAvailable"><br>
           <input type="submit" value="Ok">
          </form>  
    </body>
</html>
