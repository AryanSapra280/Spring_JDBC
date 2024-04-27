package org.example.dao;

import org.example.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDaoImpl implements StudentDao{
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Student student) {
        String insertQuery = "insert into student values(?,?,?)";
        int result = jdbcTemplate.update(insertQuery,student.getId(),student.getName(),student.getCity());
        return result;
    }

    @Override
    public int update(Student student) {
        String updateQuery = "update student set name = ?, city = ? where id = ?";
        int result = jdbcTemplate.update(updateQuery,student.getName(),student.getCity(),student.getId());
        return result;
    }

    @Override
    public int delete(Student student) {
        String deleteQuery = "delete from student where id = ?";
        int result = jdbcTemplate.update(deleteQuery,student.getId());
        return result;
    }
}
