package com.chenhao.stuscore.domain;

import org.springframework.stereotype.Component;

/**
 * @author ch
 * @date 2020/9/4
 **/

public class Course {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
