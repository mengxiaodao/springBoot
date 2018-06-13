package com.mmc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * SysRolePermission
 * @author  mc
 * @version  2018-06-12 16:22:27
 * table: sys_role_permission
 */
public class SysRolePermission  implements Serializable{
	
	//columns START
		//主键ID
		private Long id;
		
		//角色ID
		private Long roleId;
		
		//权限ID
		private Long permissionId;
		
		//是否删除：0-未删除，1-已删除
		private Integer isDeleted;
		
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
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	public Long getRoleId() {
		return this.roleId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
	public Long getPermissionId() {
		return this.permissionId;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public Integer getIsDeleted() {
		return this.isDeleted;
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
			.append("roleId=").append(getRoleId()).append(",")
			.append("permissionId=").append(getPermissionId()).append(",")
			.append("isDeleted=").append(getIsDeleted()).append(",")
			.append("createTime=").append(getCreateTime()).append(",")
			.append("updateTime=").append(getUpdateTime()).append(",")
			.toString();
	}
	
	
}

	
