<%-- 
    Document   : login
    Created on : 3-Sep-2020, 7:30:04 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Login Page</title>
    </head>
    <body class="container">
        <form action="LoginController" method="POST">
            <div class="form-group">
                <label for="username">UserName:</label>
                <input type="text" class="form-control" id="username" required name = "username">
            </div>
            <!--LAST NAME-->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" required name = "password">
            </div>
            <center>
                <input type="submit" value="Login">
            </center>
        </form>
        <div>
            <center>
                <p>${message}</p>
            </center>
        </div>
    </body>
</html>
