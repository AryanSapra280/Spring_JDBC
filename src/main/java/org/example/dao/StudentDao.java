package org.example.dao;

import org.example.entity.Student;

import java.util.List;

public interface StudentDao {
    int insert(Student student);
    int update(Student student); // in this we need to update based on the id value. So, a record with this id must be
                                // existing.
    int delete(Student student);
    Student getStudent(Integer id);
    List<Student> getAllStudent();

}
