/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import classes.*;
import javax.persistence.Table;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */

@Entity
@Table(name = "FPTEACHERCOURSE")
public class TeacherCourse {
        
    @Id
    @GeneratedValue
    private int teacherCourseID;
    private int teacherID;
    private String teacherFirstName;
    private String teacherLastName;
    private int courseID;
    private String courseCode;
    private String courseName;

    public TeacherCourse() {
    }

    public TeacherCourse(Teacher teacher, Course course) {
        this.teacherID = teacher.getTeacherID();
        this.teacherFirstName = teacher.getFirstName();
        this.teacherLastName = teacher.getLastName();
        this.courseID = course.getCourseID();
        this.courseCode = course.getCourseCode();
        this.courseName = course.getCourseName();
    }

    public int getTeacherCourseID() {
        return teacherCourseID;
    }

    public void setTeacherCourseID(int teacherCourseID) {
        this.teacherCourseID = teacherCourseID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
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
    
    public String getTeacherFullName() {
        String nameStr = String.format("%s %s", teacherFirstName, teacherLastName);
        return nameStr;
    }
    
}
