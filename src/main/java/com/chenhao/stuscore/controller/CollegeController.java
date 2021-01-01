package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.College;
import com.chenhao.stuscore.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ch
 * @date 2020/8/31
 **/
@Controller
public class CollegeController {
    @Autowired
    CollegeService collegeService;

    //学院信息展示
    @ResponseBody
    @GetMapping("/college")
    public ModelAndView college(ModelAndView mv){
        List<College> colleges=collegeService.getAll();
        mv.addObject("colleges",colleges);
        mv.setViewName("college/college");
        return mv;
    }


//添加学院信息
    @PostMapping("/addCollege")
    public String addCollege(College college){
         collegeService.add(college);
        return "redirect:/college";
    }
//到修改页面
    @GetMapping("/editCollegePage")
    @ResponseBody
    public ModelAndView editCollegePage(Integer id,
            ModelAndView mv){

        College college1 = collegeService.findById(id);

        mv.addObject("college1",college1);
        mv.setViewName("college/editCollege");

        return mv;
    }
//开始修改
    @PostMapping("/editCollege")
    public String editCollege(College college){

        collegeService.edit(college);
        return "redirect:/college";
    }
//删除某一个学院
    @GetMapping("/deleteCollege/{id}")
    public String deleteCollege(@PathVariable("id") Integer id){
//        System.out.println(id);
        collegeService.delete(id);
        return "redirect:/college";
    }

}
