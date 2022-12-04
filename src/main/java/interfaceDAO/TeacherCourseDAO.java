/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.TeacherCourse;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface TeacherCourseDAO {
    
    public void create(TeacherCourse tc);

    public void update(TeacherCourse tc);

    public void delete(int tcID);

    public TeacherCourse findByID(int targetTCID);

    public List<TeacherCourse> findAll();
        
}
