<%-- 
    Document   : ViewRegister
    Created on : Feb 18, 2021, 7:59:58 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="DTO.Register_Class"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" type="text/css" href="css/viewStyle.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <a href="UserServlet?action=register">Register</a>
        <c:set var="rList" value="${requestScope.registerClass}"/>
        <c:set var="cList" value="${requestScope.subjectList}"/>
        <c:if test="${fn:length(rList)!=0}">
        <h2>Your Registered Class</h2>
            <table width="600px" border="1px solid" align="left">
                <tr>
                    <th>Subject Name </th> <th>Semester </th>
                </tr>
                <c:forEach items="${rList}" var="r">
                <tr>
                    <td>${r.subject.name}</td>
                    <td>${r.semester}</td>
                </tr>
                </c:forEach>
            </table><br>
        </c:if>
        <c:if test="${fn:length(rList)==0}">
            <h3>You have no registered class</h3>
        </c:if>
            <br><c:forEach items="${cList}"  var="cL">
         <c:if test="${cL.ava == true}"> 
             <div style="clear:both;"></div>
             <div class="register_guess" style="float:right;">
                <table border = 1 bordercolor = red align="center" >
                <tr>    
                    <td style="display: inline;padding: 5px 0;border: none;">Class ${cL.name} Now available</td>
                     <td style="display: inline;padding: 5px 0;border: none;" ><a href="UserServlet?action=register&clName=${cL.name}&clid=${cL.id}" >
                         <div style="">
                        <img style="width: 100px;"
                        src="image/${cL.url}"  alt="dkVOVINAM" >   
                        </div>
                </a></td>
                </tr>
                
                </table>
            </div>
                
            </c:if>      
        </c:forEach>    
    </body>
</html>
