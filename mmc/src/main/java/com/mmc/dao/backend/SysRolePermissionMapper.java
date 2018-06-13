package com.mmc.dao.backend;

import java.util.List;

import com.mmc.model.dto.SysRolePermissionDto;
import com.mmc.model.vo.SysRolePermissionVo;

/**
 * SysRolePermission
 * @author  chengang
 * @version  2018-06-12 16:22:27
 * table: sys_role_permission
 */
public interface SysRolePermissionMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysRolePermissionDto record);

    int insertSelective(SysRolePermissionDto record);

    SysRolePermissionVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermissionDto record);

    int updateByPrimaryKey(SysRolePermissionDto record);
}


