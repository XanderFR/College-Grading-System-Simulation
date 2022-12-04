<%-- 
    Document   : addStudentToCourse
    Created on : 9-Dec-2020, 1:21:47 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Add Student to Course</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Add Student to Course</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to Add a Student to a Course</h3></center>
    <br>
    
    <c:url var="addAction" value='/CreateController/addStudentToCourse' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time for you to Create a Student-Course Combo</h5>
        
        <!--Student Selection-->
        <div class="form-group">
            <label for="studentChoice">Pick a Student:</label>
            <select multiple class ="form-control" name = "studentChoice">
                <c:forEach var="student" items="${studentList}">
                    <option value = "${student.studentID}">
                        <c:out value="${student.toString()}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <!--Course Selection-->
        <div class="form-group">
            <label for="courseChoice">Pick a Course:</label>
            <select multiple class ="form-control" name = "courseChoice">
                <c:forEach var="course" items="${courseList}">
                    <option value = "${course.courseID}">
                        <c:out value="${course.toString()}"/>
                    </option>
                </c:forEach>
            </select>
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
