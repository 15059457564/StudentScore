package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.Util.RandomString;
import com.chenhao.stuscore.domain.Teacher;
import com.chenhao.stuscore.domain.score;
import com.chenhao.stuscore.domain.tea_course;
import com.chenhao.stuscore.mapper.TeacherMapper;
import com.chenhao.stuscore.service.TeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.com.Utils;

import javax.rmi.CORBA.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author ch
 * @date 2020/9/1
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;


    @Override
    public List<Teacher> findAll2() {

          return teacherMapper.findAll();
    }

    //工号是  “T”+入职年份+学院id+（现在在的学院老师的最大人数+1）
    @Override
    public void addteacher(Teacher teacher) {

        String cid2;//学院id
        String temp = null;//同一个学院老师的最大人数；
        //入职年份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        //最大id+1  如果该学院下没老师  直接给他一个01  如果人数小于10 给他前面加一个0
        if (teacherMapper.Count(teacher.getCid()) == null) {
            temp = "01";
        } else if (Integer.parseInt(teacherMapper.Count(teacher.getCid())) < 10) {
            temp = "0" + String.valueOf(Integer.parseInt(teacherMapper.Count(teacher.getCid())) + 1);
        } else {
            temp = Integer.parseInt(teacherMapper.Count(teacher.getCid())) + 1 + "";
        }
        //如果学院老师人数小于10  在学院id之前加上一个0
        if (teacher.getCid() < 10) {
            cid2 = "0" + teacher.getCid();
        } else {
            cid2 = teacher.getCid() + "";
        }
        teacher.setJno("T" + sdf.format(date) + cid2 + temp);
        teacherMapper.addteacher(teacher);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.findById(id);
    }

    @Override
    public void edit(Teacher teacher) {
        teacherMapper.edit(teacher);
    }

    @Override
    public void deleteTeacher(Integer id) {
        String s = RandomString.getRandomString(10);
        teacherMapper.deleteTeacher(id, s);
    }

    @Override
    public List<Teacher> findByName(Integer page, Integer size, String name) {
        String name2 = "%" + name + "%";
        PageHelper.startPage(page, size);
        return teacherMapper.findByName(name2);
    }


    @Override
    public List<Teacher> findAll(Integer pageNum, Integer pageSize) {
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<Teacher> all = teacherMapper.findAll();
        return all;
    }

    @Override
    public List<tea_course> getTeaCour() {
        return teacherMapper.getTeaCour();
    }

    @Override
    public List<String> getTeaCourNameById(Integer id) {
        if (teacherMapper.getTeaCourNameById(id).size()==0){
            return null;
        }
        return teacherMapper.getTeaCourNameById(id);
    }

    @Override
    public List<tea_course> getTeaCourById(Integer id) {
        if (teacherMapper.getTeaCourById(id).size() == 0) {
            return null;
        } else {
            return teacherMapper.getTeaCourById(id);
        }
    }

    @Override
    public void editCourse(Integer id, int[] courseids) {
        if (courseids.length==0){
            //这个老师没教任何课程   删除该老师所有课程
            teacherMapper.deleteTeaCourse(id);
        }else {
            //因为不确定是新进来的老师还是以前的老师  就把之前该老师所有的课程全部删除   把获取到的courseids重新添加  这样就不用修改了
            teacherMapper.deleteTeaCourse(id);
            for (int courseid:courseids) {
                teacherMapper.editCourse(id, courseid);
            }

        }
    }

    @Override
    public void addteaCour(tea_course teaCourse) {
        teacherMapper.addteaCour(teaCourse);
    }

    @Override
    public tea_course editTeaCourById(Integer id) {
       return  teacherMapper.editTeaCourById(id);
    }

    @Override
    public void editTeaCour(tea_course teaCourse) {
        teacherMapper.editTeaCour(teaCourse);
    }

    @Override
    public void deleteTeaCour(Integer id) {
        teacherMapper.deleteTeaCourByMainId(id);
    }

    @Override
    public tea_course getTeaCourByIdOne(Integer id) {
        return teacherMapper.getTeaCourByIdOne(id);
    }

    @Override
    public List<score> findStudentScoreByClazzid(Integer clazzid,Integer courseid) {
        return teacherMapper.findStudentScoreByClazzid(clazzid,courseid);
    }

    @Override
    public void deletescoreByclazzid(String clazzid,String courseid) {
        teacherMapper.deletescoreByclazzid(clazzid,courseid);
    }

    @Override
    public void addScore(score s) {
        switch (s.getScore()/10){
            case 10:
            case 9:
                s.setGrade("A");
                s.setPoint(4);
                break;
            case 8:
                s.setGrade("B");
                s.setPoint(3);
                break;
            case 7:
                s.setGrade("C");
                s.setPoint(2);
                break;
            case 6:
                s.setGrade("D");
                s.setPoint(1);
                break;
            default:
                s.setGrade("F");
                s.setPoint(0);
                break;
        }
        teacherMapper.addScore(s);
    }

    @Override
    public void editScoreByStuidAndCourseid(String stuid, String courseid, String score) {
        teacherMapper.editScoreByStuidAndCourseid(stuid,courseid,score);
    }

//    @Override
//    public List<tea_course> impart(Integer id) {
//        return teacherMapper.impart(id);
//    }


}
