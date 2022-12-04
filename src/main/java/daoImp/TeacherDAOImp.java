/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;
import java.util.List;
import classes.Teacher;

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
public class TeacherDAOImp implements TeacherDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Teacher teacher) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(teacher);
        tx.commit();
        session.close();
    }

    @Override
    public Teacher findByID(int targetTeacherID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = (Teacher) session.get(Teacher.class, targetTeacherID);
        tx.commit();
        session.close();
        return teacher;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Teacher> findAll() {
       Session session = this.sessionFactory.openSession();
        List<Teacher> teachers = session.createQuery("from Teacher").list();
        session.close();
        return teachers;
    }

    @Override
    public void update(Teacher teacher) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(teacher);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int teacherID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = (Teacher) session.get(Teacher.class, teacherID);
        session.delete(teacher);
        tx.commit();
        session.close(); 
    }        
}
