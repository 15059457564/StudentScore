package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.College;

import java.util.List;

/**
 * @author ch
 * @date 2020/8/31
 **/
public interface CollegeService {
    List<College> getAll();

    int add(College college);

    void  edit(College college);

    College findById(Integer id);

    void delete(Integer id);

    String findCollegeNameById(Integer cid);
}
