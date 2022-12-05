<%-- 
    Document   : createTeacher
    Created on : 30-Nov-2020, 7:50:49 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAO Final Project Create Teacher</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body class = "container">
        <header>
            <h1>DAO Final Project Create Teacher</h1>
        </header>

        <jsp:include page="navigationMenu.jsp"/>
        
        <br>

    <center><h3 class = "">You have chosen to make a new Teacher entry in the database</h3></center>
    <br>
    
    <c:url var="addAction" value='/CreateController/createTeacher' ></c:url>
    
    <form:form action="${addAction}" method="POST" >
        <h5>Time to create a Teacher profile</h5>
        
        <!--FIRST NAME-->
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstNameid" required placeholder="John" name = "firstName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--LAST NAME-->
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastNameid" required placeholder="Doe" name = "lastName">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--ADDRESS-->
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="addressid" required placeholder="1234 Street Name" name = "address">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--CITY-->
        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="cityid" required placeholder="City Name" name = "city">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--COUNTRY-->
        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="countryid" required placeholder="Canada" name = "country">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--PHONE NUMBER-->
        <div class="form-group">
            <label for="phoneNumber">Phone Number (Max 10 digits):</label>
            <input type="number" class="form-control" id="phoneNumberid" required placeholder="5551234567" name = "phoneNumber" min = "1000000000" max = "9999999999" maxlength = "10" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        
        <!--SALARY-->
        <div class="form-group">
            <label for="salary">Salary (Positive numbers only):</label>
            <input type="number" class="form-control" id="salaryid" required placeholder="10000" name = "salary" min = "1" step = "1">
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <!--THE BUTTONS-->
        <center>
            <button id = "submitButton" type="submit" class="btn btn-primary">Submit</button>
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
