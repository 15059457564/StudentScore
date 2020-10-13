package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Mapper
public interface StudentMapper {
    @Select("select * from t_student where name like #{name}")
    List<Student> findByName(String name);
    @Insert("insert into t_student(sno,name,sex,age,password,clazzid,cid) values(#{sno},#{name},#{sex},#{age},#{password},#{clazzid},#{cid})")
    void addStudent(Student student);
    @Select("select * from t_student where id = #{id}")
    Student findById(Integer id);
    @Update("update t_student set sno=#{sno},name=#{name},sex=#{sex},age=#{age},password=#{password},clazzid=#{clazzid},cid=#{cid} where id=#{id}")
    void edit(Student student);
    @Delete("update t_student set sno=#{randomString},name='已注销' where id=#{id}")
    void deleteStudent(Integer id,String randomString);
    @Select("select count(1) from t_student where clazzid =#{clazzid}")
    String Count(Integer clazzid);
    @Select("select * from t_student where clazzid = #{clazzid}")
    List<Student> findByClazzId(Integer clazzid);
    @Select("select * from t_student")
    List<Student> getAll();
}
