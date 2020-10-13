package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.College;
import com.chenhao.stuscore.service.ClazzService;
import com.chenhao.stuscore.service.CollegeService;
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
 * @date 2020/9/3
 **/
@Controller
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    @Autowired
    CollegeService collegeService;
// 传递过来当前页和一页列多少数据  再传递过来一个需要查询的名字（默认为空  这样第一次进这个页面就有全部的信息了）
    @GetMapping("clazz")
    public ModelAndView clazz(ModelAndView mv,
                              @RequestParam(required = true,name = "page",defaultValue = "1")Integer page,
                              @RequestParam(name = "size",defaultValue = "10") Integer size
            ,@RequestParam(value = "name",defaultValue = "") String name){
        List<College> colleges = collegeService.getAll();
        List<Clazz> clazzs = clazzService.Pagination(page,size,name);
        PageInfo pageInfo = new PageInfo(clazzs);
        pageInfo.setPageSize(size);
        mv.addObject("pageInfo", pageInfo);
//        System.out.println(clazzs);
        mv.addObject("clazzs",clazzs);
        mv.addObject("colleges",colleges);
        mv.setViewName("clazz/clazz");
        return mv;
    }


//添加班级
    @PostMapping("/addClazz")
    public String addClazz(Clazz clazz){
        clazzService.addClazz(clazz);
        return "redirect:/clazz";
    }
//到修改表单  修改班级信息
    @GetMapping("/editClazzPage")
    public ModelAndView editClazzPage (ModelAndView mv,Integer id){
        List<College> colleges = collegeService.getAll();
        Clazz clazz = clazzService.findById(id);
        mv.addObject("colleges",colleges);
        mv.addObject("clazz",clazz);
        mv.setViewName("/clazz/editClazz");
        return mv;
    }
//修改班级
    @PostMapping("/editClazz")
    public String editClazz(Clazz clazz){
        clazzService.edit(clazz);
        return "redirect:/clazz";
    }
    //删除班级
    @GetMapping("/deleteClazz/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id){
        clazzService.deleteClazz(id);
        return "redirect:/clazz";
    }
}