package com.chenhao.stuscore.domain;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author ch
 * @date 2021/1/3
 **/
public class ClazzResult {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("课程")
    private String course;
    @ExcelProperty("成绩")
    private String score;


    public ClazzResult() {
    }

    public ClazzResult(String name, String score, String course) {
        this.name = name;
        this.score = score;
        this.course = course;
    }

    @Override
    public String toString() {
        return "ClazzResult{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", course='" + course + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
