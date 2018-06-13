package com.mmc.common;


import java.io.Serializable;
import java.util.Date;

public class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //各table主键ID
    private Long id;

    //后台用户ID
    private Long userId;

    private int start;

    private int page = 1;

    private int rows = 10;

    private int limit= 10;

    private Integer isDeleted;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    public interface IsDeleted {
        public final static Integer NO = 0; //未删除
        public final static Integer YES = 1;    //已删除
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStart() {
        start = (this.getPage() - 1) * this.getRows();
        return start;
    }
    public int getPage() {
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return getLimit();
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public Long getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    //后台用户ID
    public Long getUserId() {
        return this.userId;
    }

    //后台用户ID
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getLimit() {
        if (limit < 0) {
            limit = 10;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
