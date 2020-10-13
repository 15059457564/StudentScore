package com.chenhao.stuscore.controller;

import com.chenhao.stuscore.domain.ScoreData;
import com.chenhao.stuscore.service.ScoreDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ch
 * @date 2020/10/9
 **/
@Controller
public class ScoreDataController {
    @Autowired
    ScoreDateService scoreDateService;

//来到成绩评定时间页面
    @GetMapping("/scoredata")
    public ModelAndView ScoreData(ModelAndView mv){
        List<ScoreData> scoreDatas=scoreDateService.getScoreDatas();

        mv.setViewName("scoredata/layerdate.html");
        mv.addObject("scoredates",scoreDatas);
        return mv;
    }
    //获取时间  传入数据库
    @GetMapping("/ScoreData2")
    public String ScoreData2(ScoreData scoreData){

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(scoreData.getStart())+"====="+df.format(scoreData.getEnd()));
        scoreDateService.addScoreDate(scoreData);
        return "redirect:/scoredata";
    }

    @GetMapping("/deleteScoreDate/{id}")
    public String deleteScoreDate(@PathVariable("id")Integer id){

        scoreDateService.deletedeleteScoreDate(id);

        return "redirect:/scoredata";
    }
}
