package com.chenhao.stuscore.domain;

import java.util.Date;

/**
 * @author ch
 * @date 2020/10/9
 **/
public class ScoreData {
    private Integer id;
    private Date start;//开始日期
    private Date end;  //结束日期

    @Override
    public String toString() {
        return "ScoreData{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
