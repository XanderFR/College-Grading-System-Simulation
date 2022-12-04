/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.StudentCourse;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface StudentCourseDAO {
    
    public void create(StudentCourse sc);

    public void update(StudentCourse sc);

    public void delete(int scID);

    public StudentCourse findByID(int targetSCID);

    public List<StudentCourse> findAll();
        
}
