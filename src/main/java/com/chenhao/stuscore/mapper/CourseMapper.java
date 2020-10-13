package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Mapper
public interface CourseMapper {
    @Select("select * from t_course where name like #{name}")
    List<Course> findByName(String name);
    @Insert("insert into t_course(name) values(#{name})")
    void addCourse(Course course);
    @Select("select * from t_course where id = #{id}")
    Course findById(Integer id);
    @Update("update t_course set name=#{name} where id=#{id}")
    void edit(Clazz clazz);
    @Delete("delete from t_course where id = #{id}")
    void deleteCourse(Integer id);
    @Select("select * from t_course")
    List<Course> getAll();
}
