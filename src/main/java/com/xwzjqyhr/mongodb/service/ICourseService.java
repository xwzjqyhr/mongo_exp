package com.xwzjqyhr.mongodb.service;

import com.xwzjqyhr.mongodb.pojo.Course;

import java.util.List;

public interface ICourseService {

    void deleteCourse(String id);
    void insertCourse(Course course);
    void updateCourse(Course course);
    List<Course> findAllCourse();
    List<Course> findNameColumeByCid(String Cid);
}
