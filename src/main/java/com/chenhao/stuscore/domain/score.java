package com.chenhao.stuscore.domain;



/**
 * @author ch
 * @date 2020/9/12
 **/
public class score {
    private Integer id;      //主键
    private Integer stuid;   // 学生id
    private Integer courseid;//课程id
    private Integer score;   //课程成绩
    private Integer cid;     //学院id;
    private Integer clazzid; //班级id;
    private String grade;    //等级
    private Integer point;   //绩点

    public score() {
    }

    public score( Integer stuid, Integer courseid, Integer score, Integer cid, Integer clazzid) {

        this.stuid = stuid;
        this.courseid = courseid;
        this.score = score;
        this.cid = cid;
        this.clazzid = clazzid;
    }



    @Override
    public String toString() {
        return "score{" +
                "id=" + id +
                ", stuid=" + stuid +
                ", courseid=" + courseid +
                ", score=" + score +
                ", cid=" + cid +
                ", clazzid=" + clazzid +
                ", grade='" + grade + '\'' +
                ", point=" + point +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getClazzid() {
        return clazzid;
    }

    public void setClazzid(Integer clazzid) {
        this.clazzid = clazzid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
