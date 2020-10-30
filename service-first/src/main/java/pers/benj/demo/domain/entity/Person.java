package pers.benj.demo.domain.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = -3372965002491459480L;

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person() {}

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
