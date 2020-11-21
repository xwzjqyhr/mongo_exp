package com.xwzjqyhr.mongodb.service.impl;


import com.xwzjqyhr.mongodb.pojo.Teacher;
import com.xwzjqyhr.mongodb.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void deleteTeacher(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        long count = mongoTemplate.remove(query, Teacher.class).getDeletedCount();
    }

    @Override
    public List<Teacher> findTeacherByMinAge(int minAge) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("age").gt(minAge);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Teacher.class);
    }

    @Override
    public List<Teacher> findTeacherBySex(String sex) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("sex").is(sex);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Teacher.class);
    }

    @Override
    public List<Teacher> findTeacherByDname(String dName) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("dname").is(dName);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Teacher.class);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        mongoTemplate.insert(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        mongoTemplate.save(teacher);
    }

}
