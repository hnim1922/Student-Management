<%-- 
    Document   : classForm
    Created on : Feb 13, 2021, 11:07:35 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Register_Class"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Subject</title>
        <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <c:set var="r" value="${requestScope.rObject}"/>
        <c:set var="suList" value="${requestScope.suList}"/>
        <c:set var="headerMsg" value="${requestScope.msg}"/>
        <c:set var="action" value="${requestScope.action}"/>
        <c:set var="error" value="${requestScope.error}"/>
        
        <h2> ${headerMsg} </h2>

        
            <form action="UserServlet" method="POST" name="f1">
                <table width="600px" border="0px solid">
                <input type="hidden" name ="action" value="${action}">
                <tr> 
                    <td>Subject ID </td> 
                    <c:choose>
                        <c:when test="${requestScope.subjectIDFromImg == null}">
                        <td>: <select name="subject" value="" >
                            <c:forEach items="${suList}" var="s">
                            <option value="${s.id}">${s.name}</option>
                            </c:forEach>
                            </select>
                        </td> 
                        </c:when>
                    <c:otherwise>
                        <td>: <select name="subject" value="" >                     
                                <option value="${requestScope.subjectIDFromImg}">${requestScope.subjectNameFromImg}</option>                      
                            </select>
                        </td> 
                    </c:otherwise>
                    </c:choose>
                </tr>
                
                <tr> 
                    <td>Semester </td> 
                    <td>: <input type="text" name="semester" value="${r.semester}" /></td> 
                </tr>
                <tr> 
                    <td colspan="2" align="center"> 
                        <input type="submit" value="${action}" /> 
                        <input type="reset" value="Reset" />
                    </td> 
                </tr>
               </table>
            </form>
    
        <p> ${error} </p>   
        <br> <br>
        <a href="UserServlet?action=registerClass"> List of your Registered Class </a>
    </body>
</html>
