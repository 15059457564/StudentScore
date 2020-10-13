package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.*;
import com.chenhao.stuscore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学生页面控制器
 * @author ch
 * @date 2020/9/16
 **/
@Controller
public class StudentPageController {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentPageService studentPageService;
    @GetMapping("/editStudentInfo")
    public ModelAndView editStudentInfo(HttpServletRequest request,ModelAndView mv){
        Student student= (Student) request.getSession().getAttribute("user");//获取学生信息
        List<College> colleges = collegeService.getAll();
        List<Clazz> clazzs = clazzService.getAll();
        mv.addObject("colleges",colleges);
        mv.addObject("clazzs",clazzs);
        mv.addObject("student",student);//发送学生信息到前端
        mv.setViewName("Studentmanage/studentInfo/editStudentinfo");
        return mv;
    }
    @PostMapping("/editStudentInfo")
    public String editStudentInfo(Student student){

        studentPageService.editStudentInfo(student);//修改个人信息
        return "redirect:/StudentHomePage";
    }

    @GetMapping("/ShowScore")
    public ModelAndView ShowScore(HttpServletRequest request,ModelAndView mv){
        Student student= (Student) request.getSession().getAttribute("user");//获取学生信息

        List<score> scores=studentPageService.findScoreByStuid(student.getId());//根据学生id查询学生所有成绩信息
        List<Course> courses = courseService.getAll();

        mv.addObject("courses",courses);
        mv.addObject("scores",scores);
        mv.setViewName("Studentmanage/studentInfo/studentScore");

        return mv;

    }
}
