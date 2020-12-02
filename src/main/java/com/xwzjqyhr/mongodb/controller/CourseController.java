package com.xwzjqyhr.mongodb.controller;

import com.xwzjqyhr.mongodb.pojo.Course;
import com.xwzjqyhr.mongodb.pojo.StudentCourseExp6;
import com.xwzjqyhr.mongodb.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/exp5/1",method = RequestMethod.GET)
    public List<Map> getCourseBySid(@RequestParam String sid) {
        return courseService.getCoursesBySid(sid);
    }
    @RequestMapping(value = "/exp5/1",method = RequestMethod.DELETE)
    public  void deleteStudentCourse(@RequestParam String cid,@RequestParam String sid) {
        courseService.deleteStudentCourse(cid,sid);
    }
    @RequestMapping(value = "/exp5/2",method = RequestMethod.GET)
    public List<Course> getNoSelectedCourse(@RequestParam(required = false) String sid) {
        return courseService.findAllCourse();
    }

    @RequestMapping(value = "/exp5/2",method = RequestMethod.POST)
    public void addStudentCourse(@RequestParam String sid,@RequestParam String cid) {
         courseService.addStudentCourse(cid,sid);
    }
    @RequestMapping(value = "/exp6/1",method = RequestMethod.GET)
    public List<StudentCourseExp6> getDistinctField() {
        String fieldName = "cname";
        return courseService.getDistinctByFieldName(fieldName);
    }
    @RequestMapping(value = "/exp6/6",method = RequestMethod.GET)
    public List<Map> getStuCountAndAvgScore() {
       List<Map> maps = courseService.getStuCountAndAvgScore();
       return maps;
    }
    @RequestMapping(value = "/exp6/8",method = RequestMethod.GET)
    public List<Map> getMaxAvgScore() {
        List<Map> maps = courseService.getMaxAvgScore(10);
        return maps;
    }
    @RequestMapping(value = "/exp6/9",method = RequestMethod.GET)
    public List<Map> getMaxCourseCount() {
        List<Map> maps = courseService.getMaxCourseCount(10);
        return maps;
    }
}
