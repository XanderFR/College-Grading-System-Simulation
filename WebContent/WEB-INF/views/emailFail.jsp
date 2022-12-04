<%-- 
    Document   : emailFail
    Created on : 9-Jan-2021, 4:33:48 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Email Failure</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    
    <style>
        .bttnundermargin {
            margin-bottom: 1em;
        }
    </style>
    
    <body class = "container">
        <header>
            <h1>DAO Final Project Email Failure</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>

        <br>
    <center>
        <h3 class = "">Oops, something went wrong. The Email wasn't sent.</h3>
        <h3 class = "">You must've entered incorrect email information. Go back and fix them.</h3>
        <br>
        <h5 class = "">To go back to the Email form, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/SendGradeByEmailController/gotoStudentTranscriptEmailPage/${selectedStudent.studentID}'/>">Go Back</a>

        <h5 class = "">If instead you want to go to the Index page, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/IndexController'/>">Go To Index Page</a>
    </center>

    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio 201909200</p>
        </div>
    </footer>
</body> 
</html>
