/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import java.util.List;
import classes.Course;

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
public class CourseDAOImp implements CourseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course course) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(course);
        tx.commit();
        session.close();
    }

    @Override
    public Course findByID(int targetCourseID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Course course = (Course) session.get(Course.class, targetCourseID);
        tx.commit();
        session.close();
        return course;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> findAll() {
       Session session = this.sessionFactory.openSession();
        List<Course> courses = session.createQuery("from Course").list();
        session.close();
        return courses;
    }

    @Override
    public void update(Course course) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(course);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int courseID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Course course = (Course) session.get(Course.class, courseID);
        session.delete(course);
        tx.commit();
        session.close(); 
    }    
}
