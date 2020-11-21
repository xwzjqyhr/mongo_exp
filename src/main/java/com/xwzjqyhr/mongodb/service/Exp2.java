package com.xwzjqyhr.mongodb.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xwzjqyhr.mongodb.pojo.Student;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Exp2 {
    @Autowired
    private MongoTemplate mongoTemplate;


    void findAgeLower20() {
//        Query query = new Query();
        List<Student> students = mongoTemplate.findAll(Student.class);
        System.out.println(students);
    }
}
