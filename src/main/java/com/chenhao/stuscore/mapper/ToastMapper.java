package com.chenhao.stuscore.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author ch
 * @date 2020/10/10
 **/
@Mapper
public interface ToastMapper {
    @Update("update t_toast set msg = #{toast}")
    void editToast(String toast);
}
