/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.Teacher;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface TeacherDAO {

    public void create(Teacher teacher);

    public void update(Teacher teacher);

    public void delete(int teacherID);

    public Teacher findByID(int targetTeacherID);

    public List<Teacher> findAll();

}
