package org.example.dao;

import org.example.entity.Student;
import org.example.rowMappers.StudentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Student getStudent(Integer id) {
        String selectQuery = "select * from student where id = ?";
        Student student = new Student();
        /*
            Implementing rowMapper using new Class StudentRowMapper
            Optional<Student> studentFound = Optional.ofNullable(Optional.ofNullable(jdbcTemplate.queryForObject(selectQuery,
            new StudentRowMapper(), id)).orElse(null));
        */
        //Implementing rowMapper using lambda expression
        Optional<Student> studentFound = Optional.ofNullable(Optional.ofNullable(jdbcTemplate.queryForObject(selectQuery, (ResultSet res, int rowNumbers)->{
            student.setId(res.getInt("id"));
            student.setName(res.getString("name"));
            student.setCity(res.getString("city"));
            return student;
        }, id)).orElse(null));
        // it's better to use a separate class, because we have to implement everytime this code everywhere. With Earlier solution
        // we can pass the object everywhere. Code duplicacy reduces
        return studentFound.get();
    }

    @Override
    public List<Student> getAllStudent() {
        String query = "select * from student";
        List<Student> students = jdbcTemplate.query(query,new StudentRowMapper());
        return students;
    }
}