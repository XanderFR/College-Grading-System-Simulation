<%-- 
    Document   : detailsTeacher
    Created on : 5-Dec-2020, 4:32:02 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Teacher Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Teacher Details</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">Look at the Info of the Teacher you Chose</h3></center>
    <br>
 
    <h1>Teacher Information</h1>
    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Teacher ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Phone Number</th>
                    <th>Salary</th>
                    <th>Edit</th>
                </tr>
            </thead>

            <tr>
                <td><c:out value="${selectedTeacher.teacherID}"/></td>
                <td><c:out value="${selectedTeacher.firstName}"/></td>
                <td><c:out value="${selectedTeacher.lastName}"/></td>
                <td><c:out value="${selectedTeacher.address}"/></td>
                <td><c:out value="${selectedTeacher.city}"/></td>
                <td><c:out value="${selectedTeacher.country}"/></td>
                <td><c:out value="${selectedTeacher.phoneNumber}"/></td>
                <td><c:out value="${selectedTeacher.salary}"/></td>
                <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateTeacherForm/${selectedTeacher.teacherID}'/>" class="btn btn-warning">Edit Teacher</a></td>
            </tr>
        </table>
    </center>
            
            <br>
            
    <h1>Teacher's Course Information</h1>
    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Teacher-Course ID</th>
                    <th>Teacher ID</th>
                    <th>Teacher First Name</th>
                    <th>Teacher Last Name</th>
                    <th>Course ID</th>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="teacherCourse" items="${selectTeacherCourseList}">
                <tr>
                    <td><c:out value="${teacherCourse.teacherCourseID}"/></td>
                    <td><c:out value="${teacherCourse.teacherID}"/></td>
                    <td><c:out value="${teacherCourse.teacherFirstName}"/></td>
                    <td><c:out value="${teacherCourse.teacherLastName}"/></td>
                    <td><c:out value="${teacherCourse.courseID}"/></td>
                    <td><c:out value="${teacherCourse.courseCode}"/></td>
                    <td><c:out value="${teacherCourse.courseName}"/></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoCourseDetailsPage/${teacherCourse.courseID}'/>" class="btn btn-info">Course Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateCourseForm/${teacherCourse.courseID}'/>" class="btn btn-warning">Edit Course</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteCourseFromTeacherDetailsPageToTeacherDetailsPage/${selectedTeacher.teacherID}/${teacherCourse.teacherCourseID}'/>" class="btn btn-danger">Drop Course</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    
    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio 201909200</p>
        </div>
    </footer>
</body>
</html>
