<%-- 
    Document   : updateFormForResult
    Created on : 8-Dec-2020, 1:46:14 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Modify Result</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Modify Result</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to modify a Result entry in the database</h3></center>
    <br>
    <c:url var="addAction" value='/UpdateGradeFromMyOracleDBController/updateResult' ></c:url>
    
    <form:form action="${addAction}"  >
        <h5>Time for you to Modify a Result</h5>
        
        <div class="form-group">
            <label for="resultID">Result ID:</label>
            <input type="number" class="form-control" id="resultIDid" name = "resultID" readonly value = "${selectedResult.resultID}">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--Student Selection-->
        <div class="form-group">
            <label for="studentChoice">Current Student:</label>
            <input type="text" class="form-control" id="studentStringID" required readonly value = "${selectedResult.getStudentString()}">
            <label for="studentChoiceTwo">Pick a New Student:</label>
            <select multiple class ="form-control" name = "studentChoice">
                <c:forEach var="student" items="${studentList}">
                    <option value = "${student.studentID}">
                        <c:out value="${student.toString()}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <!--Course Selection-->
        <div class="form-group">
            <label for="courseChoice">Current Course:</label>
            <input type="text" class="form-control" id="courseStringID" required readonly value = "${selectedResult.getCourseString()}">
            <label for="courseChoiceTwo">Pick a New Course:</label>
            <select multiple class ="form-control" name = "courseChoice">
                <c:forEach var="course" items="${courseList}">
                    <option value = "${course.courseID}">
                        <c:out value="${course.toString()}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <!--Session Number-->
        <div class="form-group">
            <label for="modifiedSessionNumberTwo">Session Number (101 to 1299):</label>
            <input type="number" class="form-control" id="sessionNumberidTwo" required value = "${selectedResult.sessionNumber}" name = "sessionNumber" min = "101" max = "1299" maxlength = "4" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--Mid Term Mark-->
        <div class="form-group">
            <label for="modifiedMidTermMarkTwo">Mid Term Mark (0 to 20):</label>
            <input type="number" class="form-control" id="midTermMarkidTwo" required value = "${selectedResult.midTermMark}" name = "midTermMark" min = "0" max = "20" maxlength = "2" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--Final Exam Mark-->
        <div class="form-group">
            <label for="modifiedFinalMarkTwo">Final Exam Mark (0 to 20):</label>
            <input type="number" class="form-control" id="finalMarkidTwo" required value = "${selectedResult.finalMark}" name = "finalMark" min = "0" max = "20" maxlength = "2" step = "1">
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
        
    <footer style = "margin-top: 1em;">
        <div class = "container">
            <p style = "text-align: center;">&copy; DAO Final Project 2020</p>
            <p style = "text-align: center;">Alexander Dave Flores Respicio </p>
        </div>
    </footer>
</body>
</html>
