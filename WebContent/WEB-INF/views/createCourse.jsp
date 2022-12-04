<%-- 
    Document   : createCourse
    Created on : 30-Nov-2020, 6:37:54 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Create Course</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Create Course</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to make a new Course entry in the database</h3></center>
    <br>
    
    <c:url var="addAction" value='/CreateController/createCourse' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time to make a Course profile</h5>
        
        <!--COURSE CODE-->
        <div class="form-group">
            <label for="courseCode">Course Code (Max 7 Characters):</label>
            <input type="text" class="form-control" id="courseCodeid" required placeholder="COMP101" name = "courseCode" maxlength = "7">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COURSE NAME-->
        <div class="form-group">
            <label for="courseName">Course Name:</label>
            <input type="text" class="form-control" id="courseNameid" required placeholder="Intro to Computer Science" name = "courseName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COURSE DESCRIPTION-->
        <div class="form-group">
            <label for="description">Course Description:</label>
            <textarea class="form-control" id="descriptionid" required placeholder="Enter Course Description Here" name = "description" rows = "3"></textarea>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--CREDITS-->
        <div class="form-group">
            <label for="credits">Credits (1 to 8):</label>
            <input type="number" class="form-control" id="creditsid" required placeholder="3" name = "credits" min = "1" max = "8" maxlength = "1" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <!--THE BUTTONS-->
        <center>
            <button id = "submitButton" type="submit" class="btn btn-primary">Submit</button>
            &nbsp; &nbsp;
            <button type="reset" class="btn btn-primary">Reset</button>
        </center>
    </form:form>
        
    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio 201909200</p>
        </div>
    </footer>
</body>
</html>
