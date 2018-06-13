package com.mmc.dao.backend;

import java.util.List;

import com.mmc.model.dto.SysRoleDto;
import com.mmc.model.vo.SysRoleVo;

/**
 * SysRole
 * @author  mc
 * @version  2018-06-12 16:22:41
 * table: sys_role
 */
public interface SysRoleMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysRoleDto record);

    int insertSelective(SysRoleDto record);

    SysRoleVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDto record);

    int updateByPrimaryKey(SysRoleDto record);
}


