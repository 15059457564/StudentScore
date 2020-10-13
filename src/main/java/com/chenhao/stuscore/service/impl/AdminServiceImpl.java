package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.Admin;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.Teacher;
import com.chenhao.stuscore.mapper.AdminMapper;
import com.chenhao.stuscore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ch
 * @date 2020/8/30
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper mapper;
    @Override
    public Admin findByUserName(String username) {
        return mapper.findByUserName(username);
    }

    @Override
    public Teacher findByTeacherName(String username) {
        return mapper.findByTeacherName(username);
    }

    @Override
    public Student findByStudentName(String username) {
        return mapper.findByStudentName(username);
    }

    @Override
    public String getToast() {
        return mapper.getToast();
    }
}
