/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import java.util.List;
import classes.Student;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import interfaceDAO.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class StudentDAOImp implements StudentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();
        session.close();
    }

    @Override
    public Student findByID(int targetStudentID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = (Student) session.get(Student.class, targetStudentID);
        tx.commit();
        session.close();
        return student;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> findAll() {
       Session session = this.sessionFactory.openSession();
        List<Student> students = session.createQuery("from Student").list();
        session.close();
        return students;
    }

    @Override
    public void update(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(student);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int studentID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = (Student) session.get(Student.class, studentID);
        session.delete(student);
        tx.commit();
        session.close(); 
    }     
}
