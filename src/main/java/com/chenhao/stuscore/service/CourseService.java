package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.Course;
import com.chenhao.stuscore.domain.ScoreData;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
public interface CourseService {
    List<Course> Pagination(Integer page, Integer size, String name);

    void addCourse(Course course);

    Course findById(Integer id);

    void edit(Clazz clazz);

    void deleteCourse(Integer id);

    List<Course> getAll();


}
