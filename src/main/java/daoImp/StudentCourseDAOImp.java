/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import java.util.List;
import classes.StudentCourse;

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
public class StudentCourseDAOImp implements StudentCourseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(StudentCourse sc) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(sc);
        tx.commit();
        session.close();
    }

    @Override
    public StudentCourse findByID(int targetSCID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        StudentCourse studentCourse = (StudentCourse) session.get(StudentCourse.class, targetSCID);
        tx.commit();
        session.close();
        return studentCourse;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<StudentCourse> findAll() {
       Session session = this.sessionFactory.openSession();
        List<StudentCourse> studentCourses = session.createQuery("from StudentCourse").list();
        session.close();
        return studentCourses;
    }

    @Override
    public void update(StudentCourse sc) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(sc);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int scID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        StudentCourse studentCourse = (StudentCourse) session.get(StudentCourse.class, scID);
        session.delete(studentCourse);
        tx.commit();
        session.close(); 
    }      
}
