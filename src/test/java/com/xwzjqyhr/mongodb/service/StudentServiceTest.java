package com.xwzjqyhr.mongodb.service;


import com.alibaba.fastjson.JSON;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Autowired
    StudentServiceImpl studentService;
    @Test
    public void deleteStudent() {
        studentService.deleteStudent("5fb8d96a0372ac783516be6f");
    }
    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setSid("201805301355");
        student.setName("jxy");
        student.setSex("男");
        student.setAge(17);
        student.setBirthday("1996-2-2");
        student.setDName("CS");
        student.setDClass(2008);
        studentService.insertStudent(student);
    }
    @Test
    public void updateStudent() {
        Student student = new Student();
//        student.setId("5fb8da503023f118a59ba857");
        student.setSid("201805301355");
        student.setName("jxy");
        student.setSex("男");
        student.setAge(17);
        student.setBirthday("1996-9-2");
        student.setDName("CS");
        student.setDClass(2008);
        studentService.updateStudent(student);
    }
    @Test
    public void findAllStudent() {
       List<Student> students = studentService.findAllStudent();
        System.out.println(JSON.toJSONString(students));
    }
    @Test
    public void findStudentByMaxAge() {
        List<Student> students = studentService.findStudentByMaxAge(20);
        System.out.println(JSON.toJSONString(students));
    }
    @Test
    public void findStudentByMaxAgeAndDname() {
        List<Student> students = studentService.findStudentByMaxAgeAndDname(20,"CS");
        System.out.println(JSON.toJSONString(students));
    }
    @Test
    public void  findStudentNameAgeColume() {
        List<Student> students = studentService.findStudentNameAgeColume();
        System.out.println(JSON.toJSONString(students));

    }
    @Test
    public void findStudentNameAgeColumeByMaxAge() {
        List<Student> students = studentService.findStudentNameAgeColumeByMaxAge(20);
        System.out.println(JSON.toJSONString(students));
    }
}



