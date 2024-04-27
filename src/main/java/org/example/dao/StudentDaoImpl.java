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
        String query = "insert into student values(?,?,?)";
        int result = jdbcTemplate.update(query,student.getId(),student.getName(),student.getCity());
        return result;
    }
}
