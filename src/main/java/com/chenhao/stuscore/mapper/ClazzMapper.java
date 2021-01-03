package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.ClazzResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/3
 **/
@Mapper
public interface ClazzMapper {
    @Select("select * from t_clazz")
    List<Clazz> findAll();
    @Select("select * from t_clazz where name like #{name}")
    List<Clazz> findByName(String name);
    @Insert("insert into t_clazz(id,name,cid) values(#{id},#{name},#{cid})")
    void addClazz(Clazz clazz);
    @Select("select * from t_clazz where id = #{id}")
    Clazz findById(Integer id);
    @Update("update t_clazz set name=#{name},cid=#{cid} where id=#{id}")
    void edit(Clazz clazz);
    @Delete("delete from t_clazz where id = #{id}")
    void deleteClazz(Integer id);
    @Select("select * from t_clazz")
    List<Clazz> getAll();
    @Select("select * from t_clazz where cid = #{collegeid}")
    List<Clazz> findByCollegeId(Integer collegeid);
    @Select("select id from t_clazz where name = #{clazz}")
    Integer findclazzidByName(String clazz);
    @Select("Select name from t_clazz where id = #{clazzid}")
    String findClazzNameByid(Integer clazzid);
    @Select("SELECT a.name,b.score,d.name 'course'FROM t_score b join t_student a join t_clazz c join t_course d on b.stuid= a.id where b.clazzid=c.id and b.clazzid=#{id} and b.courseid=d.id")
    List<ClazzResult> getclazzResult(Integer id);
}
