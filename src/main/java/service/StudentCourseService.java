/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.StudentCourse;
import interfaceDAO.StudentCourseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class StudentCourseService {

    private StudentCourseDAO studentCourseDAO;

    public StudentCourseService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.studentCourseDAO = context.getBean(StudentCourseDAO.class);
    }

    public void create(StudentCourse sc) {
        this.studentCourseDAO.create(sc);
    }

    public void update(StudentCourse sc) {
        this.studentCourseDAO.update(sc);
    }

    public StudentCourse findByID(int targetSCID) {
        StudentCourse studentCourse = this.studentCourseDAO.findByID(targetSCID);
        return studentCourse;
    }

    public void delete(int scID) {
        this.studentCourseDAO.delete(scID);
    }

    public List<StudentCourse> findAll() {
        List<StudentCourse> studentCourses = this.studentCourseDAO.findAll();
        return studentCourses;
    }
       
}
