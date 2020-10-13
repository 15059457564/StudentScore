package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.StudentExcel;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/

public interface StudentService {
    List<Student> Pagination(Integer page, Integer size, String name) ;

    void addStudent(Student student);

    Student findById(Integer id);

    void edit(Student student);

    void deleteStudent(Integer id);

    List<Student> findByClazzId(Integer clazzid);

    void readExcel(List<StudentExcel> students);

    List<Student> getAll();
}
