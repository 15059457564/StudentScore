package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Teacher;
import com.chenhao.stuscore.domain.score;
import com.chenhao.stuscore.domain.tea_course;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/1
 **/
public interface TeacherService {
    List<Teacher> findAll2();

    void addteacher(Teacher teacher);

    Teacher findById(Integer id);

    void edit(Teacher teacher);

    void deleteTeacher(Integer id);

    List<Teacher> findByName(Integer page, Integer size, String name);

    List<Teacher> findAll(Integer pageNum, Integer pageSize);

//    List<tea_course> impart(Integer id);

    List<tea_course> getTeaCour();

    List<String> getTeaCourNameById(Integer id);

    List<tea_course> getTeaCourById(Integer id);

    void editCourse(Integer id, int[] courseids);

    void addteaCour(tea_course teaCourse);

    tea_course editTeaCourById(Integer id);

    void editTeaCour(tea_course teaCourse);

    void deleteTeaCour(Integer id);

    tea_course getTeaCourByIdOne(Integer id);

    List<score> findStudentScoreByClazzid(Integer clazzid,Integer courseid);

    void deletescoreByclazzid(String clazzid,String courseid);

    void addScore(score s);

    void editScoreByStuidAndCourseid(String stuid, String courseid, String score);
}
