package com.xwzjqyhr.mongodb.service;


import com.alibaba.fastjson.JSON;
import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}






