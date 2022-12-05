<%-- 
    Document   : teachers
    Created on : 1-Dec-2020, 6:10:24 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Project Show All Teachers</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Project Show All Teachers</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to see all Teacher entries in the database</h3></center>
    <br>
 
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
                    <th>Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="teacher" items="${teacherList}">
                <tr>
                    <td><c:out value="${teacher.teacherID}"/></td>
                    <td><c:out value="${teacher.firstName}"/></td>
                    <td><c:out value="${teacher.lastName}"/></td>
                    <td><c:out value="${teacher.address}"/></td>
                    <td><c:out value="${teacher.city}"/></td>
                    <td><c:out value="${teacher.country}"/></td>
                    <td><c:out value="${teacher.phoneNumber}"/></td>
                    <td><c:out value="${teacher.salary}"/></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoTeacherDetailsPage/${teacher.teacherID}'/>" class="btn btn-info">Teacher Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateTeacherForm/${teacher.teacherID}'/>" class="btn btn-warning">Edit Teacher</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteTeacherFromTeachersPageToTeachersPage/${teacher.teacherID}'/>" class="btn btn-danger">Delete Teacher</a></td>
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
