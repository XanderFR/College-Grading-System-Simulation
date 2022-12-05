<%-- 
    Document   : teacherCourses
    Created on : 23-Dec-2020, 4:11:55 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Project Show All Student-Courses</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Project Show All Student-Courses</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to see all Teacher-Course entries in the database</h3></center>
    <br>

    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Teacher-Course ID</th>
                    <th>Teacher ID</th>
                    <th>Teacher First Name</th>
                    <th>Teacher Last Name</th>
                    <th>Course ID</th>
                    <th>Course Course</th>
                    <th>Course Name</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="teacherCourse" items="${teacherCourseList}">
                <tr>
                    <td><c:out value="${teacherCourse.teacherCourseID}"/></td>
                    <td><c:out value="${teacherCourse.teacherID}"/></td>
                    <td><c:out value="${teacherCourse.teacherFirstName}"/></td>
                    <td><c:out value="${teacherCourse.teacherLastName}"/></td>
                    <td><c:out value="${teacherCourse.courseID}"/></td>
                    <td><c:out value="${teacherCourse.courseCode}"/></td>
                    <td><c:out value="${teacherCourse.courseName}"/></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteTeacherFromTeacherCoursesPageToTeacherCoursesPage/${teacherCourse.teacherCourseID}'/>" class="btn btn-danger">Delete Teacher-Course</a></td>
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
