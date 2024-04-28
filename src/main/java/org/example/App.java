package org.example;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
        StudentDao studentDao = context.getBean("studentDaoImpl",StudentDaoImpl.class);
        System.out.println("application started");


        //writing insert query
        /*
           One way to write query but this is not industry standard
        String insertQuery1 = "insert into Student values(?,?,?)";
        int result = jdbcTemplate.update(insertQuery1,2,"Kartik","Bangalore");
        */
        /*
            if you run this query again then you will get error because id with 4 already exists
        Student student = new Student(4,"raman","Noida");
        int result = studentDao.insert(student);
        System.out.println("Number of rows updated... " + result);

        */
        Student student1 = new Student(4,"Naman","Noida");
        int result2 = studentDao.update(student1);
        System.out.println("Number of rows updated after update operation... " + result2);

        int result3 = studentDao.delete(student1);
        System.out.println("Number of rows updated after deletion operation... " + result3);

        Student studentResult1 = studentDao.getStudent(2);
        System.out.println("Student found with id 2 = " + studentResult1);

        List<Student> students = studentDao.getAllStudent();
        students.forEach((s)->{
            System.out.println(s);
        });
    }
}
