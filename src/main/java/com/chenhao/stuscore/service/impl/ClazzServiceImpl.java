package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.ClazzResult;
import com.chenhao.stuscore.mapper.ClazzMapper;
import com.chenhao.stuscore.service.ClazzService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/3
 **/
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public List<Clazz> findByName(String name) {
        String name2="%"+name+"%";
        return clazzMapper.findByName(name2);
    }

    @Override
    public List<Clazz> Pagination(Integer page, Integer size,String name) {
        String name2="%"+name+"%";
        PageHelper.startPage(page,size);
        return clazzMapper.findByName(name2);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    @Override
    public void edit(Clazz clazz) {
        clazzMapper.edit(clazz);
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }

    @Override
    public List<Clazz> findByCollegeId(Integer collegeid) {
        return clazzMapper.findByCollegeId(collegeid);
    }

    @Override
    public String findClazzNameByid(Integer clazzid) {
        return clazzMapper.findClazzNameByid(clazzid);
    }

    @Override
    public List<ClazzResult> getclazzResult(Integer id) {
        return clazzMapper.getclazzResult(id);
    }
}
