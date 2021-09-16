<%-- 
    Document   : login
    Created on : Feb 1, 2021, 10:31:30 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/loginStyle.css" />
        <title>Welcome to our page</title>
        <style type="text/css">
            body{
                padding: 0;
                margin :0;
                background: linear-gradient(120deg,#2980b9,#8e44ad);
                overflow: hidden;
                height:100vh;
            }
            .center{
                position: absolute;
                top:50%;
                left:50%;
                transform: translate(-60%,-60%);              
                background-size: cover;
                background-repeat: no-repeat;
                background-origin: border-box;
                 background-position: center;
            }
            .login_header{
                text-align: center;
            }
            .submit_field{
               text-align: center;
                margin-top: 10%; 
                display: inline-block;
            }
            
            .submit_field:last-child{
                margin-left: 4%;
            }
            .text_feild{
                margin-top: 10%;
            }
       
        </style>
    </head>
    <body >
        <h1 class="welcome_text" style=" background: aliceblue;
    font-size: 50px;text-align: center;background-clip: content-box;">
            
            
            
            
            
            
            
            Welcome to Weeaboo Academy</h1>
        <div class="center" >
       
            <h1 class="login_header" style="color: aqua;">Login</h1>
            <font color="red">${requestScope.ERROR}</font>
            <form class="form_login" action="LoginServlet" method="POST" name="f1">
                    <div class="text_feild">   
                      <label>Username:</label>
                      <input type="text" name="username" value="${param.username}"/>
                   </div>   
                    <div class="text_feild">
                      <label> Password:</label>
                      <input type="password" name="password" value="" /></td> 
                    </div>
                   <div class="submit_field">
                        <input type="submit" value="Login" /> 
                     </div>
                   <div class="submit_field">
                        <input type="reset" value="Reset" />
                   </div>
             
            </form>
      
        </div>
              
    </body>
</html>
