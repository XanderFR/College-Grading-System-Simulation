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
import service.*;

@Controller
public class DeleteGradeFromDBController {
    
    //courses.jsp delete course button => deleteCourseFromCoursesPageToCoursesPage() => courses.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteCourseFromCoursesPageToCoursesPage/{courseid}", method = RequestMethod.GET)
    public ModelAndView deleteCourseFromCoursesPageToCoursesPage(@PathVariable("courseid") int targetIDFromTable) { 
        CourseService courseService = new CourseService(); //prepare Course service
        StudentCourseService studentCourseService = new StudentCourseService(); //prepare Student-Course service
        TeacherCourseService teacherCourseService = new TeacherCourseService(); //perepare Teacher-Course service
        
        //prepare studentCourse and teacherCourse lists
        List<StudentCourse> scList = studentCourseService.findAll();
        List<TeacherCourse> tcList = teacherCourseService.findAll();
        
        //search for and delete certain studentCourse and teacherCourse objects
        for (StudentCourse studentCourse : scList) { //search through all entries in the student course list
            int currentStudentCourseCourseID = studentCourse.getCourseID(); //get the courseID of the current studentCourse object
            if (currentStudentCourseCourseID == targetIDFromTable) { //if the studentCourse object's course ID matches the courseID of the course for deletion
                studentCourseService.delete(studentCourse.getStudentCourseID()); //delete the studentCourse object from the database
            }
        }
        for (TeacherCourse teacherCourse : tcList) { //search through all entries in the teacher course list
            int currentTeacherCourseCourseID = teacherCourse.getCourseID(); //get the courseID of the current teacherCourse object
            if (currentTeacherCourseCourseID == targetIDFromTable) { //if the teacherCourse object's course ID matches the courseID of the course for deletion
                teacherCourseService.delete(teacherCourse.getTeacherCourseID()); //delete the teacherCourse object from the database
            }
        }
        
        courseService.delete(targetIDFromTable); //delete target Course from database
        ModelAndView model = new ModelAndView("courses"); //prepare another courses page
        List<Course> courses = courseService.findAll(); //get a list of all course entries
        model.addObject("courseList", courses); //link course list to courses page
        return model; //take user to courses.jsp page
    }

    //detailsStudent.jsp Drop Course Button => deleteCourseFromStudentDetailsPageToStudentDetailsPage() => detailsStudent.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteCourseFromStudentDetailsPageToStudentDetailsPage/{studentid}/{studentcourseid}", method = RequestMethod.GET)
    public ModelAndView deleteCourseFromStudentDetailsPageToStudentDetailsPage(@PathVariable("studentid") int studentID, @PathVariable("studentcourseid") int targetIDforDelete) {
        //prepare services
        StudentService studentService = new StudentService();
        StudentCourseService scs = new StudentCourseService();
        ResultService resultService = new ResultService();
        
        Student selectedStudent = studentService.findById(studentID); //get the specific student object through its id code, the student for the details page
        
        scs.delete(targetIDforDelete); //delete the studentCourse from the database, student dropping from course
        
        //prepare lists of all student-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>(); //to provide the specific student courses
        List<Result> smallerResultsList = new ArrayList<Result>(); //to provide the specific student results
        
        //find courses tied to the specific student
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getStudentID() == studentID) { //if the student-course combo has the same student id as selectedStudent
                //prepare a list of students registered to course
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        //find results tied to the specific student
        for (Result result : allResultsList) { //check all results
            if (result.getStudentID() == studentID) { //if the result has the same student id as selectedStudent
                //prepare a result list connected to the desired student
                smallerResultsList.add(result); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsStudent"); //prepare the detailsStudent page
        model.addObject("selectedStudent", selectedStudent); //add the student object to the model
        model.addObject("selectStudentCourseList", smallerStudentCourseList); //add the modified student-course list
        model.addObject("selectedResultList", smallerResultsList); //add the modified result list
        return model;//take user to detailsStudent.jsp page
    }
    
    //detailsTeacher.jsp Drop Course Button => deleteCourseFromTeacherDetailsPageToTeacherDetailsPage() => detailsTeacher.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteCourseFromTeacherDetailsPageToTeacherDetailsPage/{teacherid}/{teachercourseid}", method = RequestMethod.GET)
    public ModelAndView deleteCourseFromTeacherDetailsPageToTeacherDetailsPage(@PathVariable("teacherid") int teacherID, @PathVariable("teachercourseid") int targetIDforDelete) {
        //prepare services
        TeacherService teacherService = new TeacherService(); //to provide the teacher
        TeacherCourseService tcs = new TeacherCourseService(); //to develop the list of courses the teacher teaches
        
        Teacher selectedTeacher = teacherService.findById(teacherID); //get the specific teacher object through its id code, the teacher for the details page
        
        tcs.delete(targetIDforDelete); //delete the teacherCourse from the database, teacher no longer teaching course
        
        //prepare lists of all teacher-course combos
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        
        //prepare lists to hold smaller amounts of teacher-course combos 
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>(); //to provide the specific teacher courses
        
        //find courses tied to the specific teacher
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getTeacherID()== teacherID) { //if the teacher-course combo has the same teacherid id as selectedTeacher
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsTeacher"); //prepare the detailsTeacher page
        model.addObject("selectedTeacher", selectedTeacher); //add the teacher object to the model
        model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //add the modified teacher-course list
        return model; //take user to detailsTeacher.jsp page
    }
    
    //updateFormForStudent.jsp Drop Course Button => deleteCourseFromUpdateStudentFormToUpdateStudentForm() => updateFormForStudent.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteCourseFromUpdateStudentFormToUpdateStudentForm/{studentid}/{studentcourseid}", method = RequestMethod.GET)
    public ModelAndView deleteCourseFromUpdateStudentFormToUpdateStudentForm(@PathVariable("studentid") int studentID, @PathVariable("studentcourseid") int targetIDforDelete) {
        //prepare services
        StudentService studentService = new StudentService(); //to get the student
        StudentCourseService scs = new StudentCourseService(); //to get the student's courses
        ResultService resultService = new ResultService(); //to get the student's results
        
        Student selectedStudent = studentService.findById(studentID); //get a specific student
        
        scs.delete(targetIDforDelete); //delete the studentCourse object through its id number
        
        //prepare lists of all student-course and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        //prepare a list of the student's courses
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getStudentID()== studentID) { //if the student-course combo has the same student id as selectedStudent
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        //prepare a list of the student's results
        for (Result result : allResultsList) { //check all results
            if (result.getStudentID()== studentID) { //if the result has the same student id as selectedStudent
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("updateFormForStudent"); //prepare another updateFormForStudent page
         model.addObject("selectedStudent", selectedStudent); //link to selectedStudent
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to updateFormForStudent.jsp page
    }
    
    //updateFormForTeacher.jsp Drop Course Button => deleteCourseFromTeacherUpdateFormToTeacherUpdateForm() => updateFormForTeacher.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteCourseFromTeacherUpdateFormToTeacherUpdateForm/{teacherid}/{teachercourseid}", method = RequestMethod.GET)
    public ModelAndView deleteCourseFromTeacherUpdateFormToTeacherUpdateForm(@PathVariable("teacherid") int teacherID, @PathVariable("teachercourseid") int targetIDforDelete) {
        //prepare services
        TeacherService teacherService = new TeacherService(); //to provide the teacher
        TeacherCourseService tcs = new TeacherCourseService(); //to develop the list of courses the teacher teaches
        
        Teacher selectedTeacher = teacherService.findById(teacherID); //get the specific teacher object through its id code, the teacher for the update form page
        
        tcs.delete(targetIDforDelete); //delete the teacherCourse from the database, teacher no longer teaching course
        
        //prepare lists of all teacher-course combos
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        
        //prepare lists to hold smaller amounts of teacher-course combos 
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>(); //to provide the specific teacher courses
        
        //find courses tied to the specific teacher
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getTeacherID()== teacherID) { //if the teacher-course combo has the same teacherid id as selectedTeacher
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("updateFormForTeacher"); //prepare the updateFormForTeacher page
        model.addObject("selectedTeacher", selectedTeacher); //add the teacher object to the model
        model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //add the modified teacher-course list
        return model; //take user to updateFormForTeacher.jsp page
    }

    
    
    //results.jsp Delete Result Button => deleteResultFromResultsPageToResultsPage() => results.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteResultFromResultsPageToResultsPage/{resultid}", method = RequestMethod.GET)
    public ModelAndView deleteResultFromResultsPageToResultsPage(@PathVariable("resultid") int targetIDforDelete) {
        ResultService resultService = new ResultService(); //prepare Result Service
        resultService.delete(targetIDforDelete); //delete the Result object from the database
        ModelAndView model = new ModelAndView("results"); //prepare the results.jsp page
        List<Result> results = resultService.findAll(); //prepare another list of all Result objects
        model.addObject("resultList", results); //link the shortened Results list to the results.jsp page
        return model; //take the user to the results.jsp page
    }
   
    //detailsCourse.jsp Delete Button => deleteResultFromCourseDetailsPageToCourseDetailsPage() => detailsCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteResultFromCourseDetailsPageToCourseDetailsPage/{courseid}/{resultid}", method = RequestMethod.GET)
    public ModelAndView deleteResultFromCourseDetailsPageToCourseDetailsPage(@PathVariable("courseid") int courseID, @PathVariable("resultid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course
        
        resultService.delete(targetIDforDelete); //delete the Result object through its id number
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("detailsCourse"); //prepare another detailsCourse page
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to detailsCourse.jsp page
    }
    
    //detailsStudent.jsp Delete Result Button => deleteResultFromStudentDetailsPageToStudentDetailsPage() => detailsStudent.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteResultFromStudentDetailsPageToStudentDetailsPage/{studentid}/{resultid}", method = RequestMethod.GET)
    public ModelAndView deleteResultFromStudentDetailsPageToStudentDetailsPage(@PathVariable("studentid") int studentID, @PathVariable("resultid") int targetIDforDelete) {
        //prepare services
        StudentService studentService = new StudentService();
        StudentCourseService scs = new StudentCourseService();
        ResultService resultService = new ResultService();
        
        Student selectedStudent = studentService.findById(studentID); //get the specific student object through its id code
        
        resultService.delete(targetIDforDelete); //delete the result from the database
        
        //prepare lists of all student-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>(); //to provide the specific student courses
        List<Result> smallerResultsList = new ArrayList<Result>(); //to provide the specific student results
        
        //find courses tied to the specific student
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getStudentID() == studentID) { //if the student-course combo has the same student id as selectedStudent
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        //find results tied to the specific student
        for (Result result : allResultsList) { //check all results
            if (result.getStudentID() == studentID) { //if the result has the same student id as selectedStudent
                smallerResultsList.add(result); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsStudent"); //prepare the detailsStudent page
        model.addObject("selectedStudent", selectedStudent); //add the student object to the model
        model.addObject("selectStudentCourseList", smallerStudentCourseList); //add the modified student-course list
        model.addObject("selectedResultList", smallerResultsList); //add the modified result list
        return model;
    }
    
    //updateFormForCourse.jsp Delete Result Button => deleteResultFromUpdateCourseFormToUpdateCourseForm() => updateFormForCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteResultFromUpdateCourseFormToUpdateCourseForm/{courseid}/{resultid}", method = RequestMethod.GET)
    public ModelAndView deleteResultFromUpdateCourseFormToUpdateCourseForm(@PathVariable("courseid") int courseID, @PathVariable("resultid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course
        
        resultService.delete(targetIDforDelete); //delete the Result object through its id number
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("updateFormForCourse"); //prepare another updateFormForCourse page
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to updateFormForCourse.jsp page
    }

    //updateFormForStudent.jsp Delete Result Button => deleteResultFromUpdateStudentFormToUpdateStudentForm() => updateFormForStudent.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteResultFromUpdateStudentFormToUpdateStudentForm/{studentid}/{resultid}", method = RequestMethod.GET)
    public ModelAndView deleteResultFromUpdateStudentFormToUpdateStudentForm(@PathVariable("studentid") int studentID, @PathVariable("resultid") int targetIDforDelete) {
        //prepare services
        StudentService studentService = new StudentService(); //to get the student
        StudentCourseService scs = new StudentCourseService(); //to get the student's courses
        ResultService resultService = new ResultService(); //to get the student's results
        
        Student selectedStudent = studentService.findById(studentID); //get a specific student
        
        resultService.delete(targetIDforDelete); //delete the Result object through its id number
        
        //prepare lists of all student-course and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getStudentID()== studentID) { //if the student-course combo has the same student id as selectedStudent
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getStudentID()== studentID) { //if the result has the same student id as selectedStudent
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("updateFormForStudent"); //prepare another updateFormForStudent page
         model.addObject("selectedStudent", selectedStudent); //link to selectedStudent
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to updateFormForStudent.jsp page
    }

    
    
    //updateFormForCourse.jsp Drop Student Button => deleteStudentFromUpdateCourseFormToUpdateCourseForm() => updateFormForCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteStudentFromUpdateCourseFormToUpdateCourseForm/{courseid}/{studentcourseid}", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromUpdateCourseFormToUpdateCourseForm(@PathVariable("courseid") int courseID, @PathVariable("studentcourseid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();

        Course selectedCourse = courseService.findById(courseID); //get a specific course

        scs.delete(targetIDforDelete); //delete the student course object through its id number, drop the student from the course

        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();

        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();

        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }

        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }

        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }

        ModelAndView model = new ModelAndView("updateFormForCourse"); //prepare another updateFormForCourse page
        model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
        model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
        model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
        model.addObject("selectedResultList", smallerResultsList); //link to modified result List

        return model; //take user to updateFormForCourse.jsp page
    }

    //students.jsp delete student button => deleteStudentFromStudentsPageToStudentsPage() => students.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteStudentFromStudentsPageToStudentsPage/{studentid}", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromStudentsPageToStudentsPage(@PathVariable("studentid") int targetIDFromTable) { //!!Don't forget the others
        StudentService studentService = new StudentService(); //prepare Student service
        StudentCourseService studentCourseService = new StudentCourseService(); //prepare the StudentCourse service
        
        List<StudentCourse> scList = studentCourseService.findAll(); //make a list of all studentCourse objects
        
        for (StudentCourse studentCourse : scList) { //search through all studentCourse object
            int currentStudentCourseStudentID = studentCourse.getStudentID(); //get the studentID of the current StudentCourse object
            if (currentStudentCourseStudentID == targetIDFromTable) { //if the current studentCourse object's studentID matches the student's for deletion
                studentCourseService.delete(studentCourse.getStudentCourseID()); //delete the studentCourse from the database
            }
        }
        
        studentService.delete(targetIDFromTable); //delete target Student from database
        ModelAndView model = new ModelAndView("students"); //prepare another students page
        List<Student> students = studentService.findAll(); //get a list of all student entries
        model.addObject("studentList", students); //link course list to courses page
        return model; //take user to students.jsp page
    }
    
    //detailsCourse.jsp Drop Student Button => deleteStudentFromCourseDetailsPageToCourseDetailsPage() => detailsCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteStudentFromCourseDetailsPageToCourseDetailsPage/{courseid}/{studentcourseid}", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromCourseDetailsPageToCourseDetailsPage(@PathVariable("courseid") int courseID, @PathVariable("studentcourseid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course
        
        scs.delete(targetIDforDelete); //delete the student course object through its id number
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("detailsCourse"); //prepare another detailsCourse page
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to detailsCourse.jsp page
    }
    
    //studentCourses.jsp delete student-course button => deleteStudentFromStudentCoursesPageToStudentCoursesPage() => studentCourses.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteStudentFromStudentCoursesPageToStudentCoursesPage/{studentcourseid}", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromStudentCoursesPageToStudentCoursesPage(@PathVariable("studentcourseid") int targetIDFromTable) { //!!Don't forget the others
        StudentCourseService scs = new StudentCourseService(); //prepare StudentCourse service
        scs.delete(targetIDFromTable); //delete target StudentCourse from database
        ModelAndView model = new ModelAndView("studentCourse"); //prepare another studentCourse page
        List<StudentCourse> studentCourseList = scs.findAll(); //get a list of all studentCourse entries
        model.addObject("studentCourseList", studentCourseList); //link studentcourse list to student course page
        return model; //take user to studentCourse.jsp page
    }

    
    
     //updateFormForCourse.jsp Drop Teacher Button => deleteTeacherFromUpdateCourseFormToUpdateCourseForm() => updateFormForCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteTeacherFromUpdateCourseFormToUpdateCourseForm/{courseid}/{teachercourseid}", method = RequestMethod.GET)
    public ModelAndView deleteTeacherFromUpdateCourseFormToUpdateCourseForm(@PathVariable("courseid") int courseID, @PathVariable("teachercourseid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService(); //provides the course
        StudentCourseService scs = new StudentCourseService(); //provides the students in the course
        TeacherCourseService tcs = new TeacherCourseService(); //provides the teachers in the course
        ResultService resultService = new ResultService(); //provides the results in the course
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course, the course to re appear
        
        tcs.delete(targetIDforDelete); //delete the teacher course object through its id number
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        //prepare lists of students, teachers and results for the update course form
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("updateFormForCourse"); //prepare another updateFormForCourse page
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to updateFormForCourse.jsp page
    } 
    
    //detailsCourse.jsp Drop Teacher Button => deleteTeacherFromCourseDetailsPageToCourseDetailsPage() => detailsCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteTeacherFromCourseDetailsPageToCourseDetailsPage/{courseid}/{teachercourseid}", method = RequestMethod.GET)
    public ModelAndView deleteTeacherFromCourseDetailsPageToCourseDetailsPage(@PathVariable("courseid") int courseID, @PathVariable("teachercourseid") int targetIDforDelete) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course, the course to re appear on the details page
        
        tcs.delete(targetIDforDelete); //delete the teacher course object through its id number
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == courseID) { //if the student-course combo has the same course id as selectedCourse
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("detailsCourse"); //prepare another detailsCourse page
         model.addObject("selectedCourse", selectedCourse); //link to selectedCourse
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to detailsCourse.jsp page
    }
    
    //teachers.jsp Delete Teacher Button => deleteTeacherFromTeachersPageToTeachersPage() => teachers.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteTeacherFromTeachersPageToTeachersPage/{teacherid}", method = RequestMethod.GET)
    public ModelAndView deleteTeacherFromTeachersPageToTeachersPage(@PathVariable("teacherid") int targetIDforDelete) { 
        TeacherService teacherService = new TeacherService(); //prepare Teacher Service
        TeacherCourseService teacherCourseService = new TeacherCourseService(); //perepare Teacher-Course service
        
        //prepare the teacherCourse list
        List<TeacherCourse> tcList = teacherCourseService.findAll();
        
        for (TeacherCourse teacherCourse : tcList) { //search through all teacherCourse objects
            int currentTeacherCourseTeacherID = teacherCourse.getTeacherID(); //get the teacherID of the current TeacherCourse object
            if (currentTeacherCourseTeacherID == targetIDforDelete) { //if the current teacherCourse object's teacherID matches the teacher's for deletion
                teacherCourseService.delete(teacherCourse.getTeacherCourseID()); //delete the teacherCourse from the database
            }
        }
        
        teacherService.delete(targetIDforDelete); //delete the Teacher object from the database
        ModelAndView model = new ModelAndView("teachers"); //prepare the teachers.jsp page
        List<Teacher> teachers = teacherService.findAll(); //prepare another list of all Teacher objects
        model.addObject("teacherList", teachers); //link the shortened Results list to the results.jsp page
        return model; //take the user to the teachers.jsp page
    }
    
    //teacherCourse.jsp Delete Teacher-Course Button => deleteTeacherFromTeacherCoursesPageToTeacherCoursesPage() => teacherCourse.jsp
    @RequestMapping(value = "/DeleteGradeFromDBController/deleteTeacherFromTeacherCoursesPageToTeacherCoursesPage/{teachercourseid}", method = RequestMethod.GET)
    public ModelAndView deleteTeacherFromTeacherCoursesPageToTeacherCoursesPage(@PathVariable("teachercourseid") int targetIDforDelete) { 
        TeacherCourseService tcs = new TeacherCourseService(); //prepare TeacherCourse Service
        tcs.delete(targetIDforDelete); //delete the TeacherCourse object from the database
        ModelAndView model = new ModelAndView("teacherCourse"); //prepare the teacherCourse.jsp page
        List<TeacherCourse> teacherCourseList = tcs.findAll(); //prepare a list of all TeacherCourse objects
        model.addObject("teacherCourseList", teacherCourseList); //link the list to the teacherCourse.jsp page
        return model; //take the user to the teacherCourse.jsp page
    }
}
