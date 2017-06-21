package com.optimus.module.user.ao.deal.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
	private Integer id;

	// 用户id
	private Long userId;

	// 所在身份
	private String province;

	// 所在城市
	private String city;

	// 详细地址
	private String address;

	// 邮编
	private Integer postalCode;

	// qq号
	private Long qq;

	// 来源ID
	private String wechatId;

	private Date gmtModified;

	private Date gmtCreated;

	// 用户名
	private String username;

	// 手机号
	private Long mobileNumber;

	// 支付密码
	private String payPassword;

	// 0, "注册来源为草根站点内注册";1, "注册来源为微信";2, "注册来源为QQ";3, "注册来源为后台管理员注册";4, "注册来源为微博";5, "注册来源为m_web";6, "注册来源为ios";7, "注册来源为android"
	private Integer registerMethod;

	// 邮箱
	private String email;

	// 真实姓名
	private String name;

	// 真实姓名 hidden
	private String nameHidden;

	// 身份证号码
	private String identityNumber;

	// 身份证号码 hidden
	private String identityNumberHidden;

	// 身份证校验标志:0未校验,1已校验,2校验出错
	private Integer identityVerify;

	// 推荐人用户id
	private Long recommendedUserId;

	// 个人头像url
	private String profileImage;

	// 预留扩展
	private Integer userExtend;

	// riskAppetite 风险偏好等级 １，２，３依次是保守型，稳健型，积极型
	private Integer extendId;

	// 唯一标识用户的短链接url
	private String shortUrl;

	private String sex;

	private Date birthday;

	// 昵称
	private String nickname;

	// 个性签名
	private String signature;

	// 存管账户编号
	private String custNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 返回用户id
	 * 
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 返回所在身份
	 * 
	 * @return 所在身份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置所在身份
	 */
	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	/**
	 * 返回所在城市
	 * 
	 * @return 所在城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置所在城市
	 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/**
	 * 返回详细地址
	 * 
	 * @return 详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置详细地址
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * 返回邮编
	 * 
	 * @return 邮编
	 */
	public Integer getPostalCode() {
		return postalCode;
	}

	/**
	 * 设置邮编
	 */
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * 返回qq号
	 * 
	 * @return qq号
	 */
	public Long getQq() {
		return qq;
	}

	/**
	 * 设置qq号
	 */
	public void setQq(Long qq) {
		this.qq = qq;
	}

	/**
	 * 返回来源ID
	 * 
	 * @return 来源ID
	 */
	public String getWechatId() {
		return wechatId;
	}

	/**
	 * 设置来源ID
	 */
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId == null ? null : wechatId.trim();
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	/**
	 * 返回用户名
	 * 
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
	 * 
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
	 * 返回支付密码
	 * 
	 * @return 支付密码
	 */
	public String getPayPassword() {
		return payPassword;
	}

	/**
	 * 设置支付密码
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword == null ? null : payPassword.trim();
	}

	/**
	 * 返回0, "注册来源为草根站点内注册";1, "注册来源为微信";2, "注册来源为QQ";3, "注册来源为后台管理员注册";4, "注册来源为微博";5, "注册来源为m_web";6, "注册来源为ios";7, "注册来源为android"
	 * 
	 * @return 0, "注册来源为草根站点内注册";1, "注册来源为微信";2, "注册来源为QQ";3, "注册来源为后台管理员注册";4, "注册来源为微博";5, "注册来源为m_web";6, "注册来源为ios";7, "注册来源为android"
	 */
	public Integer getRegisterMethod() {
		return registerMethod;
	}

	/**
	 * 设置0, "注册来源为草根站点内注册";1, "注册来源为微信";2, "注册来源为QQ";3, "注册来源为后台管理员注册";4, "注册来源为微博";5, "注册来源为m_web";6, "注册来源为ios";7, "注册来源为android"
	 */
	public void setRegisterMethod(Integer registerMethod) {
		this.registerMethod = registerMethod;
	}

	/**
	 * 返回邮箱
	 * 
	 * @return 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 返回真实姓名
	 * 
	 * @return 真实姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置真实姓名
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 返回身份证号码
	 * 
	 * @return 身份证号码
	 */
	public String getIdentityNumber() {
		return identityNumber;
	}

	/**
	 * 设置身份证号码
	 */
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber == null ? null : identityNumber.trim();
	}

	/**
	 * 返回身份证校验标志:0未校验,1已校验,2校验出错
	 * 
	 * @return 身份证校验标志:0未校验,1已校验,2校验出错
	 */
	public Integer getIdentityVerify() {
		return identityVerify;
	}

	/**
	 * 设置身份证校验标志:0未校验,1已校验,2校验出错
	 */
	public void setIdentityVerify(Integer identityVerify) {
		this.identityVerify = identityVerify;
	}

	/**
	 * 返回推荐人用户id
	 * 
	 * @return 推荐人用户id
	 */
	public Long getRecommendedUserId() {
		return recommendedUserId;
	}

	/**
	 * 设置推荐人用户id
	 */
	public void setRecommendedUserId(Long recommendedUserId) {
		this.recommendedUserId = recommendedUserId;
	}

	/**
	 * 返回个人头像url
	 * 
	 * @return 个人头像url
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * 设置个人头像url
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage == null ? null : profileImage.trim();
	}

	/**
	 * 返回预留扩展
	 * 
	 * @return 预留扩展
	 */
	public Integer getUserExtend() {
		return userExtend;
	}

	/**
	 * 设置预留扩展
	 */
	public void setUserExtend(Integer userExtend) {
		this.userExtend = userExtend;
	}

	/**
	 * 返回riskAppetite 风险偏好等级 １，２，３依次是保守型，稳健型，积极型
	 * 
	 * @return riskAppetite 风险偏好等级 １，２，３依次是保守型，稳健型，积极型
	 */
	public Integer getExtendId() {
		return extendId;
	}

	/**
	 * 设置riskAppetite 风险偏好等级 １，２，３依次是保守型，稳健型，积极型
	 */
	public void setExtendId(Integer extendId) {
		this.extendId = extendId;
	}

	/**
	 * 返回唯一标识用户的短链接url
	 * 
	 * @return 唯一标识用户的短链接url
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * 设置唯一标识用户的短链接url
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl == null ? null : shortUrl.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 返回昵称
	 * 
	 * @return 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * 返回个性签名
	 * 
	 * @return 个性签名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 设置个性签名
	 */
	public void setSignature(String signature) {
		this.signature = signature == null ? null : signature.trim();
	}

	/**
	 * 返回存管账户编号
	 * 
	 * @return 存管账户编号
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置存管账户编号
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo == null ? null : custNo.trim();
	}

	public String getNameHidden() {
		return nameHidden;
	}

	public void setNameHidden(String nameHidden) {
		this.nameHidden = nameHidden;
	}

	public String getIdentityNumberHidden() {
		return identityNumberHidden;
	}

	public void setIdentityNumberHidden(String identityNumberHidden) {
		this.identityNumberHidden = identityNumberHidden;
	}

}