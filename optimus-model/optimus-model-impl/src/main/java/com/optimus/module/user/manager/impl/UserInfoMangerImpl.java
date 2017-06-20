package com.optimus.module.user.manager.impl;

import com.optimus.module.user.dal.entity.UserInfo;
import com.optimus.module.user.dal.mapper.UserInfoMapper;
import com.optimus.module.user.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by li.huan
 * Create Date 2017-06-20 14:10
 */
@Service
public class UserInfoMangerImpl implements UserInfoManager {

    @Autowired
    private UserInfoMapper infoMapper;


    public UserInfo queryById(Integer id) {
        return infoMapper.selectByPrimaryKey(id);
    }
}
