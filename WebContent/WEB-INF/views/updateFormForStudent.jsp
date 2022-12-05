<%-- 
    Document   : updateFormForStudent
    Created on : 11-Dec-2020, 4:02:22 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Modify Student</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Modify Student</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to modify a Student entry in the database</h3></center>
    <br>
    <c:url var="addAction" value='/UpdateGradeFromMyOracleDBController/updateStudent' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time for you to Modify a Student</h5>
        
        <div class="form-group">
            <label for="studentID">Student ID:</label>
            <input type="number" class="form-control" id="studentIDid" name = "studentID" readonly value = "${selectedStudent.studentID}">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--FIRST NAME-->
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstNameid" required value = "${selectedStudent.firstName}" name = "firstName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--LAST NAME-->
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastNameid" required value = "${selectedStudent.lastName}" name = "lastName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--ADDRESS-->
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="addressid" required value = "${selectedStudent.address}" name = "address">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--CITY-->
        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="cityid" required value = "${selectedStudent.city}" name = "city">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COUNTRY-->
        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="countryid" required value = "${selectedStudent.country}" name = "country">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--PHONE NUMBER-->
        <div class="form-group">
            <label for="phoneNumber">Phone Number (Max 10 Digits):</label>
            <input type="number" class="form-control" id="phoneNumberid" required value = "${selectedStudent.phoneNumber}" name = "phoneNumber" min = "1000000000" max = "9999999999" maxlength = "10" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--MAJOR-->
        <div class="form-group">
            <label for="major">Major:</label>
            <input type="text" class="form-control" id="majorid" required value = "${selectedStudent.major}" name = "major">
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
        
    <h1>Student's Course Information</h1>
    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Student-Course ID</th>
                    <th>Student ID</th>
                    <th>Student First Name</th>
                    <th>Student Last Name</th>
                    <th>Course ID</th>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Details</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="studentCourse" items="${selectStudentCourseList}">
                <tr>
                    <td><c:out value="${studentCourse.studentCourseID}"/></td>
                    <td><c:out value="${studentCourse.studentID}"/></td>
                    <td><c:out value="${studentCourse.studentFirstName}"/></td>
                    <td><c:out value="${studentCourse.studentLastName}"/></td>
                    <td><c:out value="${studentCourse.courseID}"/></td>
                    <td><c:out value="${studentCourse.courseCode}"/></td>
                    <td><c:out value="${studentCourse.courseName}"/></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoCourseDetailsPage/${studentCourse.courseID}'/>" class="btn btn-info">Course Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateCourseForm/${studentCourse.courseID}'/>" class="btn btn-warning">Edit Course</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteCourseFromUpdateStudentFormToUpdateStudentForm/${selectedStudent.studentID}/${studentCourse.studentCourseID}'/>" class="btn btn-danger">Drop Course</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    <br>
    <h1>Student's Result Information</h1>
    <center>
        <table border ="5">
            <thead>
                <tr>
                    <th>Result ID</th>
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Session No.</th>
                    <th>Mid-Term</th>
                    <th>Final</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <c:forEach var="result" items="${selectedResultList}">
                <tr>
                    <td><c:out value="${result.resultID}"/></td>
                    <td><c:out value="${result.studentID}"/></td>
                    <td><c:out value="${result.studentFirstName}"/></td>
                    <td><c:out value="${result.studentLastName}"/></td>
                    <td><c:out value="${result.courseID}"/></td>
                    <td><c:out value="${result.courseName}"/></td>
                    <td><c:out value="${result.sessionNumber}"/></td>
                    <td><c:out value="${result.midTermMark}"/></td>
                    <td><c:out value="${result.finalMark}"/></td>
                    <td><a href = "<c:url value='/UpdateGradeFromMyOracleDBController/updateFromStudentUpdateFormPageToUpdateResultForm/${result.resultID}'/>" class="btn btn-warning">Edit Result</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteResultFromUpdateStudentFormToUpdateStudentForm/${selectedStudent.studentID}/${result.resultID}'/>" class="btn btn-danger">Delete Result</a></td>
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
