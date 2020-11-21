package com.xwzjqyhr.mongodb.service.impl;

import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Student> findAllStudent() {
        List<Student> students =  mongoTemplate.findAll(Student.class);
//        System.out.println(students);
        return students;
    }

    @Override
    public List<Student> findStudentByMaxAge(int maxAge) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("age").lt(maxAge);
        query.addCriteria(criteria);
        List<Student> students = mongoTemplate.find(query,Student.class);
        return students;
    }

    @Override
    public List<Student> findStudentByMaxAgeAndDname(int maxAge,String dname) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("age").lt(maxAge);
        criteria.and("dname").is(dname);
        query.addCriteria(criteria);
        List<Student> students = mongoTemplate.find(query,Student.class);
        return students;
    }

    @Override
    public List<Student> findStudentNameAgeColume() {
        Query query = new Query();
//      query.fields().equals("name");
      query.fields().include("name"); //包含该字段
      query.fields().include("age"); //包含该字段
//        query.fields().exclude("name");//不包含该字段
        List<Student> students = mongoTemplate.find(query,Student.class);
        return students;
    }

    @Override
    public List<Student> findStudentNameAgeColumeByMaxAge(int maxAge) {
        Query query = new Query();
//      query.fields().equals("name");
        query.fields().include("name"); //包含该字段
        query.fields().include("sex"); //包含该字段
//        query.fields().exclude("name");//不包含该字段
        Criteria criteria = new Criteria();
        criteria.and("age").lt(maxAge);
        query.addCriteria(criteria);

        List<Student> students = mongoTemplate.find(query,Student.class);
        return students;
    }

    @Override
    public void insertStudent(Student student) {
        mongoTemplate.insert(student);
    }

    @Override
    public void updateStudent(Student student) {
        mongoTemplate.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        long count = mongoTemplate.remove(query,Student.class).getDeletedCount();
    }
}



