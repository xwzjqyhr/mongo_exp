package com.xwzjqyhr.mongodb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.pojo.StudentExp6;
import com.xwzjqyhr.mongodb.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Student> findStudentByContidion(Integer maxAge,String dname) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(maxAge != null) {
            criteria.and("age").lt(maxAge);
            query.addCriteria(criteria);
        }
        if(dname != null) {
            criteria.and("dname").is(dname);
        }
        List<Student> students = mongoTemplate.find(query,Student.class);
        return students;
    }
    @Override
    public List<Student> findNameAgeColumeByContidion(Integer maxAge) {
        Query query = new Query();
//      query.fields().equals("name");
        query.fields().include("name"); //包含该字段
        query.fields().include("sex"); //包含该字段
//        query.fields().exclude("name");//不包含该字段
        Criteria criteria = new Criteria();
        if(maxAge != null) {
            criteria.and("age").lt(maxAge);
        }
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

    public List<Map> getMaxElementsByAvgScore (int maxElements) {
        // aggregation 意为聚合
        Aggregation aggregation = Aggregation.newAggregation (
            //做联合
            Aggregation.lookup("student_course_exp6", "sid", "sid", "student_course_as"),
            Aggregation.unwind("student_course_as"),
            Aggregation.project("sid","sname","sex").and("student_course_as.score").as("score"),
            Aggregation.group("sid","sname","sex")
                        .avg("score").as("avg_score"),
                Aggregation.sort(Sort.Direction.DESC,"avg_score"),
                Aggregation.limit(maxElements)
        );

        List<Map> result =  mongoTemplate.aggregate(aggregation,"student_exp6",Map.class).getMappedResults();
        System.out.println(JSONObject.toJSONString(result));

        return result;
    }

    public List<Map>  getMaxElementsByCourseCount(int maxElements) {
        Aggregation aggregation = Aggregation.newAggregation (
                //做联合
                Aggregation.lookup("student_course_exp6", "sid", "sid", "student_course_as"),
                Aggregation.unwind("student_course_as"),
                Aggregation.project("sid","sname","sex").and("student_course_as.cid").as("cid"),
                Aggregation.group("sid","sname","sex").count().as("course_count"),
                Aggregation.sort(Sort.Direction.DESC,"course_count"),
                Aggregation.limit(maxElements)
        );
        List<Map> result =  mongoTemplate.aggregate(aggregation,"student_exp6",Map.class).getMappedResults();
        System.out.println(JSONObject.toJSONString(result));
        return result;
    }

    public List<StudentExp6>  getEveryoneMaxScoreCourse() {
        List<StudentExp6> studentList = mongoTemplate.findAll(StudentExp6.class);
        System.out.println(JSONObject.toJSONString(studentList));
        int length = studentList.size();
        for (int i = 0 ;i < length; i++) {
            Aggregation aggregation = Aggregation.newAggregation (
                    Aggregation.project("sid","score"),
                    Aggregation.match(Criteria.where("sid").is(studentList.get(i).getSid())),//match必须在group之前
                    Aggregation.group("sid").
                            max("score").as("max_score")
            );
            List<Map> result =  mongoTemplate.aggregate(aggregation,"student_course_exp6",Map.class).getMappedResults();
//            System.out.println(JSONObject.toJSONString(result));
            studentList.get(i).setMaxScore(Double.parseDouble(result.get(0).get("max_score").toString()));
        }


        for (int i = 0 ;i < length; i++) {
            if(studentList.get(i).getMaxScore() == null) continue;
            Aggregation aggregation = Aggregation.newAggregation (
                    Aggregation.project("cid","sid","score","cname"),
                    Aggregation.match(Criteria.where("sid").is(studentList.get(i).getSid())),
                    Aggregation.match(Criteria.where("score").is(studentList.get(i).getMaxScore()))
            );
            List<Map> result =  mongoTemplate.aggregate(aggregation,"student_course_exp6",Map.class).getMappedResults();
//            System.out.println(JSONObject.toJSONString(result));
            studentList.get(i).setMaxScoreCourseName(result.get(0).get("cname").toString());
        }


        return studentList;
    }



}



