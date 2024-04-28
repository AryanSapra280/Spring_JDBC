package org.example;

import org.example.configurations.JdbcConfig;
import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean("getJdbcTemplate",JdbcTemplate.class);
        StudentDao studentDao = context.getBean("studentDao",StudentDaoImpl.class);
        System.out.println("application started");


        //writing insert query
        //One way to write query but this is not industry standard
        /*String insertQuery1 = "insert into Student values(?,?,?)";
        int result = jdbcTemplate.update(insertQuery1,21,"Kartik","Bangalore");
        System.out.println("Number of rows updated after insertion... " + result);
        //if you run this query again then you will get error because id with 4 already exists
        Student student = new Student(41,"raman","Noida");
        int result1 = studentDao.insert(student);
        System.out.println("Number of rows updated after insertion... " + result1);
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
