/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceDAO;

import classes.Student;
import java.util.List;

/**
 *
 * @author Alexander Dave Flores Respicio 
 */
public interface StudentDAO {
    
    public void create(Student student);

    public void update(Student student);

    public void delete(int studentID);

    public Student findByID(int targetStudentID);

    public List<Student> findAll();
    
}
