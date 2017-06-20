package com.optimus.module.user.manager;

import com.optimus.module.user.dal.entity.UserInfo;

/**
 * Created by li.huan
 * Create Date 2017-06-20 14:09
 */
public interface UserInfoManager {

    /**
     * 查询单个用户详细数据
     *
     * @param id
     * @return
     */
    UserInfo queryById(Integer id);


}
