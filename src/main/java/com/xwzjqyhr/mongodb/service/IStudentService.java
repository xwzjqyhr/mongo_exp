package com.xwzjqyhr.mongodb.service;

import com.xwzjqyhr.mongodb.pojo.Student;

import java.util.List;

public interface IStudentService {

    void insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String id);
    List<Student> findAllStudent();
    List<Student> findStudentByMaxAge(int maxAge);
    List<Student> findStudentByMaxAgeAndDname(int maxAge,String dname);
    List<Student> findStudentNameAgeColume();
    List<Student> findStudentNameAgeColumeByMaxAge(int aga);

}
