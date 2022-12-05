<%-- 
    Document   : courses
    Created on : 1-Dec-2020, 4:50:34 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Project Show All Courses</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Project Show All Courses</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to see all Course entries in the database</h3></center>
    <br>

    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Credits</th>
                    <th>Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="course" items="${courseList}">
                <tr>
                    <td><c:out value="${course.courseID}"/></td>
                    <td><c:out value="${course.courseCode}"/></td>
                    <td><c:out value="${course.courseName}"/></td>
                    <td><c:out value="${course.credits}"/></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoCourseDetailsPage/${course.courseID}'/>" class="btn btn-info">Course Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateCourseForm/${course.courseID}'/>" class="btn btn-warning">Edit Course</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteCourseFromCoursesPageToCoursesPage/${course.courseID}'/>" class="btn btn-danger">Delete Course</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio </p>
        </div>
    </footer>
</body>
</html>
