package com.optimus.module.user.ao.deal.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    // user table
    private Long id;

    // 用户名
    private String username;

    // 手机号
    private Long mobileNumber;

    // 密码
    private String password;

    // 微信用户ID
    private String wechatId;

    // 0-冻结,1-未激活,2-已激活,3 用户信息初始化完毕
    private Integer status;

    // 用户密码混淆参数
    private String salt;

    // 修改时间
    private Date gmtModified;

    // 新增时间
    private Date gmtCreated;

    /**
     * 返回user table
     * @return user table
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置user table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 返回用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 返回手机号
     * @return 手机号
     */
    public Long getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 设置手机号
     */
    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 返回密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 返回微信用户ID
     * @return 微信用户ID
     */
    public String getWechatId() {
        return wechatId;
    }

    /**
     * 设置微信用户ID
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    /**
     * 返回0-冻结,1-未激活,2-已激活,3 用户信息初始化完毕
     * @return 0-冻结,1-未激活,2-已激活,3 用户信息初始化完毕
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-冻结,1-未激活,2-已激活,3 用户信息初始化完毕
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 返回用户密码混淆参数
     * @return 用户密码混淆参数
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置用户密码混淆参数
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 返回修改时间
     * @return 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 返回新增时间
     * @return 新增时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置新增时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}