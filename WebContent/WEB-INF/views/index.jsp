<%-- 
    Document   : index
    Created on : 3-Sep-2020, 7:12:57 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Main Menu</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    
    <style>
        .bttnundermargin {
            margin-bottom: 1em;
        }
    </style>
    
    <body class = "container">
        <header>
            <h1>DAO Final Project Main Menu</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>

        <br>
    <center>
        <h3 class = "">Welcome to the College Administration System</h3>
        <h3 class = "">What do you want to do?</h3>
        <h5 class = "">To Create a Course, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/DisplayGradesController/gotoCreateCoursePage'/>">Create Course</a>

        <h5 class = "">To Create a Student, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/DisplayGradesController/gotoCreateStudentPage'/>">Create Student</a>

        <h5 class = "">To Create a Teacher, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/DisplayGradesController/gotoCreateTeacherPage'/>">Create Teacher</a>
    </center>

    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio </p>
        </div>
    </footer>
</body> 
</html>
