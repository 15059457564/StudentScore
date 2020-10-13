package com.chenhao.stuscore.service.impl;

import com.chenhao.stuscore.mapper.ToastMapper;
import com.chenhao.stuscore.service.ToastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ch
 * @date 2020/10/10
 **/
@Service
public class ToastServiceImpl implements ToastService {

    @Autowired
    ToastMapper toastMapper;

    @Override
    public void editToast(String toast) {
        toastMapper.editToast(toast);

    }
}
