/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.Course;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface CourseDAO {

    public void create(Course course);

    public void update(Course course);

    public void delete(int courseID);

    public Course findByID(int targetCourseID);

    public List<Course> findAll();
}
