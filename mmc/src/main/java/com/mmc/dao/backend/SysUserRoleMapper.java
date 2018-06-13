package com.mmc.dao.backend;

import java.util.List;

import com.mmc.model.dto.SysUserRoleDto;
import com.mmc.model.vo.SysUserRoleVo;

/**
 * SysUserRole
 * @author  chengang
 * @version  2018-06-12 16:21:20
 * table: sys_user_role
 */
public interface SysUserRoleMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysUserRoleDto record);

    int insertSelective(SysUserRoleDto record);

    SysUserRoleVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRoleDto record);

    int updateByPrimaryKey(SysUserRoleDto record);
}


