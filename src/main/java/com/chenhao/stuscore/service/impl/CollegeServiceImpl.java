package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.College;
import com.chenhao.stuscore.mapper.CollegeMapper;
import com.chenhao.stuscore.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2020/8/31
 **/
@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    CollegeMapper collegeMapper;
    @Override
    public List<College> getAll() {

        return collegeMapper.getAll();
    }

    @Override
    public int add(College college) {
        return collegeMapper.add(college);
    }

    @Override
    public void edit(College college) {
        collegeMapper.edit(college);
    }

    @Override
    public College findById(Integer id) {
        return collegeMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        collegeMapper.delete(id);
    }

    @Override
    public String findCollegeNameById(Integer cid) {
        return collegeMapper.findCollegeNameById(cid);
    }
}
