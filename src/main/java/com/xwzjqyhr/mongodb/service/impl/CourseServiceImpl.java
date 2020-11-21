package com.xwzjqyhr.mongodb.service.impl;

import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void deleteCourse(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        long count = mongoTemplate.remove(query, Course.class).getDeletedCount();
    }

    @Override
    public List<Course> findAllCourse() {
        List<Course> courses = mongoTemplate.findAll(Course.class);
        return courses;
    }

    @Override
    public List<Course> findNameColumeByCid(String cid) {

        Query query = new Query();

        query.fields().include("name");
        Criteria criteria = new Criteria();
        criteria.and("cid").is(cid);
        query.addCriteria(criteria);
        List<Course> courseNames =  mongoTemplate.find(query,Course.class);
        return courseNames;
    }

    @Override
    public void insertCourse(Course course) {
        mongoTemplate.insert(course);
    }

    @Override
    public void updateCourse(Course course) {
        mongoTemplate.insert(course);
    }
}










