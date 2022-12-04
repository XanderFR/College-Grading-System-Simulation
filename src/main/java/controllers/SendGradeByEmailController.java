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
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import service.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class SendGradeByEmailController {

    @RequestMapping(value = "/SendGradeByEmailController/gotoStudentTranscriptEmailPage/{studentid}", method = RequestMethod.GET)
    public ModelAndView gotoStudentTranscriptEmailPage(@PathVariable("studentid") int studentID) {
        //prepare services
        StudentService studentService = new StudentService(); //to provide the student's information
        StudentCourseService scs = new StudentCourseService(); //to get the courses the student is registered
        ResultService resultService = new ResultService(); //to get the results the student has so far;

        Student selectedStudent = studentService.findById(studentID); //get the student whose details you want

        //prepare lists of all student-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll(); //list of all possible student-course combos in database
        List<Result> allResultsList = resultService.findAll(); //list of all possible results in database

        //prepare lists to hold smaller amounts of student-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>(); //prepare a list to hold studentCourses tied to specific student
        List<Result> smallerResultsList = new ArrayList<Result>(); //prepare a list to hole results tied to specific student

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

        ModelAndView model = new ModelAndView("studentTranscriptEmail"); //prepare the studentTranscriptEmail page
        model.addObject("selectedStudent", selectedStudent); //add the student object to the model
        model.addObject("selectStudentCourseList", smallerStudentCourseList); //add the modified student-course list
        model.addObject("selectedResultList", smallerResultsList); //add the modified result list
        return model;//take user to studentTranscriptEmail.jsp page
    }

    @RequestMapping(value = "/SendGradeByEmailController/sendEmail/{studentid}", method = RequestMethod.POST)
    public ModelAndView sendEmail(@PathVariable("studentid") int studentID,
            @RequestParam("emailUsername") String emailUsername,
            @RequestParam("emailPassword") String emailPassword,
            @RequestParam("emailSender") String emailSender,
            @RequestParam("emailRecipient") String emailRecipient,
            @RequestParam("emailMessage") String emailMessage) {
        //prepare services
        StudentService studentService = new StudentService(); //to provide the student's information
        StudentCourseService scs = new StudentCourseService(); //to get the courses the student is registered
        ResultService resultService = new ResultService(); //to get the results the student has so far;

        Student selectedStudent = studentService.findById(studentID); //get the student whose details you want

        //prepare lists of all student-course combos and results
        List<StudentCourse> allStudentCourseList = scs.findAll(); //list of all possible student-course combos in database
        List<Result> allResultsList = resultService.findAll(); //list of all possible results in database

        //prepare lists to hold smaller amounts of student-course combos and results
        List<StudentCourse> smallerStudentCourseList = new ArrayList<StudentCourse>(); //prepare a list to hold studentCourses tied to specific student
        List<Result> smallerResultsList = new ArrayList<Result>(); //prepare a list to hole results tied to specific student

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

        //THE EMAIL COMPONENT
        //LOGIN CREDENTIALS
        final String username = emailUsername;
        final String password = emailPassword;

        //PROPERTIES
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //SESION CODE
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        ModelAndView model; //prepare a ModelAndView to go to either email success or email fail
        
        try {
            //PREPARE THE EMAIL
            Message message = new MimeMessage(session); //prepare email message
            
            message.setFrom(new InternetAddress(emailSender)); //prepare the sender of the email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailRecipient)); //prepare the recipient of the email
            
            message.setSubject("DAOWebApplicationTheFinalProject Student Transcript"); //prepare the subject of the email
            
            StringBuilder generalMessageStringBuilder = new StringBuilder(); //StringBuilder to hold the entire content of message
            
            //PREPARE THE EMAIL MESSAGE
            //INTRO
            String greeting = String.format("Dear %s %s,\n\n", selectedStudent.getFirstName(), selectedStudent.getLastName()); //the "dear" line
            generalMessageStringBuilder.append(greeting); //attach the "dear" line to the beginning of the generalMessageStringBuilder
            
            String introLine = String.format("This email contains a message from the school administration, "
                    + "your personal information, your current course listings, and your current result listings.\n\n"); //a notification to the nature
            //of the email message
            generalMessageStringBuilder.append(introLine); //attach this introductiory line to the generalMessageStringBuilder
            
            //BODY
            String schoolMessage = String.format("%s\n\n", emailMessage); //the message from the school, attached after introLine
            generalMessageStringBuilder.append(schoolMessage); //atach the school message to the generalMessageStringBuilder
            
            //PREPARE STUDENT INFO PART
            String studentInfoString = String.format("Student Profile\n"
                    + "Student ID Number: %d\n"
                    + "Student First Name: %s\n"
                    + "Student Last Name: %s\n"
                    + "Address: %s\n"
                    + "City: %s\n"
                    + "Country: %s\n"
                    + "Phone Number: %d\n"
                    + "Major: %s\n\n", selectedStudent.getStudentID(), selectedStudent.getFirstName(), selectedStudent.getLastName(), 
                    selectedStudent.getAddress(), selectedStudent.getCity(), selectedStudent.getCountry(), selectedStudent.getPhoneNumber(), 
                    selectedStudent.getMajor()); //prepare the student profile string
            generalMessageStringBuilder.append(studentInfoString); //attach the student profile to generalMessageStringBuilder
                    
            //PREPARE COURSES LIST PART
            StringBuilder coursesStringBuilder = new StringBuilder(); //this stringbuilder will hold a list of all courses connected to target student
            
            String courseIntroLine = String.format("Current Course listings for %s %s:\n\n", selectedStudent.getFirstName(), selectedStudent.getLastName());
            //the opening line for the courses list
            coursesStringBuilder.append(courseIntroLine); //attach courseIntroLine to generalMessageStringBuilder
            
            for (StudentCourse studentCourse : smallerStudentCourseList) { //go through all studentCourses connected to the target student
                String studentCourseString = String.format("Student ID: %d\n"
                        + "Student First Name: %s\n"
                        + "Student Last Name: %s\n"
                        + "Course ID: %d\n"
                        + "Course Code: %s\n"
                        + "Course Name: %s\n\n", studentCourse.getStudentID(), studentCourse.getStudentFirstName(), studentCourse.getStudentLastName(),
                        studentCourse.getCourseID(), studentCourse.getCourseCode(), studentCourse.getCourseName()); //format the info into a block
                
                coursesStringBuilder.append(studentCourseString); //attach the course info block to the coursesStringBuilder
            }
            //after making a long string list of course info blocks
            generalMessageStringBuilder.append(coursesStringBuilder.toString()); //attach the long course info block list string to generalMessageStringBuilder
            
            //PREPARE RESULTS LIST PART
            StringBuilder resultsStringBuilder = new StringBuilder(); //a stringbuilder to hold a results list tied to target student
            
            String resultsIntroLine = String.format("Current Grade Results for %s %s:\n\n", selectedStudent.getFirstName(), selectedStudent.getLastName());
            //the intro line to the student results list
            resultsStringBuilder.append(resultsIntroLine); //add the resultsIntroLine to the resultsStringBuilder
            
            for (Result result : smallerResultsList) { //go through every result entry in smallerResultsList
                String resultString = String.format("Student ID: %d\n"
                        + "Student First Name: %s\n"
                        + "Student Last Name: %s\n"
                        + "Course ID: %d\n"
                        + "Course Name: %s\n"
                        + "Session Number: %d\n"
                        + "Mid-Term Mark: %d\n"
                        + "Finals Mark: %d\n\n", result.getStudentID(), result.getStudentFirstName(), result.getStudentLastName(), result.getCourseID(),
                        result.getCourseName(), result.getSessionNumber(), result.getMidTermMark(), result.getFinalMark()); //create a block about result info
                
                resultsStringBuilder.append(resultString); //add the result info block string to the resultsStringBuilder
            }
            //after making a long string list of result info blocks 
            generalMessageStringBuilder.append(resultsStringBuilder.toString()); //add the result block list string to the generalMessageStringBuilder
            
            String closingLine = String.format("Thank you for being a student here. Have a nice day."); //this is the closing line of the email
            generalMessageStringBuilder.append(closingLine); //add the closing line to the generalMessageStringBuilder
            
            message.setText(generalMessageStringBuilder.toString()); //turn the contents of generalMessageStringBuilder into a string and add it to the email
            
            Transport.send(message); //send the email
            
            model = new ModelAndView("emailSuccess"); //prepare the emailSuccess page
            model.addObject("selectedStudent", selectedStudent); //add the student object to the model
            model.addObject("emailRecipient", emailRecipient);
            return model;//take user to emailSuccess.jsp

        } catch (MessagingException e) { //if something goes wrong during the sending of the email
            //throw new RuntimeException(e);
            model = new ModelAndView("emailFail"); //prepare the emailFail page
            model.addObject("selectedStudent", selectedStudent); //add the student object to the model
            return model;//take user to emailFail.jsp
        }    
    }
}
