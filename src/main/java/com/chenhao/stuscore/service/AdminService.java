package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Admin;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.Teacher;

/**
 * @author ch
 * @date 2020/8/30
 **/
public interface AdminService {

    Admin findByUserName(String username);

    Teacher findByTeacherName(String username);

    Student findByStudentName(String username);

    String getToast();
}
