<%-- 
    Document   : studentTranscriptEmail
    Created on : 6-Jan-2021, 4:06:26 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Student Transcript Email</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Student Transcript Email</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>

        <br>

    <center><h3 class = "">Look at the Info of the Student you want to send by Email</h3></center>

    <c:url var="addAction" value='/SendGradeByEmailController/sendEmail/${selectedStudent.studentID}' ></c:url>

    <form:form action="${addAction}"  >
        <!--Email Account-->
        <div class="form-group">
            <label for="emailUsername">Email Account Username:</label>
            <input type="text" class="form-control" id="emailusername" name = "emailUsername" placeholder="Enter email account username here">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <!--Email Password-->
        <div class="form-group">
            <label for="emailPassword">Email Account Password:</label>
            <input type="password" class="form-control" id="emailpassword" name = "emailPassword" placeholder="Enter email account password here">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <!--Email Sender-->
        <div class="form-group">
            <label for="emailSender">Sender Email Address:</label>
            <input type="email" class="form-control" id="emailsender" name = "emailSender" placeholder="Enter email sender here">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <!--Email Receiver-->
        <div class="form-group">
            <label for="emailRecipient">Recipient Email Address:</label>
            <input type="email" class="form-control" id="emailreceiver" name = "emailRecipient" placeholder="Enter email recipient here">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <!--Email Message-->
        <div class="form-group">
            <label for="description">Email Message:</label>
            <textarea class="form-control" id="emailmessage" placeholder="Enter any message you want to send here" name = "emailMessage" rows = "3"></textarea>
        </div>

        <!--THE BUTTONS-->
        <center>
            <button id = "submitButton" type="submit" class="btn btn-primary">Send Email</button>
            &nbsp; &nbsp;
            <button type="reset" class="btn btn-primary">Reset</button>
        </center>
    </form:form>

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
