package com.chenhao.stuscore.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

/**
 * @author ch
 * @date 2020/10/9
 **/
public class StudentExcel {
    @ExcelProperty("学号")
    @ColumnWidth(15)
    private String sno;//学号
     @ExcelProperty("姓名")
    private String name;//姓名
    @ExcelProperty("性别")
    private String sex;//性别
    @ExcelProperty("年龄")
    private Integer age;//年龄
    @ExcelProperty("密码")
    private String password;//密码
    @ExcelProperty("班级")
    @ColumnWidth(20)
    private String clazz;//班级名称
    @ExcelProperty("学院")
    @ColumnWidth(20)
    private String college;//学院名称




    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "StudentExcel{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", clazz='" + clazz + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}
