/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import classes.Student;
import classes.Course;
import javax.persistence.Table;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */

@Entity
@Table(name = "FPRESULT")
public class Result {
    
    @Id
    @GeneratedValue
    private int resultID;
    private int studentID;
    private String studentFirstName;
    private String studentLastName;
    private int courseID;
    private String courseName;
    private int sessionNumber;
    private int midTermMark;
    private int finalMark;

    public Result() {
    }

    public Result(int studentID, String studentFirstName, String studentLastName, int courseID, String courseName, int sessionNumber, int midTermMark, int finalMark) {
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.courseID = courseID;
        this.courseName = courseName;
        this.sessionNumber = sessionNumber;
        this.midTermMark = midTermMark;
        this.finalMark = finalMark;
    }

    public Result(int resultID, int studentID, String studentFirstName, String studentLastName, int courseID, String courseName, int sessionNumber, int midTermMark, int finalMark) {
        this.resultID = resultID;
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.courseID = courseID;
        this.courseName = courseName;
        this.sessionNumber = sessionNumber;
        this.midTermMark = midTermMark;
        this.finalMark = finalMark;
    }
    
    public Result(Student student, Course course, int sessionNumber, int midTermMark, int finalMark) {
        this.studentID = student.getStudentID();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.courseID = course.getCourseID();
        this.courseName = course.getCourseName();
        this.sessionNumber = sessionNumber;
        this.midTermMark = midTermMark;
        this.finalMark = finalMark;
    }
    
    public Result(int resultID, Student student, Course course, int sessionNumber, int midTermMark, int finalMark) {
        this.resultID = resultID;
        this.studentID = student.getStudentID();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.courseID = course.getCourseID();
        this.courseName = course.getCourseName();
        this.sessionNumber = sessionNumber;
        this.midTermMark = midTermMark;
        this.finalMark = finalMark;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public int getMidTermMark() {
        return midTermMark;
    }

    public void setMidTermMark(int midTermMark) {
        this.midTermMark = midTermMark;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(int finalMark) {
        this.finalMark = finalMark;
    }

    @Override
    public String toString() {
        return "Result{" + "resultID=" + resultID + ", studentID=" + studentID + ", studentFirstName=" + studentFirstName + ", studentLastName=" + studentLastName + ", courseID=" + courseID + ", courseName=" + courseName + ", sessionNumber=" + sessionNumber + ", midTermMark=" + midTermMark + ", finalMark=" + finalMark + '}';
    }
    
    public String getStudentString() {
        String studentStr = String.format("%d %s %s", studentID, studentFirstName, studentLastName);
        return studentStr;
    }
    
    public String getCourseString() {
        String courseStr = String.format("%d %s", courseID, courseName);
        return courseStr;
    }
    
}
