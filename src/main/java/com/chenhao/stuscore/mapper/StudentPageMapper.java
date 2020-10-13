package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/16
 **/
@Mapper
public interface StudentPageMapper {
    @Update("update t_student set sex=#{sex},age=#{age},password=#{password} where id =#{id}")
    void editStudentInfo(Student student);

    @Select("select * from t_score where stuid = #{id}")
    List<score> findScoreByStuid(Integer id);
}
