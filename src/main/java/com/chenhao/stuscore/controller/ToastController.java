package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.service.ToastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ch
 * @date 2020/10/10
 **/
@Controller
public class ToastController {
    @Autowired
    ToastService toastService;

    @GetMapping("/toast")
    public ModelAndView toast(ModelAndView mv){
        mv.setViewName("toast/toast");
        return mv;
    }

    @GetMapping("/setToast")
    public String setToast(String toast){
        toastService.editToast(toast);
        return "redirect:/admin";
    }

}
