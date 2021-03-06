package com.canal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by ThinkPad on 2021/3/2.
 */
@Data
//通过这个注解可以声明一个文档，指定其所在的索引库和type
@Document(indexName = "canal", shards =3, replicas = 2)
public class User {
    @Id
    private Long id;
    @Field(store = true)
    private Integer age;
    @Field(store = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
