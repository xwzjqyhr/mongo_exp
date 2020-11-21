package com.xwzjqyhr.mongodb.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "course")
public class Course {
    @Id
    String id;
    String cid;
    String name;
    String fcid;
    Double credit;
}
