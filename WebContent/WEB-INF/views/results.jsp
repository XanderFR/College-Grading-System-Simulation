<%-- 
    Document   : results
    Created on : 1-Dec-2020, 5:00:21 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Project Show All Results</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Project Show All Results</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to see all Result entries in the database</h3></center>
    <br>
 
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
            <c:forEach var="result" items="${resultList}">
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
                    <td><a href = "<c:url value='/UpdateGradeFromMyOracleDBController/updateFromResultsPageToUpdateResultForm/${result.resultID}'/>" class="btn btn-warning">Edit Result</a></td>
                    <td><a href = "<c:url value='/DeleteGradeFromDBController/deleteResultFromResultsPageToResultsPage/${result.resultID}'/>" class="btn btn-danger">Delete Result</a></td>
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
