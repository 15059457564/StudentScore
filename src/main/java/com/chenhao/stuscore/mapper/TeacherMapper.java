package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Teacher;

import com.chenhao.stuscore.domain.score;
import com.chenhao.stuscore.domain.tea_course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/1
 **/
@Mapper
public interface TeacherMapper {
    @Select("select * from t_teacher")
    List<Teacher> findAll();

    @Insert("INSERT INTO t_teacher (name, password, age, cid, tel, sex,jno) VALUES (#{name}, #{password}, #{age}, #{cid}, #{tel}, #{sex},#{jno});")
    void addteacher(Teacher teacher);

    @Select("select * from t_teacher where id = #{id}")
    Teacher findById(Integer id);

    @Update("update t_teacher set name=#{name},password=#{password},age=#{age},cid=#{cid},tel=#{tel},sex=#{sex} where id=#{id}")
    void edit(Teacher teacher);

    @Update("update t_teacher set Jno=#{Jno},name='已注销' where id = #{id}")
    void deleteTeacher(Integer id,String Jno);

    @Select("select count(1) from t_teacher where cid =#{cid}")
    String Count(Integer cid);

    @Select("select * from t_tea_cour")
    List<tea_course> getTeaCour();

    @Select("select * from t_tea_cour where teacherid = #{id}")
    List<tea_course> getTeaCourById(Integer id);

    @Select("select c.name course from t_tea_cour a join t_course c on a.courseid=c.id where a.teacherid = #{id}")
    List<String> getTeaCourNameById(Integer id);

    List<Teacher> findByName(String name);

    @Insert("insert into t_tea_cour(teacherid,courseid) values(#{id},#{courseid})")
    void editCourse(Integer id, int courseid);

    @Select("delete from t_tea_cour where teacherid = #{id}")
    void deleteTeaCourse(Integer id);

    @Insert("insert into t_tea_cour(teacherid,courseid,clazzid) values(#{teacherid},#{courseid},#{clazzid})")
    void addteaCour(tea_course teaCourse);

    @Select("select * from t_tea_cour where id = #{id}")
    tea_course editTeaCourById(Integer id);

    @Update("update t_tea_cour set teacherid=#{teacherid},courseid=#{courseid},clazzid=#{clazzid} where id = #{id}")
    void editTeaCour(tea_course teaCourse);

    @Select("delete from t_tea_cour where id = #{id}")
    void deleteTeaCourByMainId(Integer id);

    @Select("select * from t_tea_cour where id = #{id}")
    tea_course getTeaCourByIdOne(Integer id);

    @Select("select * from t_score where clazzid = #{clazzid} and courseid = #{courseid}")
    List<score> findStudentScoreByClazzid(Integer clazzid,Integer courseid);

    @Delete("delete from t_score where clazzid = #{clazzid} and courseid=#{courseid}")
    void deletescoreByclazzid(String clazzid,String courseid);

    @Insert("insert into t_score(stuid,courseid,score,cid,clazzid,grade,point) values(#{stuid},#{courseid},#{score},#{cid},#{clazzid},#{grade},#{point})")
    void addScore(score s);

    @Update("update t_score set score = #{score} where stuid = #{stuid} and courseid=#{courseid}")
    void editScoreByStuidAndCourseid(String stuid, String courseid, String score);
}
