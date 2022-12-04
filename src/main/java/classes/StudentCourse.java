/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */

@Entity
@Table(name = "FPSTUDENTCOURSE")
public class StudentCourse {
    
    @Id
    @GeneratedValue
    private int studentCourseID;
    private int studentID;
    private String studentFirstName;
    private String studentLastName;
    private int courseID;
    private String courseCode;
    private String courseName;

    public StudentCourse() {
    }

    public StudentCourse(Student student, Course course) {
        this.studentID = student.getStudentID();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.courseID = course.getCourseID();
        this.courseCode = course.getCourseCode();
        this.courseName = course.getCourseName();
    }

    public int getStudentCourseID() {
        return studentCourseID;
    }

    public void setStudentCourseID(int studentCourseID) {
        this.studentCourseID = studentCourseID;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getFullName() {
        String nameStr = String.format("%s %s", studentFirstName, studentLastName);
        return nameStr;
    }
    
    
}
