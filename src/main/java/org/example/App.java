package org.example;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

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
        Student student = new Student(4,"raman","Noida");
        int result = studentDao.insert(student);
        System.out.println("Number of rows updated... " + result);

    }
}
