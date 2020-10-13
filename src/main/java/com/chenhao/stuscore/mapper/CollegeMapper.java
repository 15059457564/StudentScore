package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.College;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ch
 * @date 2020/8/31
 **/
@Mapper
public interface CollegeMapper {
    @Select("select * from t_college")
    List<College> getAll();
    @Insert("insert into t_college(name,remark) values(#{name},#{remark})")
    int add(College college);
    @Update("update t_college set name=#{name},remark=#{remark} where id=#{id}")
    void edit(College college);
    @Select("select * from t_college where id=#{id}")
    College findById(Integer id);
    @Delete("delete from t_college where id=#{id}")
    void delete(Integer id);
    @Select("select id from t_college where name = #{college}")
    Integer findCidByName(String college);
    @Select("Select name from t_college where id = #{cid}")
    String findCollegeNameById(Integer cid);
}
