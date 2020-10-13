package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.score;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/16
 **/
public interface StudentPageService {
    void editStudentInfo(Student student);

    List<score> findScoreByStuid(Integer id);
}
