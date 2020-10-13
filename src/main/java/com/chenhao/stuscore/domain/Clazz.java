package com.chenhao.stuscore.domain;

import org.springframework.stereotype.Component;

/**
 * @author ch
 * @date 2020/9/3
 **/

public class Clazz {
    private Integer id;
    private String name;
    private Integer cid;

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cid=" + cid +
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
