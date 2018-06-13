package com.mmc.model.dto;

import com.mmc.common.BaseDto;

import java.util.Date;


/**
 * SysPermission
 * @author  mc
 * @version  2018-06-12 16:22:59
 * table: sys_permission
 */
public class SysPermissionDto  extends BaseDto {
	
	//columns START
		//主键ID
		private Long id;
		
		//父菜单ID
		private Long pid;
		
		//菜单名称
		private String name;
		
		//菜单URL
		private String url;
		
		//菜单唯一标识
		private String menuKey;
		
		//菜单权限
		private String permission;
		
		//菜单类型，1：模块，2：菜单URL，3：功能
		private Integer type;
		
		//0：不可见，1：可见
		private Integer isVisible;
		
		//是否是子菜单，0：否，1：是
		private Integer isLeaf;
		
		//排序
		private Integer sort;
		
		//图标
		private String icon;
		
		//权限描述
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
		
		//菜单英文名称
		private String enName;
		
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getId() {
		return this.id;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	
	public Long getPid() {
		return this.pid;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return this.name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getUrl() {
		return this.url;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	
	
	public String getMenuKey() {
		return this.menuKey;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	public String getPermission() {
		return this.permission;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public Integer getType() {
		return this.type;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	
	
	public Integer getIsVisible() {
		return this.isVisible;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	public Integer getIsLeaf() {
		return this.isLeaf;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	public Integer getSort() {
		return this.sort;
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
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	
	public String getEnName() {
		return this.enName;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("pid=").append(getPid()).append(",")
			.append("name=").append(getName()).append(",")
			.append("url=").append(getUrl()).append(",")
			.append("menuKey=").append(getMenuKey()).append(",")
			.append("permission=").append(getPermission()).append(",")
			.append("type=").append(getType()).append(",")
			.append("isVisible=").append(getIsVisible()).append(",")
			.append("isLeaf=").append(getIsLeaf()).append(",")
			.append("sort=").append(getSort()).append(",")
			.append("icon=").append(getIcon()).append(",")
			.append("description=").append(getDescription()).append(",")
			.append("status=").append(getStatus()).append(",")
			.append("isDeleted=").append(getIsDeleted()).append(",")
			.append("createBy=").append(getCreateBy()).append(",")
			.append("updateBy=").append(getUpdateBy()).append(",")
			.append("createTime=").append(getCreateTime()).append(",")
			.append("updateTime=").append(getUpdateTime()).append(",")
			.append("enName=").append(getEnName()).append(",")
			.toString();
	}
	
	
}

	
