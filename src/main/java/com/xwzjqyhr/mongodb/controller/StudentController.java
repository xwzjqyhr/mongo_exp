package com.xwzjqyhr.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.pojo.StudentExp6;
import com.xwzjqyhr.mongodb.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student) {
        studentService.insertStudent(student);

    }
    @RequestMapping(value = "/{_id}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable String _id) {

        studentService.deleteStudent(_id);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findStudentByContidion(@RequestParam(required = false) Integer maxAge,@RequestParam(required = false) String dName) {
        System.out.println(JSONObject.toJSONString(studentService.findStudentByContidion(maxAge,dName).get(0)));
        return studentService.findStudentByContidion(maxAge,dName);
    }
    @RequestMapping(value = "/nameAgeColume",method = RequestMethod.GET)
    public List<Student> findStudentByContidion(@RequestParam Integer maxAge) {
        return studentService.findNameAgeColumeByContidion(maxAge);
    }
    @RequestMapping(value = "/exp6/2")
    public List<Map> getMaxElementsByAvgScore() {
       return studentService.getMaxElementsByAvgScore(10);
    }
    @RequestMapping(value = "/exp6/3")
    public List<Map> getMaxElementsByCourseCount() {
        return studentService.getMaxElementsByCourseCount(10);
    }
    @RequestMapping(value = "/exp6/4")
    public List<StudentExp6> getEveryoneMaxScoreCourse() {
        return studentService.getEveryoneMaxScoreCourse();
    }
}











