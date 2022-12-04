/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */

import classes.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import service.*;

@Controller
public class UpdateGradeFromMyOracleDBController {
    
    //updateFormForCourse.jsp => updateCourse() => detailsCourse.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateCourse", method = RequestMethod.POST)
    public ModelAndView updateCourse(@Validated Course modifiedCourse) { //!!!Don't forget StudentCourse and TeacherCourse Updates
        //prepare services
        CourseService courseService = new CourseService(); //modify course
        StudentCourseService scs = new StudentCourseService(); //modify the course component of student course
        TeacherCourseService tcs = new TeacherCourseService(); //modify the course component of teacher course
        ResultService resultService = new ResultService(); //modify the course compnent of result
        
        courseService.update(modifiedCourse); //update the Course entry in the database
        
        int updatedCourseID = modifiedCourse.getCourseID(); //the updated Course ID number to help prepare the course details page
        
        Course selectedCourse = courseService.findById(updatedCourseID); //find the recently updated Course
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        //we only want the students, teachers and results that have a connection to the course
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == updatedCourseID) { //if the student-course combo has the same course id as the updates course
                studentCourse.setCourseCode(modifiedCourse.getCourseCode()); //change the course code of the student course
                studentCourse.setCourseName(modifiedCourse.getCourseName()); //change the course name of student course
                scs.update(studentCourse); //use student course service to save the name change
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == updatedCourseID) { //if the teacher-course combo has the same course id as the updates course
                teacherCourse.setCourseCode(modifiedCourse.getCourseCode()); //change the course code of the teacher course
                teacherCourse.setCourseName(modifiedCourse.getCourseName()); //change the course name of teacher course
                tcs.update(teacherCourse); //use teacher course service to save the name and code change
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == updatedCourseID) { //if the result has the same course id as the updates course
                result.setCourseName(modifiedCourse.getCourseName()); //change the course name in the result
                resultService.update(result); //use result service to save the changes to the database
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("detailsCourse"); //prepare the detailsCourse page to display the modified Course info
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to detailsCourse.jsp page
    }
    
    
    //updateFormForResult.jsp => updateResult() => results.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateResult", method = RequestMethod.POST)
    public ModelAndView updateResult(@RequestParam("resultID") int resultID,
            @RequestParam("studentChoice") int studentID,
            @RequestParam("courseChoice") int courseID,
            @RequestParam("sessionNumber") int sessionNumber,
            @RequestParam("midTermMark") int midTermMark,
            @RequestParam("finalMark") int finalMark) {
        //prepare the services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Student student = studentService.findById(studentID); //get the student object
        Course course = courseService.findById(courseID); //get the course object

        Result modifiedResult = new Result(resultID, student, course, sessionNumber, midTermMark, finalMark); /*prepare the Result object with the same
        result ID code as before plus some changes*/

        resultService.update(modifiedResult); //update the Result entry in the database
        ModelAndView model = new ModelAndView("results"); //prepare the results.jsp page
        List<Result> results = resultService.findAll(); //get a list of all Result entries
        model.addObject("resultList", results); //connect the list to the results.jap page
        return model; //take the user to the results.jsp page
    }
    
    //results.jsp Edit Result Button => updateFromResultsPageToUpdateResultForm() => updateFormForResult.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateFromResultsPageToUpdateResultForm/{resultid}", method = RequestMethod.GET)
    public ModelAndView updateFromResultsPageToUpdateResultForm(@PathVariable("resultid") int targetIDforUpdate) {
        //prepare services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Result selectedResult = resultService.findById(targetIDforUpdate); //find the right Result object

        ModelAndView model = new ModelAndView("updateFormForResult"); //prepare the update Result form
        model.addObject("selectedResult", selectedResult); //link page to the Result object you want to modify
        model.addObject("studentList", studentService.findAll()); //bring along a list of students
        model.addObject("courseList", courseService.findAll()); //and bring along a list of courses
        return model; //take the user to the updateFormForResult.jsp page
    }
    
    //detailsCourse.jsp Edit Result Button => updateFromCourseDetailsPageToUpdateResultForm() => updateFormForResult.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateFromCourseDetailsPageToUpdateResultForm/{resultid}", method = RequestMethod.GET)
    public ModelAndView updateFromCourseDetailsPageToUpdateResultForm(@PathVariable("resultid") int targetIDforUpdate) {
        //prepare services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Result selectedResult = resultService.findById(targetIDforUpdate); //find the right Result object

        ModelAndView model = new ModelAndView("updateFormForResult"); //prepare the update Result form
        model.addObject("selectedResult", selectedResult); //link page to the Result object you want to modify
        model.addObject("studentList", studentService.findAll()); //bring along a list of students
        model.addObject("courseList", courseService.findAll()); //and bring along a list of courses
        return model; //take the user to the updateFormForResult.jsp page
    }
    
    //detailsStudent.jsp Edit Result Button => updateFromStudentDetailsPageToUpdateResultForm() => updateFormForResult.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateFromStudentDetailsPageToUpdateResultForm/{resultid}", method = RequestMethod.GET)
    public ModelAndView updateFromStudentDetailsPageToUpdateResultForm(@PathVariable("resultid") int targetIDforUpdate) {
        //prepare services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Result selectedResult = resultService.findById(targetIDforUpdate); //find the right Result object

        ModelAndView model = new ModelAndView("updateFormForResult"); //prepare the update Result form
        model.addObject("selectedResult", selectedResult); //link page to the Result object you want to modify
        model.addObject("studentList", studentService.findAll()); //bring along a list of students
        model.addObject("courseList", courseService.findAll()); //and bring along a list of courses
        return model; //take the user to the updateFormForResult.jsp page
    }  
    
    //updateFormForCourse.jsp Edit Result Button => updateFromUpdateCourseFormPageToUpdateResultForm() => updateFormForResult.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateFromUpdateCourseFormPageToUpdateResultForm/{resultid}", method = RequestMethod.GET)
    public ModelAndView updateFromUpdateCourseFormPageToUpdateResultForm(@PathVariable("resultid") int targetIDforUpdate) {
        //prepare services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Result selectedResult = resultService.findById(targetIDforUpdate); //find the right Result object

        ModelAndView model = new ModelAndView("updateFormForResult"); //prepare the update Result form
        model.addObject("selectedResult", selectedResult); //link page to the Result object you want to modify
        model.addObject("studentList", studentService.findAll()); //bring along a list of students
        model.addObject("courseList", courseService.findAll()); //and bring along a list of courses
        return model; //take the user to the updateFormForResult.jsp page
    }  
    
    //updateFormForStudent.jsp Edit Button => updateFromStudentUpdateFormPageToUpdateResultForm() => updateFormForResult.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateFromStudentUpdateFormPageToUpdateResultForm/{resultid}", method = RequestMethod.GET)
    public ModelAndView updateFromStudentUpdateFormPageToUpdateResultForm(@PathVariable("resultid") int targetIDforUpdate) {
        //prepare services
        ResultService resultService = new ResultService();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        Result selectedResult = resultService.findById(targetIDforUpdate); //find the right Result object

        ModelAndView model = new ModelAndView("updateFormForResult"); //prepare the update Result form
        model.addObject("selectedResult", selectedResult); //link page to the Result object you want to modify
        model.addObject("studentList", studentService.findAll()); //bring along a list of students
        model.addObject("courseList", courseService.findAll()); //and bring along a list of courses
        return model; //take the user to the updateFormForResult.jsp page
    }
    
    
    //updateStudent.jsp Form => updateStudent() => detailsStudent.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateStudent", method = RequestMethod.POST)
    public ModelAndView updateStudent(@Validated Student modifiedStudent) { 
        //prepare services
        StudentService studentService = new StudentService(); //saves the student update
        StudentCourseService scs = new StudentCourseService(); //for updating the student course combo
        ResultService resultService = new ResultService(); //for updating the result
        
        studentService.update(modifiedStudent); //save student changes to database
        
        int updatedStudentID = modifiedStudent.getStudentID(); //get the student id of the modified student       
        
        Student selectedStudent = studentService.findById(updatedStudentID); //get the specific student object through its id code, the student for the details page
        
        //prepare lists of all student-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>(); //to provide the specific student courses
        List<Result> smallerResultsList = new ArrayList<Result>(); //to provide the specific student results
        
        //find courses tied to the specific student
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getStudentID() == updatedStudentID) { //if the student-course combo has the same student id as selectedStudent
                //insert the updated student info here
                //studentCourse's student ID remains intact
                studentCourse.setStudentFirstName(modifiedStudent.getFirstName()); //change the first name
                studentCourse.setStudentLastName(modifiedStudent.getLastName()); //change the last name
                scs.update(studentCourse); //update the studentCourse object throught the student course service
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        //find results tied to the specific student
        for (Result result : allResultsList) { //check all results
            if (result.getStudentID() == updatedStudentID) { //if the result has the same student id as selectedStudent
                //insert the updated result info here
                //result's student ID remains intact
                result.setStudentFirstName(modifiedStudent.getFirstName()); //change the student first name in the result
                result.setStudentLastName(modifiedStudent.getLastName());
                resultService.update(result);
                smallerResultsList.add(result); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsStudent"); //prepare the detailsStudent page to show the modifications
        model.addObject("selectedStudent", selectedStudent); //add the student object to the model
        model.addObject("selectStudentCourseList", smallerStudentCourseList); //add the modified student-course list
        model.addObject("selectedResultList", smallerResultsList); //add the modified result list
        return model;//take user to detailsStudent.jsp page
    }
    
    
    //updateTeacher.jsp Form => updateTeacher() => detailsTeacher.jsp
    @RequestMapping(value = "/UpdateGradeFromMyOracleDBController/updateTeacher", method = RequestMethod.POST)
    public ModelAndView updateTeacher(@Validated Teacher modifiedTeacher) { 
        //prepare services
        TeacherService teacherService = new TeacherService();
        TeacherCourseService tcs = new TeacherCourseService(); //to develop the list of courses the teacher teaches 
        
        teacherService.update(modifiedTeacher); //update the teacher in the database
        
        int updatedTeacherID = modifiedTeacher.getTeacherID(); //get the teacher id code of the updated Teacher entry     
        
        Teacher selectedTeacher = teacherService.findById(updatedTeacherID); //get the specific teacher object through its id code, the teacher for the details page
        
        //prepare lists of all teacher-course combos
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        
        //prepare lists to hold smaller amounts of teacher-course combos 
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>(); //to provide the specific teacher courses
        
        //find courses tied to the specific teacher
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getTeacherID()== updatedTeacherID) { //if the teacher-course combo has the same teacherid id as selectedTeacher
                //insert the updated teacher info here
                teacherCourse.setTeacherFirstName(modifiedTeacher.getFirstName()); //modify the teacher first name
                teacherCourse.setTeacherLastName(modifiedTeacher.getLastName()); //modify the teacher last name
                tcs.update(teacherCourse); //use teacher course service to save the name changes
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsTeacher"); //prepare the detailsTeacher page
        model.addObject("selectedTeacher", selectedTeacher); //add the teacher object to the model
        model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //add the modified teacher-course list
        return model; //take user to detailsTeacher.jsp page
    }
}
