package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.*;
import com.chenhao.stuscore.service.ClazzService;
import com.chenhao.stuscore.service.CourseService;
import com.chenhao.stuscore.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;
    @Autowired
    ClazzService clazzService;
    //课程主页
    @GetMapping("/course")
    public ModelAndView course(ModelAndView mv,
                               @RequestParam(required = true,name = "page",defaultValue = "1")Integer page,
                               @RequestParam(name = "size",defaultValue = "10") Integer size
            ,@RequestParam(value = "name",defaultValue = "") String name){
        List<Course> courses = courseService.Pagination(page,size,name);
        PageInfo pageInfo = new PageInfo(courses);
        pageInfo.setPageSize(size);
        List<tea_course> teaCours = teacherService.getTeaCour();
        List<Teacher> teacherList = teacherService.findAll2();
        List<Clazz> clazzes = clazzService.getAll();

        mv.addObject("pageInfo", pageInfo);
        mv.addObject("clazzs",clazzes);
        mv.addObject("teacherList",teacherList);
        mv.addObject("teaCours",teaCours);
        mv.addObject("courses",courses);
        mv.setViewName("course/course");
        return mv;
    }
//添加课程信息
    @PostMapping("/addCourse")
    public String addCourse(Course course){

        courseService.addCourse(course);
        return "redirect:/course";
    }

    //到修改表单  修改课程信息
    @GetMapping("/editCoursePage")
    public ModelAndView editCoursePage (ModelAndView mv,Integer id){

        Course course = courseService.findById(id);

        mv.addObject("course",course);
        mv.setViewName("/course/editCourse");
        return mv;
    }
    //修改课程
    @PostMapping("/editCourse")
    public String editClazz(Clazz clazz){
        courseService.edit(clazz);
        return "redirect:/course";
    }
    //删除课程
    @GetMapping("/deleteCourse/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id){
        courseService.deleteCourse(id);
        return "redirect:/course";
    }
}
