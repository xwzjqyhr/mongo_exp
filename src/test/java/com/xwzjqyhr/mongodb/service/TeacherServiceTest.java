package com.xwzjqyhr.mongodb.service;

import com.alibaba.fastjson.JSON;
import com.xwzjqyhr.mongodb.pojo.Teacher;
import com.xwzjqyhr.mongodb.service.impl.CourseServiceImpl;
import com.xwzjqyhr.mongodb.service.impl.TeacherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TeacherServiceTest {
    @Autowired
    TeacherServiceImpl teacherService;
    @Test
    public void findTeacherByMinAge() {
        List<Teacher> teachers = teacherService.findTeacherByMinAge(44);
        System.out.println(JSON.toJSONString(teachers));
    }
    @Test
    public void findTeacherBySex() {
        List<Teacher> teachers = teacherService.findTeacherBySex("M");
        System.out.println(JSON.toJSONString(teachers));
    }
    @Test
    public void findTeacherByDname() {
        List<Teacher> teachers = teacherService.findTeacherByDname("CS");
        System.out.println(JSON.toJSONString(teachers));
    }
}
