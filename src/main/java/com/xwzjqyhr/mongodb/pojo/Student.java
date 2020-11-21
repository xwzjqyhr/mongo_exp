package com.xwzjqyhr.mongodb.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String sid;
    private String name;
    private String sex;
    private Integer age;
    private String birthday;
    @Field("dname")
    private String dName;
    @Field("class")
    private Integer dClass;
}
