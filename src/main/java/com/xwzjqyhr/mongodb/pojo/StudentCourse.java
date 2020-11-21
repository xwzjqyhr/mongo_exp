package com.xwzjqyhr.mongodb.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "student_course")
public class StudentCourse {
    @Id
    String id;
    String sid;
    String cid;
    String score;
    String tid;
}
