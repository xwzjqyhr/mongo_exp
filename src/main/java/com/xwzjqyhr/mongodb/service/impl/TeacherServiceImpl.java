package com.xwzjqyhr.mongodb.service.impl;


import com.xwzjqyhr.mongodb.pojo.Teacher;
import com.xwzjqyhr.mongodb.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void deleteTeacher(String _id) {
        Query query=new Query(Criteria.where("_id").is(_id));
        long count = mongoTemplate.remove(query, Teacher.class).getDeletedCount();
    }

    public List<Teacher> findTeacherByContidion(Integer minAge,  String sex, String dName) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(sex != null)
            criteria.and("sex").is(sex);
        if(minAge != null)
            criteria.and("age").gt(minAge);
        if(dName != null)
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
