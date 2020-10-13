package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.Admin;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from t_admin where username=#{username}")
    Admin findByUserName(String username);
    @Select("select * from t_teacher where Jno=#{username}")
    Teacher findByTeacherName(String username);
    @Select("select * from t_student where sno = #{username}")
    Student findByStudentName(String username);
    @Select("Select msg from t_toast")
    String getToast();
}