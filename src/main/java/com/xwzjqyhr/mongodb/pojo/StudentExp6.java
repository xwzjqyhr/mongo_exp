package com.xwzjqyhr.mongodb.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "student_exp6")
public class StudentExp6 {

    @Id
    String id;
    String sid;
    String sname;
    String sex;
    String maxScoreCourseName;
    Double maxScore;
}
