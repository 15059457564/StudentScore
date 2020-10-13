package com.chenhao.stuscore.domain;

/**
 * @author ch
 * @date 2020/9/8
 * 教师与课程关联表
 **/
public class tea_course {
    private Integer id;
    private Integer teacherid;//教师id
    private Integer courseid;//课程id
    private Integer clazzid;//班级id

    @Override
    public String toString() {
        return "tea_course{" +
                "id=" + id +
                ", teacherid=" + teacherid +
                ", courseid=" + courseid +
                ", clazzid=" + clazzid +
                '}';
    }

    public Integer getClazzid() {
        return clazzid;
    }

    public void setClazzid(Integer clazzid) {
        this.clazzid = clazzid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
}
