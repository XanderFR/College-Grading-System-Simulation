/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import java.util.List;
import classes.TeacherCourse;

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
public class TeacherCourseDAOImp implements TeacherCourseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TeacherCourse tc) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(tc);
        tx.commit();
        session.close();
    }

    @Override
    public TeacherCourse findByID(int targetTCID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        TeacherCourse teacherCourse = (TeacherCourse) session.get(TeacherCourse.class, targetTCID);
        tx.commit();
        session.close();
        return teacherCourse;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TeacherCourse> findAll() {
       Session session = this.sessionFactory.openSession();
        List<TeacherCourse> teacherCourses = session.createQuery("from TeacherCourse").list();
        session.close();
        return teacherCourses;
    }

    @Override
    public void update(TeacherCourse tc) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(tc);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int tcID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        TeacherCourse teacherCourse = (TeacherCourse) session.get(TeacherCourse.class, tcID);
        session.delete(teacherCourse);
        tx.commit();
        session.close(); 
    }      
}
