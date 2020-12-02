package com.xwzjqyhr.mongodb.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTest {

    @Autowired
    CourseServiceImpl courseService;

    @Test
    public void findAllCourse() {
        List<Course> courses = courseService.findAllCourse();
        System.out.println(JSON.toJSONString(courses));
    }
    @Test
    public void findNameColumeByCid() {
        List<Course> names = courseService.findNameColumeByCid("300001");
        System.out.println(JSON.toJSONString(names));
    }
    @Test
    public void getStuCountAndAvgScore() {
        List<Map> maps = courseService.getStuCountAndAvgScore();
        System.out.println(JSONObject.toJSONString(maps));
    }
//    @Test
//    public void getMaxScoreAndStuName() {
//        List<Map> maps = courseService.getMaxScoreAndStuName();
//        System.out.println(JSONObject.toJSONString(maps));
//    }
    @Test
    public void getMaxAvgScore() {
        List<Map> maps = courseService.getMaxAvgScore(10);
        System.out.println(JSONObject.toJSONString(maps));
    }
    @Test
    public void getMaxCourseCount() {
        List<Map> maps = courseService.getMaxCourseCount(10);
        System.out.println(JSONObject.toJSONString(maps));
    }
    @Test
    public void getCoursesBySid() {
        List<Map> maps = courseService.getCoursesBySid("200800020101");
        System.out.println(JSONObject.toJSONString(maps));
    }

    @Test
    public void deleteStudentCourse() {
        courseService.deleteStudentCourse("300003","200800020101");
    }
}






