package com.xwzjqyhr.mongodb.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "student_course_exp6")
public class StudentCourseExp6 {
    @Id
    String id;
    String sid;
    String cid;
    String cname;
    Double credit;
    Double hours;
    String property;
    Double score;
}
