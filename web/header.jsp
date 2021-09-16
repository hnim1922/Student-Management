<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/headerStyle.css" />
    </head>
<body>

<div class="header">
    <h1 class="head_text" style="text-align: center;">Home Page</h1>
<c:set var="Username" value="${sessionScope.name}"/>
<c:choose>
    <c:when test="${Username==null}">
        <a href="login.jsp" style=" "><p style="border: 1px solid black;background:yellow;padding:5px;text-align:center">Log in</p></a>
    </c:when>
    <c:when test="${Username=='Admin'}">
        <div class="selectbar">
        <h2 class="welcome_text">welcome back Admin</h2>
        <a href="UserServlet?action=logout">Log out</a> |
        <a href="StudentServlet">Student List</a> |
        <a href="ClassServlet">Class List</a> |
        <a href="SubjectServlet">Subject List</a> |
        <a href="ScoreServlet">Score List</a> |
        <a href="TeacherServlet"> Teacher List</a>
        <hr>
        </div>
    </c:when>
    <c:otherwise>
        <div class="selectbar1">
            <h2 class="welcome_text1" style="color: crimson;" >Hello ${Username}</h2>
        <a href="UserServlet?action=logout">Log out</a> |
        <a href="UserServlet?action=info">Information</a> |
        <a href="UserServlet?action=viewClass">View Class</a> |
        <a href="UserServlet?action=viewGrade">View Grade</a> |
        <a href="UserServlet?action=registerClass">Registered Class</a>
        <hr>
        </div>
    </c:otherwise>
</c:choose>
        <font color="red">${requestScope.ERRORNOTADMIN}</font>
        <font color="red">${requestScope.ERRORNOTLOGIN}</font>
</div>        
</body>
</html>