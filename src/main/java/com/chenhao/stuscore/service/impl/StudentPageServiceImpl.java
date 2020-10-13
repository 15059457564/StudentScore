package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.score;
import com.chenhao.stuscore.mapper.StudentPageMapper;
import com.chenhao.stuscore.service.StudentPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/16
 **/
@Service
public class StudentPageServiceImpl implements StudentPageService {
    @Autowired
    StudentPageMapper studentPageMapper;
    @Override
    public void editStudentInfo(Student student) {
        studentPageMapper.editStudentInfo(student);
    }

    @Override
    public List<score> findScoreByStuid(Integer id) {
        return studentPageMapper.findScoreByStuid(id);
    }
}
