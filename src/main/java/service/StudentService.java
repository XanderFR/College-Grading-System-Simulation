/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.Student;
import interfaceDAO.StudentDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.studentDAO = context.getBean(StudentDAO.class);
    }

    public void create(Student student) {
        this.studentDAO.create(student);
    }

    public void update(Student student) {
        this.studentDAO.update(student);
    }

    public Student findById(int id) {
        Student student = this.studentDAO.findByID(id);
        return student;
    }

    public void delete(int id) {
        this.studentDAO.delete(id);
    }

    public List<Student> findAll() {
        List<Student> students = this.studentDAO.findAll();
        return students;
    }
     
}
