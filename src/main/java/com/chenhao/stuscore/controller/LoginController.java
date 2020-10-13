package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.Util.CpachaUtil;
import com.chenhao.stuscore.domain.Admin;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.Teacher;
import com.chenhao.stuscore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ch
 * @date 2020/8/27
 **/
@Controller
public class LoginController {
    @Autowired
    AdminService adminService;

    @ResponseBody
    @GetMapping(value = {"/login","/"})
    public ModelAndView login(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

    @ResponseBody
    @GetMapping(value = "/yzm")
    public void getCapcha(HttpSession session, HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(4) ;
        String generatorVCode=cpachaUtil.generatorVCode();
        session.setAttribute("loginCpacha",generatorVCode);
        BufferedImage generratorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);
        try {
            ImageIO.write(generratorRotateVCodeImage,"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证用户名  密码  验证码是否输入
    @PostMapping("/Login.do")
    @ResponseBody
    public Map<String,String> Login(HttpServletRequest request,HttpServletResponse response, String username, String password, String yzma, Integer type) {

        Map<String,String> ret = new HashMap<>();
        if(StringUtils.isEmpty(username)){
            ret.put("type","error");
            ret.put("msg","用户名不能为空");
            return ret;
        }


        if(StringUtils.isEmpty(password)){
            ret.put("type","error");
            ret.put("msg","密码不能为空");
            return ret;
        }

        if(StringUtils.isEmpty(yzma)){

            ret.put("type","error");
            ret.put("msg","验证码不能为空");
            return ret;
        }

        String yzm = (String) request.getSession().getAttribute("loginCpacha");
        if (StringUtils.isEmpty(yzm)){
            ret.put("type","error");
            ret.put("msg","长时间未操作，会话失效，请刷新页面后登陆");

            return ret;
        }
        if(!yzma.toUpperCase().equals(yzm.toUpperCase())){
            ret.put("type","error");
            ret.put("msg","验证码错误！");
            return ret;
        }

        request.getSession().setAttribute("loginCpacha",null);

        //从数据库中寻找用户
        Admin user= adminService.findByUserName(username);
        if(type==1){
            //管理员
            if (user==null){
                ret.put("type","error");
                ret.put("msg","用户名错误");
                return ret;
            }
            if(!password.equals(user.getPassword())){
                ret.put("type","error");
                ret.put("msg","密码错误");
                return ret;
            }

            request.getSession().setAttribute("user",user);

        }

        //从数据库中寻找老师
        if(type==2){
            Teacher teacher = adminService.findByTeacherName(username);
            //教师
            if (teacher==null){
                ret.put("type","error");
                ret.put("msg","用户名错误");
                return ret;
            }
            if(!password.equals(teacher.getPassword())){
                ret.put("type","error");
                ret.put("msg","密码错误");
                return ret;
            }

            request.getSession().setAttribute("user",teacher);

        }
        if (type==3){
            //学生
            Student student = adminService.findByStudentName(username);
            if (student==null){
                ret.put("type","error");
                ret.put("msg","用户名错误");
                return ret;
            }
            if(!password.equals(student.getPassword())){
                ret.put("type","error");
                ret.put("msg","密码错误");
                return ret;
            }

            request.getSession().setAttribute("user",student);
        }

        ret.put("type","success");
        ret.put("msg","登陆成功");

        return  ret;
    }


    @ResponseBody
    @RequestMapping("/admin")
    public ModelAndView admin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        String toast=adminService.getToast();
        if ("".equals(toast)){
            toast="欢迎来到宁德师范学院学生成绩管理系统";
        }
        mv.addObject("msg",toast);
        return mv;
    }

    @ResponseBody
    @GetMapping("/teacherManage")
    public ModelAndView teacherManage(){
        ModelAndView mv = new ModelAndView();
        String toast=adminService.getToast();
        if ("".equals(toast)){
            toast="欢迎来到宁德师范学院学生成绩管理系统";
        }
        mv.addObject("msg",toast);
        mv.setViewName("Teachermanage/TeacherMain");
        return mv;
    }
    @ResponseBody
    @GetMapping("/StudentHomePage")
    public ModelAndView StudentHomePage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Studentmanage/StudentHomePage");
        return mv;
    }
//退出登录
    @GetMapping("/LoginOut")
    public String LoginOut(HttpServletRequest request){

        request.getSession().setAttribute("user",null);
        return "redirect:/login";

    }


}
