<%-- 
    Document   : detailsStudent
    Created on : 5-Dec-2020, 4:31:35 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Student Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    
    <style>
        .bttnundermargin {
            margin-bottom: 1em;
        }
    </style>
    
    <body class = "container">
        <header>
            <h1>DAO Final Project Student Details</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">Look at the Info of the Student you Chose</h3></center>

    <h1>Student Information</h1>
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
                    <th>Edit</th>
                </tr>
            </thead>

            <tr>
                <td><c:out value="${selectedStudent.studentID}"/></td>
                <td><c:out value="${selectedStudent.firstName}"/></td>
                <td><c:out value="${selectedStudent.lastName}"/></td>
                <td><c:out value="${selectedStudent.address}"/></td>
                <td><c:out value="${selectedStudent.city}"/></td>
                <td><c:out value="${selectedStudent.country}"/></td>
                <td><c:out value="${selectedStudent.phoneNumber}"/></td>
                <td><c:out value="${selectedStudent.major}"/></td>
                <td><a href = "<c:url value='/DisplayGradesController/gotoUpdateStudentForm/${selectedStudent.studentID}'/>" class="btn btn-warning">Edit Student</a></td>
            </tr>

        </table>
    </center>
    
            <br>
            
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
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteCourseFromStudentDetailsPageToStudentDetailsPage/${selectedStudent.studentID}/${studentCourse.studentCourseID}'/>" class="btn btn-danger">Drop Course</a></td>
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
                    <td><a href = "<c:url value='/UpdateGradeFromMyOracleDBController/updateFromStudentDetailsPageToUpdateResultForm/${result.resultID}'/>" class="btn btn-warning">Edit Result</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteResultFromStudentDetailsPageToStudentDetailsPage/${selectedStudent.studentID}/${result.resultID}'/>" class="btn btn-danger">Delete Result</a></td>
                </tr>
            </c:forEach>
        </table>
    </center>
    
    <br>
    
    <center>
        <h5 class = "">To Send Student Transcript by Email, click the button below</h5>
        <a type="button" class="btn btn-primary bttnundermargin" href="<c:url value='/SendGradeByEmailController/gotoStudentTranscriptEmailPage/${selectedStudent.studentID}'/>">Send Transcript</a>
    </center>

    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio </p>
        </div>
    </footer>
</body>
</html>
