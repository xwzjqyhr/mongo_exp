package com.xwzjqyhr.mongodb.controller;


import com.xwzjqyhr.mongodb.pojo.Student;
import com.xwzjqyhr.mongodb.pojo.Teacher;
import com.xwzjqyhr.mongodb.service.impl.StudentServiceImpl;
import com.xwzjqyhr.mongodb.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @RequestMapping(method = RequestMethod.POST)
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.insertTeacher(teacher);
    }

    @RequestMapping(value = "/{_id}",method = RequestMethod.DELETE)
    public void deleteTeacher(@PathVariable String _id) {
       teacherService.deleteTeacher(_id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateTeacher(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Teacher> findTeacherByConditions(@RequestParam(required = false) Integer minAge,
                                                 @RequestParam(required = false) String sex,
                                                 @RequestParam(required = false) String dName)
    {
        System.out.println(minAge+" " + sex + " " + dName);
        return  teacherService.findTeacherByContidion(minAge,sex,dName);
    }

    @RequestMapping(value = "/exp6/2")
    public void getMaxAvgScore(){

    }
}
