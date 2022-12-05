<%-- 
    Document   : students
    Created on : 1-Dec-2020, 6:05:33 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Project Show All Students</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Project Show All Students</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to see all Student entries in the database</h3></center>
    <br>

    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Phone Number</th>
                    <th>Major</th>
                    <th>Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="student" items="${studentList}">
                <tr>
                    <td><c:out value="${student.studentID}"/></td>
                    <td><c:out value="${student.firstName}"/></td>
                    <td><c:out value="${student.lastName}"/></td>
                    <td><c:out value="${student.address}"/></td>
                    <td><c:out value="${student.city}"/></td>
                    <td><c:out value="${student.country}"/></td>
                    <td><c:out value="${student.phoneNumber}"/></td>
                    <td><c:out value="${student.major}"/></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoStudentDetailsPage/${student.studentID}'/>" class="btn btn-info">Student Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateStudentForm/${student.studentID}'/>" class="btn btn-warning">Edit Student</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteStudentFromStudentsPageToStudentsPage/${student.studentID}'/>" class="btn btn-danger">Delete Student</a></td>
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
