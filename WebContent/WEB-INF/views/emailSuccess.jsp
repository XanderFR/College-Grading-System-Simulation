<%-- 
    Document   : emailSuccess
    Created on : 9-Jan-2021, 3:46:46 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Email Success</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    
    <style>
        .bttnundermargin {
            margin-bottom: 1em;
        }
    </style>
    
    <body class = "container">
        <header>
            <h1>DAO Final Project Email Success</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>

        <br>
    <center>
        <h3 class = "">The Student Transcript Email was successfully sent to:</h3>
        <h3 class = "">${emailRecipient}</h3>
        <br>
        <h3 class = "">What do you want to do now?</h3>
        <h5 class = "">To go back to the Email form, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/SendGradeByEmailController/gotoStudentTranscriptEmailPage/${selectedStudent.studentID}'/>">Go Back</a>

        <h5 class = "">To go to the Index page, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/IndexController'/>">Go To Index Page</a>
    </center>

    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio </p>
        </div>
    </footer>
</body> 
</html>
