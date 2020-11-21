package com.xwzjqyhr.mongodb.service;

import com.xwzjqyhr.mongodb.pojo.Teacher;

import java.util.List;

public interface ITeacherService {
    void deleteTeacher(String id);
    void insertTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    List<Teacher> findTeacherByMinAge(int minAge);
    List<Teacher> findTeacherBySex(String sex);
    List<Teacher> findTeacherByDname(String dName);

}
