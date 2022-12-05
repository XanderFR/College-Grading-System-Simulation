<%-- 
    Document   : updateFormForTeacher
    Created on : 11-Dec-2020, 6:21:22 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Modify Teacher</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Modify Teacher</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to modify a Teacher entry in the database</h3></center>
    <br>
    <c:url var="addAction" value='/UpdateGradeFromMyOracleDBController/updateTeacher' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time for you to Modify a Teacher</h5>
        
        <div class="form-group">
            <label for="teacherID">Teacher ID:</label>
            <input type="number" class="form-control" id="teacherIDid" name = "teacherID" readonly value = "${selectedTeacher.teacherID}">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--FIRST NAME-->
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstNameid" required value = "${selectedTeacher.firstName}" name = "firstName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--LAST NAME-->
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastNameid" required value = "${selectedTeacher.lastName}" name = "lastName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--ADDRESS-->
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="addressid" required value = "${selectedTeacher.address}" name = "address">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--CITY-->
        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="cityid" required value = "${selectedTeacher.city}" name = "city">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COUNTRY-->
        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="countryid" required value = "${selectedTeacher.country}" name = "country">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--PHONE NUMBER-->
        <div class="form-group">
            <label for="phoneNumber">Phone Number (Max 10 digits):</label>
            <input type="number" class="form-control" id="phoneNumberid" required value = "${selectedTeacher.phoneNumber}" name = "phoneNumber" min = "1000000000" max = "9999999999" maxlength = "10" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--SALARY-->
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" class="form-control" id="salaryid" required value = "${selectedTeacher.salary}" name = "salary" min = "1" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <!--THE BUTTONS-->
        <center>
            <button id = "submitButton" type="submit" class="btn btn-primary">Update</button>
            &nbsp; &nbsp;
            <button type="reset" class="btn btn-primary">Reset</button>
        </center>
    </form:form>
        
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
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteCourseFromTeacherUpdateFormToTeacherUpdateForm/${selectedTeacher.teacherID}/${teacherCourse.teacherCourseID}'/>" class="btn btn-danger">Drop Course</a></td>
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
