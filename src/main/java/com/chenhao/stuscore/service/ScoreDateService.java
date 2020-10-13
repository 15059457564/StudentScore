package com.chenhao.stuscore.service;

import com.chenhao.stuscore.domain.ScoreData;

import java.util.List;

/**
 * @author ch
 * @date 2020/10/9
 **/
public interface ScoreDateService {
    void addScoreDate(ScoreData scoreData);

    List<ScoreData> getScoreDatas();

    void deletedeleteScoreDate(Integer id);

    ScoreData getDateByMaxTime();
}
