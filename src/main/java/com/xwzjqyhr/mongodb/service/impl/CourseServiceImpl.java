package com.xwzjqyhr.mongodb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.pojo.StudentCourse;
import com.xwzjqyhr.mongodb.pojo.StudentCourseExp6;
import com.xwzjqyhr.mongodb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Map;

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
    public List<Course> findNameColumeByCid(String fid) {

        Query query = new Query();

        query.fields().include("name");
        Criteria criteria = new Criteria();
        criteria.and("fid").is(fid);
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
        mongoTemplate.save(course);
    }
    public List<StudentCourseExp6> getDistinctByFieldName(String cName) {
        Query query = new Query();
        query.fields().exclude("sid");
        query.fields().exclude("score");
        //只支持返回单一字段，不支持排序
        List<StudentCourseExp6> studentCourseExp6List = mongoTemplate.findDistinct(query,cName,
                StudentCourseExp6.class,StudentCourseExp6.class);
        System.out.println(JSONObject.toJSONString(studentCourseExp6List));
        return studentCourseExp6List;
    }

    //exp6/6
    public List<Map> getStuCountAndAvgScore() {
        Aggregation aggregation = Aggregation.newAggregation (
                //做联合
                Aggregation.lookup("student_course_exp6", "cid", "cid", "student_course_as"),
                Aggregation.unwind("student_course_as"),
                Aggregation.project("cid","cname","credit","hours","property").
                        and("student_course_as.score").as("score"),
                Aggregation.group("cid","cname","credit","hours","property").
                        avg("score").as("avg_score").
                        count().as("course_count")
        );

        List<Map> result =  mongoTemplate.aggregate(aggregation,"course_exp6",Map.class).getMappedResults();
        return result;
    }
    //exp6/7
    public List<Map> getMaxAvgScore(int maxElements) {
        Aggregation aggregation = Aggregation.newAggregation (
                //做联合
                Aggregation.project("cid","cname","credit","hours","property","score"),
                Aggregation.group("cid","cname","credit","hours","property").
                        avg("score").as("avg_score"),
                Aggregation.sort(Sort.Direction.DESC,"avg_score"),
                Aggregation.limit(maxElements)
        );

        List<Map> result =  mongoTemplate.aggregate(aggregation,"student_course_exp6",Map.class).
                getMappedResults();
        return result;
    }
    //exp6/8
    public List<Map> getMaxCourseCount(int maxElements) {
        Aggregation aggregation = Aggregation.newAggregation (
                //做联合
                Aggregation.project("cid","cname","credit","hours","property","score"),
                Aggregation.group("cid","cname","credit","hours","property").count().as("course_count"),
                Aggregation.sort(Sort.Direction.DESC,"course_count"),
                Aggregation.limit(maxElements)
        );

        List<Map> result =  mongoTemplate.aggregate(aggregation,"student_course_exp6",
                Map.class).getMappedResults();
        return result;
    }
    //  exp5/1
    public List<Map> getCoursesBySid(String sid) {
        Criteria criteria = new Criteria();
        criteria.and("sid").is(sid);
        Aggregation aggregation = Aggregation.newAggregation (
                //做联合
                Aggregation.lookup("student_course", "cid", "cid", "student_course_as"),
                Aggregation.unwind("student_course_as"),
                Aggregation.project("cid","name","credit").
                        and("student_course_as.sid").as("sid"),
                Aggregation.match(criteria)
        );
        List<Map> result =  mongoTemplate.aggregate(aggregation,"course",Map.class).getMappedResults();
        return result;
    }
    //  exp5/1
    public void deleteStudentCourse(String cid,String sid) {
        Criteria criteria1 =Criteria.where("cid").is(cid);
        Criteria criteria2 = Criteria.where("sid").is(sid);
        Query query = new Query();
        query.addCriteria(criteria1);
        query.addCriteria(criteria2);
        mongoTemplate.remove(query,"student_course");
    }

    //exp5/2
    public void addStudentCourse(String cid,String sid) {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCid(cid);
        studentCourse.setSid(sid);
        mongoTemplate.insert(studentCourse);
    }
}










