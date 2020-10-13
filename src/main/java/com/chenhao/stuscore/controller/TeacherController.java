package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.*;
import com.chenhao.stuscore.service.ClazzService;
import com.chenhao.stuscore.service.CollegeService;
import com.chenhao.stuscore.service.CourseService;
import com.chenhao.stuscore.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/9/1
 **/
@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClazzService clazzService;

    //    来到教师管理页面
    @GetMapping("/teacher")
    public ModelAndView teacher(ModelAndView mv,
                                @RequestParam(required = true, name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "name", defaultValue = "") String name) {
        List<tea_course> teaCours = teacherService.getTeaCour();                    //老师跟课程的全部关联信息
        List<Course> courses = courseService.getAll();                          //课程全部信息
        List<College> colleges = collegeService.getAll();                     //学院信息
        List<Teacher> teachers = teacherService.findByName(page, size, name);   //老师信息
        List<Clazz> clazzes = clazzService.getAll();                        //班级信息
        PageInfo pageInfo = new PageInfo(teachers);                           //分页信息
        pageInfo.setPageSize(size);
        mv.addObject("clazzs",clazzes);
        mv.addObject("teaCours", teaCours);
        mv.addObject("courses", courses);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("colleges", colleges);
        mv.addObject("teachers", teachers);

        mv.setViewName("teacher/teacher");
        return mv;
    }

    @PostMapping("/addteacher")
    public String addteacher(Teacher teacher) {
//        System.out.println(teacher);
        teacherService.addteacher(teacher);
//        System.out.println(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/editTeacherPage")
    public ModelAndView editTeacherPage(Integer id, ModelAndView mv) {
        List<Course> courses = courseService.getAll();
        List<College> colleges = collegeService.getAll();
        Teacher teacher = teacherService.findById(id);
        List<tea_course> teaCours = teacherService.getTeaCourById(id);                    //老师跟课程的全部关联信息
        List<String> teaCourName = teacherService.getTeaCourNameById(id);
        List<Clazz> clazzes = clazzService.getAll();
        mv.addObject("clazzs",clazzes);
        mv.addObject("teaCourNames",teaCourName);
        mv.addObject("courses", courses);
        mv.addObject("teaCours", teaCours);
        mv.addObject("colleges", colleges);
        mv.addObject("teacher", teacher);
        mv.setViewName("teacher/editTeacher");
        return mv;
    }

    @PostMapping("/editTeacher")
    public String editTeacher(@RequestParam("courseids") int[] courseids,Teacher teacher) {
        teacherService.editCourse(teacher.getId(),courseids);
        teacherService.edit(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teacher";
    }


    @PostMapping("/addteaCour")
    public String addteaCour(tea_course teaCourse){

        teacherService.addteaCour(teaCourse);
        return "redirect:/teacher";
    }
    @GetMapping("/editTeaCourPage")
    public ModelAndView editTeaCourPage(Integer id,ModelAndView mv){
        tea_course teaCourse=teacherService.editTeaCourById(id);
        List<Clazz> clazzs = clazzService.getAll();
        List<Course> courses = courseService.getAll();

        mv.addObject("clazzs",clazzs);
        mv.addObject("courses",courses);
        mv.addObject("teaCourse",teaCourse);


        mv.setViewName("teacher/editTeaCour");
        return mv;
    }
    @PostMapping("/editTeaCour")
    public String editTeaCour(tea_course teaCourse){
//        System.out.println(teaCourse);
        teacherService.editTeaCour(teaCourse);
        return "redirect:/teacher";
    }
    @GetMapping("deleteTeaCour/{id}")
    public String deleteTeaCour(@PathVariable("id")Integer id){
        teacherService.deleteTeaCour(id);
        return "redirect:/teacher";
    }


}
