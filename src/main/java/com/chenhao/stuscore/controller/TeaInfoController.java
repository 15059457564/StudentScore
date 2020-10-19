package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.Util.GetTime;
import com.chenhao.stuscore.domain.*;
import com.chenhao.stuscore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/9/13
 **/
@Controller
public class TeaInfoController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;
    @Autowired
    ScoreDateService scoreDateService;
    private List<Course> courses;

    @GetMapping("editTeaInfo")
    public ModelAndView editTeaInfo(ModelAndView mv, HttpServletRequest request){
        Teacher teacher1= (Teacher)request.getSession().getAttribute("user");
        Integer id= teacher1.getId();
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
        mv.setViewName("Teachermanage/TeaInfo/teacher");
        return mv;
    }


    @PostMapping("editTeachermyself")
    public String editTeachermyself(Teacher teacher){
        teacherService.edit(teacher);
        return "redirect:/teacherManage";
    }
    
    @GetMapping("scoreManage")
    public ModelAndView scoreManage(HttpServletRequest request,ModelAndView mv){
        Teacher teacher= (Teacher) request.getSession().getAttribute("user");
        Integer id = teacher.getId();
        List<Clazz> clazzs = clazzService.getAll();
        List<Course> courses = courseService.getAll();
        List<tea_course> teaCourByteacherid = teacherService.getTeaCourById(id);
        ScoreData scoreDate = scoreDateService.getDateByMaxTime();//获取最大id的时间  也就是最后输入的时间
        //获取当前网络时间
        String webUrl="http://www.baidu.com";//百度时间
        long webTime= GetTime.getNetworkTime(webUrl);

        mv.addObject("webTime",webTime);
        mv.addObject("scoreDate",scoreDate);
        mv.addObject("clazzs",clazzs);
        mv.addObject("courses",courses);
        mv.addObject("teaCourByteacherid",teaCourByteacherid);
        mv.setViewName("Teachermanage/TeaInfo/Review");
        return mv;
    }
    //登记成绩页
    @GetMapping("editScorePage/{id}")
    public ModelAndView editScorePage(ModelAndView mv,@PathVariable("id")Integer id){

        tea_course teaCourByIdOne = teacherService.getTeaCourByIdOne(id);
        List<Student> students = studentService.findByClazzId(teaCourByIdOne.getClazzid());
        List<Course> courses = courseService.getAll();
        //查询学生成绩 返回list表  根据学生stuid进行匹配
//        List<score> scores = teacherService.findStudentScoreByClazzid(teaCourByIdOne.getClazzid(),teaCourByIdOne.getCourseid());
//        mv.addObject("scores",scores);
        mv.addObject("courses",courses);
        mv.addObject("teaCourByIdOne",teaCourByIdOne);
        mv.addObject("students",students);
        mv.setViewName("Teachermanage/TeaInfo/RegisterScore");
        return mv;
    }
    //登记成绩
    @PostMapping("Correct")
    public String Correct(HttpServletRequest request){
        String[] ids = request.getParameterValues("stuid");
        String[] scores = request.getParameterValues("score");
        String[] courseids = request.getParameterValues("courseid");
        String[] cids = request.getParameterValues("cid");
        String[] clazzids = request.getParameterValues("clazzid");
        List<score> list=new ArrayList<>();
        for (int i=0 ;i<ids.length;i++){
            if (scores[i]==""){
                    scores[i]=0+"";
            }

            list.add(new score(Integer.parseInt(ids[i]),Integer.parseInt(courseids[i]),Integer.parseInt(scores[i]),Integer.parseInt(cids[i]),Integer.parseInt(clazzids[i])));

        }
        //先删除再添加
        teacherService.deletescoreByclazzid(clazzids[0],courseids[0]);
        for (score s:list) {
            teacherService.addScore(s);
        }
        return "redirect:/scoreManage";


    }

    //查看成绩
    @GetMapping("showScorePage/{id}")
    public ModelAndView showScorePage(ModelAndView mv,@PathVariable("id")Integer id){
        tea_course teaCourByIdOne = teacherService.getTeaCourByIdOne(id);
        List<Student> students = studentService.findByClazzId(teaCourByIdOne.getClazzid());
        List<Course> courses = courseService.getAll();
        //查询学生成绩 返回list表  根据学生stuid进行匹配
        List<score> scores = teacherService.findStudentScoreByClazzid(teaCourByIdOne.getClazzid(),teaCourByIdOne.getCourseid());
        mv.addObject("scores",scores);
        mv.addObject("courses",courses);
        mv.addObject("teaCourByIdOne",teaCourByIdOne);
        mv.addObject("students",students);
        mv.setViewName("Teachermanage/TeaInfo/showAndEditScore");
        return mv;

    }
//修改成绩
    @PostMapping("editScore")
    public String editScore(String stuid,String courseid,String score){

        teacherService.editScoreByStuidAndCourseid(stuid,courseid,score);
        return "redirect:/scoreManage";
    }

}
