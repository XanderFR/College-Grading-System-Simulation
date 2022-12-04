<%-- 
    Document   : navigationMenu
    Created on : 3-Dec-2020, 6:51:18 PM
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class = "nav nav-tabs">
    &nbsp;&nbsp;
    <li><a href="<c:url value='/IndexController'/>">Index</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoCreateStudentPage'/>">Create Student</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAddStudentToCourse'/>">Add Student to Course</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoCreateCoursePage'/>">Create Course</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoCreateTeacherPage'/>">Create Teacher</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAddTeacherToCourse'/>">Add Teacher to Course</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoCreateResultPage'/>">Create Result</a></li>
</ul>

<ul class = "nav nav-tabs">
    &nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllStudentsPage'/>">See All Students</a></li>
    <!--&nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllStudentCoursesPage'/>">See All Student-Courses</a></li>-->
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllCoursesPage'/>">See All Courses</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllTeachersPage'/>">See All Teachers</a></li>
    <!--&nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllTeacherCoursesPage'/>">See All Teacher-Courses</a></li>-->
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/DisplayGradesController/gotoAllResultsPage'/>">See All Results</a></li>
    &nbsp;&nbsp;&nbsp;
    <li><a href="<c:url value='/LogoutController'/>">Logout</a></li>
</ul>

