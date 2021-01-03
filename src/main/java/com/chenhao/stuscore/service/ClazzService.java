package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.ClazzResult;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/3
 **/
public interface ClazzService {
    //找到全部班级
    List<Clazz> findAll();
    //通过名字查找班级
    List<Clazz> findByName(String name);
    //用于分页
    List<Clazz> Pagination(Integer page, Integer size,String name);

    void addClazz(Clazz clazz);

    Clazz findById(Integer id);

    void edit(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> getAll();

    List<Clazz> findByCollegeId(Integer collegeId);

    String findClazzNameByid(Integer clazzid);

    List<ClazzResult> getclazzResult(Integer id);
}
