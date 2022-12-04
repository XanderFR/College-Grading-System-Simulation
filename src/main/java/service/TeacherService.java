/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.Teacher;
import interfaceDAO.TeacherDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class TeacherService {

    private TeacherDAO teacherDAO;

    public TeacherService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.teacherDAO = context.getBean(TeacherDAO.class);
    }

    public void create(Teacher teacher) {
        this.teacherDAO.create(teacher);
    }

    public void update(Teacher teacher) {
        this.teacherDAO.update(teacher);
    }

    public Teacher findById(int id) {
        Teacher teacher = this.teacherDAO.findByID(id);
        return teacher;
    }

    public void delete(int id) {
        this.teacherDAO.delete(id);
    }

    public List<Teacher> findAll() {
        List<Teacher> teachers = this.teacherDAO.findAll();
        return teachers;
    }
       
}
