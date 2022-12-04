/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.Course;
import interfaceDAO.CourseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class CourseService {

    private CourseDAO courseDAO;

    public CourseService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.courseDAO = context.getBean(CourseDAO.class);
    }

    public void create(Course course) {
        this.courseDAO.create(course);
    }

    public void update(Course course) {
        this.courseDAO.update(course);
    }

    public Course findById(int id) {
        Course course = this.courseDAO.findByID(id);
        return course;
    }

    public void delete(int id) {
        this.courseDAO.delete(id);
    }

    public List<Course> findAll() {
        List<Course> courses = this.courseDAO.findAll();
        return courses;
    }
    
}
