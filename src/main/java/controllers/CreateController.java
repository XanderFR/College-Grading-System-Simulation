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
import org.springframework.web.bind.annotation.RequestParam;
import service.*;

@Controller
public class CreateController {
    
    //createCourse.jsp => createCourse() => courseList.jsp
    @RequestMapping(value = "/CreateController/createCourse", method = RequestMethod.POST)
    public ModelAndView createCourse(@Validated Course newCourse) {
        CourseService courseService = new CourseService(); //prepare the course service
        courseService.create(newCourse); //save the created course to the database
        ModelAndView model = new ModelAndView("courses"); //prepare the courses.jsp page
        List<Course> courses = courseService.findAll(); //prepare a list of all course entries
        model.addObject("courseList", courses); //link course list to courses.jsp page
        return model; //take user to courses.jsp page
    }
    
    //createResult.jsp => createResult() => results.jsp
    @RequestMapping(value = "/CreateController/createResult", method = RequestMethod.POST)
    public ModelAndView createResult(@RequestParam("studentChoice") int studentID,
            @RequestParam("courseChoice") int courseID,
            @RequestParam("sessionNumber") int sessionNumber,
            @RequestParam("midTermMark") int midTermMark,
            @RequestParam("finalMark") int finalMark) {

        //prepare the services
        ResultService resultService = new ResultService(); //to save the resulting Result object
        StudentService studentService = new StudentService(); //to provide the student object
        CourseService courseService = new CourseService(); //to provide the course object

        Student student = studentService.findById(studentID); //find the student with the student ID code
        Course course = courseService.findById(courseID); //find the course with the course ID code

        Result newResult = new Result(student, course, sessionNumber, midTermMark, finalMark); //prepare the new Result object

        resultService.create(newResult); //save the result to the database

        ModelAndView model = new ModelAndView("results"); //prepare the page that shows all the Result entries, results.jsp
        List<Result> results = resultService.findAll(); //prepare a list of every Result object
        model.addObject("resultList", results); //connect the list to the results.jsp page
        return model; //take the user to the results.jsp page
    }
    
    //createStudent Form => createStudent() => students.jsp
    @RequestMapping(value = "/CreateController/createStudent", method = RequestMethod.POST)
    public ModelAndView createStudent(@Validated Student newStudent) {
        StudentService studentService = new StudentService(); //prepare the student service
        studentService.create(newStudent); //save new student to database
        ModelAndView model = new ModelAndView("students"); //prepare the students page
        List<Student> students = studentService.findAll(); //prepare a list of all students
        model.addObject("studentList", students); //link student list to page
        return model; //take user to students.jsp page
    }
    
    //createTeacher Form => createTeacher() => teachers.jsp
    @RequestMapping(value = "/CreateController/createTeacher", method = RequestMethod.POST)
    public ModelAndView createTeacher(@Validated Teacher newTeacher) {
        TeacherService teacherService = new TeacherService(); //prepare teacher service
        teacherService.create(newTeacher); //save new teacher to database
        ModelAndView model = new ModelAndView("teachers"); //prepare the teachers page
        List<Teacher> teachers = teacherService.findAll(); //prepare list of all teachers
        model.addObject("teacherList", teachers); //link teacher list to page
        return model; //take user to the teachers page
    }
    
    //addStudentToCourse Form => addStudentToCourse() => detailsCourse.jsp
    @RequestMapping(value = "/CreateController/addStudentToCourse", method = RequestMethod.POST)
    public ModelAndView addStudentToCourse(@RequestParam("studentChoice") int studentID,
            @RequestParam("courseChoice") int courseID) {
        //prepare services
        TeacherCourseService tcs = new TeacherCourseService(); //provides the teachers teaching course
        StudentService studentService = new StudentService(); //provides the student in question
        CourseService courseService = new CourseService(); //provides the course in question
        StudentCourseService scs = new StudentCourseService(); //provides students taking course
        ResultService resultService = new ResultService(); //provides course results

        Student student = studentService.findById(studentID); //find the student
        Course course = courseService.findById(courseID); //find the course

        StudentCourse newStudentCourse = new StudentCourse(student, course); //create the student course combo
        scs.create(newStudentCourse); //save the combo to database

        ModelAndView model = new ModelAndView("detailsCourse"); //prepare course details page

        List<TeacherCourse> courseTeachersList = tcs.findAll(); //prepare course teacher combo list
        List<TeacherCourse> modifiedCourseTeachersList = new ArrayList<TeacherCourse>();

        //find teachers teaching course
        for (TeacherCourse tcElement : courseTeachersList) {
            if (tcElement.getCourseID() == courseID) { //if the teacher has a connection to the course you are looking for
                modifiedCourseTeachersList.add(tcElement); //add teacher to list
            }
        }

        List<StudentCourse> courseStudentsList = scs.findAll();
        List<StudentCourse> modifiedCourseStudentsList = new ArrayList<StudentCourse>();

        //find students taking course
        for (StudentCourse scElement : courseStudentsList) {
            if (scElement.getCourseID() == courseID) { //if the student has a connection to the course you are looking for
                modifiedCourseStudentsList.add(scElement); // add student to list
            }
        }
        
        List<Result> resultList = resultService.findAll();
        List<Result> modifiedResultList = new ArrayList<Result>();
        
        //find results linked to course
        for (Result resultElement : resultList) {
            if (resultElement.getCourseID() == courseID) { //if the result has a connection to the course you are looking for
                modifiedResultList.add(resultElement); //add result to list
            }
        }

        model.addObject("selectedCourse", course); //add course to page
        model.addObject("selectTeacherCourseList", modifiedCourseTeachersList); //link list to page
        model.addObject("selectStudentCourseList", modifiedCourseStudentsList); //link list to page
        model.addObject("selectedResultList", modifiedResultList); //link list to page

        return model; //take user to course details page
    }
    
    //addTeacherToCourse Form => addTeacherToCourse() => detailsCourse.jsp
    @RequestMapping(value = "/CreateController/addTeacherToCourse", method = RequestMethod.POST)
    public ModelAndView addTeacherToCourse(@RequestParam("teacherChoice") int teacherID,
            @RequestParam("courseChoice") int courseID) {
        //prepare the services
        TeacherCourseService tcs = new TeacherCourseService(); //for storing the teacher-course combo, teachers teaching course
        TeacherService teacherService = new TeacherService(); //provide the teacher in question
        CourseService courseService = new CourseService(); //provide the course in question
        StudentCourseService scs = new StudentCourseService(); //provide the student-course combo
        ResultService resultService = new ResultService(); //provides the course results
        
        Teacher teacher = teacherService.findById(teacherID); //find the teacher
        Course course = courseService.findById(courseID); //find the course
        
        TeacherCourse newTeacherCourse = new TeacherCourse(teacher, course); //prepare a teacher-course combo, have teacher teach the course
        tcs.create(newTeacherCourse); //save the teacher course combo to the database
        
        ModelAndView model = new ModelAndView("detailsCourse"); //prepare the detailsCourse page
        
        List<TeacherCourse> courseTeachersList = tcs.findAll(); //make a list of all teacher course combos
        List<TeacherCourse> modifiedCourseTeachersList = new ArrayList<TeacherCourse>(); //a list to store a subset of teacher-course combos
        
        //prepare a list of the teachers teaching the course you are looking for
        for (TeacherCourse tcElement : courseTeachersList) { //look through all teacher course combos
            if (tcElement.getCourseID() == courseID) { //if the teacher has a connection to the course you are looking for
                modifiedCourseTeachersList.add(tcElement); //add teacher to list
            }
        }
        
        List<StudentCourse> courseStudentsList = scs.findAll(); //make a list of all student course combos
        List<StudentCourse> modifiedCourseStudentsList = new ArrayList<StudentCourse>(); //a list to store a subset of student-course combos
        
        for (StudentCourse scElement : courseStudentsList) { //look through all student course combos
            if (scElement.getCourseID() == courseID) { //if the student has a connection to the course you are looking for
                modifiedCourseStudentsList.add(scElement); // add student to list
            }
        }
        
        List<Result> resultList = resultService.findAll();
        List<Result> modifiedResultList = new ArrayList<Result>();
        
        //find results linked to course
        for (Result resultElement : resultList) {
            if (resultElement.getCourseID() == courseID) { //if the result has a connection to the course you are looking for
                modifiedResultList.add(resultElement); //add result to list
            }
        }
        
        model.addObject("selectedCourse", course); //add course to page
        model.addObject("selectTeacherCourseList", modifiedCourseTeachersList); //link list to page
        model.addObject("selectStudentCourseList", modifiedCourseStudentsList); //link list to page
        model.addObject("selectedResultList", modifiedResultList); //link list to page
        
        return model; //take user to course details page
    }
}
