package com.optimus.module.user.ao.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.optimus.module.user.ao.UserInfoAO;
import com.optimus.module.user.dal.entity.UserInfo;
import com.optimus.module.user.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by li.huan
 * Create Date 2017-06-20 11:14
 */
@Service
@org.springframework.stereotype.Service
public class UserInfoAOImpl implements UserInfoAO {

    @Autowired
    private UserInfoManager infoManager;

    public UserInfo queryById(Integer id) {
        return infoManager.queryById(id);
    }


}
