package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.Course;
import com.chenhao.stuscore.mapper.CourseMapper;
import com.chenhao.stuscore.service.CourseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> Pagination(Integer page, Integer size, String name) {
        String name2="%"+name+"%";
        PageHelper.startPage(page,size);
        return courseMapper.findByName(name2);
    }

    @Override
    public void addCourse(Course course) {
        courseMapper.addCourse(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.findById(id);
    }

    @Override
    public void edit(Clazz clazz) {
        courseMapper.edit(clazz);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseMapper.deleteCourse(id);
    }

    @Override
    public List<Course> getAll() {
        return courseMapper.getAll();
    }


}
