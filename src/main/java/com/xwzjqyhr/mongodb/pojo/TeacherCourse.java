package com.xwzjqyhr.mongodb.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teacher_course")
public class TeacherCourse {
    @Id
    String id;
    String tid;
    String cid;
}
