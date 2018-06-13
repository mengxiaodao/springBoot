package com.mmc.model.dto;

import com.mmc.common.BaseDto;

import java.util.Date;


/**
 * SysUser
 * @author  chengang
 * @version  2018-06-12 16:21:57
 * table: sys_user
 */
public class SysUserDto  extends BaseDto {
	
	//columns START
		//主键ID
		private Long id;
		
		//登录名
		private String loginName;
		
		//登录密码
		private String password;
		
		//中文姓名
		private String nameZh;
		
		//手机号
		private String mobile;
		
		//邮箱
		private String email;
		
		//性别，0：男，1：女
		private Integer gender;
		
		//生日
		private Date birthday;
		
		//头像地址
		private String icon;
		
		//用户简介
		private String description;
		
		//启用状态，0：禁用，1：启用
		private Integer status;
		
		//是否删除：0-未删除，1-已删除
		private Integer isDeleted;
		
		//创建人ID
		private Long createBy;
		
		//修改人ID
		private Long updateBy;
		
		//创建时间
		private Date createTime;
		
		//修改时间
		private Date updateTime;
		
		//shiro加密盐
		private String salt;
		
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getId() {
		return this.id;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
	public String getLoginName() {
		return this.loginName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	
	
	public String getNameZh() {
		return this.nameZh;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getMobile() {
		return this.mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	
	public Integer getGender() {
		return this.gender;
	}
		/*
	public String getbirthdayString() {
		return DateUtils.convertDate2String(FORMAT_BIRTHDAY, getbirthday());
	}
	public void setbirthdayString(String value) throws ParseException{
		setbirthday(DateUtils.convertString2Date(FORMAT_BIRTHDAY,value));
	}*/
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	public Date getBirthday() {
		return this.birthday;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public String getIcon() {
		return this.icon;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public Integer getStatus() {
		return this.status;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	
	public Long getCreateBy() {
		return this.createBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	
	public Long getUpdateBy() {
		return this.updateBy;
	}
		/*
	public String getcreate_timeString() {
		return DateUtils.convertDate2String(FORMAT_CREATE_TIME, getcreate_time());
	}
	public void setcreate_timeString(String value) throws ParseException{
		setcreate_time(DateUtils.convertString2Date(FORMAT_CREATE_TIME,value));
	}*/
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public Date getCreateTime() {
		return this.createTime;
	}
		/*
	public String getupdate_timeString() {
		return DateUtils.convertDate2String(FORMAT_UPDATE_TIME, getupdate_time());
	}
	public void setupdate_timeString(String value) throws ParseException{
		setupdate_time(DateUtils.convertString2Date(FORMAT_UPDATE_TIME,value));
	}*/
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	public String getSalt() {
		return this.salt;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("loginName=").append(getLoginName()).append(",")
			.append("password=").append(getPassword()).append(",")
			.append("nameZh=").append(getNameZh()).append(",")
			.append("mobile=").append(getMobile()).append(",")
			.append("email=").append(getEmail()).append(",")
			.append("gender=").append(getGender()).append(",")
			.append("birthday=").append(getBirthday()).append(",")
			.append("icon=").append(getIcon()).append(",")
			.append("description=").append(getDescription()).append(",")
			.append("status=").append(getStatus()).append(",")
			.append("isDeleted=").append(getIsDeleted()).append(",")
			.append("createBy=").append(getCreateBy()).append(",")
			.append("updateBy=").append(getUpdateBy()).append(",")
			.append("createTime=").append(getCreateTime()).append(",")
			.append("updateTime=").append(getUpdateTime()).append(",")
			.append("salt=").append(getSalt()).append(",")
			.toString();
	}
	
	
}

	
