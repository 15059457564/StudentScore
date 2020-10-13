package com.chenhao.stuscore.mapper;

import com.chenhao.stuscore.domain.ScoreData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ch
 * @date 2020/10/9
 **/
@Mapper
public interface ScoreDateMapper {
    @Insert("Insert into t_scoredata(start,end) Values(#{start},#{end})")
    void addScoreDate(ScoreData scoreData);

    @Select("select * from t_scoredata")
    List<ScoreData> getScoreDatas();

    @Delete("delete from t_scoredata where id =#{id}")
    void deletedeleteScoreDate(Integer id);

    @Select("select * from t_scoredata ORDER BY id DESC LIMIT 0,1")
    ScoreData getDateByMaxTime();
}
