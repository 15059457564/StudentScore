package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.domain.ScoreData;
import com.chenhao.stuscore.mapper.ScoreDateMapper;
import com.chenhao.stuscore.service.ScoreDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch
 * @date 2020/10/9
 **/
@Service
public class ScoreDateServiceImpl implements ScoreDateService {

    @Autowired
    ScoreDateMapper scoreDateMapper;

    @Override
    public void addScoreDate(ScoreData scoreData) {
        scoreDateMapper.addScoreDate(scoreData);
    }

    @Override
    public List<ScoreData> getScoreDatas() {
        return scoreDateMapper.getScoreDatas();
    }

    @Override
    public void deletedeleteScoreDate(Integer id) {
        scoreDateMapper.deletedeleteScoreDate(id);
    }

    @Override
    public ScoreData getDateByMaxTime() {
        return scoreDateMapper.getDateByMaxTime();
    }
}
