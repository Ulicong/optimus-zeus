package com.optimus.module.user.web.rpc;

import com.optimus.module.user.dal.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by li.huan
 * Create Date 2017-06-20 16:17
 */
@Controller
public class UserInfoRpc {

    @Autowired
    private UserInfoAO userInfoAO;


    /**
     * 查询单个用户信息
     *
     * @param user_id 用户ID
     * @return
     */
    public UserInfo query_user_info_by_id(Integer user_id) {
        return userInfoAO.queryById(user_id);
    }

}
