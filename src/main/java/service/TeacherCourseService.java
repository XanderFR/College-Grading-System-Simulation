/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.TeacherCourse;
import interfaceDAO.TeacherCourseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class TeacherCourseService {

    private TeacherCourseDAO teacherCourseDAO;

    public TeacherCourseService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.teacherCourseDAO = context.getBean(TeacherCourseDAO.class);
    }

    public void create(TeacherCourse teacherCourse) {
        this.teacherCourseDAO.create(teacherCourse);
    }

    public void update(TeacherCourse teacherCourse) {
        this.teacherCourseDAO.update(teacherCourse);
    }

    public TeacherCourse findByID(int targetTCID) {
        TeacherCourse teacherCourse = this.teacherCourseDAO.findByID(targetTCID);
        return teacherCourse;
    }

    public void delete(int id) {
        this.teacherCourseDAO.delete(id);
    }

    public List<TeacherCourse> findAll() {
        List<TeacherCourse> teacherCourses = this.teacherCourseDAO.findAll();
        return teacherCourses;
    }
       
}
