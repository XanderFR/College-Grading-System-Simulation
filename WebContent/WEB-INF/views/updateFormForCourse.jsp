<%-- 
    Document   : updateFormForCourse
    Created on : 11-Dec-2020, 6:47:39 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Modify Course</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Modify Course</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to modify a Course entry in the database</h3></center>
    <br>
    <c:url var="addAction" value='/UpdateGradeFromMyOracleDBController/updateCourse' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time for you to Modify a Course</h5>
        
        <div class="form-group">
            <label for="courseID">Course ID:</label>
            <input type="number" class="form-control" id="courseIDid" name = "courseID" readonly value = "${selectedCourse.courseID}">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COURSE CODE-->
        <div class="form-group">
            <label for="courseCode">Course Code (Max 7 Characters):</label>
            <input type="text" class="form-control" id="courseCodeid" required value = "${selectedCourse.courseCode}" name = "courseCode" maxlength = "7">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COURSE NAME-->
        <div class="form-group">
            <label for="courseName">Course Name:</label>
            <input type="text" class="form-control" id="courseNameid" required value = "${selectedCourse.courseName}" name = "courseName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COURSE DESCRIPTION-->
        <div class="form-group">
            <label for="description">Course Description:</label>
            <textarea class="form-control" id="descriptionid" required name = "description" rows = "3"><c:out value="${selectedCourse.description}"/></textarea>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--CREDITS-->
        <div class="form-group">
            <label for="credits">Credits (1 to 8):</label>
            <input type="number" class="form-control" id="creditsid" required value = "${selectedCourse.credits}" name = "credits" min = "1" max = "8" maxlength = "1" step = "1">
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
        
    <h1>Teachers teaching the Course</h1>
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
                    <td><a href = "<c:url value='/DisplayGradesController/gotoTeacherDetailsPage/${teacherCourse.teacherID}'/>" class="btn btn-info">Teacher Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateTeacherForm/${teacherCourse.teacherID}'/>" class="btn btn-warning">Edit Teacher</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteTeacherFromUpdateCourseFormToUpdateCourseForm/${selectedCourse.courseID}/${teacherCourse.teacherCourseID}'/>" class="btn btn-danger">Drop Teacher</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    <br>
    <h1>Students enrolled in the Course</h1>
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
                    <td><a href = "<c:url value='/DisplayGradesController/gotoStudentDetailsPage/${studentCourse.studentID}'/>" class="btn btn-info">Student Details</a></td>
                    <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateStudentForm/${studentCourse.studentID}'/>" class="btn btn-warning">Edit Student</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteStudentFromUpdateCourseFormToUpdateCourseForm/${selectedCourse.courseID}/${studentCourse.studentCourseID}'/>" class="btn btn-danger">Drop Student</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    <br>
    <h1>Course's Result Information</h1>
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
                    <td><a href = "<c:url value='/UpdateGradeFromMyOracleDBController/updateFromUpdateCourseFormPageToUpdateResultForm/${result.resultID}'/>" class="btn btn-warning">Edit Result</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteResultFromUpdateCourseFormToUpdateCourseForm/${selectedCourse.courseID}/${result.resultID}'/>" class="btn btn-danger">Delete Result</a></td>
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
