/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classes.Result;
import interfaceDAO.ResultDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public class ResultService {

    private ResultDAO resultDAO;

    public ResultService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring6.xml");
        this.resultDAO = context.getBean(ResultDAO.class);
    }

    public void create(Result result) {
        this.resultDAO.create(result);
    }

    public void update(Result result) {
        this.resultDAO.update(result);
    }

    public Result findById(int id) {
        Result result = this.resultDAO.findByID(id);
        return result;
    }

    public void delete(int id) {
        this.resultDAO.delete(id);
    }

    public List<Result> findAll() {
        List<Result> results = this.resultDAO.findAll();
        return results;
    }
        
}
