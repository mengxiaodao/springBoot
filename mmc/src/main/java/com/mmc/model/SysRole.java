package com.mmc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * SysRole
 * @author  mc
 * @version  2018-06-12 16:22:41
 * table: sys_role
 */
public class SysRole  implements Serializable{
	
	//columns START
		//主键ID
		private Long id;
		
		//角色名称
		private String name;
		
		//角色唯一标识
		private String roleKey;
		
		//角色描述
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
		
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return this.name;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	
	
	public String getRoleKey() {
		return this.roleKey;
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
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("name=").append(getName()).append(",")
			.append("roleKey=").append(getRoleKey()).append(",")
			.append("description=").append(getDescription()).append(",")
			.append("status=").append(getStatus()).append(",")
			.append("isDeleted=").append(getIsDeleted()).append(",")
			.append("createBy=").append(getCreateBy()).append(",")
			.append("updateBy=").append(getUpdateBy()).append(",")
			.append("createTime=").append(getCreateTime()).append(",")
			.append("updateTime=").append(getUpdateTime()).append(",")
			.toString();
	}
	
	
}

	
