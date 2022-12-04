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
public class DisplayGradesController {
    
    //Create Course tab => gotoCreateCoursePage() => createCourse.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoCreateCoursePage", method = RequestMethod.GET)
    public ModelAndView gotoCreateCoursePage() {
        ModelAndView model = new ModelAndView("createCourse"); //prepares createCourse.jsp page
        return model; //take user to the createCourse.jsp page
    }

    //All Courses tab => gotoAllCoursesPage() => courses.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllCoursesPage", method = RequestMethod.GET)
    public ModelAndView gotoAllCoursesPage() {
        CourseService courseService = new CourseService(); //this will provide the list of courses
        ModelAndView model = new ModelAndView("courses"); //prepare the courses.jsp page
        List<Course> courses = courseService.findAll(); //prepare a list of the courses in the database
        model.addObject("courseList", courses); //link course list to courses.jsp
        return model; //take the user to courses.jsp
    }

    //Course Details button => gotoCourseDetailsPage() => detailsCourse.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoCourseDetailsPage/{courseid}", method = RequestMethod.GET)
    public ModelAndView gotoCourseDetailsPage(@PathVariable("courseid") int courseID) {
        //prepare services
        CourseService courseService = new CourseService(); //provides course list
        StudentCourseService scs = new StudentCourseService(); //provide combos of student-courses
        TeacherCourseService tcs = new TeacherCourseService(); //provie combos of teacher-courses
        ResultService resultService = new ResultService(); //provide results list
        
        Course selectedCourse = courseService.findById(courseID); //get a specific course based on the input course ID code in the function
        //the course you want to get the details on
        
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
                //if the course id codes match then the student is registered to course
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == courseID) { //if the teacher-course combo has the same course id as selectedCourse
                //if the course id codes match then the teacher is teaching the course
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == courseID) { //if the result has the same course id as selectedCourse
                //if the result is linked to the targeted course
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("detailsCourse"); //prepare another detailsCourse page
         model.addObject("selectedCourse", selectedCourse); //link to the course you want details on
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to list of students signed up for course
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to list of teachers teaching course
         model.addObject("selectedResultList", smallerResultsList); //link to list of results connected to course
         
         return model; //take user to detailsCourse.jsp page
    }

    //Edit Course button => gotoUpdateCourseForm() => updateFormForCourse.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoUpdateCourseForm/{courseid}", method = RequestMethod.GET)
    public ModelAndView gotoUpdateCourseForm(@PathVariable("courseid") int targetCourseID) {
        //prepare services
        CourseService courseService = new CourseService();
        StudentCourseService scs = new StudentCourseService();
        TeacherCourseService tcs = new TeacherCourseService();
        ResultService resultService = new ResultService();
        
        Course selectedCourse = courseService.findById(targetCourseID); //get a specific course you want to modify the details of
        
        //prepare lists of all student-course and teacher-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll();
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        List<Result> allResultsList = resultService.findAll();
        
        //prepare lists to hold smaller amounts of student-course and teacher-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>();
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>();
        List<Result> smallerResultsList = new ArrayList<Result>();
        
        for (StudentCourse studentCourse : allStudentCourseList) { //check every student course combo
            if (studentCourse.getCourseID() == targetCourseID) { //if the student-course combo has the same course id as selectedCourse
                //find students registered to the course you are looking for
                smallerStudentCourseList.add(studentCourse); //put it on the list
            }
        }
        
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getCourseID() == targetCourseID) { //if the teacher-course combo has the same course id as selectedCourse
                //find teachers registered to the course you are looking for
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        for (Result result : allResultsList) { //check all results
            if (result.getCourseID() == targetCourseID) { //if the result has the same course id as selectedCourse
                //find results based on the course you are looking for
                smallerResultsList.add(result); //put it on the list
            }
        }
        
         ModelAndView model = new ModelAndView("updateFormForCourse"); //prepare the updateFormForCourse page
         model.addObject("selectedCourse", selectedCourse); //link to the course you want
         model.addObject("selectStudentCourseList", smallerStudentCourseList); //link to modified studentCourse List
         model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //link to modified teacherCourse List
         model.addObject("selectedResultList", smallerResultsList); //link to modified result List
         
         return model; //take user to updateFormForCourse.jsp page
    } 
    
    
    //Create Result tab => gotoCreateResultPage() => createResult.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoCreateResultPage", method = RequestMethod.GET)
    public ModelAndView gotoCreateResultPage() {
        //create Results needs a student list and a course list
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        //prepare a list of all students and all courses
        List<Student> studentList = studentService.findAll();
        List<Course> courseList = courseService.findAll();

        ModelAndView model; //prepare a model and view

        if (studentList.isEmpty()) { //you cannot make a result with no students
            model = new ModelAndView("createStudent"); //make a student if there are none
        } else if (courseList.isEmpty()) { //you cannot make a result with no courses
            model = new ModelAndView("createCourse"); //make a course if there are none
        } else { //if there is at least ONE student AND ONE course in the system
            model = new ModelAndView("createResult"); //you can make a result
            model.addObject("studentList", studentList); //adds student list to page
            model.addObject("courseList", courseList); //adds course list to page
        }
        return model; //take the user to whichever page is necessary
    }
    
    //All Results tab => gotoAllResultsPage() => results.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllResultsPage", method = RequestMethod.GET)
    public ModelAndView gotoAllResultsPage() {
        //if you want to look at all the results in the system so far
        ResultService resultService = new ResultService();
        List<Result> results = resultService.findAll(); //prepare a list of all result entries
        
        ModelAndView model = new ModelAndView("results"); //prepare the results page
        model.addObject("resultList", results); //connect the result list to the results.jsp page
        return model; //take the user to the results.jsp page
    }
    
    
    //Create Student tab => gotoCreateStudentPage() => createStudent.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoCreateStudentPage", method = RequestMethod.GET)
    public ModelAndView gotoCreateStudentPage() {
        ModelAndView model = new ModelAndView("createStudent"); //prepare createStudent page
        return model; //take user to createStudent page
    }

    //All Students Tab => gotoAllStudentsPage() => students.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllStudentsPage", method = RequestMethod.GET)
    public ModelAndView gotoAllStudentsPage() {
        StudentService studentService = new StudentService(); //provides all students
        ModelAndView model = new ModelAndView("students"); //prepare the students page
        List<Student> students = studentService.findAll(); //prepare student list
        model.addObject("studentList", students); //link student list to page
        return model; //take user to students.jsp page
    }

    //Add Student to Course tab => gotoAddStudentToCourse() => addStudentToCourse.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAddStudentToCourse", method = RequestMethod.GET)
    public ModelAndView gotoAddStudentToCourse() {
        StudentService studentService = new StudentService(); //provide the student
        CourseService courseService = new CourseService(); //provides the course

        ModelAndView model;

        List<Student> studentList = studentService.findAll(); //prepare student list
        List<Course> courseList = courseService.findAll(); //provide course list

        if (studentList.isEmpty()) { //if there are no student for registration
            model = new ModelAndView("createStudent"); //prepare create student page
        } else if (courseList.isEmpty()) { //if there are no courses to egister to
            model = new ModelAndView("createCourse"); //prepare create course page
        } else { //if at least one student and course exist
            model = new ModelAndView("addStudentToCourse"); //prepare the add student to course page
            model.addObject("studentList", studentList); //adds student list to page
            model.addObject("courseList", courseList); //adds course list to page
        }
        return model; //take user to the appropriate page
    }
    
    //Edit Student button => gotoUpdateStudentForm() => updateFormForStudent.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoUpdateStudentForm/{studentid}", method = RequestMethod.GET)
    public ModelAndView gotoUpdateStudentForm(@PathVariable("studentid") int studentID) {
        //prepare services
        StudentService studentService = new StudentService(); //to get the student
        StudentCourseService scs = new StudentCourseService(); //to get the student's courses
        ResultService resultService = new ResultService(); //to get the student's results
        
        Student selectedStudent = studentService.findById(studentID); //get a specific student
        
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

    //Student Details Button => gotoStudentDetailsPage() => detailsStudent.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoStudentDetailsPage/{studentid}", method = RequestMethod.GET)
    public ModelAndView gotoStudentDetailsPage(@PathVariable("studentid") int studentID) {
        //prepare services
        StudentService studentService = new StudentService();
        StudentCourseService scs = new StudentCourseService();
        ResultService resultService = new ResultService();
        
        Student selectedStudent = studentService.findById(studentID); //get the specific student object through its id code, the student for the details page
        
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
        return model;//take user to detailsStudent.jsp page
    }    
    
    //All Student-Courses Tab => gotoAllStudentCoursesPage() => studentCourses.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllStudentCoursesPage", method = RequestMethod.GET)
    public ModelAndView gotoAllStudentCoursesPage() {
        StudentCourseService scs = new StudentCourseService(); //provides all studentCourses
        ModelAndView model = new ModelAndView("studentCourses"); //prepare the studentCourses page
        List<StudentCourse> studentCourseList = scs.findAll(); //prepare studentCourse list
        model.addObject("studentCourseList", studentCourseList); //link studentCourse list to page
        return model; //take user to studentCourses.jsp page
    }
    
    
    //Create Teacher Tab => gotoCreateTeacherPage() => createTeacher.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoCreateTeacherPage", method = RequestMethod.GET)
    public ModelAndView gotoCreateTeacherPage() {
        ModelAndView model = new ModelAndView("createTeacher"); //prepare createTeacher page
        return model; //take user to createTeacher page
    }
    
    //All Teachers Tab => gotoAllTeachersPage() => teachers.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllTeachersPage", method = RequestMethod.GET)
    public ModelAndView gotoAllTeachersPage() {
        TeacherService teacherService = new TeacherService(); //gives the list of teachers
        ModelAndView model = new ModelAndView("teachers"); //prepare the teachers.jsp page  
        List<Teacher> teachers = teacherService.findAll(); //prepare the list of al teachers
        model.addObject("teacherList", teachers); //add the teachers list to page     
        return model; //take the user to teachers.jsp page
    }  

    //Add Teacher to Course => gotoAddTeacherToCourse() => addTeacherToCourse.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAddTeacherToCourse", method = RequestMethod.GET)
    public ModelAndView gotoAddTeacherToCourse() {
        //prepare services
        TeacherService teacherService = new TeacherService(); //provides the teachers 
        CourseService courseService = new CourseService(); //provides the courses
        
        ModelAndView model;
              
        List<Teacher> teacherList = teacherService.findAll(); //prepare list of all teachers
        List<Course> courseList = courseService.findAll(); //prepare list of all courses
        
        if (teacherList.isEmpty()) { //if there are no teachers to register with a course
            model = new ModelAndView("createTeacher"); //prepare the create teacher page
        } else if (courseList.isEmpty()) { //if there are no courses for the teacher to teach
            model = new ModelAndView("createCourse"); //prepare the create course page
        } else { //if there is at least one teacher and course available
            model = new ModelAndView("addTeacherToCourse"); //prepare the add teacher to course page
            model.addObject("teacherList", teacherList); //adds teacher list to page
            model.addObject("courseList", courseList); //adds course list to page
        }
        return model; //take the user to the appropriate page
    }  
    
    //Teacher Details button => gotoTeacherDetailsPage() => detailsTeacher.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoTeacherDetailsPage/{teacherid}", method = RequestMethod.GET)
    public ModelAndView gotoTeacherDetailsPage(@PathVariable("teacherid") int targetID) {
        //prepare services
        TeacherService teacherService = new TeacherService(); //to provide the teachers
        TeacherCourseService tcs = new TeacherCourseService(); //to develop the list of courses the teacher teaches
        
        Teacher selectedTeacher = teacherService.findById(targetID); //get the specific teacher object through its id code, the teacher for the details page
        
        //prepare lists of all teacher-course combos
        List<TeacherCourse> allTeacherCourseList = tcs.findAll();
        
        //prepare lists to hold smaller amounts of teacher-course combos 
        List<TeacherCourse> smallerTeacherCourseList = new ArrayList<TeacherCourse>(); //to provide the specific teacher courses
        
        //find courses tied to the specific teacher
        for (TeacherCourse teacherCourse : allTeacherCourseList) { //check every teacher course combo
            if (teacherCourse.getTeacherID()== targetID) { //if the teacher-course combo has the same teacherid id as selectedTeacher
                //gets all the courses the teacher teaches
                smallerTeacherCourseList.add(teacherCourse); //put it on the list
            }
        }
        
        ModelAndView model = new ModelAndView("detailsTeacher"); //prepare the detailsTeacher page
        model.addObject("selectedTeacher", selectedTeacher); //add the teacher object to the model
        model.addObject("selectTeacherCourseList", smallerTeacherCourseList); //add the modified teacher-course list
        return model; //take user to detailsTeacher.jsp page
    }    
    
    //Edit Teacher button => gotoUpdateTeacherForm() => updateFormForTeacher.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoUpdateTeacherForm/{teacherid}", method = RequestMethod.GET)
    public ModelAndView gotoUpdateTeacherForm(@PathVariable("teacherid") int teacherID) {
        //prepare services
        TeacherService teacherService = new TeacherService(); //to provide the teacher
        TeacherCourseService tcs = new TeacherCourseService(); //to develop the list of courses the teacher teaches
        
        Teacher selectedTeacher = teacherService.findById(teacherID); //get the specific teacher object through its id code, the teacher for the update page
        
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
    
    //All Teacher-Courses Tab => gotoAllTeacherCoursesPage() => teacherCourses.jsp
    @RequestMapping(value = "/DisplayGradesController/gotoAllTeacherCoursesPage", method = RequestMethod.GET)
    public ModelAndView gotoAllTeacherCoursesPage() {
        TeacherCourseService tcs = new TeacherCourseService(); //gives the list of teacherCourses
        ModelAndView model = new ModelAndView("teacherCourses"); //prepare the teacherCourse.jsp page  
        List<TeacherCourse> teacherCourseList = tcs.findAll(); //prepare the list of all teacherCourses
        model.addObject("teacherCourseList", teacherCourseList); //add the teacherCourse list to page     
        return model; //take the user to teacherCourse.jsp page
    }
}
