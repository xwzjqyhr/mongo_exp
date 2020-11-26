package com.xwzjqyhr.mongodb.pojo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "teacher")
public class Teacher {

    @Id
    String id;
    String tid;
    String name;
    String sex;
    Integer age;
    @Field("dname")
    String dname;
}
