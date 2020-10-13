package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.Util.RandomString;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.StudentExcel;
import com.chenhao.stuscore.mapper.ClazzMapper;
import com.chenhao.stuscore.mapper.CollegeMapper;
import com.chenhao.stuscore.mapper.StudentMapper;
import com.chenhao.stuscore.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    CollegeMapper collegeMapper;

    @Override
    public List<Student> Pagination(Integer page, Integer size, String name) {
        String name2="%"+name+"%";
        PageHelper.startPage(page,size);
        return studentMapper.findByName(name2);
    }
//  学号是 “S”+入学年份+学院id+班级id+（现在班级人数的值+1）
    @Override
    public void addStudent(Student student) {
        System.out.println("接收到的student："+student);

        String cid2=String.valueOf(student.getCid());//学院id
        String clazzid2=String.valueOf(student.getClazzid());//班级id
        String temp=null;//班级人数
        //入学年份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year=sdf.format(date);
        //学院id   判断是否小于10  是 则加一个0在前面
        if (student.getCid()<10){
            cid2="0"+student.getCid();
        }else {
            cid2=student.getCid()+"";
        }
        //班级id   判断是否小于10  是 则加一个0在前面
        if (student.getClazzid()<10){
            clazzid2="0"+student.getClazzid();
        }else {
            clazzid2=student.getClazzid()+"";
        }
        //计算班级里共多少人  然后加1
        if(studentMapper.Count(student.getClazzid())==null){
            temp="01";
        }else if (Integer.parseInt(studentMapper.Count(student.getClazzid()))<10){
            temp="0"+String.valueOf(Integer.parseInt(studentMapper.Count(student.getClazzid()))+1);
        }else {
            temp=Integer.parseInt(studentMapper.Count(student.getClazzid()))+1+"";
        }
        //学生学号=“S”+入学年份+学院id+班级id+（现在班级人数的值+1）
        student.setSno("S"+year+cid2+clazzid2+temp);

        studentMapper.addStudent(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void edit(Student student) {
        studentMapper.edit(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        String randomString = RandomString.getRandomString(10);
        studentMapper.deleteStudent(id,randomString);
    }

    @Override
    public List<Student> findByClazzId(Integer clazzid) {
        return studentMapper.findByClazzId(clazzid);
    }

    @Override
    public void readExcel(List<StudentExcel> students) {
        for (StudentExcel studentExcel: students) {
            Student student=new Student();
            student.setClazzid( clazzMapper.findclazzidByName(studentExcel.getClazz()));
            student.setCid(collegeMapper.findCidByName(studentExcel.getCollege()));
            student.setSno(studentExcel.getSno());
            student.setAge(studentExcel.getAge());
            student.setName(studentExcel.getName());
            student.setSex(studentExcel.getSex());
            student.setPassword(studentExcel.getPassword());
            studentMapper.addStudent(student);
        }
    }

    @Override
    public List<Student> getAll() {
        return studentMapper.getAll();
    }
}
