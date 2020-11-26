package com.xwzjqyhr.mongodb.controller;

import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;

    @RequestMapping(method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course) {
        courseService.insertCourse(course);
    }

    @RequestMapping(value = "{_id}",method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable String _id) {
        System.out.println(_id);
        courseService.deleteCourse(_id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getCourseByContidion() {
        return courseService.findAllCourse();
    }


}
