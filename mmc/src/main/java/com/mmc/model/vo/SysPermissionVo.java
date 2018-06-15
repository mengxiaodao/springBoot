package com.mmc.model.vo;


import com.mmc.model.SysPermission;

import java.util.List;


/**
 * SysPermission
 * @author  mc
 * @version  2018-06-12 16:22:59
 * table: sys_permission
 */
public class SysPermissionVo  extends SysPermission{
    /**
     * 统计数量
     */
    private Integer dataCount;
    /**
     * 子菜单集合
     */
    private List<SysPermissionVo> children;

    public List<SysPermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionVo> children) {
        this.children = children;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }
}

	
