/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import java.util.List;
import classes.Result;

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
public class ResultDAOImp implements ResultDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Result result) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(result);
        tx.commit();
        session.close();
    }

    @Override
    public Result findByID(int targetResultID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Result result = (Result) session.get(Result.class, targetResultID);
        tx.commit();
        session.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> findAll() {
       Session session = this.sessionFactory.openSession();
        List<Result> results = session.createQuery("from Result").list();
        session.close();
        return results;
    }

    @Override
    public void update(Result result) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(result);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(int resultID) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Result result = (Result) session.get(Result.class, resultID);
        session.delete(result);
        tx.commit();
        session.close(); 
    }   
}
