/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.Result;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface ResultDAO {
    
    public void create(Result result);

    public void update(Result result);

    public void delete(int resultID);

    public Result findByID(int targetResultID);

    public List<Result> findAll();
    
}
